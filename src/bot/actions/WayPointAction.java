package bot.actions;

import bot.Controls;

public class WayPointAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	float x;
	float y;

	public WayPointAction(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void Perform() {
		Controls.NAVIGATION.goTo(x, y);
		Controls.NAVIGATION.waitForStop();
	}

}
