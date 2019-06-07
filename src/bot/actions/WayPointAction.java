package bot.actions;

import bot.Controls;

public class WayPointAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	float x;
	float y;
	float speed;

	public WayPointAction(float x, float y, float speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	@Override
	public void Perform() {
		Controls.PILOT.setAngularSpeed(Controls.PILOT.getMaxAngularSpeed() * speed);
		Controls.NAVIGATION.goTo(x, y);
		Controls.NAVIGATION.waitForStop();
	}

}
