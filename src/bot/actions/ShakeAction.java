package bot.actions;

import bot.Controls;

public class ShakeAction implements Action {

	private static final long serialVersionUID = -6030987918477451109L;
	private int times;

	public ShakeAction() {
		this.times = 1;
	}

	public ShakeAction(int times) {
		// TODO Auto-generated constructor stub

		this.times = times;
	}

	@Override
	public void Perform() {

		for (int i = 0; i < times; i++) {

			Controls.PILOT.travel(2);
			Controls.PILOT.travel(-2);
		}

	}

}
