package vooga.games.towerdefense;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.ImageUtil;
import com.golden.gamedev.util.Utility;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayBar;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayStatImage;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.overlay.StatInt;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.MouseControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.doodlejump.DoodleState;

public class TowerDefense extends Game {

	public static final int WIDTH = 1050;
	public static final int HEIGHT = 600;

	private PlayerCursor player;
	private PlayerCursorControl playerCursorControl;
	private KeyboardControl playerKeyboardControl;
	private Background background;
	private PlayField playfield;
	private SpriteGroup towerGroup, enemyGroup, towerShotGroup, overlayGroup,
			pauseGroup, playerGroup;
	private ArrayList<PathPoint> path;
	private long totalTime;
	private Stat<Integer> selfEsteem;
	private Stat<Integer> score;
	private Stat<Integer> money;
	private NonSetGameStateManager stateManager;
	private State startMenu;
	private State play;
	private State pause;
	private State gameOver;

	@Override
	public void initResources() {
		Resources.setGame(this);
		loadImages();
		initBackground();

		stateManager = new NonSetGameStateManager();
		startMenu = new State();
		play = new State();
		pause = new State();
		gameOver = new State();

		stateManager.addGameState(startMenu);
		stateManager.addGameState(play);
		stateManager.addGameState(pause);
		stateManager.addGameState(gameOver);

		stateManager.switchTo(play);

		playfield = new PlayField(background);

		towerGroup = play.addAndReturnGroup(new SpriteGroup("Tower Group"));
		enemyGroup = play.addAndReturnGroup(new SpriteGroup("Enemy Group"));
		towerShotGroup = play.addAndReturnGroup(new SpriteGroup(
				"Tower Shot Group"));
		playerGroup = play.addAndReturnGroup(new SpriteGroup("Player Group"));
		overlayGroup = play.addAndReturnGroup(new SpriteGroup("Overlay Group"));

		Sprite pauseScreen = new Sprite(Resources.getImage("pause"));
		pauseGroup = pause.addAndReturnGroup(new SpriteGroup("Pause Group"));
		pauseGroup.add(pauseScreen);
		pause.addGroup(overlayGroup);
		
		Sprite gameOverSprite = new Sprite(ImageUtil.resize(Resources.getImage("gameOver"), WIDTH, HEIGHT));
		SpriteGroup gameOverGroup = gameOver.addAndReturnGroup(new SpriteGroup("Pause Group"));
		gameOverGroup.add(gameOverSprite);
		
		

		selfEsteem = new Stat<Integer>(100);
		score = new Stat<Integer>(0);
		money = new Stat<Integer>(100);
		initPath();
		initPlayer();
		initOverlays();

	}

	private void loadImages() {
		Resources.loadImage("duvallFace",
				"src/vooga/games/towerdefense/resources/images/duvallFace.png");
		Resources
				.loadImage("duvallFaceRed",
						"src/vooga/games/towerdefense/resources/images/duvallFaceRed.png");
		Resources
				.loadImage("duvallFaceBlue",
						"src/vooga/games/towerdefense/resources/images/duvallFaceBlue.png");
		Resources
				.loadImage("duvallFaceYellow",
						"src/vooga/games/towerdefense/resources/images/duvallFaceYellow.png");
		Resources.loadImage("tower",
				"src/vooga/games/towerdefense/resources/images/tower.png");
		Resources.loadImage("towerShot",
				"src/vooga/games/towerdefense/resources/images/purpleShot.png");
		Resources
				.loadImage("towerPreview",
						"src/vooga/games/towerdefense/resources/images/towerPreview.png");
		Resources
				.loadImage("sniperTower",
						"src/vooga/games/towerdefense/resources/images/sniperTower.png");
		Resources
				.loadImage("normalTower",
						"src/vooga/games/towerdefense/resources/images/normalTower.png");
		Resources.loadImage("fastTower",
				"src/vooga/games/towerdefense/resources/images/fastTower.png");
		Resources
				.loadImage("fastTowerPreview",
						"src/vooga/games/towerdefense/resources/images/fastTowerPreview.png");
		Resources
				.loadImage("sniperTowerPreview",
						"src/vooga/games/towerdefense/resources/images/sniperTowerPreview.png");
		Resources
				.loadImage("normalTower",
						"src/vooga/games/towerdefense/resources/images/normalTower.png");
		Resources
				.loadImage("normalTowerPreview",
						"src/vooga/games/towerdefense/resources/images/normalTowerPreview.png");
		Resources
				.loadImage("pause",
						"src/vooga/games/towerdefense/resources/images/pauseScreen.gif");
		Resources
		.loadImage("gameOver",
				"src/vooga/games/towerdefense/resources/images/gameOver.gif");


	}

