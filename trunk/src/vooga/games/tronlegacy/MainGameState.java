package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.util.LinkedList;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.core.Game;
import vooga.engine.event.EventManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * 
 * @author BrentSodman
 * 
 */

public class MainGameState extends GameState {

	private final static int FPS = 100;
	private final static Point humanPlayerStartingLocation = new Point(48, 240);
	private final static Point computerPlayerStartingLocation = new Point(432,
			240);
	private final static String LEVEL_FILEPATH = "src/vooga/games/tronlegacy/resources/levels/";

	private EventManager eventManager;
	private TLevelManager levelManager;

	private Stat<Integer> currentScore;

	private CyclePlayer humanPlayer;
	private CyclePlayer computerPlayer;
	private KeyboardControl keyboardControl;
	private Blah game;

	private SpriteGroup players;
	private SpriteGroup levelBlocks;
	private SpriteGroup playerBlocks;
	private SpriteGroup overlayGroup;
	private PlayField currentPlayField;

	private LinkedList<Sprite> blockQueue = new LinkedList<Sprite>();

	int currentLevel = 1;

	public void initialize(Blah aGame) {

		game = aGame;

		eventManager = new EventManager();
		levelManager = new TLevelManager();

		initializePlayers();
		initializeOverlays();
		levelManager.addLevels(LEVEL_FILEPATH, new File("levels.txt"));

		deployLevel();

	}

	public void render(Graphics2D g) {

		currentPlayField.render(g);

		// handles those few sprites behind the players that we don't want to
		// collide immediately upon creation
		for (Sprite currentSprite : blockQueue) {
			currentSprite.render(g);
		}

	}

	public void update(long elapsedTime) {

		keyboardControl.update();
		deployPlayerBlocks();
		currentPlayField.update(elapsedTime);
		computerPlayer.aiUpdate(currentPlayField);
		checkLevelStatus();

	}

	public void deployPlayerBlocks() {

		for (Sprite currentPlayer : players.getSprites()) {
			if (currentPlayer != null) {
				blockQueue.add(new Sprite(currentPlayer.getImage(),
						currentPlayer.getX(), currentPlayer.getY()));
			}
		}

		while (blockQueue.size() > FPS / 5) {
			playerBlocks.add(blockQueue.remove());
		}

	}

	public void initializePlayers() {

		Sprite humanSprite = new Sprite(Resources.getImage("humanPlayer"),
				humanPlayerStartingLocation.x, humanPlayerStartingLocation.y);
		Sprite computerSprite = new Sprite(
				Resources.getImage("computerPlayer"),
				computerPlayerStartingLocation.x,
				computerPlayerStartingLocation.y);

		humanPlayer = new CyclePlayer("humanPlayer", "gameplay", humanSprite,
				eventManager);

		computerPlayer = new CyclePlayer("computerPlayer", "gameplay",
				computerSprite, eventManager);

		resetPlayers();

		keyboardControl = new KeyboardControl(humanPlayer, game);
		keyboardControl = humanPlayer.addPlayerControl(keyboardControl);

		players = new SpriteGroup("players");
		players.add(humanPlayer);
		players.add(computerPlayer);

	}

	public void deployLevel() {

		currentPlayField = levelManager.getCurrentPlayField(new File(
				LEVEL_FILEPATH + "/level" + currentLevel + ".txt"));

		currentPlayField.setBackground(new ColorBackground(Color.WHITE));

		resetPlayers();

		playerBlocks = new SpriteGroup("playerBlocks");
		levelBlocks = (currentPlayField.getGroup("levelSprites"));

		currentPlayField.addGroup(players);
		currentPlayField.addGroup(playerBlocks);
		currentPlayField.addGroup(overlayGroup);

		currentPlayField.addCollisionGroup(players, players,
				new TronCollision());
		currentPlayField.addCollisionGroup(players, levelBlocks,
				new TronCollision());
		currentPlayField.addCollisionGroup(players, null, new TronBounds(
				currentPlayField.getBackground()));
		currentPlayField.addCollisionGroup(players, playerBlocks,
				new TronCollision());

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
			gameOver();
		} else if (!computerPlayer.isActive()) {
			currentLevel++;
			currentScore.setStat(currentScore.getStat() + 50);
			deployLevel();
		}

		if (humanPlayer.isPaused()) {
			game.togglePauseGame();
		}
	}

	public void gameOver() {

		// todo

	}

	private void initializeOverlays() {

		OverlayTracker tracker = OverlayCreator
				.createOverlays("src/vooga/games/tronlegacy/resources/overlays.xml");

		overlayGroup = tracker.getOverlayGroups().get(0);
		currentScore = tracker.getStats().get(0);

	}

}
