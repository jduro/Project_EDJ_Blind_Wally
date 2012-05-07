package edj.blindwally.data;

public class Settings
{
	private boolean debugOn = true;
	private boolean isContinuing = true;
	
	public Settings()
	{
		super();
	}

		
	/**
	 * @return the isContinuing
	 */
	public synchronized boolean isContinuing() {
		return isContinuing;
	}

	/**
	 * @param isContinuing the isContinuing to set
	 */
	public synchronized void setContinuing(boolean isContinuing) {
		this.isContinuing = isContinuing;
	}




	/**
	 * @return the debugOn
	 */
	public synchronized boolean isDebugOn() {
		return debugOn;
	}

	/**
	 * @param debugOn the debugOn to set
	 */
	public synchronized void setDebugOn(boolean debugOn) {
		this.debugOn = debugOn;
	}
	
	
}
