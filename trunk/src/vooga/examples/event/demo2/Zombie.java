package vooga.examples.event.demo2;
/**
 * A Zombie character in the demo example. Zombie has the ability to get its position by calling
 * <code>getX</code> and change its position by calling <code>setX(int x)</code> to set its x position.
 * 
 * @author Meng Li
 *
 */
public class Zombie {
	private int x;
	/**
	 * Constructor of Zombie class
	 * @param x Zombie's initial x position
	 */
	Zombie(int x) {
		this.x = x;
	}
    /**
     * Get the x position of the Zombie's
     * @return int Returns an integer which indicates the current position of a Zombie
     */
	public int getX() {
		return x;
	}
	/**
	 * Set the current x position of the Zombie
	 * @param x Zombie's x position
	 */
	public void setX(int x) {
		this.x = x;
	}
}
