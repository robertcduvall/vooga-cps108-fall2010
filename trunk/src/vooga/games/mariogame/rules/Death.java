package vooga.games.mariogame.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.games.mariogame.sprites.MarioSprite;

/**
 * Death rule is enforced when Mario is off the screen or out of lives.
 * 
 * @author Cameron McCallie
 *
 */

public class Death implements Rule{

    @Override
    public void enforce(SpriteGroup... groups) {
    	//TODO: Make this pull up the game over BG
    	MarioSprite mario = (MarioSprite) groups[0].getSprites()[0];
    	mario.respawn();
        System.out.println("Game over");
    }

    @Override
    public boolean isSatisfied(SpriteGroup ...groups) {
        SpriteGroup group = groups[0];
        for (Sprite mario: group.getSprites()) {
        	if(mario != null && mario.isOnScreen())
        		return false;
        }
        return true;
    }


}
