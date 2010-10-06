package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.object.background.*;

import java.awt.event.KeyEvent;

public class DoodleGame extends Game {

	// Background
	private Background background;
	
	// Playfield
	private PlayField playfield;

	// Platforms
	private Sprite brown_platform;
	private Sprite dark_blue_platform;
	private Sprite gray_platform;
	private Sprite green_platform;
	private Sprite light_blue_platform;
	private Sprite red_platform;
	private Sprite spring_platform;
	private Sprite trampoline_platform;
	private Sprite white_platform;
	private Sprite yellow_platform;

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
	
	private SpriteGroup PlatformGroup, MonsterGroup, DoodleGroup;
	
	// Collision Manager
	private CollisionManager doodleToGreenPlatform;
	
	private long time;

	public void initResources() {
		
		// background
		background = new ImageBackground(getImage("background.png"));

		// playfield
		playfield = new PlayField(background);
		
		// spritegroups
		PlatformGroup = playfield.addGroup(new SpriteGroup("Platform Group"));
		MonsterGroup = playfield.addGroup(new SpriteGroup("Monster Group"));
		DoodleGroup = playfield.addGroup(new SpriteGroup("Doodle Group"));

		// platforms
		brown_platform = new Sprite(getImage("brown_platform.png"), 500, 500);
		PlatformGroup.add(brown_platform);
		dark_blue_platform = new Sprite(getImage("dark_blue_platform.png"), 400, 400);
		PlatformGroup.add(dark_blue_platform);
		gray_platform = new Sprite(getImage("gray_platform.png"), 200, 400);
		PlatformGroup.add(gray_platform);
		green_platform = new Sprite(getImage("green_platform.png"), 325, 700);
		PlatformGroup.add(green_platform);
		light_blue_platform = new Sprite(getImage("light_blue_platform.png"), 200, 100);
		PlatformGroup.add(light_blue_platform);
		red_platform = new Sprite(getImage("red_platform.png"), 600, 600);
		PlatformGroup.add(red_platform);
		spring_platform = new Sprite(getImage("spring_platform.png"), 600, 100);
		PlatformGroup.add(spring_platform);
		trampoline_platform = new Sprite(getImage("trampoline_platform.png"), 300, 200);
		PlatformGroup.add(trampoline_platform);
		white_platform = new Sprite(getImage("white_platform.png"), 525, 300);
		PlatformGroup.add(white_platform);
		yellow_platform = new Sprite(getImage("yellow_platform.png"), 100, 600);
		PlatformGroup.add(yellow_platform);
				
		// create monsters
		blue_flying_monster = new PlayerSprite("blue_flying_monster", "bfm", new Sprite(getImage("blue_flying_monster.png"),50,100));
		MonsterGroup.add(blue_flying_monster);
		blue_monster = new PlayerSprite("blue_monster", "bm", new Sprite(getImage("blue_monster.png"),70,500));
		MonsterGroup.add(blue_monster);
		green_flying_monster = new PlayerSprite("green_flying_monster", "gfm", new Sprite(getImage("green_flying_monster.png"),320,70));
		MonsterGroup.add(green_flying_monster);
		green_monster = new PlayerSprite("green_monster", "gm", new Sprite(getImage("green_monster.png"),50,200));
		MonsterGroup.add(green_monster);
		purple_monster = new PlayerSprite("purple_monster", "pm", new Sprite(getImage("purple_monster.png"),50,425));
		MonsterGroup.add(purple_monster);
		red_monster = new PlayerSprite("red_monster", "rm", new Sprite(getImage("red_monster.png"),500,550));
		MonsterGroup.add(red_monster);
		
		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(getImage("doodle_crop.png")));
		doodle.setLocation(325, 650);
		doodle.setVerticalSpeed(0.5);
		DoodleGroup.add(doodle);
		doodle_keyboard_control = new KeyboardControl(doodle, this);
		doodle_keyboard_control.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_W, "moveUp", "vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_S, "moveDown", "vooga.games.doodlejump.DoodleSprite", null);
		
		// Collision	
		doodleToGreenPlatform = new DoodleToGreenPlatformCollision();
		
		playfield.addCollisionGroup(DoodleGroup, PlatformGroup, doodleToGreenPlatform);
		
		time = 0;
		setFPS(100);
	}
	


	public void update(long elapsedTime) {
		doodle_keyboard_control.update();
		
		playfield.update(elapsedTime);
		
		if (doodle.getVerticalSpeed() < 0.5) {
			doodle.setVerticalSpeed(doodle.getVerticalSpeed()+0.01);
		}
	}

	public void render(Graphics2D g) {
		// Graphics Engine

		// render background
		background.render(g);

		// render monsters
		blue_flying_monster.render(g);
		blue_monster.render(g);
		green_flying_monster.render(g);
		green_monster.render(g);
		purple_monster.render(g);
		red_monster.render(g);

		// render platforms
		brown_platform.render(g);
		dark_blue_platform.render(g);
		gray_platform.render(g);
		green_platform.render(g);
		light_blue_platform.render(g);
		red_platform.render(g);
		spring_platform.render(g);
		trampoline_platform.render(g);
		white_platform.render(g);
		yellow_platform.render(g);

		// render doodle
		doodle.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DoodleGame(), new Dimension(695, 822), false);
		game.start();
	}

}
