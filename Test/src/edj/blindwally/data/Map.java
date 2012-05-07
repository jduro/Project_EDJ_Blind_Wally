package edj.blindwally.data;

public class Map
{
	Position playerPos = new Position(0,0);
	Position dogPos = new Position(5,5);
	/**
	 */
	public Map() {
		super();
	}
	/**
	 * @return the playerPos
	 */
	public synchronized Position getPlayerPos() {
		return playerPos;
	}
	/**
	 * @return the dogPos
	 */
	public synchronized Position getDogPos() {
		return dogPos;
	}
	/**
	 * @param playerPos the playerPos to set
	 */
	public synchronized void setPlayerPos(Position playerPos) {
		this.playerPos = playerPos;
	}
	/**
	 * @param dogPos the dogPos to set
	 */
	public synchronized void setDogPos(Position dogPos) {
		this.dogPos = dogPos;
	}
	
	

}
