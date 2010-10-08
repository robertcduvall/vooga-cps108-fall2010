package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.object.background.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class DoodleGame extends Game {

	// Background
	private Background background;
	
	// Playfield
	protected PlayField playField;

	// Platforms
	private Sprite brown_platform;
	private Sprite dark_blue_platform;
	private GrayPlatform gray_platform;
	private Sprite green_platform;
	private LightBluePlatform light_blue_platform;
	private Sprite white_platform;
	private AnimatedSprite brown_platform1;

	// monsters
	private FlyingMonster blue_flying_monster;
	private MovingMonster blue_monster;
	private PlayerSprite green_flying_monster;
	private PlayerSprite green_monster;
	private JigglingMonster purple_monster;
	private JigglingMonster red_monster;
	
	// Enhanced Platforms
	private AnimatedSprite spring;
	private AnimatedSprite trampoline;
	
	// items
	private Sprite jetpack;
	
	// Doodle (main player)
	private DoodleSprite doodle;
	private KeyboardControl doodle_keyboard_control;
	
	protected SpriteGroup PlatformGroup, MonsterGroup, DoodleGroup, BallGroup, BrownPlatformGroup, WhitePlatformGroup, SpringGroup, TrampolineGroup, JetpackGroup;
	
	// Collision Manager
	protected CollisionManager doodleToGreenPlatform, doodleToMonster, ballToMonster, doodleToBrownPlatform, doodleToWhitePlatform, doodleToSpring, doodleToTrampoline, doodleToJetpack;
	
	@Override
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
		WhitePlatformGroup = playField.addGroup(new SpriteGroup("White Platform Group"));
		SpringGroup = playField.addGroup(new SpriteGroup("Spring Group"));
		TrampolineGroup = playField.addGroup(new SpriteGroup("Trampoline Group"));
		JetpackGroup = playField.addGroup(new SpriteGroup("Jetpack Group"));

		// platforms
		brown_platform = new Sprite(getImage("images/brown_platform.png"), 500, 500);
		PlatformGroup.add(brown_platform);
		dark_blue_platform = new Sprite(getImage("images/dark_blue_platform.png"), 400, 400);
		PlatformGroup.add(dark_blue_platform);
		gray_platform = new GrayPlatform(getImage("images/gray_platform.png"), 200, 400);
		PlatformGroup.add(gray_platform);
		green_platform = new Sprite(getImage("images/green_platform.png"), 325, 700);
		PlatformGroup.add(green_platform);
		light_blue_platform = new LightBluePlatform(getImage("images/light_blue_platform.png"), 200, 100);
		PlatformGroup.add(light_blue_platform);
		
		white_platform = new Sprite(getImage("images/white_platform.png"), 300, 500);
		WhitePlatformGroup.add(white_platform);
		
		BufferedImage[] breaking_brown_images = new BufferedImage[4];
		breaking_brown_images[0] = getImage("images/brown_platform.png");
		breaking_brown_images[1] = getImage("images/brown_platform_breaking_1.png");
		breaking_brown_images[2] = getImage("images/brown_platform_breaking_2.png");
		breaking_brown_images[3] = getImage("images/brown_platform_breaking_3.png");
		brown_platform1 = new AnimatedSprite(breaking_brown_images, 100, 600);
		BrownPlatformGroup.add(brown_platform1);
				
		// create monsters
		BufferedImage[] blue_flying_images = new BufferedImage[5];
		blue_flying_images[0] = getImage("images/blue_flying_monster_1.png");
		blue_flying_images[1] = getImage("images/blue_flying_monster_2.png");
		blue_flying_images[2] = getImage("images/blue_flying_monster_3.png");
		blue_flying_images[3] = getImage("images/blue_flying_monster_4.png");
		blue_flying_images[4] = getImage("images/blue_flying_monster_5.png");
		blue_flying_monster = new FlyingMonster(blue_flying_images, 50, 100);
		MonsterGroup.add(blue_flying_monster);
		blue_monster = new MovingMonster(getImage("images/blue_monster_left.png"), 70, 500);
		MonsterGroup.add(blue_monster);
		green_flying_monster = new PlayerSprite("green_flying_monster", "gfm", new Sprite(getImage("images/green_flying_monster.png"),320,70));
		MonsterGroup.add(green_flying_monster);
		green_monster = new PlayerSprite("green_monster", "gm", new Sprite(getImage("images/green_monster.png"),50,200));
		MonsterGroup.add(green_monster);
		purple_monster = new JigglingMonster(getImage("images/purple_monster.png"), 50, 425);
		MonsterGroup.add(purple_monster);
		red_monster = new JigglingMonster(getImage("images/red_monster.png"), 500, 550);
		MonsterGroup.add(red_monster);
		
		// enhanced platforms
		BufferedImage[] spring_images = new BufferedImage[2];
		spring_images[0] = getImage("images/spring_compressed.png");
		spring_images[1] = getImage("images/spring_full.png");
		spring = new AnimatedSprite(spring_images, 325, 680);
		SpringGroup.add(spring);
		BufferedImage[] trampoline_images = new BufferedImage[2];
		trampoline_images[0] = getImage("images/trampoline.png");
		trampoline_images[1] = getImage("images/trampoline_down.png");
		trampoline = new AnimatedSprite(trampoline_images, 100, 530);
		TrampolineGroup.add(trampoline);
		
		// items (eventually ItemSprites)
		jetpack = new Sprite(getImage("images/jetpack.png"));
		
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
		doodleToWhitePlatform = new DoodleToWhitePlatformCollision();
		doodleToSpring = new DoodleToSpringCollision();
		doodleToTrampoline = new DoodleToTrampolineCollision();
		
		playField.addCollisionGroup(DoodleGroup, PlatformGroup, doodleToGreenPlatform);
		playField.addCollisionGroup(DoodleGroup, MonsterGroup, doodleToMonster);
		playField.addCollisionGroup(BallGroup, MonsterGroup, ballToMonster);
		playField.addCollisionGroup(DoodleGroup, BrownPlatformGroup, doodleToBrownPlatform);
		playField.addCollisionGroup(DoodleGroup, WhitePlatformGroup, doodleToWhitePlatform);
		playField.addCollisionGroup(DoodleGroup, SpringGroup, doodleToSpring);
		playField.addCollisionGroup(DoodleGroup, TrampolineGroup, doodleToTrampoline);
		setFPS(100);
	}
	


	@Override
	public void update(long elapsedTime) {
		doodle_keyboard_control.update();
		
		playField.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {		
		playField.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DoodleGame(), new Dimension(532, 850), false);
		game.start();
	}

}
