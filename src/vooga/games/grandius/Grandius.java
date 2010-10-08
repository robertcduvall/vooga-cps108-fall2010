package vooga.games.grandius;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import vooga.engine.level.LevelManager;
import vooga.engine.level.ScrollerLevel;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.*;
import vooga.engine.resource.GameClock;
import vooga.engine.resource.GameClockException;
import vooga.engine.resource.Resources;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

import vooga.engine.overlay.*;
import vooga.games.grandius.collisions.PlayerEnemyCollision;
import vooga.games.grandius.collisions.PlayerZipsterLaserCollision;
import vooga.games.grandius.collisions.ProjectileBossPartCollision;
import vooga.games.grandius.collisions.ProjectileEnemyCollision;
import vooga.games.grandius.enemy.boss.BossPart;
import vooga.games.grandius.enemy.common.Zipster;

public class Grandius extends Game{

	private static final int INITIAL_PLAYER_HEALTH = 50;
	private static final int INITIAL_PLAYER_RANK = 1;
	private static final int INITIAL_PLAYER_LIVES = 3;
	private static final int INITIAL_ZERO = 0;
	private static final double bulletSpeed = 0.2;
	private static final double playerSpeed = 0.1;
	private static final int MENU = 0;
	private static final int GAME_PLAY = 1;
	private static final int LEVEL_COMPLETE = 2;
	private static final int START_NEW_LEVEL = 3;
	private static final int GAME_COMPLETE = 4;

	private PlayField myPlayfield;
	private Background myBackground;

	private SpriteGroup PLAYER_GROUP;
	private SpriteGroup PROJECTILE_GROUP;
	private SpriteGroup ZIPSTER_LASER_GROUP;
	private SpriteGroup ENEMY_GROUP;
	private SpriteGroup BOSS_PART_GROUP;
	private SpriteGroup OVERLAYS_GROUP;

	private Sprite shipsprite;
	private PlayerSprite playersprite;

	private PlayerEnemyCollision collision;
	private ProjectileEnemyCollision projectileEnemyCollision;
	private ProjectileBossPartCollision projectileBossPartCollision;
	private PlayerZipsterLaserCollision playerZipsterLaserCollision;

	
	private OverlayStatImage livesIcon;
	private OverlayString gameOver = new OverlayString("GAME OVER",
			new Font("mine", Font.PLAIN, 30), java.awt.Color.RED);
	private Stat<Integer> myLives;
	private Stat<Integer> myScore;
	private Stat<Integer> myCash;

	private LevelManager levelManager;

	private GameFont font;
	private double playerInitialX;
	private double playerInitialY;
	private int gameState;
	private Dimension screen;
	
	//Cheat options
	private boolean isInvincible = false;
	private boolean skipLevel = false;

	/**
	 * Initializes the lives, score, and
	 * cash trackers as well as the
	 * overlay panel.
	 */
	 public Grandius()
	{
		OVERLAYS_GROUP = new SpriteGroup("overlays");
		myLives = new Stat<Integer>(new Integer(INITIAL_PLAYER_LIVES));
		myScore = new Stat<Integer>(new Integer(INITIAL_ZERO));
		myCash = new Stat<Integer>(new Integer(INITIAL_ZERO));
	}

