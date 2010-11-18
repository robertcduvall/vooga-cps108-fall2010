package vooga.games.grandius;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.overlay.OverlayIcon;
import vooga.engine.overlay.OverlayPanel;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayStatImage;
import vooga.engine.overlay.OverlayString;
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
import vooga.games.grandius.weapons.Missile;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Grandius is a side-scrolling space shooter. The object of each level is to destroy all enemies. The player is the red ship on the
 * left side of the screen, and he or she can use various weapons to destroy enemies. The "boss" of each level can only be destroyed once
 * all 5 "mini-bosses" have been destroyed.
 * @author Se-Gil Feldsott, John Kline, Bhawana Singh 
 * @version 3.0
 */
public class DropThis extends Game {

	//private Collection<GameState> gameStates; //TODO is this Collection needed?
	private GrandiusMenuState myMenuState;
	private PlayState myPlayState;
	private LevelCompleteState myLevelCompleteState;
	private ShoppingLevelState myShoppingLevelState;
	private StartNewLevelState myStartNewLevelState;
	private GameCompleteState myGameCompleteState;
	private GameOverState myGameOverState;
	private LevelManager levelManager;
	private EventPool eventPool;
//	private PauseGameState myPauseGameState;

	//private boolean reacherShieldsDepleted;

	private Sprite shipSprite;
	//private PlayerSprite playerSprite;
	private Player player;

	private OverlayPanel overlayPanel;
	private OverlayStatImage livesIcon;
	private Stat<Integer> statLives;
	private Stat<Integer> statScore;
	private Stat<Integer> statCash;

//	private GrandiusLevelManager levelManager;
	private GameFont font;
	private double playerInitialX;
	private double playerInitialY;

	private Dimension screen;

	//TODO: Good practice here? Use Missile/BlackHole classes?
	private boolean missileActive =         false;
	private boolean blackHoleActive =       false;

	//Cheat options
	private boolean isInvincible = false;
	private boolean skipLevel = false;

	@Override
	public void initResources() {
		super.initResources();
		initLevelManager();
		initEvents();
		
		createCollisions(); //TODO will this work here?
		//levelManager = new GrandiusLevelManager();
		//levelManager.makeLevels(Resources.getString("levelFilesDirectory"),Resources.getString("levelNamesFile"));
		
		//TODO - change to work with new Overlay xml
		font = fontManager.getFont(getImages("resources/images/font.png", 20, 3),
				" !            .,0123" +
				"456789:   -? ABCDEFG" +
		"HIJKLMNOPQRSTUVWXYZ ");

		overlayPanel = new OverlayPanel("GrandiusOverlay", this, true);
		statLives = new Stat<Integer>(new Integer(Resources.getInt("InitialPlayerLives")));
		statScore = new Stat<Integer>(new Integer(Resources.getInt("InitialZero"))); //TODO get rid of this var?
		statCash = new Stat<Integer>(new Integer(Resources.getInt("InitialZero")));
		int screenWidth = Resources.getInt("screenWidth");
		int screenHeight = Resources.getInt("screenHeight");
		screen = new Dimension(screenWidth,screenHeight);
		playerInitialX = Resources.getInt("PlayerInitialX");
		playerInitialY = Resources.getInt("PlayerInitialY");
		livesIcon = new OverlayStatImage(Resources.getImage("PlayerShipSingle"));
		levelManager.getLevelFactory().setBackground(new ImageBackground(Resources.getImage("BG"), 640, 480));
		shipSprite = new Sprite(Resources.getImage("PlayerShipSingle"),playerInitialX,playerInitialY);
		playerSprite = new PlayerSprite("ThePlayer", "alive", shipSprite);
		playerGroup.add(playerSprite);
		backgroundGroup.add(new Sprite(Resources.getImage("BG")));
		reacherShieldsDepleted = false;
		addOverlays();
	}

	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new FireHorizontalEvent(this,player,myPlayState));
		eventPool.addEvent(new FireVerticalEvent(this,player,myPlayState));
	}

	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}
	
	@Override
	public void initGameStates() {
		super.initGameStates();
		List<GameState> gameStates = new ArrayList<GameState>();
		GameState[] gameStatesArray = new GameState[gameStates.size()];
		gameStates.add(myMenuState = new GrandiusMenuState()); //Default GameState is menu.
		gameStates.add(myPlayState = new PlayState());
		gameStates.add(myLevelCompleteState = new LevelCompleteState());
		gameStates.add(myGameCompleteState = new GameCompleteState());
		gameStates.add(myShoppingLevelState = new ShoppingLevelState());
		gameStates.add(myStartNewLevelState = new StartNewLevelState());
		gameStates.add(myGameOverState = new GameOverState());
		//gameStates.add(myPauseGameState = new PauseGameState());
		for (int i = 0; i < gameStates.size(); i++) {
			gameStatesArray[i] = gameStates.get(i);
		}
		stateManager.addGameState(gameStatesArray);
		stateManager.switchTo(myMenuState); //TODO is this needed if menu is default?
	}

	//TODO this GameState should be implemented using a MenuGameState
