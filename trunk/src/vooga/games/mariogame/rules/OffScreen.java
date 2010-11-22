package vooga.games.mariogame.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.games.mariogame.sprites.MarioSprite;

/**
 * OffScreen rule is enforced when Mario is off the screen.
 * 
 * @author Cameron McCallie
 *
 */

public class OffScreen implements Rule{

    @Override
    public void enforce(SpriteGroup... groups) {
    	//TODO: Make this pull up the game over BG
    	MarioSprite mario = (MarioSprite) groups[0].getSprites()[0];
    	mario.respawn();
    }

    @Override
    public boolean isSatisfied(SpriteGroup ...groups) {
    	MarioSprite mario = (MarioSprite) groups[0].getSprites()[0];
    	if(mario != null && mario.isOnScreen())
    		return false;
    	return true;
    }


}
