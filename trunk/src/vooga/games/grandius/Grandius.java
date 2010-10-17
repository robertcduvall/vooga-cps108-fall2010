package vooga.games.grandius;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import vooga.engine.level.LevelManager;
import vooga.engine.overlay.*;
import vooga.engine.player.control.*;
import vooga.engine.resource.GameClock;
import vooga.engine.resource.GameClockException;
import vooga.engine.resource.Resources;

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

public class Grandius extends Game {

	private static final int PLAYER_INITIAL_X = 15;
	private static final int INITIAL_PLAYER_HEALTH = 50;
	private static final int INITIAL_PLAYER_RANK = 1;
	private static final int INITIAL_PLAYER_LIVES = 3;
	private static final int INITIAL_ZERO = 0;
	private static final int MENU = 0;
	private static final int GAME_PLAY = 1;
	private static final int LEVEL_COMPLETE = 2;
	private static final int START_NEW_LEVEL = 3;
	private static final int GAME_COMPLETE = 4;
	private static final int SHOPPING_LEVEL = 5;
	private static final int LAST_LEVEL = 3;
	private static final int NUM_COMETS = 1500;
	
	private static final double BULLET_SPEED = 0.2;
	private static final double PLAYER_SPEED = 0.1;
	
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
	private OverlayString gameOver = new OverlayString("GAME OVER",
			new Font("mine", Font.PLAIN, 30), java.awt.Color.RED);
	private Stat<Integer> myLives;
	private Stat<Integer> myScore;
	private Stat<Integer> myCash;

	private GrandiusLevelManager grandiusLevelManager;

	private GameFont font;
	private double playerInitialX;
	private double playerInitialY;
	private int gameState;
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

	/**
	 * Initializes the lives, score, and
	 * cash trackers as well as the
	 * overlay panel.
	 */
	public Grandius()
	{
		myPanel = new OverlayPanel(this, true);
//		OVERLAYS_GROUP = new SpriteGroup("overlays");
		myLives = new Stat<Integer>(new Integer(INITIAL_PLAYER_LIVES));
		myScore = new Stat<Integer>(new Integer(INITIAL_ZERO));
		myCash = new Stat<Integer>(new Integer(INITIAL_ZERO));
	}

	@Override
	public void initResources() { 
		Resources.setGame(this);
		gameState = 0;
		screen = new Dimension(640,480);
		playerInitialX = PLAYER_INITIAL_X;
		playerInitialY = screen.getHeight()/2;

		//Load the resourcelist.txt file to initialize resource mappings.
		try {
			//TODO Modify resources file so resources/images doesn't need to be typed every time
			Resources.loadFile("src/vooga/games/grandius/resources/resourcelist.txt");
		} catch (IOException e) {
			System.out.println("failed to load resource file");
		}

		livesIcon = new OverlayStatImage(Resources.getImage("PlayerShipSingle"));

		myPlayfield = new PlayField();
		addOverlays();

		myBackground = new ImageBackground(Resources.getImage("BG"), 640, 480);
		myPlayfield.setBackground(myBackground);

		shipsprite = new Sprite(Resources.getImage("PlayerShipSingle"),playerInitialX,playerInitialY);
		playersprite = new PlayerSprite("ThePlayer", "alive", shipsprite, INITIAL_PLAYER_HEALTH, INITIAL_PLAYER_RANK);
		createComets();

		PLAYER_GROUP = myPlayfield.addGroup(new SpriteGroup("Player"));
		PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Projectile"));
		ENEMY_PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("EnemyProjectile"));
		ENEMY_GROUP = myPlayfield.addGroup(new SpriteGroup("Enemy"));
		BOSS_PART_GROUP = myPlayfield.addGroup(new SpriteGroup("BossPart"));
		BOSS_GROUP = myPlayfield.addGroup(new SpriteGroup("Boss"));
		MISSILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Missile"));
		BLACK_HOLE_GROUP = myPlayfield.addGroup(new SpriteGroup("BlackHole"));
		PLAYER_GROUP.add(playersprite);

		grandiusLevelManager = new GrandiusLevelManager();
		reacherShieldsDepleted = false;

