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

import vooga.engine.overlay.*;
import vooga.engine.player.control.*;
import vooga.engine.resource.ResourcesBeta;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

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
public class Grandius extends Game {

	private static final int PLAYER_INITIAL_X = 15;
	private static final int INITIAL_PLAYER_LIVES = 3;
	private static final int INITIAL_ZERO = 0;
	
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
	
	private PlayField myPlayfield;
	private Background myBackground;

	private boolean reacherShieldsDepleted;

	private SpriteGroup PLAYER_GROUP;
	private SpriteGroup PROJECTILE_GROUP;
	private SpriteGroup ENEMY_PROJECTILE_GROUP;
	private SpriteGroup ENEMY_GROUP;
	private SpriteGroup BOSS_PART_GROUP;
	private SpriteGroup BOSS_GROUP;
	private SpriteGroup MISSILE_GROUP;
	private SpriteGroup BLACK_HOLE_GROUP;
	private SpriteGroup MENU_GROUP;
	private SpriteGroup LEVEL_COMPLETE_GROUP;
	private SpriteGroup SHOPPING_LEVEL_GROUP;
	private SpriteGroup GAME_COMPLETE_GROUP;
	private SpriteGroup GAME_OVER_GROUP;
	
	private Map<SpriteGroup, Double> spriteGroupSpeedMap;

	private Sprite shipsprite;
	private PlayerSprite playersprite;

	private PlayerEnemyCollision playerEnemyCollision;
	private PlayerBossPartCollision playerBossPartCollision;
	private PlayerBossCollision playerBossCollision;
	private ProjectileEnemyCollision projectileEnemyCollision;
	private ProjectileBossPartCollision projectileBossPartCollision;
	private PlayerEnemyProjectileCollision playerEnemyProjectileCollision;
	private ProjectileBossCollision projectileBossCollision;
	private MissileEnemyCollision missileEnemyCollision;
	private MissileBossPartCollision missileBossPartCollision;
	private MissileBossCollision missileBossCollision;
	private BlackHoleEnemyCollision blackHoleEnemyCollision;

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
	private boolean missileActive = false;
	private boolean blackHoleActive = false;

	//Cheat options
	private boolean isInvincible = false;
	private boolean skipLevel = false;
	private static final String INVINCIBILITY = "superman";
	private static final String EXTRA_POINTS = "highscore";
	private static final String EXTRA_CASH = "showmethemoney";
	private static final String SKIP_LEVEL = "getmeouttahere";
	private static final String ACTIVATE_MISSILE = "missiletime";

