package vooga.games.grandius;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.player.control.*;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

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
import vooga.games.grandius.weapons.BlackHole;
import vooga.games.grandius.weapons.Missile;

/**
 * Grandius is a side-scrolling space shooter. The object of each level is to destroy all enemies. The player is the red ship on the
 * left side of the screen, and he or she can use various weapons to destroy enemies. The "boss" of each level can only be destroyed once
 * all 5 "mini-bosses" have been destroyed.
 * @author Se-Gil Feldsott, John Kline, Bhawana Singh 
 * @version 2.0
 */
public class Blah extends Game {

	private static final int PLAYER_INITIAL_X = 15;
	private static final int INITIAL_PLAYER_LIVES = 3;
	private static final int INITIAL_ZERO = 0;
	
	private Collection<GameState> gameStates;
	private GameState menuState;
	private GameState playState;
	private GameState levelCompleteState;
	private GameState shoppingLevelState;
	private GameState startNewLevelState;
	private GameState gameCompleteState;
	private GameState gameOverState;
	
	private GameStateManager gameStateManager;
	
	private static final int LAST_LEVEL = 3;
	private static final int NUM_COMETS = 1500;
	
	//TODO Make these part of actual enemy, boss, etc. classes?
	private static final double PROJECTILE_SPEED = 0.15;
	private static final double PLAYER_SPEED = 0.1;
	private static final double ENEMY_SPEED = 0.015;
	private static final double BOSS_PART_SPEED = 0.01;
	private static final double BOSS_SPEED = 0.005;
	
	private PlayField playfield;

	private boolean reacherShieldsDepleted;

	private SpriteGroup playerGroup;
	private SpriteGroup projectileGroup;
	private SpriteGroup enemyProjectileGroup;
	private SpriteGroup enemyGroup;
	private SpriteGroup bossPartGroup;
	private SpriteGroup bossGroup;
	private SpriteGroup missileGroup;
	private SpriteGroup blackHoleGroup;
	private SpriteGroup menuGroup;
	private SpriteGroup levelCompleteGroup;
	private SpriteGroup shoppingLevelGroup;
	private SpriteGroup gameCompleteGroup;
	private SpriteGroup gameOverGroup;
	
	private Map<SpriteGroup, Double> spriteGroupSpeedMap;

	private Sprite shipSprite;
	private PlayerSprite playerSprite;

	private PlayerEnemyCollision 			playerEnemyCollision;
	private PlayerBossPartCollision 		playerBossPartCollision;
	private PlayerBossCollision 			playerBossCollision;
	private ProjectileEnemyCollision 		projectileEnemyCollision;
	private ProjectileBossPartCollision 	projectileBossPartCollision;
	private PlayerEnemyProjectileCollision	playerEnemyProjectileCollision;
	private ProjectileBossCollision 		projectileBossCollision;
	private MissileEnemyCollision 			missileEnemyCollision;
	private MissileBossPartCollision 		missileBossPartCollision;
	private MissileBossCollision 			missileBossCollision;
	private BlackHoleEnemyCollision 		blackHoleEnemyCollision;

	private OverlayPanel myPanel;
	private OverlayStatImage livesIcon;
	private Stat<Integer> myLives;
	private Stat<Integer> myScore;
	private Stat<Integer> myCash;

	private GrandiusLevelManager levelManager;
	private GameFont font;
	private double playerInitialX;
	private double playerInitialY;

	private Dimension screen;
	
	//TODO: Good practice here? Use Missile/BlackHole classes?
	private boolean missileActive = 	false;
	private boolean blackHoleActive = 	false;

	//Cheat options
	private boolean isInvincible = false;
	private boolean skipLevel = false;
	private static final String INVINCIBILITY = "superman";
	private static final String EXTRA_POINTS = "highscore";
	private static final String EXTRA_CASH = "showmethemoney";
	private static final String SKIP_LEVEL = "getmeouttahere";
	private static final String ACTIVATE_MISSILE = "missiletime";
	
	private Properties directoriesFile;
	private Properties stringsFile;

