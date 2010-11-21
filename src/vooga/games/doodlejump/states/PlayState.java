package vooga.games.doodlejump.states;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelParser;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

import vooga.games.asteroids.collisions.BulletToAsteroidCollision;
import vooga.games.asteroids.collisions.ShipToAsteroidCollision;
import vooga.games.doodlejump.BallSprite;
import vooga.games.doodlejump.DoodleSprite;

public class PlayState extends GameState{
	
	private Game game;
	PlayField myField;
	private DoodleSprite doodle;
	
	public PlayState(Game game, PlayField field){
		super(field);
		this.game = game;
		myField = field;
	}

	@Override
	public void initialize() {
		initLevel();
	}
	
	public void addBall(BallSprite ball){
		myField.getGroup("ballGroup").add(ball);
	}
	
	private void initLevel(){
		doodle = (DoodleSprite)(getGroup("doodleGroup").getSprites()[0]);
		initControls(doodle);

	}
	
	@Override
	public void update(long elapsedTime){
		for (SpriteGroup group : myField.getGroups()) {
			for (Sprite sprite : group.getSprites()) {
//				if (group.getName().equals("doodleGroup")){
//					doodle = (DoodleSprite) (group.getSprites()[0]);
//				}
//				if (doodle.getY() < 400 && sprite != null) {
//					if (group.getName().equals("Doodle Group"))
//						score.setStat(score.getStat() + 5);
//					sprite.moveY(400 - doodle.getY());
//				}
//				System.out.println(sprite.getClass());
				if(group.getName().equals("group0") && sprite != null){
					System.out.println(sprite);
				}
			}
		}
		super.update(elapsedTime);
	}
	
	private void initControls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", "vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", "vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_SPACE, "shoot", "vooga.games.doodlejump.DoodleSprite");
		myField.addControl("doodle", playerControl);
	}
}
