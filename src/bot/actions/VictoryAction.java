package bot.actions;


import lejos.hardware.Sound;

public class VictoryAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {

	   Sound.beepSequenceUp();
	
	}
}
