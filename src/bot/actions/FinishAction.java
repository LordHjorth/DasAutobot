package bot.actions;

import bot.Controls;
import lejos.hardware.Sound;

public class FinishAction implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {
	
		Sound.beepSequence();	
		Sound.beepSequenceUp();
	}

}