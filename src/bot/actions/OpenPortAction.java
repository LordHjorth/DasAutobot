package bot.actions;

import bot.Controls;

public class OpenPortAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {
	
		Controls.PORT_OPEN.rotateTo(115);
		
	}

}
