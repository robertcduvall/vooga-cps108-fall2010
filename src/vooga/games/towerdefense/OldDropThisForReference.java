package vooga.games.towerdefense;



import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.resource.*;
import vooga.games.towerdefense.tower.*;

/**
 * This is the main class of the game. It creates the different states of the
 * games, and loads all the sprites used in the game.
 * 
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 * 
 */
public class OldDropThisForReference extends Game {}
	/*

	public static final int WIDTH = 1050;
	public static final int HEIGHT = 600;
	private static final int SECOND = 1000;
	private static final int EASY = 0;
	private static final int MEDIUM = 1;
	private static final int HARD = 2;
	

	private PlayerCursorControl playerCursorControl, menuPlayerCursorControl,
			gameOverPlayerCursorControl;
	private KeyboardControl playerKeyboardControl;
	protected Background[] background;
	private SpriteGroup towerGroup, enemyGroup, towerShotGroup, overlayGroup,
			pauseGroup, playerGroup, gameOverGroup, menuGroup;
	private ArrayList<PathPoint> path;
	private long totalTime;
	private Stat<Integer> selfEsteem;
	private Stat<Integer> score;
	private Stat<Integer> money;
	protected State startMenu;
	protected State play;
	protected State pause;
	protected State gameOver;
	private int difficulty;
	private boolean go;
	private Counter counter;
	private Timer hit1;
	private Timer hit2;
	private Timer hit3;
	private Timer spawn;
	private Timer gameTimer;
	private int spawnSpeed;
	private TowerBuilder towerBuilder;
	private TowerTargetFinder towerTargetFinder;

	@Override
	public void initResources() {
		super.initResources();
		initBackgrounds();
		initEvents();
		initOverlays();
		initPlayer();
	}

	@Override
	public void update(long elapsedTime) {
		if (keyPressed(KeyEvent.VK_P)) {
			if (play.isActive())
				getGameStateManager().switchTo(pause);
			else if (pause.isActive())
				getGameStateManager().switchTo(play);
		}
		super.update(elapsedTime);

		if (play.isActive() && selfEsteem.getStat() <= 0) {
			getGameStateManager().switchTo(gameOver);
		}

		if (go) {
			createEnemies(elapsedTime);
		} else if (totalTime < 12000) {
			totalTime += elapsedTime;
		} else {
			go = true;
			playerGroup.remove(counter);
		}
	}

	@Override
	public void render(Graphics2D g) {
		background[difficulty].render(g);
		super.render(g);
	}

	@Override
	public void initGameStates() {
		super.initGameStates();
		startMenu = new State();
		play = new State();
		pause = new State();
		gameOver = new State();

		getGameStateManager().addGameState(startMenu);
		getGameStateManager().addGameState(play);
		getGameStateManager().addGameState(pause);
		getGameStateManager().addGameState(gameOver);

		playerGroup = play.addAndReturnGroup(new SpriteGroup("Player Group"));

		Sprite pauseScreen = new Sprite(Resources.getImage("pause"));
		pauseGroup = pause.addAndReturnGroup(new SpriteGroup("Pause Group"));
		pauseGroup.add(pauseScreen);

		gameOverGroup = gameOver.addAndReturnGroup(new SpriteGroup(
				"Game Over Group"));

		Sprite menuSprite = new Sprite(ImageUtil.resize(Resources
				.getImage("menu"), WIDTH, HEIGHT));
		menuGroup = startMenu.addAndReturnGroup(new SpriteGroup("Menu Group"));
		menuGroup.add(menuSprite);

		getGameStateManager().switchTo(startMenu);
	}

	private void initBackgrounds() {
		background = new Background[3];
		background[EASY] = new ImageBackground(ImageUtil.resize(Resources
				.getImage("easyLevelBG"), WIDTH, HEIGHT), WIDTH, HEIGHT);
		background[MEDIUM] = new ImageBackground(ImageUtil.resize(Resources
				.getImage("mediumLevelBG"), WIDTH, HEIGHT), WIDTH, HEIGHT);
		background[HARD] = new ImageBackground(ImageUtil.resize(Resources
				.getImage("hardLevelBG"), WIDTH, HEIGHT), WIDTH, HEIGHT);
	}

	private void initEvents() {
		towerBuilder = new TowerBuilder();
		towerBuilder.setGame(this);
		towerTargetFinder = new TowerTargetFinder();
		towerTargetFinder.setGame(this);
		SingletonEventManager.addEventListener("BuildTowerEvent", towerBuilder);
		SingletonEventManager.addEventListener("NeedsTargetsEvent",
				towerTargetFinder);
	}

	private void initOverlays() {
		OverlayCreator.setGame(this);
		OverlayTracker track = OverlayCreator
				.createOverlays("src/vooga/games/towerdefense/resources/overlays.xml");
		overlayGroup = track.getOverlayGroups().get(0);
		gameOverGroup = track.getOverlayGroups().get(1);
		gameOverGroup.add(0, new Sprite(ImageUtil.resize(Resources
				.getImage("gameOver"), WIDTH, HEIGHT)));
		selfEsteem = track.getStats().get(0);
		score = track.getStats().get(1);
		money = track.getStats().get(2);

		play.addGroup(overlayGroup);
		gameOver.addGroup(gameOverGroup);
		pause.addGroup(overlayGroup);
	}

	private void initPlayer() {

		PlayerSprite player = new PlayerCursor("player", "playerCursor",
				new Sprite(Resources.getImage("towerPreview")), this, money,
				getGameStateManager());

		playerGroup.add(player);
		playerCursorControl = (PlayerCursorControl) play
				.addControl(new PlayerCursorControl(player, this));
		playerKeyboardControl = (KeyboardControl) play
				.addControl(new KeyboardControl(player, this));
		playerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
				"vooga.games.towerdefense.PlayerCursor");
		playerKeyboardControl.setParams(new Class[] { Tower.class });
		playerKeyboardControl.addInput(KeyEvent.VK_1, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", new Normal(0, 0));
		playerKeyboardControl.setParams(new Class[] { Tower.class });
		playerKeyboardControl.addInput(KeyEvent.VK_2, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", new Fast(0, 0));
		playerKeyboardControl.setParams(new Class[] { Tower.class });
		playerKeyboardControl.addInput(KeyEvent.VK_3, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", new Sniper(0, 0));

		menuGroup.add(player);
		menuPlayerCursorControl = (PlayerCursorControl) startMenu
				.addControl(new PlayerCursorControl(player, this));
		menuPlayerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
				"vooga.games.towerdefense.PlayerCursor");

		gameOverGroup.add(player);
		gameOverPlayerCursorControl = (PlayerCursorControl) gameOver
				.addControl(new PlayerCursorControl(player, this));
		gameOverPlayerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
				"vooga.games.towerdefense.PlayerCursor");

	}

	private void createPath() {
		path = new ArrayList<PathPoint>();
		try {
			File thisLevel;
			if (difficulty == EASY) {
				thisLevel = new File(
						"src/vooga/games/towerdefense/resources/levels/easy.txt");
			} else if (difficulty == MEDIUM) {
				thisLevel = new File(
						"src/vooga/games/towerdefense/resources/levels/medium.txt");
			} else {
				thisLevel = new File(
						"src/vooga/games/towerdefense/resources/levels/hard.txt");
			}
			Scanner sc = new Scanner(thisLevel);
			while (sc.hasNextInt()) {
				int x = sc.nextInt();
				if (sc.hasNextInt()) {
					int y = sc.nextInt();
					path.add(new PathPoint(x, y));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("failed to implement scanner");
			System.exit(0);
		}
		;

	}

	private void createEnemies(long elapsedTime) {
		if (hit1.action(elapsedTime)) {
			enemyGroup.add(new Enemy(path, 50, 1, selfEsteem, score, money));
		}
		if (hit2.action(elapsedTime)) {
			enemyGroup.add(new Enemy(path, 80, 2, selfEsteem, score, money));
		}
		if (hit3.action(elapsedTime)) {
			enemyGroup.add(new Enemy(path, 40, 3, selfEsteem, score, money));
		}
		if (spawn.action(elapsedTime)) {
			enemyGroup.add(new EnemySpawn(path, spawnSpeed, selfEsteem, score,
					money, enemyGroup));
		}
		if (gameTimer.action(elapsedTime)) {
			long delay = spawn.getDelay() / 2;
			if (delay < 500) {
				spawn.setDelay(SECOND / 2);
			} else {
				spawn.setDelay(delay);
			}
			if (delay > 5000) {
				spawnSpeed -= 10;
			} else if (delay < 501) {
				spawnSpeed += 10;
			}
		}

	}

	protected ArrayList<PathPoint> getPath() {
		return path;
	}

	public SpriteGroup getEnemyGroup() {
		return this.enemyGroup;
	}

	public SpriteGroup getTowerGroup() {
		return this.towerGroup;
	}

	public SpriteGroup getTowerShotGroup() {
		return this.towerShotGroup;
	}

	public ArrayList<PathPoint> getPathPoints() {
		return this.path;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	protected void begin() {
		hit1 = new Timer(SECOND * 5);
		hit2 = new Timer(SECOND * 6);
		hit3 = new Timer(SECOND * 7);
		spawn = new Timer(SECOND * 45);
		gameTimer = new Timer(SECOND * 45);
		spawnSpeed = 80;

		play.removeGroup(enemyGroup);
		enemyGroup = play.addAndReturnGroup(new SpriteGroup("Enemy Group"));

		play.removeGroup(towerGroup);
		towerGroup = play.addAndReturnGroup(new SpriteGroup("Tower Group"));

		play.removeGroup(towerShotGroup);
		towerShotGroup = play.addAndReturnGroup(new SpriteGroup(
				"Tower Shot Group"));

		selfEsteem.reset();
		score.reset();
		money.reset();

		createPath();
		totalTime = 0;
		go = false;

		counter = new Counter();
		counter.setLocation(350, HEIGHT / 2 - 75);
		playerGroup.add(counter);

		getGameStateManager().switchTo(play);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new OldDropThisForReference(), new Dimension(WIDTH, HEIGHT), false);
		game.start();
	}

}
*/
