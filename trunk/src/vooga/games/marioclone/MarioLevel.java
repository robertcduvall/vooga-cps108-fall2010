package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelFactory;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.ResourcesBeta;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class MarioLevel implements LevelFactory {

	private Game myGame;
	private KeyboardControl myControl;
	private MarioPlayField myPlayField;
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
	private boolean myLevelFinished;

	public MarioLevel(File mapFile, int levelNumber, Game game,
			OverlayStat scoreOverlay, Stat<Integer> enemiesKilled,
			OverlayStat livesOverlay, Stat<Integer> livesLeft) {

		myWidth = ResourcesBeta.getInt("Width");
		myHeight = ResourcesBeta.getInt("Height");

		// Overlay stuff to be consolidated
		// I am not quite sure why we need separate fields for the overlays and
		// their stats considering that overlays contain the stats - David
		myScoreOverlay = scoreOverlay;
		myLives = livesLeft;
		myLivesOverlay = livesOverlay;
		myLevelFinished = false;

		Stat<Integer> levelNum = new Stat<Integer>(levelNumber);
		OverlayStat levelOverlay = new OverlayStat("Level: ", levelNum);
		myScoreOverlay.setLocation(myWidth - 1000, 5);
		myLivesOverlay.setLocation(myWidth - 400, 5);
		levelOverlay.setLocation(myWidth - 600, 5);

		myGame = game;

		TileMap map = null;
		try {
			map = new TileMap(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		myBackgroundWidth = map.width * map.TILE_SIZE;
		int backgroundHeight = map.height * map.TILE_SIZE;

		myPlayField = new MarioPlayField(map);
		myPlayField.addGroup(map.getTileGroup());

		// More overlay stuff
		myPlayField.add(myScoreOverlay);
		myPlayField.add(myLivesOverlay);
		myPlayField.add(levelOverlay);

		Background marioBackground = new ColorBackground(new Color(139, 201,
				240), myBackgroundWidth, backgroundHeight);
		marioBackground.setClip(0, 0, myWidth, myHeight);

		BufferedImage[] MarioR = new BufferedImage[] {
				ResourcesBeta.getImage("MarioR1"),
				ResourcesBeta.getImage("MarioR2"),
				ResourcesBeta.getImage("MarioR3"),
				ResourcesBeta.getImage("MarioR4") };
		BufferedImage[] MarioL = new BufferedImage[] {
				ResourcesBeta.getImage("MarioL1"),
				ResourcesBeta.getImage("MarioL2"),
				ResourcesBeta.getImage("MarioL3"),
				ResourcesBeta.getImage("MarioL4") };

		myMario = new MarioSprite("mario", "regular", MarioR, MarioL);
		myMario.setLocation(150, 290);
		myMario.addStat("Kills", enemiesKilled);

		setUpKeyboard();
		myPlayField.getGroup("Mario Group").add(myMario);
		spawnEnemies();
		myPlayField.setBackground(marioBackground);
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
		getPlayField().getGroup("Enemy Group").removeInactiveSprites();

		if (myTimer.action(elapsedTime))
			spawnEnemies();
		myLives.setStat(myMario.getHealth());
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
					ResourcesBeta.getImage("Enemy1"),
					ResourcesBeta.getImage("Enemy2"),
					ResourcesBeta.getImage("Enemy3"),
					ResourcesBeta.getImage("Enemy4") };
			Enemy enemy = new Enemy("enemy1", "regular", enemyImgs, enemyImgs);
			enemy.setLocation(400, 200);
			getPlayField().getGroup("Enemy Group").add(enemy);
		}
		myTimer = new Timer(FREQ_ENEMIES);
	}

	private Enemy spawnEnemy(int x, int y) {
		BufferedImage[] enemyImgs = new BufferedImage[] {
				ResourcesBeta.getImage("Enemy1"),
				ResourcesBeta.getImage("Enemy2"),
				ResourcesBeta.getImage("Enemy3"),
				ResourcesBeta.getImage("Enemy4") };
		Enemy enemy = new Enemy("enemy1", "regular", enemyImgs, enemyImgs);
		enemy.setLocation(x, y);
		return enemy;
	}
	
	private MarioSprite spawnMario(int x, int y) {
		BufferedImage[] MarioR = new BufferedImage[] {
				ResourcesBeta.getImage("MarioR1"),
				ResourcesBeta.getImage("MarioR2"),
				ResourcesBeta.getImage("MarioR3"),
				ResourcesBeta.getImage("MarioR4") };
		BufferedImage[] MarioL = new BufferedImage[] {
				ResourcesBeta.getImage("MarioL1"),
				ResourcesBeta.getImage("MarioL2"),
				ResourcesBeta.getImage("MarioL3"),
				ResourcesBeta.getImage("MarioL4") };

		MarioSprite mario = new MarioSprite("mario", "regular", MarioR, MarioL);
		
		mario.setLocation(x, y);
		return mario;
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

		/*
		 * Template for MarioClone level factory file: *variable*=*value*
		 * 
		 * map=*location of level's map file* (only first is read)
		 * enemy=*enemy x*,*enemy y*
		 * mario=*mario x*,*mario y* (only first is read)
		 */
		MarioPlayField playfield = new MarioPlayField();

		Scanner s = null;
		try {
			s = new Scanner(levelFactoryFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot load level file.");
			e.printStackTrace();
		}

		HashMap<String, List<String>> vars = new HashMap<String, List<String>>();

		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] tokens = line.split("=");
			String name = tokens[0].trim();
			String val = tokens[1].trim();
			if (!vars.containsKey(name))
				vars.put(name, new ArrayList<String>());
			vars.get(name).add(val);
		}

		// Load TileMap
		File mapFile = new File(vars.get("map").get(0));
		TileMap map = null;
		try {
			map = new TileMap(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Load Enemies
		List<String> enemyStrings = vars.get("enemy");
		for (String curEnemy : enemyStrings) {
			int[] coords = parseCoords(curEnemy);
			playfield.getGroup("Enemy Group").add(spawnEnemy(coords[0],coords[1]));
		}
		
		// Load Mario
		String marioString = vars.get("mario").get(0);
		int[] coords = parseCoords(marioString);
		MarioSprite mario = spawnMario(coords[0],coords[1]);	
		playfield.getGroup("Mario Group").add(mario);
		
		// Add objects to playfield
		playfield.addTileMap(map);
		
		return playfield;
	}
	
	private int[] parseCoords(String stringCoords) {
		String[] coordString = stringCoords.split(",");
		int x = Integer.parseInt(coordString[0]);
		int y = Integer.parseInt(coordString[1]);	
		return new int[]{x,y};
	}
}