	private void initOverlays() {
		GameFont fontRed = fontManager.getFont(getImages(
				"src/vooga/games/towerdefense/resources/images/fontRed.png",
				20, 3), " !            .,0123" + "456789:   -? ABCDEFG"
				+ "HIJKLMNOPQRSTUVWXYZ ");
		GameFont fontOrange = fontManager.getFont(getImages(
				"src/vooga/games/towerdefense/resources/images/fontOrange.png",
				20, 3), " !            .,0123" + "456789:   -? ABCDEFG"
				+ "HIJKLMNOPQRSTUVWXYZ ");
		GameFont fontGreen = fontManager.getFont(getImages(
				"src/vooga/games/towerdefense/resources/images/fontGreen.png",
				20, 3), " !            .,0123" + "456789:   -? ABCDEFG"
				+ "HIJKLMNOPQRSTUVWXYZ ");
		OverlayString temp = new OverlayString("Selfestem".toUpperCase(),
				fontRed);
		temp.setLocation(800, 50);
		OverlayBar bar = new OverlayBar(selfEsteem, 100);
		bar.setMaxLength(200);
		bar.setLocation(775, 70);

		OverlayStat overlayMoney = new OverlayStat("MONEY : ", money);
		overlayMoney.setFont(fontGreen);
		overlayMoney.setLocation(800, 100);

		OverlayStat overlayScore = new OverlayStat("SCORE : ", score);
		overlayScore.setFont(fontGreen);
		overlayScore.setLocation(800, 130);

		OverlayString towers = new OverlayString("TOWERS");
		towers.setFont(new Font("random", Font.ITALIC, 26));
		towers.setLocation(830, 180);

		OverlayString normal = new OverlayString("NORMAL TOWER");
		OverlayString normalCost = new OverlayString("COST 40 DOLLARS");
		normal.setFont(fontOrange);
		normal.setLocation(800, 220);
		normalCost.setFont(fontOrange);
		normalCost.setLocation(770, 240);
		
		OverlayStatImage normalTower = new OverlayStatImage(Resources.getImage("normalTower"));
		normalTower.setLocation(880, 260);
		
		OverlayString fast = new OverlayString("FAST TOWER");
		OverlayString fastCost = new OverlayString("COST 200 DOLLARS");
		fast.setFont(fontGreen);
		fast.setLocation(800, 320);
		fastCost.setFont(fontGreen);
		fastCost.setLocation(770, 340);
		
		OverlayStatImage fastTower = new OverlayStatImage(Resources.getImage("fastTower"));
		fastTower.setLocation(880, 360);
		
		OverlayString sniper = new OverlayString("SNIPER TOWER");
		OverlayString sniperCost = new OverlayString("COST 80 DOLLARS");
		sniper.setFont(fontRed);
		sniper.setLocation(800, 420);
		sniperCost.setFont(fontRed);
		sniperCost.setLocation(770, 440);
		
		OverlayStatImage sniperTower = new OverlayStatImage(Resources.getImage("sniperTower"));
		sniperTower.setLocation(880, 460);
		
		


		overlayGroup.add(temp);
		overlayGroup.add(bar);
		overlayGroup.add(overlayMoney);
		overlayGroup.add(overlayScore);
		overlayGroup.add(towers);
		overlayGroup.add(normal);
		overlayGroup.add(normalCost);
		overlayGroup.add(normalTower);
		overlayGroup.add(fast);
		overlayGroup.add(fastCost);
		overlayGroup.add(fastTower);
		overlayGroup.add(sniper);
		overlayGroup.add(sniperCost);
		overlayGroup.add(sniperTower);
		

	}

	private void initPath() {
		path = new ArrayList<PathPoint>();
		File thisLevel = new File(
				"src/vooga/games/towerdefense/resources/levels/level1.txt");
		try {
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

	private void initPlayer() {

		player = new PlayerCursor("player", "playerCursor", new Sprite(
				Resources.getImage("towerPreview")), towerGroup, this, money);
		playerGroup.add(player);
		playerCursorControl = (PlayerCursorControl) play
				.addControl(new PlayerCursorControl(player, this));
		playerKeyboardControl = (KeyboardControl) play
				.addControl(new KeyboardControl(player, this));
		playerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
				"vooga.games.towerdefense.PlayerCursor");
		playerKeyboardControl.setParams(new Class[] { String.class });
		playerKeyboardControl.addInput(KeyEvent.VK_1, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", "NormalTower");
		playerKeyboardControl.setParams(new Class[] { String.class });
		playerKeyboardControl.addInput(KeyEvent.VK_2, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", "FastTower");
		playerKeyboardControl.setParams(new Class[] { String.class });
		playerKeyboardControl.addInput(KeyEvent.VK_3, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", "SniperTower");
	}

	private void initBackground() {
		background = new ImageBackground(ImageUtil
				.resize(getImage("resources/images/EasyLevel.png"),
						WIDTH, HEIGHT), WIDTH, HEIGHT);
	}

	@Override
	public void update(long elapsedTime) {
		if (keyPressed(KeyEvent.VK_P)) {
			if (play.isActive())
				stateManager.switchTo(pause);
			else if (pause.isActive())
				stateManager.switchTo(play);
		}
		stateManager.update(elapsedTime);
		
		if(selfEsteem.getStat() <= 0 && !gameOver.isActive()){
			stateManager.switchTo(gameOver);
		}

		if (play.isActive()) {
			totalTime += elapsedTime;
			if (totalTime > 20000) {
				enemyGroup.add(new Enemy(path, Utility.getRandom(20, 80),
						Utility.getRandom(1, 3), selfEsteem, score, money));
				totalTime = 0;
			}
			if (totalTime > 1000) {
				enemyGroup.add(new EnemySpawn(path, Utility.getRandom(80, 200),
						selfEsteem, score, money, enemyGroup));
				totalTime = 0;
			}
		}

		playfield.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
		stateManager.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new TowerDefense(), new Dimension(WIDTH, HEIGHT), false);
		game.start();
	}

	protected ArrayList<PathPoint> getPath() {
		return path;
	}

	public SpriteGroup getEnemyGroup() {
		return this.enemyGroup;
	}

	public SpriteGroup getTowerShotGroup() {
		return this.towerShotGroup;
	}

	public ArrayList<PathPoint> getPathPoints() {
		return this.path;
	}

}
