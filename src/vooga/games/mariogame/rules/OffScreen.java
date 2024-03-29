package vooga.games.mariogame.rules;

import vooga.engine.level.Rule;
import vooga.games.mariogame.sprites.MarioSprite;

import com.golden.gamedev.object.SpriteGroup;

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
    	if(mario != null && mario.getY()<=mario.getBackground().getHeight())
    		return false;
    	return true;
    }


}
