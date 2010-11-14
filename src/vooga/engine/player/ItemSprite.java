package vooga.engine.player;

import com.golden.gamedev.object.Sprite;

/**
 * The ItemSprite class is designed to represent an entity in the game that
 * causes an effect when interacted with. The act method represents this effect.
 * The act method generally should be written in the game class and a reference
 * to the main game class should be passed to the ItemSprite extended class. It
 * can be used as-is, if your item does not require an act method, or it can be
 * extended, and the act method can be implemented.
 * 
 * @author Marcus Molchany, Drew Sternesky, Jimmy Mu
 * 
 */
@SuppressWarnings("serial")
public abstract class ItemSprite extends GameEntitySprite {

	private int myNumberOfUses;
	private static final int DEFAULT_NUM_USES = 1;
	
	 /**
     * Constructs an ItemSprite.
     * 
     * @param s sprite that will represent this item
     */
    public ItemSprite(Sprite s) {
        this("", "default", s, DEFAULT_NUM_USES);
    }
	
    /**
     * Constructs an ItemSprite.
     * 
     * @param s sprite that will represent this item
     */
    public ItemSprite(Sprite s, int numUses) {
        this("", "default", s, numUses);
    }

    /**
     * Constructs an ItemSprite.
     * 
     * @param name is a name for this item (if desired)
     * @param stateName is a name to map to the Sprite parameter.
     * @param s is the Sprite that will represent this item.
     */
    public ItemSprite(String name, String stateName, Sprite s, int numUses) {
        super(name, stateName, s);
        myNumberOfUses = numUses;
    }
   
    /**
     * Examines the number of uses the item has left.
     * 
     * @return Whether or not the item has more uses left.
     */
    public boolean hasMoreUses() {
    	return myNumberOfUses > 0;
    }

    /**
     * This method defines what should happen when an item is "used."
     */
    public void act() {
    	myNumberOfUses--;
    }
    
}
