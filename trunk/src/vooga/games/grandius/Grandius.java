package vooga.games.grandius;

import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

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
	
	private PlayField myPlayfield;
	private Background myBackground;

	private SpriteGroup PLAYER_GROUP;
	private SpriteGroup PROJECTILE_GROUP;
	private SpriteGroup ENEMY_GROUP;
	private SpriteGroup BOSS_PART_GROUP;

	private Sprite shipsprite;
	private PlayerSprite playersprite;
	//private AnimatedSprite zipster;
	//private AnimatedSprite boomer;
	
//	private ShipSprite myShip;
	
	private PlayerEnemyCollision collision;
	private ProjectileEnemyCollision projectileEnemyCollision;
	private ProjectileBossPartCollision projectileBossPartCollision;
	
	private OverlayPanel myOverlayPanel;
	private OverlayStatImage livesIcon;
	private Stat<Integer> myLives;
	private Stat<Integer> myScore;
	private Stat<Integer> myCash;
	
	private GameFont font;
	private double playerInitialX;
	private double playerInitialY;
	
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
		playerInitialX = 5;
		playerInitialY = 5;
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
		
//		myShip = new ShipSprite(playersprite);

		PLAYER_GROUP = myPlayfield.addGroup(new SpriteGroup("Player"));
		PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Projectile"));
		ENEMY_GROUP = myPlayfield.addGroup(new SpriteGroup("Enemy"));
		BOSS_PART_GROUP = myPlayfield.addGroup(new SpriteGroup("Boss"));

		PLAYER_GROUP.add(playersprite);

		AnimatedSprite zipster1 = new AnimatedSprite(Resources.getAnimation("Zipster"), 300, 150);
		AnimatedSprite zipster2 = new AnimatedSprite(Resources.getAnimation("Zipster"), 300, 250);
		AnimatedSprite zipster3 = new AnimatedSprite(Resources.getAnimation("Zipster"), 300, 350);
		AnimatedSprite boomer1 = new AnimatedSprite(Resources.getAnimation("Boomer"), 100, 200);
		AnimatedSprite boomer2 = new AnimatedSprite(Resources.getAnimation("Boomer"), 100, 300);
		AnimatedSprite boomer3 = new AnimatedSprite(Resources.getAnimation("Boomer"), 100, 400);
		zipster1.setAnimate(true);
		zipster1.setLoopAnim(true);
		zipster2.setAnimate(true);
		zipster2.setLoopAnim(true);
		zipster3.setAnimate(true);
		zipster3.setLoopAnim(true);
		boomer1.setAnimate(true);
		boomer1.setLoopAnim(true);
		boomer2.setAnimate(true);
		boomer2.setLoopAnim(true);
		boomer3.setAnimate(true);
		boomer3.setLoopAnim(true);
		int[] reacherEyeBreakpoints = new int[2];
		reacherEyeBreakpoints[0] = 60;
		reacherEyeBreakpoints[1] = 30;
		Sprite reacherEye1 = new BossPart(Resources.getAnimation("ReacherEye"), reacherEyeBreakpoints, 400, 100, 100, 50);
		Sprite reacherEye2 = new BossPart(Resources.getAnimation("ReacherEye"), reacherEyeBreakpoints, 400, 300, 100, 50);
		
		zipster1.setHorizontalSpeed(-0.03);
		zipster2.setHorizontalSpeed(-0.03);
		zipster3.setHorizontalSpeed(-0.03);
		boomer1.setHorizontalSpeed(-0.01);
		boomer2.setHorizontalSpeed(-0.01);
		boomer3.setHorizontalSpeed(-0.01);
		
		ENEMY_GROUP.add(zipster1);
		ENEMY_GROUP.add(zipster2);
		ENEMY_GROUP.add(zipster3);
		ENEMY_GROUP.add(boomer1);
		ENEMY_GROUP.add(boomer2);
		ENEMY_GROUP.add(boomer3);
		
		BOSS_PART_GROUP.add(reacherEye1);
		BOSS_PART_GROUP.add(reacherEye2);
		//int startX = 10, startY = 30;     // starting coordinate
		//for (int j=0;j < 4;j++) {         // 4 rows
		//	for (int i=0;i < 7;i++) {     // 7 sprites in a row
		//		Sprite enemy = new Sprite(image, startX+(i*80), startY+(j*70));
		//		enemy.setHorizontalSpeed(0.04);
		//		ENEMY_GROUP.add(enemy);
		//	}
		//}

		// init the timer to control enemy sprite behaviour
		// (moving left-to-right, right-to-left)
		//moveTimer = new Timer(2000); // every 2 secs the enemies reverse its speed


		// register collision
		collision = new PlayerEnemyCollision(this);
		projectileEnemyCollision = new ProjectileEnemyCollision(this);
		projectileBossPartCollision = new ProjectileBossPartCollision(this);
		// register collision to playfield
		myPlayfield.addCollisionGroup(PLAYER_GROUP, ENEMY_GROUP, collision);
		myPlayfield.addCollisionGroup(PROJECTILE_GROUP, ENEMY_GROUP, projectileEnemyCollision);
		myPlayfield.addCollisionGroup(PROJECTILE_GROUP, BOSS_PART_GROUP, projectileBossPartCollision);


//		font = fontManager.getFont(getImages("resources/font.png", 20, 3),
//				" !            .,0123" +
//				"456789:   -? ABCDEFG" +
//		"HIJKLMNOPQRSTUVWXYZ ");
		
	}

	@Override
	public void render(Graphics2D g) {
		myPlayfield.render(g);

		// draw info text
//		font.drawString(g, "ARROW KEY : MOVE", 10, 10);
//		font.drawString(g, "CONTROL   : FIRE", 10, 30);
//		font.drawString(g, "ENTER     : TOGGLE PPC", 10, 50);
//
//		if (collision.pixelPerfectCollision) {
//			font.drawString(g, " USE PIXEL PERFECT COLLISION ", GameFont.RIGHT, 0, 460, getWidth());
//		}
	}

	@Override
	public void update(long elapsedTime) {
		updatePlayerSpeed();
		shootEnemy();
		for(Overlay overlay : myOverlayPanel.getOverlays())
		{
			overlay.update(elapsedTime);
		}
		
		// playfield updates all things and checks for collisions
		myPlayfield.update(elapsedTime);

		// enemy sprite movement timer
//		if (moveTimer.action(elapsedTime)) {
//			// reverse all enemies' speed
//			Sprite[] sprites = ENEMY_GROUP.getSprites();
//			int size = ENEMY_GROUP.getSize();
//
//			// iterate the sprites
//			for (int i=0;i < size;i++) {
//				// reverse sprite velocity
//				sprites[i].setHorizontalSpeed(-sprites[i].getHorizontalSpeed());
//			}
//		}




//		// firing!!
//		if (keyPressed(KeyEvent.VK_CONTROL)) {
//			// create projectile sprite
//			Sprite projectile = new Sprite(Resources.getImage("Projectile"));
////			projectile.setLocation(myShip.getSprite().getX()+myShip.getSprite().getWidth(), myShip.getSprite().getY());
//			projectile.setHorizontalSpeed(0.2);
//
//			// add it to PROJECTILE_GROUP
//			PROJECTILE_GROUP.add(projectile);
//
//			// play laser sound
//			//playSound(Resources.getMapping("Laser"));
//		}


//		// toggle ppc
//		if (keyPressed(KeyEvent.VK_ENTER)) {
//			collision.pixelPerfectCollision = !collision.pixelPerfectCollision;
//		}


//		myBackground.setToCenter(myShip.getSprite());
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
			//TODO - modify this - add an end game screen, etc
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

