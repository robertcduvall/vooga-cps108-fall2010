package vooga.games.mariogame.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.level.Rule;

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
        System.out.println("Game over");       
    }

    @Override
    public boolean isSatisfied(SpriteGroup ...groups) {
        SpriteGroup group = groups[0];
        for (Sprite mario: group.getSprites()) {
            if (mario!=null && mario.isActive()) //&& !(((BetterSprite)mario).getStat("lives").getStat().equals(new Integer(0))))
                return false;
        }
        return true;
    }


}
