package edj.blindwally.data;

public class Position
{
	private int x;
	private int y;
	
	public Position(int x, int y)
	{
		super();
		this.x=x;
		this.y=y;
	}

	/**
	 * @return the x
	 */
	public synchronized int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public synchronized int getY() {
		return y;
	}

	/**
	 * @param x the x to set
	 */
	public synchronized void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public synchronized void setY(int y) {
		this.y = y;
	}
	
	
}