	@Override
	public void initResources() {
		//TODO - change to work with ResourcesBeta class (or overlay?)
		font = fontManager.getFont(getImages("resources/images/font.png", 20, 3),
				" !            .,0123" +
				"456789:   -? ABCDEFG" +
		"HIJKLMNOPQRSTUVWXYZ ");
		
		myPanel = new OverlayPanel("GrandiusOverlay", this, true);
		myLives = new Stat<Integer>(new Integer(INITIAL_PLAYER_LIVES));
		myScore = new Stat<Integer>(new Integer(INITIAL_ZERO));
		myCash = new Stat<Integer>(new Integer(INITIAL_ZERO));
		levelManager = new GrandiusLevelManager();
		
		Properties propertiesFile = new Properties();
		try {
			propertiesFile.load(new FileInputStream("src/vooga/games/grandius/Directories.properties"));
		}
		catch(IOException e)
		{
			System.out.println(".properties file not found!");
		}
		
		
		String levelFilesDirectory = propertiesFile.getProperty("levelFilesDirectory");
		String levelNamesFile = propertiesFile.getProperty("levelNamesFile");
		levelManager.addLevels(levelFilesDirectory,new File(levelNamesFile));
		
		ResourcesBeta.setDefaultPath(propertiesFile.getProperty("resourcesPath"));
		ResourcesBeta.setGame(this);
		screen = new Dimension(640,480);
		playerInitialX = PLAYER_INITIAL_X;
		playerInitialY = screen.getHeight()/2;

		try {
			ResourcesBeta.loadImageFile("imagelist.txt");
			ResourcesBeta.loadSoundFile("soundlist.txt");
		} catch (IOException e) {
			System.out.println("Failed to load resource files.");
		}

		livesIcon = new OverlayStatImage(ResourcesBeta.getImage("PlayerShipSingle"));

		myPlayfield = new PlayField();

		myBackground = new ImageBackground(ResourcesBeta.getImage("BG"), 640, 480);
		myPlayfield.setBackground(myBackground);

		shipsprite = new Sprite(ResourcesBeta.getImage("PlayerShipSingle"),playerInitialX,playerInitialY);
		//playersprite = new PlayerSprite("ThePlayer", "alive", shipsprite, INITIAL_PLAYER_HEALTH, INITIAL_PLAYER_RANK);
		playersprite = new PlayerSprite("ThePlayer", "alive", shipsprite);
		createComets();

		createSpriteGroups();
		PLAYER_GROUP.add(playersprite);

		
		reacherShieldsDepleted = false;


		gameStateManager = new GameStateManager();
		
		buildPlayState();
		
		buildLevelCompleteState();
		
		gameCompleteState = new GameState();
		GAME_COMPLETE_GROUP.add(new OverlayString("Game complete"));
		gameCompleteState.addRenderGroup(GAME_COMPLETE_GROUP);
		
		buildShoppingLevelState();
		
		startNewLevelState = new GameState();
		
		buildMenuState();
		
		gameOverState = new GameState();
		GAME_OVER_GROUP.add(new OverlayString("GAME OVER",
				new Font("mine", Font.PLAIN, 30), java.awt.Color.RED));
		gameOverState.addRenderGroup(GAME_OVER_GROUP);
		
		gameStateManager.addGameState(menuState);
		gameStateManager.addGameState(playState);
		gameStateManager.addGameState(levelCompleteState);
		gameStateManager.addGameState(shoppingLevelState);
		gameStateManager.addGameState(startNewLevelState);
		gameStateManager.addGameState(gameCompleteState);
		gameStateManager.addGameState(gameOverState);
		
		gameStateManager.switchTo(menuState);
		
		//create collisions and register them to the playfield
		createCollisions();
		addOverlays();
	}

	private void buildShoppingLevelState() {
		shoppingLevelState = new GameState();
		OverlayString shoppingLevel1 = new OverlayString("CASH: " + myCash.getStat().intValue(), font);
		shoppingLevel1.setLocation((int) screen.getWidth()/3, (int) (screen.getHeight()/5));
		OverlayString shoppingLevel2 = new OverlayString("CLICK HERE TO BUY MISSILE - 500", font);
		shoppingLevel2.setLocation((int) screen.getWidth()/10, (int) screen.getHeight()/4);
		OverlayString shoppingLevel3 = new OverlayString("CLICK HERE TO BUY BLACK HOLE - 1000", font);
		shoppingLevel3.setLocation((int) screen.getWidth()/10, (int) screen.getHeight()/3);
		OverlayString shoppingLevel4 = new OverlayString("OR HIT SPACEBAR FOR NEXT LEVEL", font);
		shoppingLevel4.setLocation((int) screen.getWidth()/6, (int) screen.getHeight()/2);
		SHOPPING_LEVEL_GROUP.add(shoppingLevel1);
		SHOPPING_LEVEL_GROUP.add(shoppingLevel2);
		SHOPPING_LEVEL_GROUP.add(shoppingLevel3);
		SHOPPING_LEVEL_GROUP.add(shoppingLevel4);
		shoppingLevelState.addRenderGroup(SHOPPING_LEVEL_GROUP);
		shoppingLevelState.addUpdateGroups(SHOPPING_LEVEL_GROUP);
	}

	private void buildLevelCompleteState() {
		levelCompleteState = new GameState();
		OverlayString levelComplete1 = new OverlayString("LEVEL " +
				levelManager.getCurrentLevel() + " COMPLETE", font);
		levelComplete1.setLocation((int) screen.getWidth() / 3,(int) (screen.getHeight() / 2.5));
		OverlayString levelComplete2 = new OverlayString("CLICK FOR SHOPPING LEVEL", font);
		levelComplete2.setLocation((int) screen.getWidth() / 3, (int) screen.getHeight() / 2);
		LEVEL_COMPLETE_GROUP.add(levelComplete1);
		LEVEL_COMPLETE_GROUP.add(levelComplete2);
		levelCompleteState.addRenderGroup(LEVEL_COMPLETE_GROUP);
	}

