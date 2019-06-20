package bot;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bot.actions.Action;
import bot.actions.ActionList;
import bot.messages.Messages;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;

public class Main {

	private ServerSocket server;
	private Socket client;
	private ObjectInputStream inputStream;
	private int port = 4444;
	private DataOutputStream outputStream;
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	private int counter = 0;
	private WheeledChassis myChassis;

	public Main() throws IOException {

		LCD.drawString("Booting that shiit", 0, 4);
//		Delay.msDelay(1000);

//		double wheelDiameter = 3.8;
//		double trackWidth = 12.2;

		double wheelDiameter = 4.2;
	//	double trackWidth = 12.3 / 2;

		// Setup Motors
		
		Controls.COLLECTOR = new EV3MediumRegulatedMotor(MotorPort.A);
		Controls.LEFT_WHEEL = new EV3LargeRegulatedMotor(MotorPort.B);
		Controls.RIGHT_WHEEL = new EV3LargeRegulatedMotor(MotorPort.C);
		Controls.PORT_OPEN = new EV3MediumRegulatedMotor(MotorPort.D);

		Wheel leftWheel = WheeledChassis.modelWheel(Controls.LEFT_WHEEL, wheelDiameter).offset(-13.5D/2D)
				.invert(true);
		Wheel rightWheel = WheeledChassis.modelWheel(Controls.RIGHT_WHEEL, wheelDiameter).offset(13.5D/2D)
				.invert(true);

		 myChassis = new WheeledChassis(new Wheel[] { rightWheel, leftWheel }, WheeledChassis.TYPE_DIFFERENTIAL);
		Controls.PILOT = new MovePilot(myChassis);
		Controls.NAVIGATION = new Navigator(Controls.PILOT);

		// Setup ColorSensor
		Controls.COLORSENSOR = new EV3ColorSensor(SensorPort.S4);
		
		/*
		
		//Controls.PILOT.travel(100);
		
		
		Controls.PILOT.setAngularSpeed(Controls.PILOT.getMaxAngularSpeed() * 0.4);		
		Controls.PILOT.rotate(-180);
		Controls.PILOT.rotate(-180);
		Controls.PILOT.rotate(-180);
		Controls.PILOT.rotate(-180);
		
		
		ActionList list = new ActionList();
		list.add(new StartCollectionAction());
		list.add(new WaitAction(5200));
		list.add(new StopCollectionAction());
		
		ExecuteActions(list);
		
		//System.exit(0);
		*/
		
		server = new ServerSocket(port);

		while (true) {
			LCD.drawString("Waiting for connections", 0, 4);

			client = server.accept();

			LCD.drawString("Client Connected", 0, 4);

			client.setTcpNoDelay(true);
			
			outputStream = new DataOutputStream(client.getOutputStream());
			inputStream = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));

			Runnable inputGrapper = new Runnable() {

				@Override
				public void run() {

					while (true) {
						try {
							ActionList list = (ActionList) inputStream.readObject();
							ExecuteActions(list);
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("something failed!!");
							System.out.println("Trying to restart!!");
							break;
						}

					}
				}
			};

			executor.execute(inputGrapper);

			Runnable runsensor = new Runnable() {

				@Override
				public void run() {

					while (counter < 10) {
						try {

							int currentDetectedColor = Controls.COLORSENSOR.getColorID();

							if (currentDetectedColor == Color.WHITE || currentDetectedColor == Color.BLUE) {
								
								System.out.println("ONE MORE BAAAALL");

								counter++;

								outputStream.writeUTF(Messages.COLLECTED);
								Thread.sleep(1000);

							}

						} catch (Exception e) {

							e.printStackTrace();
							System.out.println("something failed!!");
							System.out.println("Trying to restart!!");
							break;
						}

					}
					Controls.COLORSENSOR.close();
					try {
						outputStream.writeUTF(Messages.FINISHED);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};

			executor.execute(runsensor);

		}
		
	}

	public void ExecuteActions(ActionList list) {

		for (Action action : list) {

			action.Perform();
		}

		Controls.PILOT = new MovePilot(myChassis);
		Controls.NAVIGATION = new Navigator(Controls.PILOT);
		
		try {
			outputStream.writeUTF(Messages.DONE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new Main();
	}

}