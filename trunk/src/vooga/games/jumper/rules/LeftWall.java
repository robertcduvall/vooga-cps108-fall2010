package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Allow DoodleSprite to move through the left wall
 * @author Cody, Devon, Brian
 */
public class LeftWall extends BlockThroughWall {
	
	private final int THIS_WALL_POSITION = 0;
	private final int OTHER_WALL_POSITION = Resources.getInt("gameWidth");
	
}
