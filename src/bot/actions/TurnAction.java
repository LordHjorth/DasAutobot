package bot.actions;

import bot.Controls;

public class TurnAction implements Action {

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
		Controls.PILOT.setAngularSpeed(Controls.PILOT.getMaxAngularSpeed() * 0.4);
		Controls.PILOT.rotate(deg);
	}

}
