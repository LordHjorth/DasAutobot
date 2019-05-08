package bot;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;

public class Controls {
	
	public static EV3MediumRegulatedMotor COLLECTOR;
	public static EV3LargeRegulatedMotor LEFT_WHEEL;
	public static EV3LargeRegulatedMotor RIGHT_WHEEL;
	public static MovePilot PILOT;
	public static Navigator NAVIGATION;
}
