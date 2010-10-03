package vooga.engine.player.control;

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

    /**
     * Constructs an ItemSprite.
     * 
     * @param s sprite that will represent this item
     */
    public ItemSprite(Sprite s) {
        super("", "default", s);
    }

    /**
     * Constructs an ItemSprite.
     * 
     * @param name is a name for this item (if desired)
     * @param stateName is a name to map to the Sprite parameter.
     * @param s is the Sprite that will represent this item.
     */
    public ItemSprite(String name, String stateName, Sprite s) {
        super(name, stateName, s);
    }

    /**
     * This method defines what should happen when an item is "used."
     */
    public abstract void act();
    
}