	@Override
	public void initResources() {
		//TODO - change to work with new Overlay xml
		super.initResources();
		font = fontManager.getFont(getImages("resources/images/font.png", 20, 3),
				" !            .,0123" +
				"456789:   -? ABCDEFG" +
		"HIJKLMNOPQRSTUVWXYZ ");
		
		myPanel = new OverlayPanel("GrandiusOverlay", this, true);
		myLives = new Stat<Integer>(new Integer(INITIAL_PLAYER_LIVES));
		myScore = new Stat<Integer>(new Integer(INITIAL_ZERO));
		myCash = new Stat<Integer>(new Integer(INITIAL_ZERO));
		levelManager = new GrandiusLevelManager();
		
		directoriesFile = new Properties();
		stringsFile = new Properties();
		try {
			directoriesFile.load(new FileInputStream("src/vooga/games/grandius/Directories.properties"));
			stringsFile.load(new FileInputStream("src/vooga/games/grandius/Strings.properties"));
		}
		catch(IOException e)
		{
			System.out.println(".properties file not found!");
		}
		
		
		String levelFilesDirectory = directoriesFile.getProperty("levelFilesDirectory");
		String levelNamesFile = directoriesFile.getProperty("levelNamesFile");
		levelManager.addLevels(levelFilesDirectory,new File(levelNamesFile));
		
		Resources.setDefaultPath(directoriesFile.getProperty("resourcesPath"));
		Resources.setGame(this);
		int screenWidth = Integer.parseInt(stringsFile.getProperty("screenWidth"));
		int screenHeight = Integer.parseInt(stringsFile.getProperty("screenHeight"));
		screen = new Dimension(screenWidth,screenHeight);
		playerInitialX = PLAYER_INITIAL_X;
		playerInitialY = screen.getHeight()/2;

		try {
			Resources.loadImageFile("imagelist.txt");
			Resources.loadSoundFile("soundlist.txt");
		} catch (IOException e) {
			System.out.println("Failed to load resource files.");
		}

		livesIcon = new OverlayStatImage(Resources.getImage("PlayerShipSingle"));

		playfield = new PlayField();

		shipSprite = new Sprite(Resources.getImage("PlayerShipSingle"),playerInitialX,playerInitialY);
		playerSprite = new PlayerSprite("ThePlayer", "alive", shipSprite);
		createComets();

		createSpriteGroups();
		playerGroup.add(playerSprite);

		reacherShieldsDepleted = false;
		
		//Start building the game states and state manager
		gameStateManager = new GameStateManager();
		
		buildPlayState();
		buildLevelCompleteState();
		
		gameCompleteState = new GameState();
		gameCompleteGroup.add(new OverlayString("Game complete"));
		gameCompleteState.addRenderGroup(gameCompleteGroup);
		
		buildShoppingLevelState();
		
		startNewLevelState = new GameState();
		
		buildMenuState();
		
		gameOverState = new GameState();
		OverlayString gameOver = new OverlayString("GAME OVER",
				new Font("mine", Font.PLAIN, 30), java.awt.Color.RED);
		gameOver.setLocation(screenWidth/2 - gameOver.getWidth()/2, screenHeight/2);
		gameOverGroup.add(gameOver);
		gameOverState.addRenderGroup(gameOverGroup);
		
		gameStates = new ArrayList<GameState>();
		
		addToGameStateList(menuState);
		addToGameStateList(playState);
		addToGameStateList(levelCompleteState);
		addToGameStateList(shoppingLevelState);
		addToGameStateList(startNewLevelState);
		addToGameStateList(gameCompleteState);
		addToGameStateList(gameOverState);
		
		gameStateManager.switchTo(menuState);
		
		//create collisions and register them to the playfield
		createCollisions();
		addOverlays();
	}

	private void addToGameStateList(GameState gameState) {
		gameStates.add(gameState);
		gameStateManager.addGameState(gameState);
	}

	private void buildShoppingLevelState() {
		shoppingLevelState = new GameState();
		int displayX = Integer.parseInt(stringsFile.getProperty("shoppingLevelX"));
		int displayY = Integer.parseInt(stringsFile.getProperty("shoppingLevelY"));
		
		OverlayStat shoppingLevel1 = new OverlayStat("CASH: ", myCash);
		shoppingLevel1.setFont(font);
		shoppingLevel1.setLocation(displayX,displayY);
		shoppingLevelGroup.add(shoppingLevel1);
		
		for(int i=2;i<5;i++){
			displayY=displayY*i;
			OverlayString shoppingLevel = new OverlayString(stringsFile.getProperty("shoppingLevel"+i), font);
			shoppingLevel.setLocation(displayX, displayY);
			shoppingLevelGroup.add(shoppingLevel);
		}
		shoppingLevelState.addRenderGroup(shoppingLevelGroup);
		shoppingLevelState.addUpdateGroups(shoppingLevelGroup);
	}