//	private void buildShoppingLevelState() {
//		shoppingLevelState = new GameState();
//		int displayX = Resources.getInt("shoppingLevelX");
//		int displayY = Resources.getInt("shoppingLevelY");
//
//		//shoppingLevel1 is an OverlayStat vs. String (displays Stat)
//		OverlayStat shoppingLevel1 = new OverlayStat("CASH: ", statCash);
//		shoppingLevel1.setFont(font);
//		shoppingLevel1.setLocation(displayX,displayY);
//		shoppingLevelGroup.add(shoppingLevel1);
//
//		for(int i=2;i<5;i++){
//			displayY=displayY*i;
//			OverlayString shoppingLevel = new OverlayString(Resources.getString("shoppingLevel"+i), font);
//			shoppingLevel.setLocation(displayX, displayY);
//			shoppingLevelGroup.add(shoppingLevel);
//		}
//		shoppingLevelState.addGroup(backgroundGroup);
//		shoppingLevelState.addGroup(shoppingLevelGroup);
//	}

	private void buildLevelCompleteState() {
		levelCompleteState = new GameState();
		int displayX = Resources.getInt("levelCompleteX");
		int displayY = Resources.getInt("levelCompleteY");

		OverlayString levelComplete1 = new OverlayString("LEVEL " + (levelManager.getCurrentLevel()+1) + " COMPLETE", font);
		levelComplete1.setLocation(displayX,displayY);
		OverlayString levelComplete2 = new OverlayString(Resources.getString("levelCompleteMessage"), font);
		levelComplete2.setLocation(displayX,2*displayY);

		levelCompleteState.addGroup(backgroundGroup);
		levelCompleteGroup.add(levelComplete1);
		levelCompleteGroup.add(levelComplete2);
		levelCompleteState.addRenderGroup(levelCompleteGroup);
	}

	//TODO This GameState should be implemented using a MenuGameState
