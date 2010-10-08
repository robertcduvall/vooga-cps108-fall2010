package vooga.games.grandius;

import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

import vooga.engine.level.LevelManager;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.*;
import vooga.engine.resource.Resources;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.object.collision.*;
import com.golden.gamedev.object.sprite.*;

import vooga.engine.overlay.*;
import vooga.games.grandius.collisions.PlayerEnemyCollision;
import vooga.games.grandius.collisions.ProjectileBossPartCollision;
import vooga.games.grandius.collisions.ProjectileEnemyCollision;
import vooga.games.grandius.enemy.boss.BossPart;

public class Grandius extends Game{

	private static final int INITIAL_PLAYER_HEALTH = 50;
	private static final int INITIAL_PLAYER_RANK = 1;
	private static final int INITIAL_PLAYER_LIVES = 3;
	private static final int INITIAL_ZERO = 0;
	private static final double bulletSpeed = 0.2;
	private static final double playerSpeed = 0.1;
	private static final int MENU = 0;
	private static final int GAME_PLAY = 1;

	private PlayField myPlayfield;
	private Background myBackground;

	private SpriteGroup PLAYER_GROUP;
	private SpriteGroup PROJECTILE_GROUP;
	private SpriteGroup ENEMY_GROUP;
	private SpriteGroup BOSS_PART_GROUP;

	private Sprite shipsprite;
	private PlayerSprite playersprite;

	private PlayerEnemyCollision collision;
	private ProjectileEnemyCollision projectileEnemyCollision;
	private ProjectileBossPartCollision projectileBossPartCollision;

	private OverlayPanel myOverlayPanel;
	private OverlayStatImage livesIcon;
	private Stat<Integer> myLives;
	private Stat<Integer> myScore;
	private Stat<Integer> myCash;

	private LevelManager levelManager;
//	private int myCurrentLevel;

	private GameFont font;
	private double playerInitialX;
	private double playerInitialY;
	private int gameState;
	private Dimension screenDimension;

	/**
	 * Initializes the lives, score, and
	 * cash trackers as well as the
	 * overlay panel.
	 */
	 public Grandius()
	{
		myOverlayPanel = new OverlayPanel(this, true);
		myLives = new Stat<Integer>(new Integer(INITIAL_PLAYER_LIVES));
		myScore = new Stat<Integer>(new Integer(INITIAL_ZERO));
		myCash = new Stat<Integer>(new Integer(INITIAL_ZERO));
	}

	 @Override
	 public void initResources() { 
		 Resources.setGame(this);
		 //TODO : assign appropriate values to playerInitialY and playerInitialX
		 gameState = 0;
		 playerInitialX = 5;
		 playerInitialY = 5;
		 screenDimension = new Dimension(640,480);
		 //Load the resourcelist.txt file to initialize resource mappings.
		 try {
			 Resources.loadFile("src/vooga/games/grandius/resources/resourcelist.txt");
		 } catch (IOException e) {
			 System.out.println("failed to load resource file");
		 }

		 livesIcon = new OverlayStatImage(Resources.getImage("PlayerShipSingle"));
		 OverlayIcon livesCounter = new OverlayIcon(myLives, livesIcon, "Lives");
		 OverlayStat scoreCounter = new OverlayStat("Score", myScore);
		 OverlayStat cashCounter = new OverlayStat("Cash", myCash);

		 myOverlayPanel.addOverlay(livesCounter, 0, 0);
		 myOverlayPanel.addOverlay(scoreCounter, 0, 0);
		 myOverlayPanel.addOverlay(cashCounter, 0, 0);

		 myOverlayPanel.initialize();

		 myPlayfield = new PlayField();

		 //TODO Scrolling background
		 myBackground = new ImageBackground(Resources.getImage("BG"), 640, 480);
		 myPlayfield.setBackground(myBackground);

		 shipsprite = new Sprite(Resources.getImage("PlayerShipSingle"));
		 playersprite = new PlayerSprite("ThePlayer", "alive", shipsprite, INITIAL_PLAYER_HEALTH, INITIAL_PLAYER_RANK);

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
		 
		 PLAYER_GROUP = myPlayfield.addGroup(new SpriteGroup("Player"));
		 PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Projectile"));
		 ENEMY_GROUP = myPlayfield.addGroup(new SpriteGroup("Enemy"));
		 BOSS_PART_GROUP = myPlayfield.addGroup(new SpriteGroup("Boss"));
		 PLAYER_GROUP.add(playersprite);

		 levelManager = new LevelManager();
//		 myCurrentLevel = 1;
		 
		 try {
			 levelManager.addLevels("src/vooga/games/grandius/resources");
		 } catch (IOException e) {
			 System.out.println("Levels not loaded correctly");
		 }
		 
		 initLevel(levelManager.currentLevel());

//		 int[] reacherEyeBreakpoints = new int[2];
//		 reacherEyeBreakpoints[0] = 60;
//		 reacherEyeBreakpoints[1] = 30;
//		 Sprite reacherEye1 = new BossPart(Resources.getAnimation("ReacherEye"),
//				 reacherEyeBreakpoints, 400, 100, 100, 50);
//		 Sprite reacherEye2 = new BossPart(Resources.getAnimation("ReacherEye"),
//				 reacherEyeBreakpoints, 400, 300, 100, 50);

//		 BOSS_PART_GROUP.add(reacherEye1);
//		 BOSS_PART_GROUP.add(reacherEye2);

		 // register collisions
		 collision = new PlayerEnemyCollision(this);
		 projectileEnemyCollision = new ProjectileEnemyCollision(this);
		 projectileBossPartCollision = new ProjectileBossPartCollision(this);
		 // register collisions to playfield
		 myPlayfield.addCollisionGroup(PLAYER_GROUP, ENEMY_GROUP, collision);
		 myPlayfield.addCollisionGroup(PROJECTILE_GROUP, ENEMY_GROUP, projectileEnemyCollision);
		 
		 //TODO - 
		 myPlayfield.addCollisionGroup(PROJECTILE_GROUP, BOSS_PART_GROUP, projectileBossPartCollision);


		               font = fontManager.getFont(getImages("resources/font.png", 20, 3),
		                               " !            .,0123" +
		                               "456789:   -? ABCDEFG" +
		               "HIJKLMNOPQRSTUVWXYZ ");

	 }