	private void buildLevelCompleteState() {
		levelCompleteState = new GameState();
		int displayX = Integer.parseInt(stringsFile.getProperty("levelCompleteX"));
		int displayY = Integer.parseInt(stringsFile.getProperty("levelCompleteY"));
		
		OverlayString levelComplete1 = new OverlayString("LEVEL " + (levelManager.getCurrentLevel()+1) + " COMPLETE", font);
		levelComplete1.setLocation(displayX,displayY);
		OverlayString levelComplete2 = new OverlayString(stringsFile.getProperty("levelCompleteMessage"), font);
		levelComplete2.setLocation(displayX,2*displayY);

		levelCompleteGroup.add(levelComplete1);
		levelCompleteGroup.add(levelComplete2);
		levelCompleteState.addRenderGroup(levelCompleteGroup);
	}

	private void buildPlayState() {
		playState = new GameState();
		playState.addRenderGroup(playerGroup);
		playState.addRenderGroup(projectileGroup);
		playState.addRenderGroup(enemyProjectileGroup);
		playState.addRenderGroup(enemyGroup);
		playState.addRenderGroup(bossPartGroup);
		playState.addRenderGroup(bossGroup);
		playState.addRenderGroup(missileGroup);
		playState.addRenderGroup(blackHoleGroup);
		
		playState.addUpdateGroups(playerGroup);
		playState.addUpdateGroups(projectileGroup);
		playState.addUpdateGroups(enemyProjectileGroup);
		playState.addUpdateGroups(enemyGroup);
		playState.addUpdateGroups(bossPartGroup);
		playState.addUpdateGroups(bossGroup);
		playState.addUpdateGroups(missileGroup);
		playState.addUpdateGroups(blackHoleGroup);
	}

	private void buildMenuState() {
		menuState = new GameState();
		for(int i=1;i<9;i++){
			OverlayString menu = new OverlayString(stringsFile.getProperty("menu"+i), font);
			menu.setLocation(Integer.parseInt(stringsFile.getProperty("menuX")), Integer.parseInt(stringsFile.getProperty("menuY"))*i);
			menuGroup.add(menu);
		}		
		menuState.addRenderGroup(menuGroup);
	}

	/**
	 * Creates different types of collisions and registers them to the playfield.
	 */
	private void createCollisions() {
		playerEnemyCollision = new PlayerEnemyCollision(this);
		playerBossPartCollision = new PlayerBossPartCollision(this);
		playerBossCollision = new PlayerBossCollision(this);
		projectileEnemyCollision = new ProjectileEnemyCollision(this);
		projectileBossPartCollision = new ProjectileBossPartCollision(this);
		playerEnemyProjectileCollision = new PlayerEnemyProjectileCollision(this);
		projectileBossCollision = new ProjectileBossCollision(this);
		missileEnemyCollision = new MissileEnemyCollision(this);
		missileBossPartCollision = new MissileBossPartCollision(this);
		missileBossCollision = new MissileBossCollision(this); 
		blackHoleEnemyCollision = new BlackHoleEnemyCollision(this);

		playfield.addCollisionGroup(playerGroup, enemyGroup, playerEnemyCollision);
		playfield.addCollisionGroup(playerGroup, bossPartGroup, playerBossPartCollision);
		playfield.addCollisionGroup(playerGroup, bossGroup, playerBossCollision);
		playfield.addCollisionGroup(projectileGroup, enemyGroup, projectileEnemyCollision);
		playfield.addCollisionGroup(projectileGroup, bossPartGroup, projectileBossPartCollision);
		playfield.addCollisionGroup(playerGroup, enemyProjectileGroup, playerEnemyProjectileCollision);
		playfield.addCollisionGroup(projectileGroup, bossGroup, projectileBossCollision);
		playfield.addCollisionGroup(missileGroup, enemyGroup, missileEnemyCollision);
		playfield.addCollisionGroup(missileGroup, bossPartGroup, missileBossPartCollision);
		playfield.addCollisionGroup(missileGroup, bossGroup, missileBossCollision);
		playfield.addCollisionGroup(blackHoleGroup, enemyGroup, blackHoleEnemyCollision);
	}

