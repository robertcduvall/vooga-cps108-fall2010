package vooga.examples.event.demo2;

/**
 * A Human character in the demo example. A human has the ability to get its position by calling
 * <code>getX</code> and change its position by calling <code>setX(int x)</code> to set its x position.
 * 
 * @author Meng Li
 *
 */
public class Human {
	private int x;
	/**
	 * Constructor of Human class
	 * @param x Human's initial x position
	 */
	Human(int x) {
		this.x = x;

	}
	/**
     * Get the x position of the human
     * @return int Returns an integer which indicates the current position of a human
     */
	public int getX() {
		return x;
	}
	/**
	 * Set the current x position of the human
	 * @param x human's x position
	 */
	public void setX(int x) {

		this.x = x;
	}

}
