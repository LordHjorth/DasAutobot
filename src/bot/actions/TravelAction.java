package bot.actions;

import bot.Controls;

public class TravelAction implements Action{

	private double distance;

	public TravelAction(Double distance) {
		this.distance  = distance;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {

		Controls.PILOT.travel(distance,false);
		
	}

}
