package vooga.games.towerdefense;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.towerdefense.states.GameOverState;
import vooga.games.towerdefense.states.MainMenu;
import vooga.games.towerdefense.states.PauseState;
import vooga.games.towerdefense.states.PlayState;




/**
 * This is the main class of the game. It creates the different game states,
 * sets the beginning game state, and loads overlays which are common to 
 * multiple game states. Initializing the resources, launching the game window,
 * updating, and rendering are handled by the superclass Game.
 * 
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 * 
 */
public class Blah extends Game {

	private int gameWidth;
	private int gameHeight;
	private LevelManager levelManager;
	

//	private PlayerCursorControl playerCursorControl, menuPlayerCursorControl,
//			gameOverPlayerCursorControl;
//	private KeyboardControl playerKeyboardControl;
//	protected Background[] background;
//	private SpriteGroup towerGroup, enemyGroup, towerShotGroup, overlayGroup,
//			pauseGroup, playerGroup, gameOverGroup, menuGroup;
//	private ArrayList<PathPoint> path;
//	private long totalTime;
//	private Stat<Integer> selfEsteem;
//	private Stat<Integer> score;
//	private Stat<Integer> money;
//	protected State startMenu;
//	protected State play;
//	protected State pause;
//	protected State gameOver;
//	private int difficulty;
//	private boolean go;
//	private Counter counter;
//	private Timer hit1;
//	private Timer hit2;
//	private Timer hit3;
//	private Timer spawn;
//	private Timer gameTimer;
//	private int spawnSpeed;
//	private TowerBuilder towerBuilder;
//	private TowerTargetFinder towerTargetFinder;

	@Override
	public void initResources() {
		super.initResources();
		gameWidth = Resources.getInt("gameWidth");
		gameHeight = Resources.getInt("gameHeight");
	}
	