	private void buildPlayState() {
		playState = new GameState();
		playState.addRenderGroup(PLAYER_GROUP);
		playState.addRenderGroup(PROJECTILE_GROUP);
		playState.addRenderGroup(ENEMY_PROJECTILE_GROUP);
		playState.addRenderGroup(ENEMY_GROUP);
		playState.addRenderGroup(BOSS_PART_GROUP);
		playState.addRenderGroup(BOSS_GROUP);
		playState.addRenderGroup(MISSILE_GROUP);
		playState.addRenderGroup(BLACK_HOLE_GROUP);
		
		playState.addUpdateGroups(PLAYER_GROUP);
		playState.addUpdateGroups(PROJECTILE_GROUP);
		playState.addUpdateGroups(ENEMY_PROJECTILE_GROUP);
		playState.addUpdateGroups(ENEMY_GROUP);
		playState.addUpdateGroups(BOSS_PART_GROUP);
		playState.addUpdateGroups(BOSS_GROUP);
		playState.addUpdateGroups(MISSILE_GROUP);
		playState.addUpdateGroups(BLACK_HOLE_GROUP);
	}

	private void buildMenuState() {
		menuState = new GameState();
		OverlayString menu1 = new OverlayString("WELCOME TO THE GRANDIUS GALAXY", font);
		menu1.setLocation((int) screen.getWidth() / 7, 50);
		OverlayString menu2 = new OverlayString("YOUR MISSION: DESTROY ALL ENEMIES", font);
		menu2.setLocation((int) screen.getWidth() / 7, 100);
		OverlayString menu3 = new OverlayString("ARROW KEY : MOVE", font);
		menu3.setLocation((int) screen.getWidth() / 4, 150);
		OverlayString menu4 = new OverlayString("ALT   : FIRE HORIZONTALLY", font);
		menu4.setLocation((int) screen.getWidth() / 4, 200);
		OverlayString menu5 = new OverlayString("SPACE   : FIRE VERTICALLY", font);
		menu5.setLocation((int) screen.getWidth() / 4, 250);
		OverlayString menu6 = new OverlayString("M: FIRE MISSILE - ONCE PURCHASED", font);
		menu6.setLocation((int) screen.getWidth() / 8, 300);
		OverlayString menu7 = new OverlayString("B: CREATE BLACK HOLE - ONCE PURCHASED", font);
		menu7.setLocation((int) screen.getWidth() / 13, 350);
		OverlayString menu8 = new OverlayString("CLICK TO PLAY", font);
		menu8.setLocation((int) screen.getWidth() / 4, 400);
		
		MENU_GROUP.add(menu1);
		MENU_GROUP.add(menu2);
		MENU_GROUP.add(menu3);
		MENU_GROUP.add(menu4);
		MENU_GROUP.add(menu5);
		MENU_GROUP.add(menu6);
		MENU_GROUP.add(menu7);
		MENU_GROUP.add(menu8);
		
		menuState.addRenderGroup(MENU_GROUP);
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

		myPlayfield.addCollisionGroup(PLAYER_GROUP, ENEMY_GROUP, playerEnemyCollision);
		myPlayfield.addCollisionGroup(PLAYER_GROUP, BOSS_PART_GROUP, playerBossPartCollision);
		myPlayfield.addCollisionGroup(PLAYER_GROUP, BOSS_GROUP, playerBossCollision);
		myPlayfield.addCollisionGroup(PROJECTILE_GROUP, ENEMY_GROUP, projectileEnemyCollision);
		myPlayfield.addCollisionGroup(PROJECTILE_GROUP, BOSS_PART_GROUP, projectileBossPartCollision);
		myPlayfield.addCollisionGroup(PLAYER_GROUP, ENEMY_PROJECTILE_GROUP, playerEnemyProjectileCollision);
		myPlayfield.addCollisionGroup(PROJECTILE_GROUP, BOSS_GROUP, projectileBossCollision);
		myPlayfield.addCollisionGroup(MISSILE_GROUP, ENEMY_GROUP, missileEnemyCollision);
		myPlayfield.addCollisionGroup(MISSILE_GROUP, BOSS_PART_GROUP, missileBossPartCollision);
		myPlayfield.addCollisionGroup(MISSILE_GROUP, BOSS_GROUP, missileBossCollision);
		myPlayfield.addCollisionGroup(BLACK_HOLE_GROUP, ENEMY_GROUP, blackHoleEnemyCollision);
	}