//	private void buildMenuState() {
//		menuState = new GameState();
//		menuState.addGroup(backgroundGroup);
//		for(int i=1;i<9;i++){
//			OverlayString menu = new OverlayString(Resources.getString("menu"+i), font);
//			menu.setLocation(Resources.getInt("menuX"), Resources.getInt("menuY")*i);
//			menuGroup.add(menu);
//		}               
//		menuState.addRenderGroup(menuGroup);
//	}

	/**
	 * Creates different types of collisions and registers them to the playfield.
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

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void update(long elapsedTime) {
		eventPool.checkEvents(); // TODO - is this the right place?
		if(levelManager.getCurrentLevel()==0){
			PlayField playfield=levelManager.loadNextLevel();
			playfield.setBackground(levelManager.getBackground());
			for(SpriteGroup group: playfield.getGroups()){
				playfield.addGroup(group);
			}
		}
		super.update(elapsedTime);
		if (menuState.isActive()){
			if(click()){
				stateManager.switchTo(playState);
				playSound(Resources.getSound("WatchThisSound"));
				playSound(Resources.getSound("StartLevelSound"));
			}
		}

		if (playState.isActive()) {
			fireWeapon();
			updateEntities();
			checkCheats();
			checkBossParts();
			if (checkLevelComplete()) {
				playfield.clearPlayField();
				stateManager.switchTo(levelCompleteState);
			}
			playfield.update(elapsedTime);
			if (statLives.getStat().intValue() <= 0) {
				playfield.clearPlayField();
				stateManager.switchTo(gameOverState);
				playSound(Resources.getSound("OhManSound"));
			}
		}

		if (levelCompleteState.isActive()){
			if(levelManager.getCurrentLevel()==LAST_LEVEL){
				playfield.clearPlayField();
				stateManager.switchTo(gameCompleteState);
			}
			else if(click()){
				playfield.clearPlayField();
				stateManager.switchTo(shoppingLevelState);
			}
		}

		if (shoppingLevelState.isActive()){
			//TODO factor out switch to out of if statement
			//                      SHOPPING_LEVEL_GROUP.update(elapsedTime);
			int displayX = Resources.getInt("shoppingLevelX");
			int displayY = Resources.getInt("shoppingLevelY");
			if(click()){
				System.out.println("Made it here");
				if((this.getMouseX()>displayX)){
					if((this.getMouseY()>displayY*2) &&
							(this.getMouseY()<displayY*3)){
						missileActive = true;
						updateStat(statCash,-500);
						playfield.clearPlayField();
						stateManager.switchTo(startNewLevelState);
					}
					else if((this.getMouseY()>displayY*3) &&
							(this.getMouseY()<displayY*4)){
						blackHoleActive = true;
						updateStat(statCash,-1000);
						playfield.clearPlayField();
						stateManager.switchTo(startNewLevelState);
					}
				}
			}
			if (keyPressed(KeyEvent.VK_SPACE)){
				stateManager.switchTo(startNewLevelState);
			}
		}

		if (startNewLevelState.isActive()){
			PlayField playfield=levelManager.loadNextLevel();
			playfield.clearPlayField();
			playfield.setBackground(levelManager.getBackground());
			for(SpriteGroup group: playfield.getGroups()){
				playfield.addGroup(group);
			}
			playerGroup.add(playerSprite);
			createComets();
			playfield.addGroup(overlayPanel);
			playSound(Resources.getSound("StartLevelSound"));
			stateManager.switchTo(playState);
		}
		//stateManager.update(elapsedTime);
	}

	/**
	 * Updates the various enemies that are on screen.
	 */
	private void updateScreenSprites() {
		ArrayList<ArrayList<Sprite>> currentSprites = levelManager.currentLevel();
		updateSpriteGroup(enemyGroup, currentSprites,0);
		updateSpriteGroup(bossPartGroup, currentSprites, 1);
		updateSpriteGroup(bossGroup, currentSprites, 2);
	}

	/**
	 * Utility method used in updateScreenSprites().
	 */
	private void updateSpriteGroup(SpriteGroup spriteGroup, ArrayList<ArrayList<Sprite>> currentSprites,  int index) {
		spriteGroup.clear();
		Collection<Sprite> screenSprites = currentSprites.get(index);
		for (Sprite s: screenSprites) {
			if (s==null) 
				break;
			spriteGroup.add(s);
		}
	}

	/**
	 * Use keys to fire weapons:
	 * ALT: Fire horizontal regular projectile.
	 * SPACE: Fire vertical regular projectile.
	 * M: Fire missile, if it has been purchased.
	 * B: Create Black Hole, if it has been purchased.
	 */
	private void fireWeapon() {
		//Replaced by event classes FireHorizontalEvent and FireVerticalEvent
//		if (keyPressed(KeyEvent.VK_ALT)) {             
//			Sprite projectile = new Sprite(Resources.getImage("Projectile"),playerSprite.getX()+playerSprite.getWidth(),playerSprite.getY());
//			projectile.setHorizontalSpeed(PROJECTILE_SPEED);
//			projectileGroup.add(projectile);
//			playSound(Resources.getSound("LaserSound"));
//		}
//		if (keyPressed(KeyEvent.VK_SPACE)){
//			Sprite projectile = new Sprite(Resources.getImage("ProjectileVertical"),playerSprite.getX()+playerSprite.getWidth(),playerSprite.getY());
//			projectile.setVerticalSpeed(PROJECTILE_SPEED);
//			projectileGroup.add(projectile);
//			playSound(Resources.getSound("LaserSound"));
//		}
		if (keyPressed(KeyEvent.VK_M) && missileActive) {  
			Missile missile = new Missile(Resources.getImage("Missile"),playerSprite.getX()+playerSprite.getWidth(),playerSprite.getY());
			missile.setHorizontalSpeed(PROJECTILE_SPEED);
			missileGroup.add(missile);
			playSound(Resources.getSound("MissileSound"));
		}
		if (keyPressed(KeyEvent.VK_B) && blackHoleActive) {  
			BlackHole blackHole = new BlackHole(Resources.getImage("BlackHole"),playerSprite.getX()+playerSprite.getWidth(),playerSprite.getY());
			blackHoleGroup.add(blackHole);
			playSound(Resources.getSound("MissileSound"));
		}

	}

	private void updateEntities() {
		updateScreenSprites();
		playerSprite.setVerticalSpeed(0);
		for (Sprite as: enemyGroup.getSprites()) {
			if (as == null) 
				break;
			if (as instanceof Zipster) {
				if (((Zipster)(as)).willFire(playerSprite)) {
					enemyProjectileGroup.add(((Zipster)(as)).fireLaser());
					playSound(Resources.getSound("ZipsterLaserSound"));
				}
				as.setHorizontalSpeed(-((Zipster)(as)).getSpeed());
				((Zipster) as).setImages(new BufferedImage[]{Resources.getAnimation("SpinningZipster")[((Zipster) as).getSpin()]});
				if (!((Zipster) as).isProximateToBlackHole())
					((Zipster) as).setSpin(0);
				((Zipster)as).setProximateToBlackHole(false);
			}
		}
		for (Sprite bp: bossPartGroup.getSprites()) {
			if (bp == null) 
				break;
			if (bp instanceof ReacherEye) {
				if (((ReacherEye)(bp)).willFire(playerSprite)) {
					enemyProjectileGroup.add(((ReacherEye)(bp)).fireBeam());
					playSound(Resources.getSound("ReacherEyeBeamSound"));
				} 
				bp.setHorizontalSpeed(-((ReacherEye)(bp)).getSpeed());
			}
		}
		for (Sprite b: bossGroup.getSprites()) {
			if (b == null) 
				break;
			if (b instanceof Reacher) {
				if (((Reacher)(b)).topBeamWillFire(playerSprite)) {
					enemyProjectileGroup.add(((Reacher)(b)).fireTopBeam());
					playSound(Resources.getSound("ReacherBeamSound"));
				}
				if (((Reacher)(b)).bottomBeamWillFire(playerSprite)) {
					enemyProjectileGroup.add(((Reacher)(b)).fireBottomBeam());
					playSound(Resources.getSound("ReacherBeamSound"));
				} 
				if (((Reacher)(b)).redRayWillFire(playerSprite)) {
					enemyProjectileGroup.add(((Reacher)(b)).fireRedRay());
					playSound(Resources.getSound("ReacherRedRaySound"));
				} 
				b.setHorizontalSpeed(-((Reacher)(b)).getSpeed());
			}
		}

		double bulletSpeed = Resources.getDouble("bulletSpeed");
		resetSpriteSpeed(projectileGroup, bulletSpeed);
		resetSpriteSpeed(enemyProjectileGroup, -1*bulletSpeed);
		resetSpriteSpeed(missileGroup, bulletSpeed);

		for (Sprite h: blackHoleGroup.getSprites()) {
			if (h == null) 
				break;
			if (h.isActive()) {
				((BlackHole)h).suckEnemies(enemyGroup);
				((BlackHole)h).setPlayerCompensationSpeed(0);
			}
		}

		if (keyDown(KeyEvent.VK_LEFT)){
			for (SpriteGroup sg: spriteGroupSpeedMap.keySet()) {
				moveSpriteGroup(sg, "right", spriteGroupSpeedMap.get(sg));
			}
			for (Sprite h: blackHoleGroup.getSprites()) {
				if (h == null)
					break;
				((BlackHole)h).setPlayerCompensationSpeed(1*PLAYER_SPEED);
			}
		}
		if (keyDown(KeyEvent.VK_RIGHT)){
			for (SpriteGroup sg: spriteGroupSpeedMap.keySet()) {
				moveSpriteGroup(sg, "left", spriteGroupSpeedMap.get(sg));
			}
			for (Sprite h: blackHoleGroup.getSprites()) {
				if (h == null)
					break;
				((BlackHole)h).setPlayerCompensationSpeed(-1*PLAYER_SPEED);
			}
		}
		if (keyDown(KeyEvent.VK_DOWN)){
			playerSprite.setVerticalSpeed(PLAYER_SPEED);
		}
		if (keyDown(KeyEvent.VK_UP)){
			playerSprite.setVerticalSpeed(-1*PLAYER_SPEED);
		}
	}

	private void resetSpriteSpeed(SpriteGroup spriteGroup, double newSpeed) {
		for (Sprite s: spriteGroup.getSprites()) {
			if (s == null)
				break;
			s.setHorizontalSpeed(newSpeed);
		}
	}

	/**
	 * Utility method used in updateEntities().
	 */
	private void moveSpriteGroup(SpriteGroup spriteGroup, String direction, double offset) {
		if (direction.equals("right")) {
			for (Sprite s: spriteGroup.getSprites()) {
				if (s == null) 
					break;
				s.setHorizontalSpeed(1*PLAYER_SPEED+offset);
			}
		} else if (direction.equals("left")) {
			for (Sprite s: spriteGroup.getSprites()) {
				if (s == null) 
					break;
				s.setHorizontalSpeed(-1*PLAYER_SPEED+offset);
			}
		}
	}

	private void checkBossParts() {
		if (bossGroup.isActive()) {
			//Change this method to accomodate more than one level
			Sprite reacherSprite = bossGroup.getSprites()[0];
			int reacherEyesDestroyed = 0;
			for (Sprite bp: bossPartGroup.getSprites()) {
				if (bp==null)
					break;
				if (bp instanceof ReacherEye && !bp.isActive())
					reacherEyesDestroyed++;
			}
			if (reacherEyesDestroyed == 1) {
				((Reacher)(reacherSprite)).setImages(new BufferedImage[]{Resources.getAnimation("Reacher")[1]});
			} else if (reacherEyesDestroyed == 3) {
				((Reacher)(reacherSprite)).setImages(new BufferedImage[]{Resources.getAnimation("Reacher")[2]}); 
			} else if (reacherEyesDestroyed == 5 && !reacherShieldsDepleted) {
				//Deplete shields, but do not reset to the "depleted shields" image more than once (the next
				//images should be of the Reacher's status becoming yellow, then red)
				((Reacher)(reacherSprite)).setImages(new BufferedImage[]{Resources.getAnimation("Reacher")[3]});
				reacherShieldsDepleted = true;
				((Reacher)(reacherSprite)).setVulnerable(true);
			}
		}
	}

	/**
	 * Checks to see if a level has been completed (all the enemies have been cleared, or the user has used the
	 * skip level cheat code.
	 */
	private boolean checkLevelComplete() {
		if(skipLevel)
		{
			skipLevel = false;
			return true;
		}
		for (int i = 0; i < 3; i++) {
			for (Sprite s: levelManager.currentLevel().get(i)) {
				if (s.isActive()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method for adding a value (including negative ones)
	 * to any Stat<Integer>, primarily for the lives, cash,
	 * and score counters.
	 */
	public void updateStat(Stat<Integer> stat, int addedValue)
	{
		int myCurrentStat = stat.getStat();
		//Can't go below 0
		if(myCurrentStat + addedValue > 0)
			stat.setStat(new Integer(stat.getStat().intValue()+addedValue));
		else
			stat.setStat(new Integer(0));
	}

	/**
	 * Decrements the number of lives the player has.
	 * Called when Player collides with enemy or an
	 * enemy projectile.
	 */
	public void updatePlayerLives(){
		int playerLives = (statLives.getStat()).intValue();
		if(playerLives > 0  && !isInvincible){
			updateStat(statLives, (-1));
			playerSprite.setLocation(playerInitialX, playerInitialY);
		}
	}

	/**
	 * Adds the given points to the Stat score.
	 */
	public void updateScoreOnCollision(int points) {
		updateStat(statScore, points);
	}

	/**
	 * Adds the given cash amounts to the Stat cash.
	 */
	public void updateCashOnCollision(int cash) {
		updateStat(statCash, cash);
	}

	private void addOverlays() {
		OverlayIcon livesCounter = new OverlayIcon(statLives, livesIcon, "Lives");
		OverlayStat scoreCounter = new OverlayStat("Score", statScore);
		OverlayStat cashCounter  = new OverlayStat("Cash", statCash);

		overlayPanel.add(livesCounter);
		overlayPanel.add(cashCounter);              
		overlayPanel.add(scoreCounter);
		overlayPanel.initialize();

		playfield.addGroup(overlayPanel);
	}

	private void checkCheats() {
		if(keyPressed(KeyEvent.VK_ENTER))
		{
			JFrame frame = new JFrame();
			String userInput = (String)JOptionPane.showInputDialog(frame,
					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
			if(userInput.equals(Resources.getString("Invincibility")))
				isInvincible = true;
			else if(userInput.equals(Resources.getString("SkipLevel")))
				skipLevel = true;
			else if(userInput.equals(Resources.getString("ExtraPoints")))
				updateStat(statScore, 1000000);
			else if(userInput.equals(Resources.getString("ExtraCash")))
				updateStat(statCash, 5000);
			else if(userInput.equals(Resources.getString("ActivateMissile")))
				missileActive = true;
		}
	}

	public static void main(String[] args) {
		launch(new DropThis());
	}
}

