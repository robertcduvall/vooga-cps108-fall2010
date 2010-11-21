package vooga.games.grandius.collisions;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.weapons.BlackHole;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * This class takes care of collisions between a black hole
 * fired by the player and a common enemy - a Boomer or a Zipster. 
 * An instance of this class needs to be added to the playfield using 
 * the playfield's addCollisionGroup method. Once this is 
 * done,the playfield checks for a collision between a bullet
 * and an enemy every time the playfield is updated.
 * @author Bhawana, John, Se-Gil
 */
public class BlackHoleEnemyCollision extends BasicCollision{

	private Game grandius;
	
	public BlackHoleEnemyCollision(Game grandius) {
		super(grandius);
		this.grandius = grandius;
	}
	
	@Override
	public void collided(Sprite blackHole, Sprite enemy) {
		super.collided(blackHole, enemy);
		((BlackHole)(blackHole)).swallowEnemy();
		//TODO:make spinning animation for swallowing zipster into blackhole
		if (((BlackHole)(blackHole)).hitsRemaining()<=0) {
			blackHole.setActive(false);
		}
		enemy.setActive(false);
		BufferedImage[] images = Resources.getAnimation("vortexAnimation");
		AnimatedSprite vortex = new VolatileSprite(images, enemy.getX(), enemy.getY());
		PlayField newField = new PlayField();
		newField.add(vortex);
		((DropThis)grandius).getPlayState().getRenderField().add(newField);
		((DropThis)grandius).getPlayState().getUpdateField().add(newField);
//		getPlayer().updateScore(((Enemy)enemy).getScore());
//		getPlayer().updateCash(((Enemy)enemy).getCash());
	}

	
}