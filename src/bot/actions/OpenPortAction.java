package bot.actions;

import bot.Controls;

public class OpenPortAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8970961981440617810L;

	@Override
	public void Perform() {

		Controls.PORT_OPEN.rotateTo(115);

		new ShakeAction(2).Perform();
		new WaitAction(3000).Perform();
		new ClosePortAction().Perform();
	}

}
