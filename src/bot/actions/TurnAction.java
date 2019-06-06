package bot.actions;

import bot.Controls;

public class TurnAction implements Action{

	
	private long deg;

	public TurnAction(long deg) {
		this.deg = deg;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {

		Controls.NAVIGATION.rotateTo(deg);
		Controls.NAVIGATION.waitForStop();
	}

}
