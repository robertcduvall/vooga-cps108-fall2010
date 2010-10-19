package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class MarioLevel {

	private Game myGame;
	private KeyboardControl myControl;
	private MarioPlayField myPlayField;
	private MarioSprite myMario;
	private OverlayStat myScoreOverlay;
	private Stat<Integer> myEnemiesKilled;
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

	public MarioLevel(File mapFile, int width, int height, int levelNumber, Game game,
			OverlayStat scoreOverlay, Stat<Integer> enemiesKilled,
			OverlayStat livesOverlay, Stat<Integer> livesLeft) {
		myScoreOverlay = scoreOverlay;
		myEnemiesKilled = enemiesKilled;
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
		myPlayField = new MarioPlayField(map);
		myPlayField.addGroup(map.getTileGroup());
		Stat<Integer> levelNum = new Stat<Integer>(levelNumber);
		OverlayStat levelOverlay = new OverlayStat("Level: ",levelNum);
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
				Resources.getImage("MarioR1"), Resources.getImage("MarioR2"),
				Resources.getImage("MarioR3"), Resources.getImage("MarioR4") };
		BufferedImage[] MarioL = new BufferedImage[] {
				Resources.getImage("MarioL1"), Resources.getImage("MarioL2"),
				Resources.getImage("MarioL3"), Resources.getImage("MarioL4") };
		myMario = new MarioSprite("mario", "regular", MarioR, MarioL);
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
		return myPlayField;
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
		int numKilled = 0;
		removeKilled();
		//getPlayField().getGroup("Enemy Group").removeInactiveSprites();

		
		if (myTimer.action(elapsedTime))
			spawnEnemies();
		myEnemiesKilled.setStat(myEnemiesKilled.getStat().intValue()
				+ numKilled);
		myLives.setStat(myMario.getHealth());
		/*
		SpriteGroup group = getPlayField().getGroup("Enemy Group");
		System.out.println(((CharacterSprite) group.getSprites()[0])
				.getHealth()
				+ " : "
				+ ((CharacterSprite) group.getSprites()[0]).isActive()
				+ " : " + group.getSprites()[0].isActive());
				*/
	}

	private int removeKilled() {
		SpriteGroup group = getPlayField().getGroup("Enemy Group");
		Sprite[] sprites = group.getSprites();
		int num = 0;
		for (int i = 0; i < group.getSize(); i++) {
			if (!sprites[i].isActive()) {
				num++;
				group.remove(sprites[i]);
			}
		}
		return num;
	}

	public boolean getLevelFinished() {
		return myLevelFinished;
	}

	public void render(Graphics2D g) {
		myPlayField.render(g);
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
					Resources.getImage("Enemy1"),
					Resources.getImage("Enemy2"),
					Resources.getImage("Enemy3"),
					Resources.getImage("Enemy4")};
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
		myMario.setHealth(myMario.getMaxHealth());
		myMario.setActive(true);
	}

}
