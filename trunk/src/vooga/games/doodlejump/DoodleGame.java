package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.overlay.*;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.object.background.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The DoodleGame class creates a game based on the popular iPhone app Doodle
 * Jump
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleGame extends Game {

	// Background
	private Background background;

	// Playfield
	protected PlayField playField;
	
	private OverlayString score;

	// items
	private Sprite jetpack;

	// Doodle (main player)
	private DoodleSprite doodle;
	private KeyboardControl doodle_keyboard_control;

	protected SpriteGroup DoodleGroup, BallGroup;

	// Collision Manager
	protected CollisionManager doodleToGreenPlatform, doodleToMonster,
			ballToMonster, doodleToBrownPlatform, doodleToWhitePlatform,
			doodleToSpring, doodleToTrampoline, doodleToJetpack;

	@Override
	public void initResources() {
		Scanner levelScanner = null;
		try {
			levelScanner = new Scanner(new File("src/vooga/games/doodlejump/levels/level1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DoodleLevel level1 = new DoodleLevel(levelScanner);
		
		// background
		background = level1.getBackground();

		// playfield
		playField = new PlayField(background);

		// spritegroups
		playField.addGroup(level1.getPlatformGroup());
		playField.addGroup(level1.getMonsterGroup());
		BallGroup = playField.addGroup(new SpriteGroup("Ball Group"));
		playField.addGroup(level1.getBrownPlatformGroup());
		playField.addGroup(level1.getWhitePlatformGroup());
		playField.addGroup(level1.getSpringGroup());
		playField.addGroup(level1.getTrampolineGroup());
		playField.addGroup(level1.getJetpackGroup());
		DoodleGroup = playField.addGroup(new SpriteGroup("Doodle Group"));

		// items (eventually ItemSprites)
		jetpack = new Sprite(getImage("images/jetpack.png"));

		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(
				getImage("images/doodle_right.png"), 325, 550), this);
		doodle.setVerticalSpeed(0.5);
		DoodleGroup.add(doodle);
		doodle_keyboard_control = new KeyboardControl(doodle, this);
		doodle_keyboard_control.addInput(KeyEvent.VK_LEFT, "moveLeft",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_RIGHT, "moveRight",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_SPACE, "shoot",
				"vooga.games.doodlejump.DoodleSprite", null);

		score = new OverlayString("0");
		score.setX(500);
		score.setY(50);
		
		// Collision
		doodleToGreenPlatform = new DoodleToGreenPlatformCollision();
		doodleToMonster = new DoodleToMonsterCollision();
		ballToMonster = new BallToMonsterCollision();
		doodleToBrownPlatform = new DoodleToBrownPlatformCollision();
		doodleToWhitePlatform = new DoodleToWhitePlatformCollision();
		doodleToSpring = new DoodleToSpringCollision();
		doodleToTrampoline = new DoodleToTrampolineCollision();

		playField.addCollisionGroup(DoodleGroup, level1.getPlatformGroup(),
				doodleToGreenPlatform);
		playField.addCollisionGroup(DoodleGroup, level1.getMonsterGroup(), doodleToMonster);
		playField.addCollisionGroup(BallGroup, level1.getMonsterGroup(), ballToMonster);
		playField.addCollisionGroup(DoodleGroup, level1.getBrownPlatformGroup(),
				doodleToBrownPlatform);
		playField.addCollisionGroup(DoodleGroup, level1.getWhitePlatformGroup(),
				doodleToWhitePlatform);
		playField.addCollisionGroup(DoodleGroup, level1.getSpringGroup(), doodleToSpring);
		playField.addCollisionGroup(DoodleGroup, level1.getTrampolineGroup(),
				doodleToTrampoline);
		setFPS(100);
	}

	@Override
	public void update(long elapsedTime) {
		doodle_keyboard_control.update();
		for(SpriteGroup group : playField.getGroups()){
			for(Sprite sprite : group.getSprites()){
				if(doodle.getY() < 400 && sprite != null){
					if(group.getName().equals("Doodle Group"))
						doodle.setScore(doodle.getScore() + 5);
					sprite.moveY(400 - doodle.getY());
				}
			}
		}
		System.out.println();
		playField.update(elapsedTime);
		score.setString(Integer.toString(doodle.getScore()));
	}

	@Override
	public void render(Graphics2D g) {
		playField.render(g);
		score.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DoodleGame(), new Dimension(532, 850), false);
		game.start();
	}

}
