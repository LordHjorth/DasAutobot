package bot.actions;

public class WaitAction implements Action{

	
	private long time;

	public WaitAction(long time) {
		this.time = time;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void Perform() {
		try {
			Thread.sleep(this.time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
