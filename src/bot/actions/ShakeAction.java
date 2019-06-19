package bot.actions;

import bot.Controls;

public class ShakeAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {
	
		Controls.PILOT.travel(2);
		
		Controls.PILOT.travel(-2);

	}
	

}
