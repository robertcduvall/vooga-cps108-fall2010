package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.sprites.enemy.common.Enemy;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;


/**
 * This class takes care of collisions between a player-side
 * sprite (player itself or the fired bullet)and a common enemy 
 * which could be a Boomer or a Zipster. 
 * @author bhawana
 */
public class BasicCollision extends BasicCollisionGroup {

	//TODO the collision classes can definitely be cleaned up - lots of repeated code right now.
	private Game game;
	
	public BasicCollision(Game grandius) {
		super();
		this.game = grandius;
	}
	
	@Override
	public void collided(Sprite playerSideSprite, Sprite commonEnemy) {
		game.playSound(Resources.getSound("explosionSound"));
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, commonEnemy.getX(), commonEnemy.getY());
		((DropThis)game).getPlayState().getGroup("explosionGroup").add(explosion);
		if(!(playerSideSprite instanceof Player)){
			getPlayer().updateScore(((Enemy)commonEnemy).getScore());
			getPlayer().updateCash(((Enemy)commonEnemy).getCash());
		}
	}
	
	public Player getPlayer() {
		return ((DropThis)game).getPlayState().getPlayer();
	}
	
}
