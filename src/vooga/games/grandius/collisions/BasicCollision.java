package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;
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

	protected Game myGame;
	
	public BasicCollision(Game grandius) {
		super();
		this.myGame = grandius;
	}
	
	@Override
	public void collided(Sprite playerSideSprite, Sprite commonEnemy) {
		//myGame.playSound(Resources.getSound("explosionSound")); Sort of loud...
		BufferedImage[] images = Resources.getAnimation("explosionAnimation");
		AnimatedSprite explosion = new VolatileSprite(images, commonEnemy.getX(), commonEnemy.getY());
		((Blah)myGame).getPlayState().getGroup("explosionGroup").add(explosion);
		if(!(playerSideSprite instanceof Player)){
			getPlayer().updateScore(((Enemy)commonEnemy).getScore());
			getPlayer().updateCash(((Enemy)commonEnemy).getCash());
		}
	}
	
	public Player getPlayer() {
		return ((Blah)myGame).getPlayState().getPlayer();
	}
	
}
