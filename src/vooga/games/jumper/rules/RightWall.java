package vooga.games.jumper.rules;

import com.golden.gamedev.object.SpriteGroup;

import com.golden.gamedev.object.Sprite;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Allow DoodleSprite to move through the right wall
 * 
 * @author Cody
 */
public class RightWall extends BlockThroughWall {

	private final int THIS_WALL_POSITION = Resources.getInt("gameWidth");
	private final int OTHER_WALL_POSITION = 0;
	
}
