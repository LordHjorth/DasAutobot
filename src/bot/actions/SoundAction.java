package bot.actions;

import lejos.hardware.Sound;

public class SoundAction implements Action{
	
	private boolean downSequence = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SoundAction(boolean downSequence) {
		this.downSequence = downSequence;
	}

	@Override
	public void Perform() {

		if(downSequence) {
			Sound.beepSequence();
		} else {
			Sound.beepSequenceUp();
		}
		
	}

}
