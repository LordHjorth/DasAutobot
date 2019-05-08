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
	}

}
