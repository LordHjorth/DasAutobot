package bot.actions;

import bot.Controls;

public class StopCollectionAction implements Action{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {	
		Controls.COLLECTOR.stop();
		
		double angle = Controls.COLLECTOR.getTachoCount() % 360;
		Controls.COLLECTOR.resetTachoCount();
		Controls.COLLECTOR.rotateTo((int)(360 - angle));
		Controls.COLLECTOR.resetTachoCount();
	}


}
