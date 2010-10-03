package vooga.engine.player.control;

/**
 * MovementKeys sets default values or specific values to the LEFT, RIGHT, UP
 * and DOWN keys on the keyboard. These values define how much the PlayerSprite
 * should moved when one of the keys is pressed.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 * 
 */
public class MovementKeys {

	private boolean manualReset;

	// default values for LEFT, RIGHT, UP and DOWN
	private double myDefaultDeltaLeft;
	private double myDefaultDeltaRight;
	private double myDefaultDeltaUp;
	private double myDefaultDeltaDown;

	// specific values for LEFT, RIGHT, UP and DOWN
	private double myDeltaLeft;
	private double myDeltaRight;
	private double myDeltaUp;
	private double myDeltaDown;

	/**
	 * MovementKeys empty constructor sets LEFT, RIGHT, UP and DOWN to default
	 * values.
	 */
	public MovementKeys() {
		setDefaultMovement();
		manualReset = false;
	}

	/**
	 * MovementKeys constructor takes in values for LEFT, RIGHT, UP and DOWN and
	 * passes these values to the resetMovement method.
	 * 
	 * @param left
	 *            - the value for movement in the left direction
	 * @param right
	 *            - the value for movement in the right direction
	 * @param up
	 *            - the value for movement in the up direction
	 * @param down
	 *            - the value for movement in the down direction
	 */
	public MovementKeys(double left, double right, double up, double down) {
		resetMovement(left, right, up, down);
		setDefaultMovement();
	}

	/**
	 * resetMovement assigns values for LEFT, RIGHT, UP and DOWN.
	 * 
	 * @param left
	 *            - the value for movement in the left direction
	 * @param right
	 *            - the value for movement in the right direction
	 * @param up
	 *            - the value for movement in the up direction
	 * @param down
	 *            - the value for movement in the down direction
	 */
	public void resetMovement(double left, double right, double up, double down) {
		myDeltaLeft = left;
		myDeltaRight = right;
		myDeltaUp = up;
		myDeltaDown = down;
		manualReset = true;
	}

	/**
	 * setDefaultMovement() sets default values for LEFT, RIGHT, UP and DOWN
	 */
	private void setDefaultMovement() {
		myDefaultDeltaLeft = myDefaultDeltaDown = 5;
		myDefaultDeltaRight = myDefaultDeltaUp = -5;
	}

	/**
	 * getDeltaLeft() gets the value for LEFT
	 * 
	 * @return value for LEFT
	 */
	public double getDeltaLeft() {
		if (manualReset)
			return myDeltaLeft;
		return myDefaultDeltaLeft;
	}

	/**
	 * getDeltaRight() gets the value for RIGHT
	 * 
	 * @return value for RIGHT
	 */
	public double getDeltaRight() {
		if (manualReset)
			return myDeltaRight;
		return myDefaultDeltaRight;
	}

	/**
	 * getDeltaUp() gets the value for UP
	 * 
	 * @return value for UP
	 */
	public double getDeltaUp() {
		if (manualReset)
			return myDeltaUp;
		return myDefaultDeltaUp;
	}

	/**
	 * getDeltaDown() gets the value for DOWN
	 * 
	 * @return value for DOWN
	 */
	public double getDeltaDown() {
		if (manualReset)
			return myDeltaDown;
		return myDefaultDeltaDown;
	}

}