	/**
	 * Creates the different SpriteGroups and registers them to the playfield. Also adds the necessary SpriteGroups to the
	 * spriteGroupSpeedMap.
	 */
	private void createSpriteGroups() {
		playerGroup = playfield.addGroup(new SpriteGroup("Player"));
		projectileGroup = playfield.addGroup(new SpriteGroup("Projectile"));
		enemyProjectileGroup = playfield.addGroup(new SpriteGroup("EnemyProjectile"));
		enemyGroup = playfield.addGroup(new SpriteGroup("Enemy"));
		bossPartGroup = playfield.addGroup(new SpriteGroup("BossPart"));
		bossGroup = playfield.addGroup(new SpriteGroup("Boss"));
		missileGroup = playfield.addGroup(new SpriteGroup("Missile"));
		blackHoleGroup = playfield.addGroup(new SpriteGroup("BlackHole"));
		menuGroup = new SpriteGroup("MenuGroup");
		levelCompleteGroup = new SpriteGroup("LevelCompleteGroup");
		shoppingLevelGroup = new SpriteGroup("ShoppingLevelGroup");
		gameCompleteGroup = new SpriteGroup("GameCompleteGroup");
		gameOverGroup = new SpriteGroup("GameOverGroup");
		
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		spriteGroupSpeedMap.put(projectileGroup, new Double(PROJECTILE_SPEED));
		spriteGroupSpeedMap.put(enemyProjectileGroup, new Double(-PROJECTILE_SPEED));
		spriteGroupSpeedMap.put(enemyGroup, new Double(-ENEMY_SPEED));
		spriteGroupSpeedMap.put(bossPartGroup, new Double(-BOSS_PART_SPEED));
		spriteGroupSpeedMap.put(bossGroup, new Double(-BOSS_SPEED));
		spriteGroupSpeedMap.put(missileGroup, new Double(PROJECTILE_SPEED));
	}

	@Override
	public void render(Graphics2D g) {
		if (menuState.isActive()) {
			//myPlayfield.render(g);
		}
		//This game should not be here; GameStateManager should take care of this.
		for(GameState gamestate: gameStates){
			if (!gamestate.equals(menuState) && gamestate.isActive()) {
				playfield.render(g);
			}
		}		
		gameStateManager.render(g);
	}

	@Override
	public void update(long elapsedTime) {
		
		if(levelManager.getCurrentLevel()==0){
			PlayField playfield=levelManager.loadNextLevel();
			playfield.setBackground(levelManager.getBackground());
			for(SpriteGroup group: playfield.getGroups()){
				playfield.addGroup(group);
			}
		}
		
		if (menuState.isActive()){
			if(click()){
				gameStateManager.switchTo(playState);
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

				gameStateManager.switchTo(levelCompleteState);
				playfield.clearPlayField();
			}
			playfield.update(elapsedTime);
			if (myLives.getStat().intValue() <= 0) {
				playfield.clearPlayField();
				gameStateManager.switchTo(gameOverState);
				playSound(Resources.getSound("OhManSound"));
			}
		}
		
		if (levelCompleteState.isActive()){
			if(levelManager.getCurrentLevel()==LAST_LEVEL){
				playfield.clearPlayField();
				gameStateManager.switchTo(gameCompleteState);
			}
			else if(click()){
				playfield.clearPlayField();
				gameStateManager.switchTo(shoppingLevelState);
			}
		}

		if (shoppingLevelState.isActive()){
			//TODO factor out switch to out of if statement
//			SHOPPING_LEVEL_GROUP.update(elapsedTime);
			int displayX = Integer.parseInt(stringsFile.getProperty("shoppingLevelX"));
			int displayY = Integer.parseInt(stringsFile.getProperty("shoppingLevelY"));
			if(click()){
			System.out.println("Made it here");
				if((this.getMouseX()>displayX)){
					if((this.getMouseY()>displayY*2) &&
							(this.getMouseY()<displayY*3)){
						missileActive = true;
						updateStat(myCash,-500);
						playfield.clearPlayField();
						gameStateManager.switchTo(startNewLevelState);
					}
					else if((this.getMouseY()>displayY*3) &&
							(this.getMouseY()<displayY*4)){
						blackHoleActive = true;
						updateStat(myCash,-1000);
						playfield.clearPlayField();
						gameStateManager.switchTo(startNewLevelState);
					}
				}
			}
			if (keyPressed(KeyEvent.VK_SPACE)){
				gameStateManager.switchTo(startNewLevelState);
			}
		}

		if (startNewLevelState.isActive()){
			PlayField playfield=levelManager.loadNextLevel();
			playfield.setBackground(levelManager.getBackground());
			for(SpriteGroup group: playfield.getGroups()){
				playfield.addGroup(group);
			}
			playerGroup.add(playerSprite);
			createComets();
			playfield.addGroup(myPanel);
			playSound(Resources.getSound("StartLevelSound"));
			gameStateManager.switchTo(playState);
		}
		gameStateManager.update(elapsedTime);
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
	 * @param spriteGroup
	 * @param currentLevel
	 * @param currentSprites
	 * @param playerX
	 * @param playerY
	 * @param index
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
		if (keyPressed(KeyEvent.VK_ALT)) {             
			Sprite projectile = new Sprite(Resources.getImage("Projectile"),playerSprite.getX()+playerSprite.getWidth(),playerSprite.getY());
			projectile.setHorizontalSpeed(PROJECTILE_SPEED);
			projectileGroup.add(projectile);
			playSound(Resources.getSound("LaserSound"));
		}
		if (keyPressed(KeyEvent.VK_SPACE)){
			Sprite projectile = new Sprite(Resources.getImage("ProjectileVertical"),playerSprite.getX()+playerSprite.getWidth(),playerSprite.getY());
			projectile.setVerticalSpeed(PROJECTILE_SPEED);
			projectileGroup.add(projectile);
			playSound(Resources.getSound("LaserSound"));
		}
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
		
		double bulletSpeed = Double.parseDouble(stringsFile.getProperty("bulletSpeed"));
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
	 * @param spriteGroup
	 * @param direction
	 * @param offset
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
	 * @return Whether or not the level is complete.
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
	 * @param stat
	 * @param addedValue
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

	public void updatePlayerLives(){
		int playerLives = (myLives.getStat()).intValue();
		if(playerLives > 0  && !isInvincible){
			updateStat(myLives, (-1));
			playerSprite.setLocation(playerInitialX, playerInitialY);
		}
		//TODO: switch this to a game state?
//		else if (playerLives==0){ //Game over.
//			gameStateManager.switchTo(gameOverState);
////			PLAYER_GROUP.setActive(false);
////			PROJECTILE_GROUP.setActive(false);
////			ENEMY_PROJECTILE_GROUP.setActive(false);
////			ENEMY_GROUP.setActive(false);
////			BOSS_PART_GROUP.setActive(false);
////			MISSILE_GROUP.setActive(false);
//			//myPlayfield.clearPlayField();
//			//gameOver.setLocation(screen.getWidth() / 3, screen.getHeight() / 2);
//			//myPlayfield.add(gameOver);
//			playSound(Resources.getSound("OhManSound"));
//		}

	}

	public PlayField getPlayfield() {
		return this.playfield;
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Blah(), new Dimension(640,480), false);
		game.start();
	}

