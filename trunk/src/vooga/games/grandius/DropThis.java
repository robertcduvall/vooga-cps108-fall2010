package vooga.games.grandius;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayPanel;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.grandius.collisions.BasicCollision;
import vooga.games.grandius.collisions.BlackHoleEnemyCollision;
import vooga.games.grandius.collisions.MissileBossCollision;
import vooga.games.grandius.collisions.MissileBossPartCollision;
import vooga.games.grandius.collisions.MissileEnemyCollision;
import vooga.games.grandius.collisions.PlayerBossCollision;
import vooga.games.grandius.collisions.PlayerBossPartCollision;
import vooga.games.grandius.collisions.PlayerEnemyCollision;
import vooga.games.grandius.collisions.PlayerEnemyProjectileCollision;
import vooga.games.grandius.collisions.ProjectileBossCollision;
import vooga.games.grandius.collisions.ProjectileBossPartCollision;
import vooga.games.grandius.collisions.ProjectileEnemyCollision;
import vooga.games.grandius.events.FireBlackHoleEvent;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.games.grandius.events.FireVerticalEvent;
import vooga.games.grandius.states.GameCompleteState;
import vooga.games.grandius.states.GameOverState;
import vooga.games.grandius.states.GrandiusMenuState;
import vooga.games.grandius.states.LevelCompleteState;
import vooga.games.grandius.states.PlayState;
import vooga.games.grandius.states.ShoppingLevelState;
import vooga.games.grandius.states.StartNewLevelState;

import com.golden.gamedev.object.GameFont;

/**
 * Grandius is a side-scrolling space shooter. The object of each level is to
 * destroy all enemies. The player is the red ship on the left side of the
 * screen, and he or she can use various weapons to destroy enemies. The "boss"
 * of each level can only be destroyed once all 5 "mini-bosses" have been
 * destroyed.
 * 
 * @author Se-Gil Feldsott, John Kline, Bhawana Singh
 * @version 3.0
 */
public class DropThis extends Game {

	private GrandiusMenuState myMenuState;
	private PlayState myPlayState;
	private LevelCompleteState myLevelCompleteState;
	private ShoppingLevelState myShoppingLevelState;
	private StartNewLevelState myStartNewLevelState;
	private GameCompleteState myGameCompleteState;
	private GameOverState myGameOverState;
	private LevelManager levelManager;
	// private PauseGameState myPauseGameState;

	private OverlayPanel overlayPanel;
	private OverlayTracker overlayTracker;
	//private OverlayStatImage livesIcon;
	//private BufferedImage livesIcon;

	private GameFont font;

	private Dimension screen;

	@Override
	public void initResources() {
		super.initResources();
		// TODO - change this to work with new Overlay XML
		font = fontManager.getFont(
				getImages("resources/images/font.png", 20, 3),
				" !            .,0123" + "456789:   -? ABCDEFG"
						+ "HIJKLMNOPQRSTUVWXYZ ");
		// TODO errors with overlays line>: addOverlays();
	}

	/**
	 * Initialize the LevelManager for Grandius.
	 */
	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
	}

	/**
	 * Initialize the different GameStates possible in Grandius.
	 */
	@Override
	public void initGameStates() {
		super.initGameStates();
		initLevelManager();
		List<GameState> gameStates = new ArrayList<GameState>();
		gameStates.add(myMenuState = new GrandiusMenuState(this)); // Default state.
		gameStates.add(myPlayState = new PlayState(levelManager, this));
		gameStates.add(myLevelCompleteState = new LevelCompleteState());
		gameStates.add(myGameCompleteState = new GameCompleteState());
		gameStates.add(myShoppingLevelState = new ShoppingLevelState(this));
		gameStates.add(myStartNewLevelState = new StartNewLevelState());
		gameStates.add(myGameOverState = new GameOverState());
		// gameStates.add(myPauseGameState = new PauseGameState());
		GameState[] gameStatesArray = new GameState[gameStates.size()];
		for (int i = 0; i < gameStates.size(); i++) {
			gameStatesArray[i] = gameStates.get(i);
		}
		stateManager.addGameState(gameStatesArray);
		stateManager.switchTo(myPlayState);
	}

//	/**
//	 * Creates different types of collisions and registers them to the
//	 * Grandius PlayState.
//	 */
//	private void createCollisions() {
//		List<BasicCollision> collisionList = new ArrayList<BasicCollision>();
//		collisionList.add(new PlayerEnemyCollision(this));
//		collisionList.add(new PlayerBossPartCollision(this));
//		collisionList.add(new PlayerBossCollision(this));
//		collisionList.add(new ProjectileEnemyCollision(this));
//		collisionList.add(new ProjectileBossPartCollision(this));
//		collisionList.add(new PlayerEnemyProjectileCollision(this));
//		collisionList.add(new ProjectileBossCollision(this));
//		collisionList.add(new MissileEnemyCollision(this));
//		collisionList.add(new MissileBossPartCollision(this));
//		collisionList.add(new MissileBossCollision(this));
//		collisionList.add(new BlackHoleEnemyCollision(this));
//		myPlayState.initCollisions(collisionList);
//	}

//	@Override
//	public void update(long elapsedTime) {
//		super.update(elapsedTime);
//	}

	//Moving to PlayState
//	private void addOverlays() {
//		overlayTracker = OverlayCreator.createOverlays("src/vooga/games/grandius/resources/overlays.xml");
//		overlayPanel = new OverlayPanel("GrandiusOverlay", this, true);
//		livesIcon = Resources.getImage("playerImage");
//		player.setLives(overlayTracker.getStat("lives"));
//		player.setScore(overlayTracker.getStat("score"));
//		player.setCash(overlayTracker.getStat("cash"));
////		OverlayStat scoreCounter = new OverlayStat("Score", player.getScore());
////		OverlayStat cashCounter = new OverlayStat("Cash", player.getCash());
//		overlayPanel.add(overlayTracker.getOverlay("lives"));
//		overlayPanel.add(overlayTracker.getOverlay("cash"));
//		overlayPanel.add(overlayTracker.getOverlay("score"));
//		overlayPanel.initialize();
//		PlayField newField = new PlayField();
//		newField.addGroup(overlayPanel);
//		myPlayState.getUpdateField().add(newField);
//		myPlayState.getRenderField().add(newField);
//	}

	//Moving to PlayState
//	private void checkCheats() {
//		if (keyPressed(KeyEvent.VK_ENTER)) {
//			JFrame frame = new JFrame();
//			String userInput = (String) JOptionPane.showInputDialog(frame,
//					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
//			if (userInput.equals(Resources.getString("invincibility")))
//				player.setInvincible();
//			else if (userInput.equals(Resources.getString("skipLevel")))
//				player.skipLevel();
//			else if (userInput.equals(Resources.getString("extraPoints")))
//				player.updateScore(1000000);
//			else if (userInput.equals(Resources.getString("extraCash")))
//				player.updateCash(5000);
//			else if (userInput.equals(Resources.getString("activateMissile")))
//				player.setMissileActive();
//		}
//	}

	//TODO could these two methods be removed somehow? they're used
	//to deal with collisions...
	public Player getPlayer() {
		return this.myPlayState.getPlayer();
	}
	
	public PlayState getPlayState() {
		return this.myPlayState;
	}
	
	public static void main(String[] args) {
		launch(new DropThis());
	}
}
