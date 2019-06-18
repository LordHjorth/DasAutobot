package bot.actions;

import bot.Controls;

public class ShakeAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {
	
		try {

			Controls.PILOT.forward();
			
			Thread.sleep(50);
			
			Controls.PILOT.backward();
			
			Thread.sleep(50);
			
			Controls.PILOT.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