	 @Override
	 public void initResources() { 
		 Resources.setGame(this);
		 gameState = 0;
		 screen = new Dimension(640,480);
		 playerInitialX = 0;
		 playerInitialY = screen.getHeight()/2;
		 //Load the resourcelist.txt file to initialize resource mappings.
		 try {
			 Resources.loadFile("src/vooga/games/grandius/resources/resourcelist.txt");
		 } catch (IOException e) {
			 System.out.println("failed to load resource file");
		 }

		 livesIcon = new OverlayStatImage(Resources.getImage("PlayerShipSingle"));

		 myPlayfield = new PlayField();
		 addOverlays();
		 
		 //TODO Scrolling background
		 myBackground = new ImageBackground(Resources.getImage("BG"), 640, 480);
		 myPlayfield.setBackground(myBackground);

		 shipsprite = new Sprite(Resources.getImage("PlayerShipSingle"),playerInitialX,playerInitialY);
		 playersprite = new PlayerSprite("ThePlayer", "alive", shipsprite, INITIAL_PLAYER_HEALTH, INITIAL_PLAYER_RANK);
		 createComets();
		 
		 PLAYER_GROUP = myPlayfield.addGroup(new SpriteGroup("Player"));
		 PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Projectile"));
		 ZIPSTER_LASER_GROUP = myPlayfield.addGroup(new SpriteGroup("ZipsterLaser"));
		 ENEMY_GROUP = myPlayfield.addGroup(new SpriteGroup("Enemy"));
		 BOSS_PART_GROUP = myPlayfield.addGroup(new SpriteGroup("Boss"));
		 PLAYER_GROUP.add(playersprite);

		 levelManager = new LevelManager();
		 
		 try {
			 levelManager.addLevels("src/vooga/games/grandius/resources");
		 } catch (IOException e) {
			 System.out.println("Levels not loaded correctly");
		 }
		 System.out.println("initializing level 1");
		 initLevel(levelManager.currentLevel().get(0), levelManager.currentLevel().get(1));

		 // register collisions
		 collision = new PlayerEnemyCollision(this);
		 projectileEnemyCollision = new ProjectileEnemyCollision(this);
		 projectileBossPartCollision = new ProjectileBossPartCollision(this);
		 playerZipsterLaserCollision = new PlayerZipsterLaserCollision(this);
		 // register collisions to playfield
		 myPlayfield.addCollisionGroup(PLAYER_GROUP, ENEMY_GROUP, collision);
		 myPlayfield.addCollisionGroup(PROJECTILE_GROUP, ENEMY_GROUP, projectileEnemyCollision);
		 myPlayfield.addCollisionGroup(PROJECTILE_GROUP, BOSS_PART_GROUP, projectileBossPartCollision);
		 myPlayfield.addCollisionGroup(PLAYER_GROUP, ZIPSTER_LASER_GROUP, playerZipsterLaserCollision);
		 
		 				//TODO - 
		               font = fontManager.getFont(getImages("resources/font.png", 20, 3),
		                               " !            .,0123" +
		                               "456789:   -? ABCDEFG" +
		               "HIJKLMNOPQRSTUVWXYZ ");

	 }

	private void createComets() {
		for (int j = 0; j < 200; j++) { // create 200 background sprites
			 Random valX = new Random();
			 Random valY = new Random();
			 double x = valX.nextDouble();
			 double y = valY.nextDouble();
			 Sprite backgroundSprite = new Sprite(Resources.getImage("Commet"),
					 (x * 3000), (y * 480));
			 backgroundSprite.setHorizontalSpeed(-0.09);
			 myPlayfield.add(backgroundSprite);
		 }
	}

