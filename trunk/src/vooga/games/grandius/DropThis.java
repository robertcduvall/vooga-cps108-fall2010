package vooga.games.grandius;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayIcon;
import vooga.engine.overlay.OverlayPanel;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayStatImage;
import vooga.engine.overlay.Stat;
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
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Zipster;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireVerticalEvent;
import vooga.games.grandius.states.GameCompleteState;
import vooga.games.grandius.states.GameOverState;
import vooga.games.grandius.states.GrandiusMenuState;
import vooga.games.grandius.states.LevelCompleteState;
import vooga.games.grandius.states.PlayState;
import vooga.games.grandius.states.ShoppingLevelState;
import vooga.games.grandius.states.StartNewLevelState;
import vooga.games.grandius.weapons.BlackHole;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.SpriteGroup;

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
	private EventPool eventPool;
	// private PauseGameState myPauseGameState;

	private BetterSprite shipSprite;
	private Player player;

	private OverlayPanel overlayPanel;
	//private OverlayStatImage livesIcon;
	private BufferedImage livesIcon;

	private GameFont font;

	private Dimension screen;

	@Override
	public void initResources() {
		super.initResources();
		initLevelManager();
		initEvents();
		createCollisions(); // TODO will this method call work here?

		// TODO - change to work with new Overlay xml
		font = fontManager.getFont(
				getImages("resources/images/font.png", 20, 3),
				" !            .,0123" + "456789:   -? ABCDEFG"
						+ "HIJKLMNOPQRSTUVWXYZ ");

		overlayPanel = new OverlayPanel("GrandiusOverlay", this, true);
		statLives = new Stat<Integer>(new Integer(
				Resources.getInt("InitialPlayerLives")));
		statScore = new Stat<Integer>(new Integer(
				Resources.getInt("InitialZero"))); // TODO get rid of this var?
		statCash = new Stat<Integer>(new Integer(
				Resources.getInt("InitialZero")));
		//int screenWidth = Resources.getInt("screenWidth");
		//int screenHeight = Resources.getInt("screenHeight");
		//screen = new Dimension(screenWidth, screenHeight);
		//livesIcon = new OverlayStatImage(Resources.getImage("PlayerShipSingle"));
		livesIcon = Resources.getImage("PlayerShipSingle");
//		reacherShieldsDepleted = false;
		addOverlays();
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
	 * Initialize the specific Events handled in Grandius.
	 */
	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new FireHorizontalEvent(this, player, myPlayState));
		eventPool.addEvent(new FireVerticalEvent(this, player, myPlayState));
	}

	/**
	 * Initialize the different GameStates possible in Grandius.
	 */
	@Override
	public void initGameStates() {
		super.initGameStates();
		List<GameState> gameStates = new ArrayList<GameState>();
		GameState[] gameStatesArray = new GameState[gameStates.size()];
		gameStates.add(myMenuState = new GrandiusMenuState(this)); // Default state is Menu.
		gameStates.add(myPlayState = new PlayState());
		gameStates.add(myLevelCompleteState = new LevelCompleteState());
		gameStates.add(myGameCompleteState = new GameCompleteState());
		gameStates.add(myShoppingLevelState = new ShoppingLevelState(this));
		gameStates.add(myStartNewLevelState = new StartNewLevelState());
		gameStates.add(myGameOverState = new GameOverState());
		// gameStates.add(myPauseGameState = new PauseGameState());
		for (int i = 0; i < gameStates.size(); i++) {
			gameStatesArray[i] = gameStates.get(i);
		}
		stateManager.addGameState(gameStatesArray);
		stateManager.switchTo(myMenuState); // TODO is this needed if menu is
											// default?
	}

	/**
	 * Creates different types of collisions and registers them to the
	 * Grandius PlayState.
	 */
	private void createCollisions() {
		List<BasicCollision> collisionList = new ArrayList<BasicCollision>();
		collisionList.add(new PlayerEnemyCollision(this));
		collisionList.add(new PlayerBossPartCollision(this));
		collisionList.add(new PlayerBossCollision(this));
		collisionList.add(new ProjectileEnemyCollision(this));
		collisionList.add(new ProjectileBossPartCollision(this));
		collisionList.add(new PlayerEnemyProjectileCollision(this));
		collisionList.add(new ProjectileBossCollision(this));
		collisionList.add(new MissileEnemyCollision(this));
		collisionList.add(new MissileBossPartCollision(this));
		collisionList.add(new MissileBossCollision(this));
		collisionList.add(new BlackHoleEnemyCollision(this));
		myPlayState.addCollisions(collisionList);
	}

	// @Override
	// public void update(long elapsedTime) {
	// if(levelManager.getCurrentLevel()==0){
	// PlayField playfield=levelManager.loadNextLevel();
	// playfield.setBackground(levelManager.getBackground());
	// for(SpriteGroup group: playfield.getGroups()){
	// playfield.addGroup(group);
	// }
	// }
	// super.update(elapsedTime);
	// if (menuState.isActive()){
	// if(click()){
	// stateManager.switchTo(playState);
	// playSound(Resources.getSound("WatchThisSound"));
	// playSound(Resources.getSound("StartLevelSound"));
	// }
	// }
	//
	// if (playState.isActive()) {
	// fireWeapon();
	// updateEntities();
	// checkCheats();
	// checkBossParts();
	// if (checkLevelComplete()) {
	// playfield.clearPlayField();
	// stateManager.switchTo(levelCompleteState);
	// }
	// playfield.update(elapsedTime);
	// if (statLives.getStat().intValue() <= 0) {
	// playfield.clearPlayField();
	// stateManager.switchTo(gameOverState);
	// playSound(Resources.getSound("OhManSound"));
	// }
	// }
	//
	// if (levelCompleteState.isActive()){
	// if(levelManager.getCurrentLevel()==LAST_LEVEL){
	// playfield.clearPlayField();
	// stateManager.switchTo(gameCompleteState);
	// }
	// else if(click()){
	// playfield.clearPlayField();
	// stateManager.switchTo(shoppingLevelState);
	// }
	// }
	//
	// if (shoppingLevelState.isActive()){
	// //TODO factor out switch to out of if statement
	// // SHOPPING_LEVEL_GROUP.update(elapsedTime);
	// int displayX = Resources.getInt("shoppingLevelX");
	// int displayY = Resources.getInt("shoppingLevelY");
	// if(click()){
	// System.out.println("Made it here");
	// if((this.getMouseX()>displayX)){
	// if((this.getMouseY()>displayY*2) &&
	// (this.getMouseY()<displayY*3)){
	// missileActive = true;
	// updateStat(statCash,-500);
	// playfield.clearPlayField();
	// stateManager.switchTo(startNewLevelState);
	// }
	// else if((this.getMouseY()>displayY*3) &&
	// (this.getMouseY()<displayY*4)){
	// blackHoleActive = true;
	// updateStat(statCash,-1000);
	// playfield.clearPlayField();
	// stateManager.switchTo(startNewLevelState);
	// }
	// }
	// }
	// if (keyPressed(KeyEvent.VK_SPACE)){
	// stateManager.switchTo(startNewLevelState);
	// }
	// }
	//
	// if (startNewLevelState.isActive()){
	// PlayField playfield=levelManager.loadNextLevel();
	// playfield.clearPlayField();
	// playfield.setBackground(levelManager.getBackground());
	// for(SpriteGroup group: playfield.getGroups()){
	// playfield.addGroup(group);
	// }
	// playerGroup.add(playerSprite);
	// createComets();
	// playfield.addGroup(overlayPanel);
	// playSound(Resources.getSound("StartLevelSound"));
	// stateManager.switchTo(playState);
	// }
	// //stateManager.update(elapsedTime);
	// }

