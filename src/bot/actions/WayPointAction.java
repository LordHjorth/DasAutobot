package bot.actions;

import bot.Controls;

public class WayPointAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	double x;
	double y;
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

	
	@Override
	public void Perform() {
		Controls.PILOT.setAngularSpeed(Controls.PILOT.getMaxAngularSpeed() * turnSpeed);
		Controls.PILOT.setLinearSpeed(Controls.PILOT.getMaxLinearSpeed() * speed);
		
		Controls.NAVIGATION.goTo((float) x, (float) y);
		Controls.NAVIGATION.waitForStop();
	}

}
