package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.state.GameState;
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

	// GameStates
	GameState startMenu, play, pauseMenu, gameOver, win;

	// Background
	private Background background;

	// Playfield
	protected PlayField playField;

	private OverlayString scoreString, startString, winString;
	private Stat<Integer> score;
	private int currentLevel;
	private int passScore;
	private int nextLevel;

	{
		distribute = true;
	}

	// Doodle (main player)
	private DoodleSprite doodle;
	private KeyboardControl doodle_keyboard_control;

	protected SpriteGroup doodleGroup, ballGroup;

	// Collision Manager
	protected CollisionManager doodleToGreenPlatform, doodleToMonster,
			ballToMonster, doodleToBrownPlatform, doodleToWhitePlatform,
			doodleToSpring, doodleToTrampoline, doodleToJetpack;

	private boolean showStart;

	public DoodleGame() {
		super();
		currentLevel = 3;
		showStart = true;
		score = new Stat<Integer>(0);
		scoreString = new OverlayString("Score: " + 0);
		startString = new OverlayString("Press Enter to Start!", new Font(
				"SansSerif", Font.BOLD, 40));
		startString.setX(532 / 2 - startString.getWidth() / 2);
		startString.setY(550 / 2 - startString.getHeight() / 2);
		winString = new OverlayString("You Win! Enter to Restart.", new Font(
				"SansSerif", Font.BOLD, 35));
		winString.setX(532 / 2 - startString.getWidth() / 2);
		winString.setY(200 - startString.getHeight() / 2);
		scoreString.setX(400);
		scoreString.setY(50);
	}

	@Override
	public void initResources() {
		DoodleLevel level = new DoodleLevel();

		startMenu = new GameState();
		play = new GameState();
		pauseMenu = new GameState();
		gameOver = new GameState();
		win = new GameState();
		if (showStart)
			startMenu.activate();
		else
			play.activate();

		// playfield
		playField = level.getPlayfield(new File(
				"src/vooga/games/doodlejump/levels/level"
						+ Integer.toString(currentLevel) + ".txt"));

		// background
		background = level.getBackground();

		passScore += level.getScore();
		nextLevel = level.getNextLevel();

		// spritegroups
		ballGroup = playField.addGroup(new SpriteGroup("Ball Group"));
		doodleGroup = playField.addGroup(new SpriteGroup("Doodle Group"));

		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(
				getImage("images/doodle_right.png"), 325, 550), this);
		doodle.setVerticalSpeed(0.5);
		doodleGroup.add(doodle);
		doodle_keyboard_control = new KeyboardControl(doodle, this);
		doodle_keyboard_control.addInput(KeyEvent.VK_LEFT, "moveLeft",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_RIGHT, "moveRight",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_SPACE, "shoot",
				"vooga.games.doodlejump.DoodleSprite", null);

		// Collision
		doodleToGreenPlatform = new DoodleToGreenPlatformCollision();
		doodleToMonster = new DoodleToMonsterCollision();
		ballToMonster = new BallToMonsterCollision();
		doodleToBrownPlatform = new DoodleToBrownPlatformCollision();
		doodleToWhitePlatform = new DoodleToWhitePlatformCollision();
		doodleToSpring = new DoodleToSpringCollision();
		doodleToTrampoline = new DoodleToTrampolineCollision();

		playField.addCollisionGroup(doodleGroup, level.getPlatformGroup(),
				doodleToGreenPlatform);
		playField.addCollisionGroup(doodleGroup, level.getMonsterGroup(),
				doodleToMonster);
		playField.addCollisionGroup(ballGroup, level.getMonsterGroup(),
				ballToMonster);
		playField.addCollisionGroup(doodleGroup, level.getBrownPlatformGroup(),
				doodleToBrownPlatform);
		playField.addCollisionGroup(doodleGroup, level.getWhitePlatformGroup(),
				doodleToWhitePlatform);
		playField.addCollisionGroup(doodleGroup, level.getSpringGroup(),
				doodleToSpring);
		playField.addCollisionGroup(doodleGroup, level.getTrampolineGroup(),
				doodleToTrampoline);

		setFPS(100);
	}

	@Override
	public void update(long elapsedTime) {
		doodle_keyboard_control.update();

		if (gameOver.isActive()) {
			if (keyPressed(KeyEvent.VK_ENTER)) {
				currentLevel = 1;
				score.setStat(0);
				passScore = 0;
				initResources();
			}
		} else if (startMenu.isActive()) {
			if (keyPressed(KeyEvent.VK_ENTER)) {
				playGame();
			}
			playField.setBackground(new ImageBackground(
					getImage("images/default-play.png")));
		} else if (play.isActive()) {
			for (SpriteGroup group : playField.getGroups()) {
				for (Sprite sprite : group.getSprites()) {
					if (doodle.getY() < 400 && sprite != null) {
						if (group.getName().equals("Doodle Group"))
							score.setStat(score.getStat() + 5);
						sprite.moveY(400 - doodle.getY());
					}
				}
			}
			playField.update(elapsedTime);
			scoreString
					.setString("Score: " + Integer.toString(score.getStat()));
			if (score.getStat() >= passScore) {
				if (nextLevel == 0) {
					win.activate();
					if (keyPressed(KeyEvent.VK_ENTER)) {
						gameOver();
						win.deactivate();
					}
				}
				if (nextLevel != 0) {
					currentLevel = nextLevel;
					showStart = false;
					initResources();
				} else {
					gameOver();
				}
			}
			if (keyPressed(KeyEvent.VK_P)) {
				pauseGame();
			}
		} else {
			if (keyPressed(KeyEvent.VK_P)) {
				playGame();
			}
			playField.setBackground(new ImageBackground(
					getImage("images/pause-cover-resume.png")));
		}
	}

	public void gameOver() {
		play.deactivate();
		pauseMenu.deactivate();
		startMenu.deactivate();
		gameOver.activate();
	}

	public void playGame() {
		startMenu.deactivate();
		play.activate();
		pauseMenu.deactivate();
		gameOver.deactivate();
	}

	public void pauseGame() {
		play.deactivate();
		pauseMenu.activate();
		startMenu.deactivate();
		gameOver.deactivate();
	}

	@Override
	public void render(Graphics2D g) {
		if (play.isActive()) {
			playField.setBackground(background);
			playField.render(g);
			scoreString.render(g);
		} else if (startMenu.isActive()) {
			playField.getBackground().render(g);
			startString.render(g);
		} else if (pauseMenu.isActive()) {
			playField.getBackground().render(g);
			// scoreString.render(g);
		} else if (win.isActive()) {
			winString.render(g);
		}
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DoodleGame(), new Dimension(532, 850), false);
		game.start();
	}

}