	public void updateScoreOnCollision(int points) {
		updateStat(myScore, points);
	}

	public void updateCashOnCollision(int cash) {
		updateStat(myCash, cash);
	}

	private void createComets() {
		for (int j = 0; j < NUM_COMETS; j++) { // create 500 background sprites
			Random valX = new Random();
			Random valY = new Random();
			double x = valX.nextDouble();
			double y = valY.nextDouble();
			Sprite backgroundSprite = new Sprite(Resources.getImage("Comet"),
					(x * Integer.parseInt(stringsFile.getProperty("cometX"))), 
					(y * Integer.parseInt(stringsFile.getProperty("cometY"))));
			backgroundSprite.setHorizontalSpeed(Double.parseDouble(stringsFile.getProperty("cometVX")));
			playfield.add(backgroundSprite);
		}
	}

	public void addOverlays() {
		OverlayIcon livesCounter = new OverlayIcon(myLives, livesIcon, "Lives");
		OverlayStat scoreCounter = new OverlayStat("Score", myScore);
		OverlayStat cashCounter  = new OverlayStat("Cash", myCash);

	    myPanel.add(livesCounter);
	    myPanel.add(cashCounter);		
		myPanel.add(scoreCounter);
		myPanel.initialize();
		
		playfield.addGroup(myPanel);
	}

	public void checkCheats() {
		if(keyPressed(KeyEvent.VK_ENTER))
		{
			JFrame frame = new JFrame();
			String userInput = (String)JOptionPane.showInputDialog(frame,
					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
			if(userInput.equals(INVINCIBILITY))
				isInvincible = true;
			else if(userInput.equals(SKIP_LEVEL))
				skipLevel = true;
			else if(userInput.equals(EXTRA_POINTS))
				updateStat(myScore, 1000000);
			else if(userInput.equals(EXTRA_CASH))
				updateStat(myCash, 5000);
			else if(userInput.equals(ACTIVATE_MISSILE))
				missileActive = true;
				
		}
	}

}