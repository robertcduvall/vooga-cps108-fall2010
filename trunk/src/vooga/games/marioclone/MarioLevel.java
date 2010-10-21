package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelFactory;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.ResourcesBeta;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class MarioLevel implements LevelFactory {

	private Game myGame;
	private KeyboardControl myControl;
	private MarioPlayField myPlayfield;
	private MarioSprite myMario;
	private OverlayStat myScoreOverlay;
	private Stat<Integer> myLives;
	private OverlayStat myLivesOverlay;
	private Timer myTimer;
	private static final int FREQ_ENEMIES = 5000;
	private static final int NUM_ENEMIES = 1;
	private int myWidth;
	private int myHeight;
	private int myBackgroundWidth;
	private int myBackgroundHeight;
	private boolean myLevelFinished;

	public MarioLevel(File mapFile, int width, int height, int levelNumber,
			Game game, OverlayStat scoreOverlay, Stat<Integer> enemiesKilled,
			OverlayStat livesOverlay, Stat<Integer> livesLeft) {
		myScoreOverlay = scoreOverlay;
		myLives = livesLeft;
		myLivesOverlay = livesOverlay;
		myLevelFinished = false;
		myGame = game;
		TileMap map = null;
		try {
			map = new TileMap(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		myWidth = width;
		myHeight = height;
		myBackgroundWidth = map.width * map.TILE_SIZE;
		myBackgroundHeight = map.height * map.TILE_SIZE;
		myPlayfield = new MarioPlayField(map);
		myPlayfield.addGroup(map.getTileGroup());
		Stat<Integer> levelNum = new Stat<Integer>(levelNumber);
		OverlayStat levelOverlay = new OverlayStat("Level: ", levelNum);
		myScoreOverlay.setLocation(myWidth - 1000, 5);
		myLivesOverlay.setLocation(myWidth - 400, 5);
		levelOverlay.setLocation(myWidth - 600, 5);

		getPlayField().add(myScoreOverlay);
		getPlayField().add(myLivesOverlay);
		getPlayField().add(levelOverlay);
		Background marioBackground = new ColorBackground(new Color(139, 201,
				240), myBackgroundWidth, myBackgroundHeight);
		marioBackground.setClip(0, 0, myWidth, myHeight);

		BufferedImage[] MarioR = new BufferedImage[] {
				ResourcesBeta.getImage("MarioR1"), ResourcesBeta.getImage("MarioR2"),
				ResourcesBeta.getImage("MarioR3"), ResourcesBeta.getImage("MarioR4") };
		BufferedImage[] MarioL = new BufferedImage[] {
				ResourcesBeta.getImage("MarioL1"), ResourcesBeta.getImage("MarioL2"),
				ResourcesBeta.getImage("MarioL3"), ResourcesBeta.getImage("MarioL4") };
		myMario = new MarioSprite("mario", "regular", MarioR, MarioL,
				enemiesKilled);
		myMario.setLocation(150, 290);
		myMario.addStat("Kills", enemiesKilled);
		setUpKeyboard();
		getPlayField().getGroup("Mario Group").add(myMario);
		spawnEnemies();
		getPlayField().setBackground(marioBackground);
		myTimer = new Timer(FREQ_ENEMIES);
	}

	public MarioSprite getMario() {
		return myMario;
	}

	public void setMario(MarioSprite myMario) {
		this.myMario = myMario;
	}

	public MarioPlayField getPlayField() {
		return myPlayfield;
	}

	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}

	public void update(long elapsedTime) {
		myControl.update();
		if (myMario.getX() >= (myBackgroundWidth - 200)) {
			myLevelFinished = true;
		}
		if (myMario.getX() > myMario.getMaxX()) {
			scrollLevel();
		}
		getPlayField().update(elapsedTime);
		getPlayField().getGroup("Enemy Group").removeInactiveSprites();

		if (myTimer.action(elapsedTime))
			spawnEnemies();
		myLives.setStat(myMario.getHealth());
	}

	public boolean getLevelFinished() {
		return myLevelFinished;
	}

	public void render(Graphics2D g) {
		myPlayfield.render(g);
	}

	private void scrollLevel() {
		getPlayField().getBackground().setToCenter(myMario);
	}

	/**
	 * This method is responsible for spawning enemies at random locations on
	 * the map.
	 * 
	 */

	public void spawnEnemies() {
		for (int j = 0; j < NUM_ENEMIES; j++) {
			BufferedImage[] enemyImgs = new BufferedImage[] {
					ResourcesBeta.getImage("Enemy1"), ResourcesBeta.getImage("Enemy2"),
					ResourcesBeta.getImage("Enemy3"), ResourcesBeta.getImage("Enemy4") };
			Enemy enemy = new Enemy("enemy1", "regular", enemyImgs, enemyImgs);
			enemy.setLocation(400, 200);
			getPlayField().getGroup("Enemy Group").add(enemy);
		}
		myTimer = new Timer(FREQ_ENEMIES);
	}

	private void setUpKeyboard() {
		myControl = new KeyboardControl(myMario, myGame);

		myControl.addInput(KeyEvent.VK_D, "moveRight",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jumpCmd",
				"vooga.games.marioclone.MarioSprite");
		for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) {
			if (i == KeyEvent.VK_D || i == KeyEvent.VK_A || i == KeyEvent.VK_W)
				continue;
			myControl.setParams(new Class[] { char.class });
			myControl.addInput(i, "cheat",
					"vooga.games.marioclone.MarioSprite", (char) i);
		}

		myMario.setHealth(myMario.getMaxHealth());
		myMario.setActive(true);
	}

	@Override
	public PlayField getPlayfield(File levelFactoryFile) {
		return myPlayfield;
	}

}