	 @Override
	 public void render(Graphics2D g) {
		 
		if (gameState == MENU) {
			// TODO - replace Magic numbers
			font.drawString(g, "ARROW KEY : MOVE", (int) screen.getWidth() / 4, 150);
			font.drawString(g, "ALT   : FIRE HORIZONTALLY",(int) screen.getWidth() / 4, 200);
			font.drawString(g, "SPACE   : FIRE VERTICALLY",(int) screen.getWidth() / 4, 250);
			font.drawString(g, "CLICK TO PLAY", (int) screen.getWidth() / 4, 300);
		}
		
		if (gameState == GAME_PLAY) {
			myPlayfield.render(g);
		}
		
		if ( gameState == LEVEL_COMPLETE){
			myPlayfield.clearPlayField();
			myPlayfield.render(g);
			font.drawString(g, "LEVEL " + levelManager.getMyCurrentLevel() + " COMPLETE",
					(int) screen.getWidth() / 3,
					(int) (screen.getHeight() / 2.5));
			font.drawString(g, "CLICK TO PLAY",
					(int) screen.getWidth() / 3,
					(int) screen.getHeight() / 2);
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
		 
		 if ( gameState == MENU){
			 if(click()){
				 gameState = GAME_PLAY;
				 try {
						GameClock.start();
					} catch (GameClockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
		 }
		 if ( gameState == GAME_PLAY){	 
			 shootEnemy();
			 updateScreenSprites();
			 updatePlayerSpeed();
			 if (checkCleared()) {
				 gameState=LEVEL_COMPLETE;
			 }
			 
			 // playfield updates all things and checks for collisions
			 myPlayfield.update(elapsedTime);
		 }
		
		 if ( gameState == LEVEL_COMPLETE){
			 if(levelManager.getMyCurrentLevel()==3){
				 gameState = GAME_COMPLETE;
			 }
			 else if(click()){
				 gameState = START_NEW_LEVEL;
			 }
		 }
		 
		 if ( gameState == START_NEW_LEVEL){
			 try {
				GameClock.reset();
			} catch (GameClockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 ArrayList<ArrayList<Sprite>> nextLevel = levelManager.nextLevel();
			 PLAYER_GROUP.add(playersprite);
			 myPlayfield.addGroup(PLAYER_GROUP);
			 createComets();
			 initLevel(nextLevel.get(0), nextLevel.get(1));
			 addOverlays();
			 gameState = GAME_PLAY;
		 }
	 }

	 private void updateScreenSprites() {
		 ENEMY_GROUP.clear();
		 Collection<Sprite> screenSprites = ( (ScrollerLevel)(levelManager.getCurrentLevel()) ).getCurrentScreenSprites(levelManager.currentLevel().get(0), playersprite.getX(), playersprite.getY());
		 for (Sprite s: screenSprites) {
			 ENEMY_GROUP.add(s);
		 }
		 BOSS_PART_GROUP.clear();
		 Collection<Sprite> screenBosses = ( (ScrollerLevel)(levelManager.getCurrentLevel()) ).getCurrentScreenSprites(levelManager.currentLevel().get(1), playersprite.getX(), playersprite.getY());
		 for (Sprite s: screenBosses) {
			 BOSS_PART_GROUP.add(s);
		 }
		 
	 }

	/**
	  * Use ALT key to fire a bullet
	  */
	 private void shootEnemy() {
		 // TODO - avoid repeated code
		 if (keyPressed(KeyEvent.VK_ALT)) {		
			 Sprite projectile = new Sprite(Resources.getImage("Projectile"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			 projectile.setHorizontalSpeed(bulletSpeed);
			 PROJECTILE_GROUP.add(projectile);
			 // play laser sound
			 playSound(Resources.getMapping("LaserSound"));

		 }
		 if (keyPressed(KeyEvent.VK_SPACE)){
			 Sprite projectile = new Sprite(Resources.getImage("ProjectileVertical"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			 projectile.setVerticalSpeed(bulletSpeed);
			 PROJECTILE_GROUP.add(projectile);
			 // play laser sound
			 playSound(Resources.getMapping("LaserSound"));
		 }
	 }

	 private void updatePlayerSpeed() {
		 // TODO avoid repeated code here
		 playersprite.setHorizontalSpeed(0);
		 playersprite.setVerticalSpeed(0);
		 for (Sprite as: ENEMY_GROUP.getSprites()) {
			 if (as == null) 
				 break;
			 if (as instanceof Zipster) {
				 if (((Zipster)(as)).willFire(playersprite)) {
					 ZIPSTER_LASER_GROUP.add(((Zipster)(as)).fireLaser());
					// play laser sound
					playSound(Resources.getMapping("LaserSound"));
				 }
			 }
//			 if (as instanceof Zipster) {
//				 if (as.getY() > playersprite.getY())
//					 ((Zipster)(as)).setDirection(Zipster.MOVING_NW);
//				 else if (as.getY() < playersprite.getY())
//					 ((Zipster)(as)).setDirection(Zipster.MOVING_SW);
//				 else 
//					 ((Zipster)(as)).setDirection(Zipster.MOVING_W);
//			 }
			 as.setHorizontalSpeed(-0.03);
		 }
		 for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
			 if (bp == null) 
				 break;
			 bp.setHorizontalSpeed(-0.01);
		 }
		 
		 if (keyDown(KeyEvent.VK_LEFT)){
			 //playersprite.setHorizontalSpeed(-1*playerSpeed);
			 for (Sprite as: ENEMY_GROUP.getSprites()) {
				 if (as == null) 
					 break;
				 as.setHorizontalSpeed(1*playerSpeed-0.03);
			 }
			 for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
				 if (bp == null) 
					 break;
				 bp.setHorizontalSpeed(1*playerSpeed-0.01);
			 }
		 }
		 if (keyDown(KeyEvent.VK_RIGHT)){
			 //playersprite.setHorizontalSpeed(playerSpeed);
			 for (Sprite as: ENEMY_GROUP.getSprites()) {
				 if (as == null) 
					 break;
				 as.setHorizontalSpeed(-1*playerSpeed-0.03);
			 }
			 for (Sprite bp: BOSS_PART_GROUP.getSprites()) {
				 if (bp == null) 
					 break;
				 bp.setHorizontalSpeed(-1*playerSpeed-0.01);
			 }
		 }
		 if (keyDown(KeyEvent.VK_DOWN)){
			 playersprite.setVerticalSpeed(playerSpeed);
		 }
		 if (keyDown(KeyEvent.VK_UP)){
			 playersprite.setVerticalSpeed(-1*playerSpeed);
		 }
	 }
	 
	 private boolean checkCleared() {
		 if(skipLevel)
		 {
			 skipLevel = false;
			 return true;
		 }
		 for (int i = 0; i < 2; i++) {
			 for (Sprite s: levelManager.currentLevel().get(i)) {
				 if (s.isActive()) {
					 return false;
				 }
			 }
		 }
		 return true;
	 }
	 
	 private void initLevel(Collection<Sprite> sprites, Collection<Sprite> bosses) {
		 System.out.println("initializing next level");
		 for (Sprite s: sprites) {
			 AnimatedSprite as = (AnimatedSprite)s;
			 as.setAnimate(true);
			 as.setLoopAnim(true);
			 ENEMY_GROUP.add(as);
		 }
		 for (Sprite s: bosses) {
			 BossPart bp = (BossPart)s;
			 BOSS_PART_GROUP.add(bp);
		 }
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

	 public PlayField getPlayfield() {
		 return this.myPlayfield;
	 }

	 public void updatePlayerLives(){
		 int playerLives = (myLives.getStat()).intValue();
		 if(playerLives > 0){
			 updateStat(myLives, (-1));
			 playersprite.setLocation(playerInitialX, playerInitialY);
//			 System.out.println("Lives left: "+playersprite.getLives());
		 }
		 else{
			 playersprite.setActive(false);
			 //TODO - modify this - add an`` end game screen, etc
			 myPlayfield.clearPlayField();
			 gameOver.setLocation(screen.getWidth()/2, screen.getHeight()/2);
			 myPlayfield.add(gameOver);
//			 this.stop();
		 }
		 
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

	 public void addOverlays() {
		 OverlayIcon livesCounter = new OverlayIcon(myLives, livesIcon, "Lives");
		 livesCounter.setLocation(5, 5);
		 OverlayStat scoreCounter = new OverlayStat("Score", myScore);
		 scoreCounter.setLocation(screen.getWidth() - 150, 5);
		 OverlayStat cashCounter = new OverlayStat("Cash", myCash);
		 cashCounter.setLocation(screen.getWidth()/2, 5);

		 OVERLAYS_GROUP.add(livesCounter);
		 OVERLAYS_GROUP.add(scoreCounter);
		 OVERLAYS_GROUP.add(cashCounter);
		 
		 myPlayfield.addGroup(OVERLAYS_GROUP);
	 }
}