	/**
	 * Creates the different SpriteGroups and registers them to the playfield. Also adds the necessary SpriteGroups to the
	 * spriteGroupSpeedMap.
	 */
	private void createSpriteGroups() {
		PLAYER_GROUP = myPlayfield.addGroup(new SpriteGroup("Player"));
		PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Projectile"));
		ENEMY_PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("EnemyProjectile"));
		ENEMY_GROUP = myPlayfield.addGroup(new SpriteGroup("Enemy"));
		BOSS_PART_GROUP = myPlayfield.addGroup(new SpriteGroup("BossPart"));
		BOSS_GROUP = myPlayfield.addGroup(new SpriteGroup("Boss"));
		MISSILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Missile"));
		BLACK_HOLE_GROUP = myPlayfield.addGroup(new SpriteGroup("BlackHole"));
		MENU_GROUP = new SpriteGroup("MenuGroup");
		LEVEL_COMPLETE_GROUP = new SpriteGroup("LevelCompleteGroup");
		SHOPPING_LEVEL_GROUP = new SpriteGroup("ShoppingLevelGroup");
		GAME_COMPLETE_GROUP = new SpriteGroup("GameCompleteGroup");
		GAME_OVER_GROUP = new SpriteGroup("GameOverGroup");
		
		spriteGroupSpeedMap = new HashMap<SpriteGroup, Double>();
		spriteGroupSpeedMap.put(PROJECTILE_GROUP, new Double(PROJECTILE_SPEED));
		spriteGroupSpeedMap.put(ENEMY_PROJECTILE_GROUP, new Double(-PROJECTILE_SPEED));
		spriteGroupSpeedMap.put(ENEMY_GROUP, new Double(-ENEMY_SPEED));
		spriteGroupSpeedMap.put(BOSS_PART_GROUP, new Double(-BOSS_PART_SPEED));
		spriteGroupSpeedMap.put(BOSS_GROUP, new Double(-BOSS_SPEED));
		spriteGroupSpeedMap.put(MISSILE_GROUP, new Double(PROJECTILE_SPEED));
	}

	@Override
	public void render(Graphics2D g) {
		if (menuState.isActive()) {
			// TODO - replace Magic numbers

		}

		if (playState.isActive()) {
			myPlayfield.render(g);
		}

		if (levelCompleteState.isActive()){
//			myPlayfield.clearPlayField();
			myPlayfield.render(g);

		}
		if (shoppingLevelState.isActive()){
//			myPlayfield.clearPlayField();
			myPlayfield.render(g);
//			font.drawString(g, "CASH: " + myCash.getStat().intValue(),
//					(int) screen.getWidth()/3,
//					(int) (screen.getHeight()/5));
//			font.drawString(g, "CLICK HERE TO BUY MISSILE - 500",
//					(int) screen.getWidth()/10,
//					(int) screen.getHeight()/4);
//			font.drawString(g, "CLICK HERE TO BUY BLACK HOLE - 1000",
//					(int) screen.getWidth()/10,
//					(int) screen.getHeight()/3);
//			font.drawString(g, "OR HIT SPACEBAR FOR NEXT LEVEL",
//					(int) screen.getWidth()/6,
//					(int) screen.getHeight()/2);
		}

		if (gameCompleteState.isActive()) {
//			myPlayfield.clearPlayField();
			myPlayfield.render(g);
//			font.drawString(g, "GAME COMPLETE",
//					(int) screen.getWidth() / 3,
//					(int) (screen.getHeight() / 2.5));
//			font.drawString(g, "YOU WIN",
//					(int) screen.getWidth() / 3,
//					(int) screen.getHeight() / 2);
//			this.stop();
		}
		gameStateManager.render(g);
	}

	@Override
	public void update(long elapsedTime) {
		//myPlayfield.
		if(levelManager.getCurrentLevel()==0){
			PlayField playfield=levelManager.loadNextLevel();
			for(SpriteGroup group: playfield.getGroups()){
				myPlayfield.addGroup(group);
			}
		}
		
		//playfield.update(elapsedTime);
		
//		if(levelcomplete condition){
//			playfield.clearPlayField();
//			playfield=levelManager.loadNextLevel();
//		}

		//TODO Utilize VOOGA State API
		if (menuState.isActive()){
			if(click()){
				gameStateManager.switchTo(playState);
				playSound(ResourcesBeta.getSound("WatchThisSound"));
				playSound(ResourcesBeta.getSound("StartLevelSound"));
			}
		}

		if (playState.isActive()) {
			fireWeapon();
			updateEntities();
			checkCheats();
			checkBossParts();
			if (checkLevelComplete()) {

				gameStateManager.switchTo(levelCompleteState);
				myPlayfield.clearPlayField();
			}
			myPlayfield.update(elapsedTime);
			if (myLives.getStat().intValue() <= 0) {
				myPlayfield.clearPlayField();
				gameStateManager.switchTo(gameOverState);
				playSound(ResourcesBeta.getSound("OhManSound"));
			}
		}
		
		if (levelCompleteState.isActive()){
			if(levelManager.getCurrentLevel()==LAST_LEVEL){
				myPlayfield.clearPlayField();
				gameStateManager.switchTo(gameCompleteState);
			}
			else if(click()){
				myPlayfield.clearPlayField();
				gameStateManager.switchTo(shoppingLevelState);
			}
		}

		if (shoppingLevelState.isActive()){
			//TODO factor out switch to out of if statement
			if(click()){
				if((this.getMouseX()>screen.getWidth()/3) &&
						(this.getMouseX()<screen.getWidth()-100)){
					if((this.getMouseY()>screen.getHeight()/4) &&
							(this.getMouseY()<screen.getHeight()/3)){
						missileActive = true;
						updateStat(myCash,-500);
						myPlayfield.clearPlayField();
					}
					else if((this.getMouseY()>screen.getHeight()/3) &&
							(this.getMouseY()<screen.getHeight()/2)){
						blackHoleActive = true;
						updateStat(myCash,-1000);
						myPlayfield.clearPlayField();
					}
					gameStateManager.switchTo(startNewLevelState);
				}
			}
			if (keyPressed(KeyEvent.VK_SPACE)){
				gameStateManager.switchTo(startNewLevelState);
			}
		}

		if (startNewLevelState.isActive()){
			PlayField playfield=levelManager.loadNextLevel();
			for(SpriteGroup group: playfield.getGroups()){
				myPlayfield.addGroup(group);
			}
			//ArrayList<ArrayList<Sprite>> currentLevel = levelManager.currentLevel();
			PLAYER_GROUP.add(playersprite);
			//myPlayfield.addGroup(PLAYER_GROUP);
			createComets();
			//initLevel(currentLevel.get(0), currentLevel.get(1), currentLevel.get(2));
			addOverlays();
			playSound(ResourcesBeta.getSound("StartLevelSound"));
			gameStateManager.switchTo(playState);
		}
		gameStateManager.update(elapsedTime);
	}

	/**
	 * Updates the various enemies that are on screen.
	 */
	private void updateScreenSprites() {
		ArrayList<ArrayList<Sprite>> currentSprites = levelManager.currentLevel();
		updateSpriteGroup(ENEMY_GROUP, currentSprites,0);
		updateSpriteGroup(BOSS_PART_GROUP, currentSprites, 1);
		updateSpriteGroup(BOSS_GROUP, currentSprites, 2);
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
			Sprite projectile = new Sprite(ResourcesBeta.getImage("Projectile"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			projectile.setHorizontalSpeed(PROJECTILE_SPEED);
			PROJECTILE_GROUP.add(projectile);
			playSound(ResourcesBeta.getSound("LaserSound"));
		}
		if (keyPressed(KeyEvent.VK_SPACE)){
			Sprite projectile = new Sprite(ResourcesBeta.getImage("ProjectileVertical"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			projectile.setVerticalSpeed(PROJECTILE_SPEED);
			PROJECTILE_GROUP.add(projectile);
			playSound(ResourcesBeta.getSound("LaserSound"));
		}
		if (keyPressed(KeyEvent.VK_M) && missileActive) {  
			Missile missile = new Missile(ResourcesBeta.getImage("Missile"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			missile.setHorizontalSpeed(PROJECTILE_SPEED);
			MISSILE_GROUP.add(missile);
			playSound(ResourcesBeta.getSound("MissileSound"));
		}
		if (keyPressed(KeyEvent.VK_B) && blackHoleActive) {  
			BlackHole blackHole = new BlackHole(ResourcesBeta.getImage("BlackHole"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			BLACK_HOLE_GROUP.add(blackHole);
			playSound(ResourcesBeta.getSound("MissileSound"));
		}

	}

	private void updateEntities() {
		updateScreenSprites();
		playersprite.setVerticalSpeed(0);
		for (Sprite as: ENEMY_GROUP.getSprites()) {
			if (as == null) 
				break;
			if (as instanceof Zipster) {
				if (((Zipster)(as)).willFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Zipster)(as)).fireLaser());
					playSound(ResourcesBeta.getSound("ZipsterLaserSound"));
				}
				as.setHorizontalSpeed(-((Zipster)(as)).getSpeed());
				((Zipster) as).setImages(new BufferedImage[]{ResourcesBeta.getAnimation("SpinningZipster")[((Zipster) as).getSpin()]});
				if (!((Zipster) as).isProximateToBlackHole())
					((Zipster) as).setSpin(0);
				((Zipster)as).setProximateToBlackHole(false);
			}
		}
		for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
			if (bp == null) 
				break;
			if (bp instanceof ReacherEye) {
				if (((ReacherEye)(bp)).willFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((ReacherEye)(bp)).fireBeam());
					playSound(ResourcesBeta.getSound("ReacherEyeBeamSound"));
				} 
				bp.setHorizontalSpeed(-((ReacherEye)(bp)).getSpeed());
			}
		}
		for (Sprite b: BOSS_GROUP.getSprites()) {
			if (b == null) 
				break;
			if (b instanceof Reacher) {
				if (((Reacher)(b)).topBeamWillFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Reacher)(b)).fireTopBeam());
					playSound(ResourcesBeta.getSound("ReacherBeamSound"));
				}
				if (((Reacher)(b)).bottomBeamWillFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Reacher)(b)).fireBottomBeam());
					playSound(ResourcesBeta.getSound("ReacherBeamSound"));
				} 
				if (((Reacher)(b)).redRayWillFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Reacher)(b)).fireRedRay());
					playSound(ResourcesBeta.getSound("ReacherRedRaySound"));
				} 
				b.setHorizontalSpeed(-((Reacher)(b)).getSpeed());
			}
		}
		resetSpriteSpeed(PROJECTILE_GROUP, 0.15);
		resetSpriteSpeed(ENEMY_PROJECTILE_GROUP, -0.15);
		resetSpriteSpeed(MISSILE_GROUP, 0.15);
		
		for (Sprite h: BLACK_HOLE_GROUP.getSprites()) {
			if (h == null) 
				break;
			if (h.isActive()) {
				((BlackHole)h).suckEnemies(ENEMY_GROUP);
				((BlackHole)h).setPlayerCompensationSpeed(0);
			}
		}

		if (keyDown(KeyEvent.VK_LEFT)){
			for (SpriteGroup sg: spriteGroupSpeedMap.keySet()) {
				moveSpriteGroup(sg, "right", spriteGroupSpeedMap.get(sg));
			}
			for (Sprite h: BLACK_HOLE_GROUP.getSprites()) {
				if (h == null)
					break;
				((BlackHole)h).setPlayerCompensationSpeed(1*PLAYER_SPEED);
			}
		}
		if (keyDown(KeyEvent.VK_RIGHT)){
			for (SpriteGroup sg: spriteGroupSpeedMap.keySet()) {
				moveSpriteGroup(sg, "left", spriteGroupSpeedMap.get(sg));
			}
			for (Sprite h: BLACK_HOLE_GROUP.getSprites()) {
				if (h == null)
					break;
				((BlackHole)h).setPlayerCompensationSpeed(-1*PLAYER_SPEED);
			}
		}
		if (keyDown(KeyEvent.VK_DOWN)){
			playersprite.setVerticalSpeed(PLAYER_SPEED);
		}
		if (keyDown(KeyEvent.VK_UP)){
			playersprite.setVerticalSpeed(-1*PLAYER_SPEED);
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
		if (BOSS_GROUP.isActive()) {
			//Change this method to accomodate more than one level
			Sprite reacherSprite = BOSS_GROUP.getSprites()[0];
			int reacherEyesDestroyed = 0;
			for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
				if (bp==null)
					break;
				if (bp instanceof ReacherEye && !bp.isActive())
					reacherEyesDestroyed++;
			}
			if (reacherEyesDestroyed == 1) {
				((Reacher)(reacherSprite)).setImages(new BufferedImage[]{ResourcesBeta.getAnimation("Reacher")[1]});
			} else if (reacherEyesDestroyed == 3) {
				((Reacher)(reacherSprite)).setImages(new BufferedImage[]{ResourcesBeta.getAnimation("Reacher")[2]}); 
			} else if (reacherEyesDestroyed == 5 && !reacherShieldsDepleted) {
				//Deplete shields, but do not reset to the "depleted shields" image more than once (the next
				//images should be of the Reacher's status becoming yellow, then red)
				((Reacher)(reacherSprite)).setImages(new BufferedImage[]{ResourcesBeta.getAnimation("Reacher")[3]});
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

//	private void initLevel(Collection<Sprite> sprites, Collection<Sprite> bossparts, Collection<Sprite> bosses) {
//		ENEMY_GROUP.clear();
//		BOSS_PART_GROUP.clear();
//		BOSS_GROUP.clear();
//		for (Sprite s: sprites) {
//			AnimatedSprite as = (AnimatedSprite)s;
//			as.setAnimate(true);
//			as.setLoopAnim(true);
//			ENEMY_GROUP.add(as);
//		}
//		for (Sprite s: bossparts) {
//			BOSS_PART_GROUP.add(s);
//		}
//		for (Sprite s: bosses) {
//			BOSS_GROUP.add(s);
//		}
//		reacherShieldsDepleted = false;
//	}

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
			playersprite.setLocation(playerInitialX, playerInitialY);
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
//			playSound(ResourcesBeta.getSound("OhManSound"));
//		}

	}

	public PlayField getPlayfield() {
		return this.myPlayfield;
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Grandius(), new Dimension(640,480), false);
		game.start();
	}

	public void updateScoreOnCollision(int points) {
		updateStat(myScore, points);
	}

	public void updateCashOnCollision(int cash) {
		updateStat(myCash, cash);
	}

	private void createComets() {
		//TODO Fix magic numbers
		for (int j = 0; j < NUM_COMETS; j++) { // create 500 background sprites
			Random valX = new Random();
			Random valY = new Random();
			double x = valX.nextDouble();
			double y = valY.nextDouble();
			Sprite backgroundSprite = new Sprite(ResourcesBeta.getImage("Comet"),
					(x * 10000), (y * 480));
			backgroundSprite.setHorizontalSpeed(-0.18);
			myPlayfield.add(backgroundSprite);
		}
	}

	public void addOverlays() {
		OverlayIcon livesCounter = new OverlayIcon(myLives, livesIcon, "Lives");
		OverlayStat scoreCounter = new OverlayStat("Score", myScore);
		OverlayStat cashCounter = new OverlayStat("Cash", myCash);

	    myPanel.add(livesCounter);
	    myPanel.add(cashCounter);		
		myPanel.add(scoreCounter);
		myPanel.initialize();
		
		myPlayfield.addGroup(myPanel);
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