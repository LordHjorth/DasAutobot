package bot;
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
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.utility.Delay;

public class Main {

	private ServerSocket server;
	private Socket client;
	private ObjectInputStream inputStream;
	private int port = 4444;
	private DataOutputStream outputStream;

	public Main() throws IOException {

		LCD.drawString("Booting that shiit", 0, 4);
		Delay.msDelay(1000);

		 double wheelDiameter = 3.7;
		 double trackWidth = 13;
 
		
		// Setup Motors

		Controls.COLLECTOR = new EV3MediumRegulatedMotor(MotorPort.A);
		Controls.LEFT_WHEEL = new EV3LargeRegulatedMotor(MotorPort.B);
		Controls.RIGHT_WHEEL = new EV3LargeRegulatedMotor(MotorPort.C);
		
		 Wheel leftWheel = WheeledChassis.modelWheel(Controls.LEFT_WHEEL,wheelDiameter).offset(-12).invert(true);
		 Wheel rightWheel = WheeledChassis.modelWheel(Controls.RIGHT_WHEEL,wheelDiameter).offset(12).invert(true);

		 Chassis myChassis = new WheeledChassis(new Wheel[]{rightWheel,leftWheel} ,WheeledChassis.TYPE_DIFFERENTIAL);
		 Controls.PILOT = new MovePilot(myChassis);
		 Controls.NAVIGATION = new Navigator(Controls.PILOT);
		
		server = new ServerSocket(port);

		//while (true) {
			LCD.drawString("Waiting for connections", 0, 4);

			client = server.accept();
			
			LCD.drawString("Client Connected", 0, 4);

			
			outputStream = new DataOutputStream(client.getOutputStream());
			inputStream = new ObjectInputStream(client.getInputStream());

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
						}

					}
				}
			};
			
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(inputGrapper);

	//	}
	}

	public void ExecuteActions(ActionList list) {
		
		for (Action action : list) {
			
			action.Perform();
		}
		
		Controls.NAVIGATION = new Navigator(Controls.PILOT);
		Controls.NAVIGATION.clearPath();
		
		try {
			outputStream.writeUTF(Messages.DONE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Main();
	}

}
