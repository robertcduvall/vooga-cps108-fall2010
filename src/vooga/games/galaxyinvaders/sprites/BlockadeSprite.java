package vooga.games.galaxyinvaders.sprites;


import vooga.engine.core.BetterSprite;


/**
 * The BlockadeSprite class is the class used to implement the barriers which protect the ship.
 * It is initialized in GalaxyInvaders.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class BlockadeSprite extends BetterSprite {

	private static final int DEFAULT_HP = 10;		
	private int hitPoints;

	
	/**
	 * This is the constructor for a BlockadeSprite. It sends everything to its
	 * superclass
	 * 
	 * @param name see GameEntitySprite
	 * @param spr see GameEntitySprite
	 */
//	public BlockadeSprite(String name, BetterSprite spr) {
//		super(name, spr);
//		hitPoints = DEFAULT_HP;
//	}

	/**
	 * Update method, called by Game every turn. If hitPoints are zero, the blockade disappears
	 */
	public void update(long time) {
		super.update(time);
		if(hitPoints <= 0) {
			setActive(false);
		}
	}

	/**
	 * This method decreases the blockade's health by a certain amount
	 *
	 * @param dmg the amount to decrease by 
	 */
	public void decrementHitPoints(int dmg) {
		hitPoints = hitPoints-dmg;
	}


}

