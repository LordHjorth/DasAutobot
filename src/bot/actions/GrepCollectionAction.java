
package bot.actions;

import bot.Controls;

public class GrepCollectionAction implements Action{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {
		
		
		Controls.COLLECTOR.setSpeed((int)(Controls.COLLECTOR.getMaxSpeed()*0.50));

		Controls.COLLECTOR.rotate(-160);
		Controls.COLLECTOR.waitComplete();
	}
	

}