		try {
			grandiusLevelManager.addLevels("src/vooga/games/grandius/resources/levels");
		} catch (IOException e) {
			System.out.println("Levels not loaded correctly");
		}

		//Initialize the first level.
		initLevel(grandiusLevelManager.currentLevel().get(0), grandiusLevelManager.currentLevel().get(1), grandiusLevelManager.currentLevel().get(2));

		//register collisions
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

		//register collisions to playfield
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

		//TODO - change to work with Resources class
		font = fontManager.getFont(getImages("resources/images/font.png", 20, 3),
				" !            .,0123" +
				"456789:   -? ABCDEFG" +
		"HIJKLMNOPQRSTUVWXYZ ");
	}

	@Override
	public void render(Graphics2D g) {

		if (gameState == MENU) {
			// TODO - replace Magic numbers
			font.drawString(g, "WELCOME TO THE GRANDIUS GALAXY", (int) screen.getWidth() / 7, 50);
			font.drawString(g, "YOUR MISSION: DESTROY ALL ENEMIES", (int) screen.getWidth() / 7, 100);
			font.drawString(g, "ARROW KEY : MOVE", (int) screen.getWidth() / 4, 150);
			font.drawString(g, "ALT   : FIRE HORIZONTALLY",(int) screen.getWidth() / 4, 200);
			font.drawString(g, "SPACE   : FIRE VERTICALLY",(int) screen.getWidth() / 4, 250);
			font.drawString(g, "M: FIRE MISSILE - ONCE PURCHASED",(int) screen.getWidth() / 8, 300);
			font.drawString(g, "B: CREATE BLACK HOLE - ONCE PURCHASED",(int) screen.getWidth() / 13, 350);
			font.drawString(g, "CLICK TO PLAY", (int) screen.getWidth() / 4, 400);
		}

		if (gameState == GAME_PLAY) {
			myPlayfield.render(g);
		}

		if (gameState == LEVEL_COMPLETE){
			myPlayfield.clearPlayField();
			myPlayfield.render(g);
			font.drawString(g, "LEVEL " + grandiusLevelManager.getMyCurrentLevel() + " COMPLETE",
					(int) screen.getWidth() / 3,
					(int) (screen.getHeight() / 2.5));
			font.drawString(g, "CLICK FOR SHOPPING LEVEL",
					(int) screen.getWidth() / 3,
					(int) screen.getHeight() / 2);
		}
		if (gameState == SHOPPING_LEVEL){
			myPlayfield.clearPlayField();
			myPlayfield.render(g);
			font.drawString(g, "CASH: " + myCash.getStat().intValue(),
					(int) screen.getWidth()/3,
					(int) (screen.getHeight()/5));
			font.drawString(g, "CLICK HERE TO BUY MISSILE - 500",
					(int) screen.getWidth()/10,
					(int) screen.getHeight()/4);
			font.drawString(g, "CLICK HERE TO BUY BLACK HOLE - 1000",
					(int) screen.getWidth()/10,
					(int) screen.getHeight()/3);
			font.drawString(g, "OR HIT SPACEBAR FOR NEXT LEVEL",
					(int) screen.getWidth()/6,
					(int) screen.getHeight()/2);
		}

		if (gameState == GAME_COMPLETE) {
			myPlayfield.clearPlayField();
			myPlayfield.render(g);
			font.drawString(g, "GAME COMPLETE",
					(int) screen.getWidth() / 3,
					(int) (screen.getHeight() / 2.5));
			font.drawString(g, "YOU WIN",
					(int) screen.getWidth() / 3,
					(int) screen.getHeight() / 2);
			this.stop();
		}
	}

	@Override
	public void update(long elapsedTime) {

		//TODO Utilize VOOGA State API
		if (gameState == MENU){
			if(click()){
				gameState = GAME_PLAY;
				playSound(Resources.getMapping("WatchThisSound"));
				playSound(Resources.getMapping("StartLevelSound"));
				try {
					//TODO Understand GameClock's role
					GameClock.start();
				} catch (GameClockException e) {
					e.printStackTrace();
				}
			}
		}

		if (gameState == GAME_PLAY){	 
			fireWeapon();
			updateScreenSprites();
			updateEnemies();
			checkCheats();
			checkBossParts();
			if (checkCleared()) {
				gameState=LEVEL_COMPLETE;
			}
			// Playfield updates all things and checks for collisions
			myPlayfield.update(elapsedTime);
		}

		if (gameState == LEVEL_COMPLETE){
			if(grandiusLevelManager.getMyCurrentLevel()==LAST_LEVEL){
				gameState = GAME_COMPLETE;
			}
			else if(click()){
				gameState = SHOPPING_LEVEL;
			}
		}

		if (gameState == SHOPPING_LEVEL){
			if(click()){
				if((this.getMouseX()>screen.getWidth()/3) &&
						(this.getMouseX()<screen.getWidth()-100)){
					if((this.getMouseY()>screen.getHeight()/4) &&
							(this.getMouseY()<screen.getHeight()/3)){
						missileActive = true;
						updateStat(myCash,-500);
						gameState = START_NEW_LEVEL;
					}
					if((this.getMouseY()>screen.getHeight()/3) &&
							(this.getMouseY()<screen.getHeight()/2)){
						blackHoleActive = true;
						updateStat(myCash,-1000);
						gameState = START_NEW_LEVEL;
					}
				}

			}
			if (keyPressed(KeyEvent.VK_SPACE)){
				gameState = START_NEW_LEVEL;
			}
		}

		if (gameState == START_NEW_LEVEL){
			try {
				//TODO Understand GameClock's role
				GameClock.reset();
			} catch (GameClockException e) {
				e.printStackTrace();
			}
			ArrayList<ArrayList<Sprite>> nextLevel = grandiusLevelManager.nextLevel();
			PLAYER_GROUP.add(playersprite);
			myPlayfield.addGroup(PLAYER_GROUP);
			createComets();
			initLevel(nextLevel.get(0), nextLevel.get(1), nextLevel.get(2));
			addOverlays();
			playSound(Resources.getMapping("StartLevelSound"));
			gameState = GAME_PLAY;
		}
	}

	//TODO Modify Level.getCurrentScreenSprites() to actually determine which sprites should be visible?
	private void updateScreenSprites() {
		ENEMY_GROUP.clear();
		Collection<Sprite> screenSprites = ( (GrandiusLevel)(grandiusLevelManager.getCurrentLevel()) ).getCurrentScreenSprites(grandiusLevelManager.currentLevel().get(0), playersprite.getX(), playersprite.getY());
		for (Sprite s: screenSprites) {
			if (s==null) 
				break;
			ENEMY_GROUP.add(s);
		}
		BOSS_PART_GROUP.clear();
		Collection<Sprite> screenBossParts = ( (GrandiusLevel)(grandiusLevelManager.getCurrentLevel()) ).getCurrentScreenSprites(grandiusLevelManager.currentLevel().get(1), playersprite.getX(), playersprite.getY());
		for (Sprite s: screenBossParts) {
			if (s==null) 
				break;
			BOSS_PART_GROUP.add(s);
		}
		BOSS_GROUP.clear();
		Collection<Sprite> screenBosses = ( (GrandiusLevel)(grandiusLevelManager.getCurrentLevel()) ).getCurrentScreenSprites(grandiusLevelManager.currentLevel().get(2), playersprite.getX(), playersprite.getY());
		for (Sprite s: screenBosses) {
			if (s==null) 
				break;
			BOSS_GROUP.add(s);
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
			Sprite projectile = new Sprite(Resources.getImage("Projectile"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			projectile.setHorizontalSpeed(BULLET_SPEED);
			PROJECTILE_GROUP.add(projectile);
			playSound(Resources.getMapping("LaserSound"));
		}
		if (keyPressed(KeyEvent.VK_SPACE)){
			Sprite projectile = new Sprite(Resources.getImage("ProjectileVertical"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			projectile.setVerticalSpeed(BULLET_SPEED);
			PROJECTILE_GROUP.add(projectile);
			playSound(Resources.getMapping("LaserSound"));
		}
		if (keyPressed(KeyEvent.VK_M) && missileActive) {  
			Missile missile = new Missile(Resources.getImage("Missile"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			missile.setHorizontalSpeed(BULLET_SPEED);
			MISSILE_GROUP.add(missile);
			playSound(Resources.getMapping("MissileSound"));
		}
		if (keyPressed(KeyEvent.VK_B) && blackHoleActive) {  
			BlackHole blackHole = new BlackHole(Resources.getImage("BlackHole"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			BLACK_HOLE_GROUP.add(blackHole);
			playSound(Resources.getMapping("MissileSound"));
		}

	}

	//TODO Rename method? Or split into multiple methods?
	private void updateEnemies() {
		//TODO Make kamikaze weapon; sacrifice life to use
		// TODO avoid repeated code here
		//playersprite.setHorizontalSpeed(0);
		playersprite.setVerticalSpeed(0);
		for (Sprite as: ENEMY_GROUP.getSprites()) {
			if (as == null) 
				break;
			if (as instanceof Zipster) {
				if (((Zipster)(as)).willFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Zipster)(as)).fireLaser());
					playSound(Resources.getMapping("ZipsterLaserSound"));
				}
				as.setHorizontalSpeed(-((Zipster)(as)).getSpeed());
				//((Zipster) as).setSpin(0);
				((Zipster) as).setImages(new BufferedImage[]{Resources.getAnimation("SpinningZipster")[((Zipster) as).getSpin()]});
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
					playSound(Resources.getMapping("ReacherEyeBeamSound"));
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
					playSound(Resources.getMapping("ReacherBeamSound"));
				}
				if (((Reacher)(b)).bottomBeamWillFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Reacher)(b)).fireBottomBeam());
					playSound(Resources.getMapping("ReacherBeamSound"));
				} 
				if (((Reacher)(b)).redRayWillFire(playersprite)) {
					ENEMY_PROJECTILE_GROUP.add(((Reacher)(b)).fireRedRay());
					playSound(Resources.getMapping("ReacherRedRaySound"));
				} 
				b.setHorizontalSpeed(-((Reacher)(b)).getSpeed());
			}
		}
		for (Sprite p: PROJECTILE_GROUP.getSprites()) {
			if (p == null)
				break;
			p.setHorizontalSpeed(0.15);
		}

		for (Sprite ep: ENEMY_PROJECTILE_GROUP.getSprites()) {
			if (ep == null)
				break;
			ep.setHorizontalSpeed(-0.15);
		}

		for (Sprite h: BLACK_HOLE_GROUP.getSprites()) {
			if (h == null) 
				break; //Should this actually be a "continue"?
			if (h.isActive()) {
				((BlackHole)h).suckEnemies(ENEMY_GROUP);
				((BlackHole)h).setPlayerCompensationSpeed(0);
			}
		}

		if (keyDown(KeyEvent.VK_LEFT)){
			for (Sprite as: ENEMY_GROUP.getSprites()) {
				if (as == null) 
					break;
				as.setHorizontalSpeed(1*PLAYER_SPEED-0.015);
			}
			for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
				if (bp == null) 
					break;
				bp.setHorizontalSpeed(1*PLAYER_SPEED-0.01);
			}
			for (Sprite b: BOSS_GROUP.getSprites()) {
				if (b == null) 
					break;
				b.setHorizontalSpeed(1*PLAYER_SPEED-0.005);
			}
			for (Sprite p: PROJECTILE_GROUP.getSprites()) {
				if (p == null)
					break;
				p.setHorizontalSpeed(1*PLAYER_SPEED+0.15);
			}
			for (Sprite m: MISSILE_GROUP.getSprites()) {
				if (m == null)
					break;
				m.setHorizontalSpeed(1*PLAYER_SPEED+0.15);
			}
			for (Sprite h: BLACK_HOLE_GROUP.getSprites()) {
				if (h == null)
					break;
				((BlackHole)h).setPlayerCompensationSpeed(1*PLAYER_SPEED);
			}
			for (Sprite ep: ENEMY_PROJECTILE_GROUP.getSprites()) {
				if (ep == null)
					break;
				ep.setHorizontalSpeed(1*PLAYER_SPEED-0.15);
			}
		}
		if (keyDown(KeyEvent.VK_RIGHT)){
			for (Sprite as: ENEMY_GROUP.getSprites()) {
				if (as == null) 
					break;
				as.setHorizontalSpeed(-1*PLAYER_SPEED-0.015);
			}
			for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
				if (bp == null) 
					break;
				bp.setHorizontalSpeed(-1*PLAYER_SPEED-0.01);
			}
			for (Sprite b: BOSS_GROUP.getSprites()) {
				if (b == null) 
					break;
				b.setHorizontalSpeed(-1*PLAYER_SPEED-0.005);
			}
			for (Sprite p: PROJECTILE_GROUP.getSprites()) {
				if (p == null)
					break;
				p.setHorizontalSpeed(-1*PLAYER_SPEED+0.15);
			}
			for (Sprite m: MISSILE_GROUP.getSprites()) {
				if (m == null)
					break;
				m.setHorizontalSpeed(-1*PLAYER_SPEED+0.15);
			}
			for (Sprite h: BLACK_HOLE_GROUP.getSprites()) {
				if (h == null)
					break;
				((BlackHole)h).setPlayerCompensationSpeed(-1*PLAYER_SPEED);
			}
			for (Sprite ep: ENEMY_PROJECTILE_GROUP.getSprites()) {
				if (ep == null)
					break;
				ep.setHorizontalSpeed(-1*PLAYER_SPEED-0.15);
			}
		}
		if (keyDown(KeyEvent.VK_DOWN)){
			playersprite.setVerticalSpeed(PLAYER_SPEED);
		}
		if (keyDown(KeyEvent.VK_UP)){
			playersprite.setVerticalSpeed(-1*PLAYER_SPEED);
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
	
	private boolean checkCleared() {
		if(skipLevel)
		{
			skipLevel = false;
			return true;
		}
		for (int i = 0; i < 3; i++) {
			for (Sprite s: grandiusLevelManager.currentLevel().get(i)) {
				if (s.isActive()) {
					return false;
				}
			}
		}
		return true;
	}

	private void initLevel(Collection<Sprite> sprites, Collection<Sprite> bossparts, Collection<Sprite> bosses) {
		System.out.println("initializing next level");
		ENEMY_GROUP.clear();
		BOSS_PART_GROUP.clear();
		BOSS_GROUP.clear();
		for (Sprite s: sprites) {
			AnimatedSprite as = (AnimatedSprite)s;
			as.setAnimate(true);
			as.setLoopAnim(true);
			ENEMY_GROUP.add(as);
		}
		for (Sprite s: bossparts) {
			BOSS_PART_GROUP.add(s);
		}
		for (Sprite s: bosses) {
			BOSS_GROUP.add(s);
		}
		reacherShieldsDepleted = false;
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
			playersprite.setLocation(playerInitialX, playerInitialY);
		}
		//TODO: switch this to a game state?
		else if (playerLives==0){ //Game over.
			PLAYER_GROUP.setActive(false);
			PROJECTILE_GROUP.setActive(false);
			ENEMY_PROJECTILE_GROUP.setActive(false);
			ENEMY_GROUP.setActive(false);
			BOSS_PART_GROUP.setActive(false);
			MISSILE_GROUP.setActive(false);
			myPlayfield.clearPlayField();
			gameOver.setLocation(screen.getWidth() / 3, screen.getHeight() / 2);
			myPlayfield.add(gameOver);
			playSound(Resources.getMapping("OhManSound"));
		}

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
			Sprite backgroundSprite = new Sprite(Resources.getImage("Comet"),
					(x * 10000), (y * 480));
			backgroundSprite.setHorizontalSpeed(-0.18);
			myPlayfield.add(backgroundSprite);
		}
	}

	public void addOverlays() {
		OverlayIcon livesCounter = new OverlayIcon(myLives, livesIcon, "Lives");
		OverlayStat scoreCounter = new OverlayStat("Score", myScore);
		OverlayStat cashCounter = new OverlayStat("Cash", myCash);

		myPanel.addOverlay(livesCounter);
		myPanel.addOverlay(cashCounter);		
		myPanel.addOverlay(scoreCounter);
		myPanel.initialize();
		
		myPlayfield.addGroup(myPanel.getOverlayGroup());
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