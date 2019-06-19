package bot.actions;

import bot.Controls;
import lejos.robotics.geometry.Point;

public class WayPointAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	double x;
	double y;
	Double heading;
	float speed;
	private float turnSpeed;

	public WayPointAction(double x, double y, float speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.turnSpeed = speed;
	}

	public WayPointAction(double x, double y, float speed, float turnSpeed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.turnSpeed = turnSpeed;
	}

	public WayPointAction(double x, double y, float speed, float turnSpeed, double heading) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.turnSpeed = turnSpeed;
		this.heading = heading;
	}

	@Override
	public void Perform() {
		Controls.PILOT.setAngularSpeed(Controls.PILOT.getMaxAngularSpeed() * turnSpeed);
		Controls.PILOT.setLinearSpeed(Controls.PILOT.getMaxLinearSpeed() * speed);

		/*
		 * System.out.println("Location " +
		 * Controls.NAVIGATION.getPoseProvider().getPose().getLocation());
		 * System.out.println("Heading " +
		 * Controls.NAVIGATION.getPoseProvider().getPose().getHeading());
		 * 
		 * System.out.println((float) x + " " + (float) y); Point p = new Point((float)
		 * x, (float) y);
		 * 
		 * System.out.println("Angle " +
		 * Controls.NAVIGATION.getPoseProvider().getPose().angleTo(p));
		 * 
		 */

		if (heading != null) {

			// System.out.println("Heading " + heading);
			Controls.NAVIGATION.goTo((float) x, (float) y, heading.floatValue());
		} else {
			Controls.NAVIGATION.goTo((float) x, (float) y);
		}
		Controls.NAVIGATION.waitForStop();

		/*
		 * System.out.println(Controls.NAVIGATION.getPoseProvider().getPose().getHeading
		 * ());
		 */

	}

	@Override
	public void stop() {
		Controls.NAVIGATION.stop();
		
	}

}
