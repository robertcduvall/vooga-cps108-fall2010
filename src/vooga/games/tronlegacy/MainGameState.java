package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * 
 * @author BrentSodman
 * 
 *         to-do status list 
 *         level API - functional - create more complex levels
 *         player API - functional - improve computer ai (in essence, implement any ai whatsoever) 
 *         state API - functional - improve pause graphics
 *         overlay API - functional - track more stats? lives?
 *         resource API - functional - complete 
 *         collision API - deprecated 
 *         core.Game - complete 
 *         core.Sprite - pending changes to APIs
 */

public class MainGameState extends GameState {

	private final static int FPS = 100;
	private final static Point humanPlayerStartingLocation = new Point(48, 240);
	private final static Point computerPlayerStartingLocation = new Point(432,
			240);
	private final static String LEVEL_FILEPATH = "src/vooga/games/tronlegacy/resources/levels/";
	private final static Color BACKGROUND_COLOR = Color.BLACK;
	
	
	private LevelManager levelManager;
	private Stat<Integer> currentScore;
	private CyclePlayer humanPlayer;
	private CyclePlayer computerPlayer;
	private KeyboardControl keyboardControl;
	private Blah game;
	private SpriteGroup levelBlocks;
	private SpriteGroup playerBlocks;
	private SpriteGroup overlayGroup;
	private PlayField currentPlayField;
	private LinkedList<Sprite> blockQueue = new LinkedList<Sprite>();

	private int currentLevel = 1;

	public void initialize(Blah currentGame) {
		game = currentGame;
		
		initLevelManager();
		
		initialize();
	}
	
	private void initLevelManager() {
		levelManager = new LevelManager(game);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}

	public void initializePlayers() {
		
		
		Sprite humanSprite = new Sprite(Resources.getImage("humanPlayer"),
				humanPlayerStartingLocation.x, humanPlayerStartingLocation.y);
		Sprite computerSprite = new Sprite(
				Resources.getImage("computerPlayer"),
				computerPlayerStartingLocation.x,
				computerPlayerStartingLocation.y);

		humanPlayer = new CyclePlayer("humanPlayer", humanSprite);

		computerPlayer = new CyclePlayer("computerPlayer",
				computerSprite);

		resetPlayers();

		keyboardControl = new KeyboardControl(humanPlayer, game);
		keyboardControl = humanPlayer.addPlayerControl(keyboardControl,
													   KeyEvent.VK_UP,
													   KeyEvent.VK_DOWN,
													   KeyEvent.VK_LEFT,
													   KeyEvent.VK_RIGHT);
		
	}
	
	private void initializeOverlays() {
		OverlayTracker tracker = OverlayCreator
				.createOverlays("src/vooga/games/tronlegacy/resources/overlays.xml");

		//overlayGroup = tracker.getOverlayGroups().get(0);
		//currentScore = tracker.getStats().get(0);
	}

	public void deployLevel(int targetLevel) {
		currentPlayField = levelManager.skipToLevel(targetLevel);

		currentPlayField.setBackground(new ColorBackground(BACKGROUND_COLOR));

		resetPlayers();

		playerBlocks = new SpriteGroup("playerBlocks");
		levelBlocks = (currentPlayField.getGroup("levelBlockGroup"));		
		
		currentPlayField.addGroup(playerBlocks);
		currentPlayField.addGroup(overlayGroup);

		
		//currentPlayField.addCollisionGroup(players, null, new TronBounds(
		//		currentPlayField.getBackground()));
		
	}

	public void deployPlayerBlocks() {
		for (Sprite currentPlayer : currentPlayField.getGroup("humanPlayerGroup").getSprites()) {
			if (currentPlayer != null) {
				blockQueue.add(new Sprite(currentPlayer.getImage(),
						currentPlayer.getX(), currentPlayer.getY()));
			}
		}
		for (Sprite currentPlayer : currentPlayField.getGroup("computerPlayerGroup").getSprites()) {
			if (currentPlayer != null) {
				blockQueue.add(new Sprite(currentPlayer.getImage(),
						currentPlayer.getX(), currentPlayer.getY()));
			}
		}

		while (blockQueue.size() > FPS / 5) {
			playerBlocks.add(blockQueue.remove());
		}
	}

	public void resetPlayers() {
		humanPlayer.setLocation(humanPlayerStartingLocation.x,
				humanPlayerStartingLocation.y);
		computerPlayer.setLocation(computerPlayerStartingLocation.x,
				computerPlayerStartingLocation.y);
		
		humanPlayer.changeDirection("RIGHT");
		computerPlayer.changeDirection("LEFT");

		humanPlayer.setActive(true);
		computerPlayer.setActive(true);

		blockQueue.clear();
	}

	public void checkLevelStatus() {
		if (!humanPlayer.isActive()) {
			game.playSound(Resources.getSound("explosion"));
			game.gameOver();
		} else if (!computerPlayer.isActive()) {
			game.playSound(Resources.getSound("explosion"));
			currentLevel++;
			currentScore.setStat(currentScore.getStat() + 10);
			deployLevel(currentLevel);
		}

		if (humanPlayer.isPaused()) {
			humanPlayer.invokePause();
			game.togglePauseGame();
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		currentPlayField.render(g);

		// handles those few sprites behind the players that we don't want to
		// collide immediately upon creation
		for (Sprite currentSprite : blockQueue) {
			currentSprite.render(g);
		}
	}

	@Override
	public void update(long elapsedTime) {
		keyboardControl.update();
		deployPlayerBlocks();
		currentPlayField.update(elapsedTime);
		computerPlayer.aiUpdate(currentPlayField);
		checkLevelStatus();
	}

	@Override
	public void initialize() {

		initializePlayers();
		initializeOverlays();		
		deployLevel(currentLevel);
		
	}
	
}
