package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.MouseControl;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.object.background.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class DoodleGame extends Game {

	// Background
	private Background background;
	
	// Playfield
	protected PlayField playField;

	// Platforms
	private Sprite brown_platform;
	private Sprite dark_blue_platform;
	private Sprite gray_platform;
	private Sprite green_platform;
	private Sprite light_blue_platform;
	private Sprite white_platform;
	private AnimatedSprite brown_platform1;

	// monsters
	private PlayerSprite blue_flying_monster;
	private PlayerSprite blue_monster;
	private PlayerSprite green_flying_monster;
	private PlayerSprite green_monster;
	private PlayerSprite purple_monster;
	private PlayerSprite red_monster;

	// Doodle (main player)
	private DoodleSprite doodle;
	private KeyboardControl doodle_keyboard_control;
	
	protected SpriteGroup PlatformGroup, MonsterGroup, DoodleGroup, BallGroup, BrownPlatformGroup;
	
	// Collision Manager
	protected CollisionManager doodleToGreenPlatform, doodleToMonster, ballToMonster, doodleToBrownPlatform;
	
	public void initResources() {
		
		// background
		background = new ImageBackground(getImage("images/background.png"));

		// playfield
		playField = new PlayField(background);
		
		// spritegroups
		PlatformGroup = playField.addGroup(new SpriteGroup("Platform Group"));
		MonsterGroup = playField.addGroup(new SpriteGroup("Monster Group"));
		DoodleGroup = playField.addGroup(new SpriteGroup("Doodle Group"));
		BallGroup = playField.addGroup(new SpriteGroup("Ball Group"));
		BrownPlatformGroup = playField.addGroup(new SpriteGroup("Brown Platform Group"));

		// platforms
		brown_platform = new Sprite(getImage("images/brown_platform.png"), 500, 500);
		PlatformGroup.add(brown_platform);
		dark_blue_platform = new Sprite(getImage("images/dark_blue_platform.png"), 400, 400);
		PlatformGroup.add(dark_blue_platform);
		gray_platform = new Sprite(getImage("images/gray_platform.png"), 200, 400);
		PlatformGroup.add(gray_platform);
		green_platform = new Sprite(getImage("images/green_platform.png"), 325, 700);
		PlatformGroup.add(green_platform);
		light_blue_platform = new Sprite(getImage("images/light_blue_platform.png"), 200, 100);
		PlatformGroup.add(light_blue_platform);
		white_platform = new Sprite(getImage("images/white_platform.png"), 525, 300);
		PlatformGroup.add(white_platform);
		BufferedImage[] breaking_brown_images = new BufferedImage[4];
		breaking_brown_images[0] = getImage("images/brown_platform.png");
		breaking_brown_images[1] = getImage("images/brown_platform_breaking_1.png");
		breaking_brown_images[2] = getImage("images/brown_platform_breaking_2.png");
		breaking_brown_images[3] = getImage("images/brown_platform_breaking_3.png");
		brown_platform1 = new AnimatedSprite(breaking_brown_images, 100, 600);
		BrownPlatformGroup.add(brown_platform1);
				
		// create monsters
		blue_flying_monster = new PlayerSprite("blue_flying_monster", "bfm", new Sprite(getImage("images/blue_flying_monster.png"),50,100));
		MonsterGroup.add(blue_flying_monster);
		blue_monster = new PlayerSprite("blue_monster", "bm", new Sprite(getImage("images/blue_monster.png"),70,500));
		MonsterGroup.add(blue_monster);
		green_flying_monster = new PlayerSprite("green_flying_monster", "gfm", new Sprite(getImage("images/green_flying_monster.png"),320,70));
		MonsterGroup.add(green_flying_monster);
		green_monster = new PlayerSprite("green_monster", "gm", new Sprite(getImage("images/green_monster.png"),50,200));
		MonsterGroup.add(green_monster);
		purple_monster = new PlayerSprite("purple_monster", "pm", new Sprite(getImage("images/purple_monster.png"),50,425));
		MonsterGroup.add(purple_monster);
		red_monster = new PlayerSprite("red_monster", "rm", new Sprite(getImage("images/red_monster.png"),500,550));
		MonsterGroup.add(red_monster);
		
		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(getImage("images/doodle_right.png")), this);
		doodle.setLocation(325,550);
		doodle.setVerticalSpeed(0.5);
		DoodleGroup.add(doodle);
		doodle_keyboard_control = new KeyboardControl(doodle, this);
		doodle_keyboard_control.addInput(KeyEvent.VK_LEFT, "moveLeft", "vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_RIGHT, "moveRight", "vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_SPACE, "shoot", "vooga.games.doodlejump.DoodleSprite", null);
		//doodle_keyboard_control.addInput(KeyEvent.VK_W, "moveUp", "vooga.games.doodlejump.DoodleSprite", null);
		//doodle_keyboard_control.addInput(KeyEvent.VK_S, "moveDown", "vooga.games.doodlejump.DoodleSprite", null);
		
		// Collision	
		doodleToGreenPlatform = new DoodleToGreenPlatformCollision();
		doodleToMonster = new DoodleToMonsterCollision();
		ballToMonster = new BallToMonsterCollision();
		doodleToBrownPlatform = new DoodleToBrownPlatformCollision();
		
		playField.addCollisionGroup(DoodleGroup, PlatformGroup, doodleToGreenPlatform);
		playField.addCollisionGroup(DoodleGroup, MonsterGroup, doodleToMonster);
		playField.addCollisionGroup(BallGroup, MonsterGroup, ballToMonster);
		playField.addCollisionGroup(DoodleGroup, BrownPlatformGroup, doodleToBrownPlatform);
		
		setFPS(100);
	}
	


	public void update(long elapsedTime) {
		doodle_keyboard_control.update();
		
		playField.update(elapsedTime);
	}

	public void render(Graphics2D g) {		
		playField.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DoodleGame(), new Dimension(532, 850), false);
		game.start();
	}

}
