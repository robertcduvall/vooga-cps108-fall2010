package vooga.games.towerdefense;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import vooga.engine.event.EventManager;
import vooga.engine.overlay.OverlayBar;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayStatImage;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.overlay.StatInt;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.MouseControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.doodlejump.DoodleState;
import vooga.games.towerdefense.tower.*;
/**
 * This is the main class of the game.  It creates the different states of the games, and loads all the sprites used in the game.
 * 
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */
public class TowerDefense extends Game {

	public static final int WIDTH = 1050;
	public static final int HEIGHT = 600;
	private static final int SECOND = 1000;

	private PlayerCursorControl playerCursorControl, menuPlayerCursorControl , gameOverPlayerCursorControl;
	private KeyboardControl playerKeyboardControl;
	protected Background[] background;
	private SpriteGroup towerGroup, enemyGroup, towerShotGroup, overlayGroup,
			pauseGroup, playerGroup, gameOverGroup, menuGroup;
	private ArrayList<PathPoint> path;
	private long totalTime;
	private Stat<Integer> selfEsteem;
	private Stat<Integer> score;
	private Stat<Integer> money;
	private NonSetGameStateManager stateManager;
	protected State startMenu;
	protected State play;
	protected State pause;
	protected State gameOver;
	private int difficulty;
	private boolean go;
	private Counter counter;
	Timer hit1;
	Timer hit2;
	Timer hit3;
	Timer spawn;
	Timer gameTimer;
	private int spawnSpeed;
	private EventManager eventManager;
	private TowerBuilder towerBuilder;
	private TowerTargetFinder towerTargetFinder;



	@Override
	public void initResources() {
		ResourcesBeta.setGame(this);
		Resources.setGame(this);
		loadImages();
		initBackgrounds();
		eventManager = new EventManager();
		towerBuilder = new TowerBuilder();
		towerBuilder.setGame(this);
		towerTargetFinder = new TowerTargetFinder();
		towerTargetFinder.setGame(this);
		eventManager.addEventListener("BuildTowerEvent", towerBuilder);
		eventManager.addEventListener("NeedsTargetsEvent", towerTargetFinder);
		
		
		stateManager = new NonSetGameStateManager();
		startMenu = new State();
		play = new State();
		pause = new State();
		gameOver = new State();

		stateManager.addGameState(startMenu);
		stateManager.addGameState(play);
		stateManager.addGameState(pause);
		stateManager.addGameState(gameOver);

		
		playerGroup = play.addAndReturnGroup(new SpriteGroup("Player Group"));
		
		

		Sprite pauseScreen = new Sprite(ResourcesBeta.getImage("pause"));
		pauseGroup = pause.addAndReturnGroup(new SpriteGroup("Pause Group"));
		pauseGroup.add(pauseScreen);
		
		
		gameOverGroup = gameOver.addAndReturnGroup(new SpriteGroup("Game Over Group"));
		
		Sprite menuSprite = new Sprite(ImageUtil.resize(ResourcesBeta.getImage("menu"), WIDTH, HEIGHT));
		menuGroup = startMenu.addAndReturnGroup(new SpriteGroup("Menu Group"));
		menuGroup.add(menuSprite);		
		

	
		initOverlays();
		play.addGroup(overlayGroup);
		gameOver.addGroup(gameOverGroup);
		pause.addGroup(overlayGroup);
		
		
		initPlayer();
		
		
		
		
		stateManager.switchTo(startMenu);

	}
	