//TODO handle key events

	//moved updatePlayerLives, updateScoreOnCollision, updateCashOnCollision to Player

	private void addOverlays() {
		OverlayIcon livesCounter = new OverlayIcon(player.getLives(), livesIcon,
				"Lives");
		OverlayStat scoreCounter = new OverlayStat("Score", player.getScore());
		OverlayStat cashCounter = new OverlayStat("Cash", player.getCash());

		overlayPanel.add(livesCounter);
		overlayPanel.add(cashCounter);
		overlayPanel.add(scoreCounter);
		overlayPanel.initialize();

		playfield.addGroup(overlayPanel);
	}

	private void checkCheats() {
		if (keyPressed(KeyEvent.VK_ENTER)) {
			JFrame frame = new JFrame();
			String userInput = (String) JOptionPane.showInputDialog(frame,
					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
			if (userInput.equals(Resources.getString("Invincibility")))
				player.setInvincible();
			else if (userInput.equals(Resources.getString("SkipLevel")))
				player.skipLevel();
			else if (userInput.equals(Resources.getString("ExtraPoints")))
				player.updateScore(1000000);
			else if (userInput.equals(Resources.getString("ExtraCash")))
				player.updateCash(5000);
			else if (userInput.equals(Resources.getString("ActivateMissile")))
				player.setMissileActive();
		}
	}

	public static void main(String[] args) {
		launch(new DropThis());
	}
}