	@Override
	public void initGameStates(){
		OverlayTracker tracker = initOverlays();
		initLevelManager();
		super.initGameStates();
		
		//TODO uncomment once prolog error is found and fixed
		GameState mainMenu = addGameState(new MainMenu());
		PlayState play = (PlayState) addGameState(new PlayState(tracker));
		GameState pause = addGameState(new PauseState(play, tracker));
		addGameState(new GameOverState());
		getGameStateManager().switchTo(mainMenu);
	}

private OverlayTracker initOverlays() {
		OverlayCreator.setGame(this);
		return OverlayCreator.createOverlays(Resources.getString("overlayPath"));
	}

/**
 * Initialize the LevelManager for TowerDefence.
 */
private void initLevelManager() {
	 levelManager = new LevelManager(this);
	 String levelFilesDirectory = Resources.getString("levelFilesDirectory");
	 String levelNamesFile = Resources.getString("levelNamesFile");
	 levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
}


//
//	@Override
//	public void update(long elapsedTime) {
//		if (keyPressed(KeyEvent.VK_P)) {
//			if (play.isActive())
//				getGameStateManager().switchTo(pause);
//			else if (pause.isActive())
//				getGameStateManager().switchTo(play);
//		}
//		super.update(elapsedTime);
//
//		if (play.isActive() && selfEsteem.getStat() <= 0) {
//			getGameStateManager().switchTo(gameOver);
//		}
//
//		if (go) {
//			createEnemies(elapsedTime);
//		} else if (totalTime < 12000) {
//			totalTime += elapsedTime;
//		} else {
//			go = true;
//			playerGroup.remove(counter);
//		}
//	}
//
//	@Override
//	public void render(Graphics2D g) {
//		background[difficulty].render(g);
//		super.render(g);
//	}
//
//	@Override
//	public void initGameStates() {
//		super.initGameStates();
//		startMenu = new State();
//		play = new State();
//		pause = new State();
//		gameOver = new State();
//
//		getGameStateManager().addGameState(startMenu);
//		getGameStateManager().addGameState(play);
//		getGameStateManager().addGameState(pause);
//		getGameStateManager().addGameState(gameOver);
//
//		playerGroup = play.addAndReturnGroup(new SpriteGroup("Player Group"));
//
//		Sprite pauseScreen = new Sprite(Resources.getImage("pause"));
//		pauseGroup = pause.addAndReturnGroup(new SpriteGroup("Pause Group"));
//		pauseGroup.add(pauseScreen);
//
//		gameOverGroup = gameOver.addAndReturnGroup(new SpriteGroup(
//				"Game Over Group"));
//
//		Sprite menuSprite = new Sprite(ImageUtil.resize(Resources
//				.getImage("menu"), gameWidth, gameHeight));
//		menuGroup = startMenu.addAndReturnGroup(new SpriteGroup("Menu Group"));
//		menuGroup.add(menuSprite);
//
//		getGameStateManager().switchTo(startMenu);
//	}
//
//	private void initBackgrounds() {
//		background = new Background[3];
//		background[EASY] = new ImageBackground(ImageUtil.resize(Resources
//				.getImage("easyLevelBG"), gameWidth, gameHeight), gameWidth, gameHeight);
//		background[MEDIUM] = new ImageBackground(ImageUtil.resize(Resources
//				.getImage("mediumLevelBG"), gameWidth, gameHeight), gameWidth, gameHeight);
//		background[HARD] = new ImageBackground(ImageUtil.resize(Resources
//				.getImage("hardLevelBG"), gameWidth, gameHeight), gameWidth, gameHeight);
//	}
//
//	private void initEvents() {
//		towerBuilder = new TowerBuilder();
//		towerBuilder.setGame(this);
//		towerTargetFinder = new TowerTargetFinder();
//		towerTargetFinder.setGame(this);
//		SingletonEventManager.addEventListener("BuildTowerEvent", towerBuilder);
//		SingletonEventManager.addEventListener("NeedsTargetsEvent",
//				towerTargetFinder);
//	}

//	private void initOverlays() {
//		OverlayCreator.setGame(this);
//		OverlayTracker track = OverlayCreator
//				.createOverlays("src/vooga/games/towerdefense/resources/overlays.xml");
//		overlayGroup = track.getOverlayGroups("play");
//		gameOverGroup = track.getOverlayGroup("gameOver");
//		gameOverGroup.add(0, new Sprite(ImageUtil.resize(Resources
//				.getImage("gameOver"), gameWidth, gameHeight)));
//		selfEsteem = track.getStat("selfEsteem" , new Integer(0));
//		score = track.getStat("score" , new Integer(0));
//		money = track.getStat("money" , new Integer(0));
//
//		play.addGroup(overlayGroup);
//		gameOver.addGroup(gameOverGroup);
//		pause.addGroup(overlayGroup);
//	}
//
//	private void initPlayer() {
//
//		PlayerSprite player = new PlayerCursor("player", "playerCursor",
//				new Sprite(Resources.getImage("towerPreview")), this, money,
//				getGameStateManager());
//
//		playerGroup.add(player);
//		playerCursorControl = (PlayerCursorControl) play
//				.addControl(new PlayerCursorControl(player, this));
//		playerKeyboardControl = (KeyboardControl) play
//				.addControl(new KeyboardControl(player, this));
//		playerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
//				"vooga.games.towerdefense.PlayerCursor");
//		playerKeyboardControl.setParams(new Class[] { Tower.class });
//		playerKeyboardControl.addInput(KeyEvent.VK_1, "changeTowerType",
//				"vooga.games.towerdefense.PlayerCursor", new Normal(0, 0));
//		playerKeyboardControl.setParams(new Class[] { Tower.class });
//		playerKeyboardControl.addInput(KeyEvent.VK_2, "changeTowerType",
//				"vooga.games.towerdefense.PlayerCursor", new Fast(0, 0));
//		playerKeyboardControl.setParams(new Class[] { Tower.class });
//		playerKeyboardControl.addInput(KeyEvent.VK_3, "changeTowerType",
//				"vooga.games.towerdefense.PlayerCursor", new Sniper(0, 0));
//
//		menuGroup.add(player);
//		menuPlayerCursorControl = (PlayerCursorControl) startMenu
//				.addControl(new PlayerCursorControl(player, this));
//		menuPlayerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
//				"vooga.games.towerdefense.PlayerCursor");
//
//		gameOverGroup.add(player);
//		gameOverPlayerCursorControl = (PlayerCursorControl) gameOver
//				.addControl(new PlayerCursorControl(player, this));
//		gameOverPlayerCursorControl.addInput(MouseEvent.BUTTON1, "onClick",
//				"vooga.games.towerdefense.PlayerCursor");
//
//	}
//
//	private void createPath() {
//		path = new ArrayList<PathPoint>();
//		try {
//			File thisLevel;
//			if (difficulty == EASY) {
//				thisLevel = new File(
//						"src/vooga/games/towerdefense/resources/levels/easy.txt");
//			} else if (difficulty == MEDIUM) {
//				thisLevel = new File(
//						"src/vooga/games/towerdefense/resources/levels/medium.txt");
//			} else {
//				thisLevel = new File(
//						"src/vooga/games/towerdefense/resources/levels/hard.txt");
//			}
//			Scanner sc = new Scanner(thisLevel);
//			while (sc.hasNextInt()) {
//				int x = sc.nextInt();
//				if (sc.hasNextInt()) {
//					int y = sc.nextInt();
//					path.add(new PathPoint(x, y));
//				}
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("failed to implement scanner");
//			System.exit(0);
//		}
//		;
//
//	}
//
//	private void createEnemies(long elapsedTime) {
//		if (hit1.action(elapsedTime)) {
//			enemyGroup.add(new Enemy(path, 50, 1, selfEsteem, score, money));
//		}
//		if (hit2.action(elapsedTime)) {
//			enemyGroup.add(new Enemy(path, 80, 2, selfEsteem, score, money));
//		}
//		if (hit3.action(elapsedTime)) {
//			enemyGroup.add(new Enemy(path, 40, 3, selfEsteem, score, money));
//		}
//		if (spawn.action(elapsedTime)) {
//			enemyGroup.add(new EnemySpawn(path, spawnSpeed, selfEsteem, score,
//					money, enemyGroup));
//		}
//		if (gameTimer.action(elapsedTime)) {
//			long delay = spawn.getDelay() / 2;
//			if (delay < 500) {
//				spawn.setDelay(SECOND / 2);
//			} else {
//				spawn.setDelay(delay);
//			}
//			if (delay > 5000) {
//				spawnSpeed -= 10;
//			} else if (delay < 501) {
//				spawnSpeed += 10;
//			}
//		}
//
//	}
//
//	protected ArrayList<PathPoint> getPath() {
//		return path;
//	}
//
//	public SpriteGroup getEnemyGroup() {
//		return this.enemyGroup;
//	}
//
//	public SpriteGroup getTowerGroup() {
//		return this.towerGroup;
//	}
//
//	public SpriteGroup getTowerShotGroup() {
//		return this.towerShotGroup;
//	}
//
//	public ArrayList<PathPoint> getPathPoints() {
//		return this.path;
//	}
//
//	public int getDifficulty() {
//		return difficulty;
//	}
//
//	public void setDifficulty(int difficulty) {
//		this.difficulty = difficulty;
//	}
//
//	protected void begin() {
//		hit1 = new Timer(SECOND * 5);
//		hit2 = new Timer(SECOND * 6);
//		hit3 = new Timer(SECOND * 7);
//		spawn = new Timer(SECOND * 45);
//		gameTimer = new Timer(SECOND * 45);
//		spawnSpeed = 80;
//
//		play.removeGroup(enemyGroup);
//		enemyGroup = play.addAndReturnGroup(new SpriteGroup("Enemy Group"));
//
//		play.removeGroup(towerGroup);
//		towerGroup = play.addAndReturnGroup(new SpriteGroup("Tower Group"));
//
//		play.removeGroup(towerShotGroup);
//		towerShotGroup = play.addAndReturnGroup(new SpriteGroup(
//				"Tower Shot Group"));
//
//		selfEsteem.reset();
//		score.reset();
//		money.reset();
//
//		createPath();
//		totalTime = 0;
//		go = false;
//
//		counter = new Counter();
//		counter.setLocation(350, gameHeight / 2 - 75);
//		playerGroup.add(counter);
//
//		getGameStateManager().switchTo(play);
//	}

	public static void main(String[] args) {
		launch(new Blah(), "player");
	}

	

}
