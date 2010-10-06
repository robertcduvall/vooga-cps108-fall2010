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

	public void initResources() {

		// background
		background = new ImageBackground(getImage("background.png"));


		// platforms
		brown_platform = new Sprite(getImage("brown_platform.png"), 500, 500);
		dark_blue_platform = new Sprite(getImage("dark_blue_platform.png"), 400, 400);
		gray_platform = new Sprite(getImage("gray_platform.png"), 200, 400);
		green_platform = new Sprite(getImage("green_platform.png"), 100, 200);
		light_blue_platform = new Sprite(getImage("light_blue_platform.png"), 200, 100);
		red_platform = new Sprite(getImage("red_platform.png"), 600, 600);
		spring_platform = new Sprite(getImage("spring_platform.png"), 600, 100);
		trampoline_platform = new Sprite(getImage("trampoline_platform.png"), 300, 200);
		white_platform = new Sprite(getImage("white_platform.png"), 525, 300);
		yellow_platform = new Sprite(getImage("yellow_platform.png"), 100, 600);

		// create monsters
		blue_flying_monster = new PlayerSprite("blue_flying_monster", "bfm", new Sprite(getImage("blue_flying_monster.png"),50,100));
		blue_monster = new PlayerSprite("blue_monster", "bm", new Sprite(getImage("blue_monster.png"),70,500));
		green_flying_monster = new PlayerSprite("green_flying_monster", "gfm", new Sprite(getImage("green_flying_monster.png"),320,70));
		green_monster = new PlayerSprite("green_monster", "gm", new Sprite(getImage("green_monster.png"),50,200));
		purple_monster = new PlayerSprite("purple_monster", "pm", new Sprite(getImage("purple_monster.png"),50,425));
		red_monster = new PlayerSprite("red_monster", "rm", new Sprite(getImage("red_monster.png"),500,550));

		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(getImage("doodle_crop.png")));
		doodle.setLocation(325, 650);
		doodle_keyboard_control = new KeyboardControl(doodle, this);
		doodle_keyboard_control.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.doodlejump.DoodleSprite", null);
		setFPS(100);
	}
	


	public void update(long elapsedTime) {
		doodle_keyboard_control.update();
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