	private void loadImages(){
		try {
			ResourcesBeta.loadImageFile("src/vooga/games/towerdefense/resources/images/imageList.txt");
			Resources.loadImage("sniperTower","resources/images/sniperTower.png");
			Resources.loadImage("normalTower","resources/images/normalTower.png");
			Resources.loadImage("fastTower","resources/images/fastTower.png");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initOverlays() {
		OverlayCreator.setGame(this);
		OverlayTracker track = OverlayCreator.createOverlays("src/vooga/games/towerdefense/resources/overlays.xml");
		overlayGroup = track.getOverlayGroups().get(0);
		gameOverGroup = track.getOverlayGroups().get(1);
		gameOverGroup.add(0, new Sprite(ImageUtil.resize(ResourcesBeta.getImage("gameOver"), WIDTH, HEIGHT)));
		selfEsteem = track.getStats().get(0);
		score = track.getStats().get(1);
		money = track.getStats().get(2);
		
		/*OverlayString temp = new OverlayString("Selfesteem".toUpperCase(),
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
		
		OverlayStatImage normalTower = new OverlayStatImage(ResourcesBeta.getImage("normalTower"));
		normalTower.setLocation(880, 260);
		
		OverlayString fast = new OverlayString("FAST TOWER");
		OverlayString fastCost = new OverlayString("COST 250 DOLLARS");
		fast.setFont(fontGreen);
		fast.setLocation(800, 320);
		fastCost.setFont(fontGreen);
		fastCost.setLocation(770, 340);
		
		OverlayStatImage fastTower = new OverlayStatImage(ResourcesBeta.getImage("fastTower"));
		fastTower.setLocation(880, 360);
		
		OverlayString sniper = new OverlayString("SNIPER TOWER");
		OverlayString sniperCost = new OverlayString("COST 80 DOLLARS");
		sniper.setFont(fontRed);
		sniper.setLocation(800, 420);
		sniperCost.setFont(fontRed);
		sniperCost.setLocation(770, 440);
		
		OverlayStatImage sniperTower = new OverlayStatImage(ResourcesBeta.getImage("sniperTower"));
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
		
		
		OverlayString gameOver = new OverlayString("SelfEsteem depleted".toUpperCase());
		gameOver.setFont(fontGreen);
		gameOver.setLocation(((WIDTH/2) - gameOver.getWidth()/2), 220 );
		
		OverlayStat overlayScoreEnd = new OverlayStat("SCORE : ", score);
		overlayScoreEnd.setFont(fontOrange);
		overlayScoreEnd.setLocation(((WIDTH/2) - overlayScoreEnd.getWidth()/2), 250 );
		
		gameOverGroup.add(gameOver);
		gameOverGroup.add(overlayScoreEnd);
		*/

	}

	private void createPath() {
		path = new ArrayList<PathPoint>();
		try {
			File thisLevel;
			if(difficulty == 0){
				thisLevel = new File(
				"src/vooga/games/towerdefense/resources/levels/easy.txt");
			}else if(difficulty == 1){
				thisLevel = new File(
				"src/vooga/games/towerdefense/resources/levels/medium.txt");
			}else{
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

	private void initPlayer() {
		
		PlayerSprite player = new PlayerCursor("player", "playerCursor", new Sprite(
				ResourcesBeta.getImage("towerPreview")), this, money, stateManager, eventManager);
		

		playerGroup.add(player);
		playerCursorControl = (PlayerCursorControl) play
				.addControl(new PlayerCursorControl(player, this));
		playerKeyboardControl = (KeyboardControl) play
				.addControl(new KeyboardControl(player, this));
		playerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
				"vooga.games.towerdefense.PlayerCursor");
		playerKeyboardControl.setParams(new Class[] { Tower.class });
		playerKeyboardControl.addInput(KeyEvent.VK_1, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", new Normal(0,0, eventManager));
		playerKeyboardControl.setParams(new Class[] { Tower.class });
		playerKeyboardControl.addInput(KeyEvent.VK_2, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", new Fast(0,0, eventManager));
		playerKeyboardControl.setParams(new Class[] { Tower.class });
		playerKeyboardControl.addInput(KeyEvent.VK_3, "changeTowerType",
				"vooga.games.towerdefense.PlayerCursor", new Sniper(0, 0, eventManager));
		
		
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

	private void initBackgrounds() {
		background = new Background[3];
		background[0] = new ImageBackground(ImageUtil
				.resize(getImage("resources/images/EasyLevel.png"),
						WIDTH, HEIGHT), WIDTH, HEIGHT);
		background[1] = new ImageBackground(ImageUtil
				.resize(getImage("resources/images/MediumLevel.png"),
						WIDTH, HEIGHT), WIDTH, HEIGHT);
		background[2] = new ImageBackground(ImageUtil
				.resize(getImage("resources/images/HardLevel.png"),
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
		
		if(play.isActive() && selfEsteem.getStat() <= 0){
			stateManager.switchTo(gameOver);
		}

		if (go) {
			createEnemies(elapsedTime);			
		}else if(totalTime < 12000){
			totalTime+=elapsedTime;
		}else{
			go = true;
			playerGroup.remove(counter);
		}
	}

	private void createEnemies(long elapsedTime) {
		if(hit1.action(elapsedTime)){
			enemyGroup.add(new Enemy(path, 50,
					1, selfEsteem, score, money));
		}
		if(hit2.action(elapsedTime)){
			enemyGroup.add(new Enemy(path, 80,
					2, selfEsteem, score, money));
		}
		if(hit3.action(elapsedTime)){
			enemyGroup.add(new Enemy(path, 40,
					3, selfEsteem, score, money));
		}if(spawn.action(elapsedTime)){
			enemyGroup.add(new EnemySpawn(path, spawnSpeed,
					selfEsteem, score, money, enemyGroup));
		}if(gameTimer.action(elapsedTime)){
			long delay = spawn.getDelay()/2;
			if(delay<500){
				spawn.setDelay(SECOND/2);
			}else{
				spawn.setDelay(delay);
			}
			if(delay > 5000){
				spawnSpeed-=10;
			}else if(delay > 501){
				spawnSpeed = spawnSpeed;
			}else{
				spawnSpeed+=10;
			}
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		background[difficulty].render(g);
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
	
	protected void begin(){		
		
		
		hit1 = new Timer(SECOND * 5);
		hit2 = new Timer(SECOND * 6);
		hit3 = new Timer(SECOND * 7);
		spawn = new Timer(SECOND*45);
		gameTimer = new Timer(SECOND * 45);
		spawnSpeed = 80;
		
		play.removeGroup(enemyGroup);
		enemyGroup = play.addAndReturnGroup(new SpriteGroup("Enemy Group"));
		
		play.removeGroup(towerGroup);
		towerGroup = play.addAndReturnGroup(new SpriteGroup("Tower Group"));
		
		play.removeGroup(towerShotGroup);
		towerShotGroup = play.addAndReturnGroup(new SpriteGroup("Tower Shot Group"));

		selfEsteem.setStat(100);
		score.setStat(0);
		money.setStat(100);
		
		createPath();
		totalTime = 0;
		go = false;
		
		counter = new Counter();
		counter.setLocation(350, HEIGHT/2-75);
		playerGroup.add(counter);
		
		stateManager.switchTo(play);
	}
	

}