	 @Override
	 public void render(Graphics2D g) {
		 
		if (gameState == MENU) {
			// TODO - draw info text
			font.drawString(g, "ARROW KEY : MOVE",
					(int) screenDimension.getWidth()/3,
					(int) screenDimension.getHeight() / 3);
			font.drawString(g, "CONTROL   : FIRE",
					(int) screenDimension.getWidth()/3,
					(int) (screenDimension.getHeight() / 2.5));
			font.drawString(g, "CLICK TO PLAY",
					(int) screenDimension.getWidth() / 3,
					(int) screenDimension.getHeight() / 2);
		}
		if (gameState == GAME_PLAY) {
			myPlayfield.render(g);
		}

		

	 }

	 @Override
	 public void update(long elapsedTime) {
		 if ( gameState == MENU){
			 if(click()){
				 gameState = GAME_PLAY;
			 }
		 }
		 if ( gameState == GAME_PLAY){
			 
			 updatePlayerSpeed();
			 shootEnemy();
			 if (checkCleared()) {
				 initLevel(levelManager.nextLevel());
				 //myCurrentLevel++;
			 }
			 for(Overlay overlay : myOverlayPanel.getOverlays())
			 {
				 overlay.update(elapsedTime);
			 }
			 // playfield updates all things and checks for collisions
			 myPlayfield.update(elapsedTime);
		 }
	 }

	 /**
	  * Use CTRL key to fire a bullet
	  */
	 private void shootEnemy() {
		 if (keyPressed(KeyEvent.VK_CONTROL)) {
			 Sprite projectile = new Sprite(Resources.getImage("Projectile"),playersprite.getX()+playersprite.getWidth(),playersprite.getY());
			 projectile.setHorizontalSpeed(bulletSpeed);
			 PROJECTILE_GROUP.add(projectile);

			 // play laser sound
			 playSound(Resources.getMapping("LaserSound"));
		 }

	 }

	 private void updatePlayerSpeed() {
		 // TODO avoid repeated code here
		 playersprite.setHorizontalSpeed(0);
		 playersprite.setVerticalSpeed(0);
		 if (keyDown(KeyEvent.VK_LEFT)){
			 playersprite.setHorizontalSpeed(-1*playerSpeed);
		 }
		 if (keyDown(KeyEvent.VK_RIGHT)){
			 playersprite.setHorizontalSpeed(playerSpeed);
		 }
		 if (keyDown(KeyEvent.VK_DOWN)){
			 playersprite.setVerticalSpeed(playerSpeed);
		 }
		 if (keyDown(KeyEvent.VK_UP)){
			 playersprite.setVerticalSpeed(-1*playerSpeed);
		 }
	 }
	 
	 private boolean checkCleared() {
		 for (AnimatedSprite as: levelManager.currentLevel()) {
			 if (as.isActive()) {
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 private void initLevel(Collection<AnimatedSprite> c) {
		 for (AnimatedSprite as: c) {
			 as.setAnimate(true);
			 as.setLoopAnim(true);
			 as.setHorizontalSpeed(-0.01);
			 ENEMY_GROUP.add(as);
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
		 stat.setStat(new Integer(stat.getStat().intValue()+addedValue));
	 }

	 public PlayField getPlayfield() {
		 return this.myPlayfield;
	 }

	 public void updatePlayerLives(){
		 int playerLives = playersprite.getLives();
		 if(playerLives>1){
			 playersprite.setLocation(playerInitialX, playerInitialY);
			 playersprite.setLives(playerLives-1);
			 System.out.println("lives left: "+playersprite.getLives());
		 }
		 else{
			 playersprite.setActive(false);
			 //TODO - modify this - add an`` end game screen, etc
			 this.stop();
		 }
	 }

	 public static void main(String[] args) {
		 GameLoader game = new GameLoader();
		 game.setup(new Grandius(), new Dimension(640,480), false);
		 game.start();
	 }

	 public void updateScoreOnCollision() {
		 // TODO Auto-generated method stub

	 }

}