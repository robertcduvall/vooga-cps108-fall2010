package vooga.games.mariogame;


import vooga.engine.core.BetterSprite;


import vooga.engine.core.BetterSprite;

/**
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         This class represents the main enemy 'mario' sprite.
 * 
 */

@SuppressWarnings("serial")
public class Enemy extends BetterSprite {

	private static final double mySpeed = .25;
	
	public Enemy(){
		
	}
	
	
	/**
	 * Constructs an enemy sprite out of the parameters listed below:
	 * 
	 * @param name
	 * @param stateName
	 *            - ex. alive, dead, etc...
	 * @param left
	 *            - represents the image used when the sprite turns/moves left
	 * @param right
	 *            - represents the image used when the sprite turns/moves right
	 */
	/*
//	public Enemy(String name, String stateName, BufferedImage[] left,
//			BufferedImage[] right) {
//		super(name, stateName, left, right);
//		setHorizontalSpeed(mySpeed * Math.signum(Math.random() - .5));
//	}
	*/
	/**
	 * Sends the sprite in the opposite direction with opposite velocity. The
	 * method is named 'bounce' because it occurs after a collision with the
	 * main sprite or wall.
	 * 
	 */

	public void bounce() {
		setHorizontalSpeed(-getHorizontalSpeed());
	}

}
