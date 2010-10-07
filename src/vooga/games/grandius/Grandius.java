package vooga.games.grandius;

import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

import vooga.engine.overlay.OverlayManager;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.*;
import vooga.engine.resource.ResourceHandler;

import com.golden.gamedev.*;
import com.golden.gamedev.engine.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.object.collision.*;
import com.golden.gamedev.object.sprite.*;

import vooga.engine.overlay.*;

public class Grandius extends Game{

	private static final int INITIAL_PLAYER_HEALTH = 50;
	private static final int INITIAL_PLAYER_RANK = 1;
	private static final int INITIAL_PLAYER_LIVES = 3;
	private static final int INITIAL_ZERO = 0;
	
	private PlayField myPlayfield;
	private Background myBackground;

	private SpriteGroup PLAYER_GROUP;
	private SpriteGroup PROJECTILE_GROUP;
	private SpriteGroup ENEMY_GROUP;

	private Sprite shipsprite;
	private PlayerSprite playersprite;
	private AnimatedSprite zipster;
	private AnimatedSprite boomer;
	
//	private ShipSprite myShip;
	
	private ProjectileEnemyCollision2 collision;
	
	private OverlayPanel myOverlayPanel;
	private OverlayStatImage livesIcon;
	private Stat<Integer> myLives;
	private Stat<Integer> myScore;
	private Stat<Integer> myCash;
	
	private GameFont font;
	
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
	
	public void initResources() { 
		//Load the resourcelist.txt file to initialize resource mappings.
		try {
        	ResourceHandler.loadFile("resources/resourcelist.txt");
        } catch (IOException e) {
        	
        }

		livesIcon = new OverlayStatImage(getImage(ResourceHandler.getMapping("PlayerShipSingle")));
		OverlayIcon livesCounter = new OverlayIcon(myLives, livesIcon, "Lives");
		OverlayStat scoreCounter = new OverlayStat("Score", myScore);
		OverlayStat cashCounter = new OverlayStat("Cash", myCash);
		
		myOverlayPanel.addOverlay(livesCounter, 0, 0);
		myOverlayPanel.addOverlay(scoreCounter, 0, 0);
		myOverlayPanel.addOverlay(cashCounter, 0, 0);
		
		myOverlayPanel.initialize();
		
		myPlayfield = new PlayField();
		
		//TODO Scrolling background
		myBackground = new ImageBackground(getImage(ResourceHandler.getMapping("BG")), 640, 480);
		myPlayfield.setBackground(myBackground);

		//Does PlayerSprite support animations? It extends GameEntitySprite, which extends the regular Sprite (not AnimatedSprite).
		//Confused about GameEntitySprite's documentation:
		/* It supports having
		 * multiple images (implemented as sprites) to represent a single GameEntity.
		 * This class extends Sprite so that it can act as a sprite, which is the unit
		 * that Golden-T is built around, for things like collision detection and being
		 * able to be added to SpriteGroups, but can also use multiple sprites (and
		 * different kind of sprites) to represent the entity.
		 */	
		//playersprite = new AnimatedSprite(getImages(ResourceHandler.getMapping("PlayerShip"), 3, 1), 0, 300);
		shipsprite = new Sprite(getImage(ResourceHandler.getMapping("PlayerShipSingle")));
		playersprite = new PlayerSprite("ThePlayer", "alive", shipsprite, INITIAL_PLAYER_HEALTH, INITIAL_PLAYER_RANK);
		//playersprite.setAnimate(true);
		//playersprite.setLoopAnim(true);
		
//		myShip = new ShipSprite(playersprite);

		PLAYER_GROUP = myPlayfield.addGroup(new SpriteGroup("Player"));
		PROJECTILE_GROUP = myPlayfield.addGroup(new SpriteGroup("Projectile"));
		ENEMY_GROUP = myPlayfield.addGroup(new SpriteGroup("Enemy"));

		PLAYER_GROUP.add(playersprite);

		Sprite zipster = new AnimatedSprite(getImages(ResourceHandler.getMapping("Zipster"), 2, 1), 400, 400);
		Sprite boomer = new AnimatedSprite(getImages(ResourceHandler.getMapping("Boomer"), 2, 1), 200, 200);
		zipster.setHorizontalSpeed(-0.08);
		boomer.setHorizontalSpeed(-0.03);
		ENEMY_GROUP.add(zipster);
		ENEMY_GROUP.add(boomer);
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
		collision = new ProjectileEnemyCollision2(this);
		// register collision to playfield
		myPlayfield.addCollisionGroup(PROJECTILE_GROUP, ENEMY_GROUP, collision);


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

		// control the sprite with arrow key
		double speedY = 0;
		if (keyDown(KeyEvent.VK_UP))   speedY = -0.3;
		if (keyDown(KeyEvent.VK_DOWN))  speedY = 0.3;
//		myShip.getSprite().setVerticalSpeed(speedY);


		// firing!!
		if (keyPressed(KeyEvent.VK_CONTROL)) {
			// create projectile sprite
			Sprite projectile = new Sprite(getImage(ResourceHandler.getMapping("Projectile")));
//			projectile.setLocation(myShip.getSprite().getX()+myShip.getSprite().getWidth(), myShip.getSprite().getY());
			projectile.setHorizontalSpeed(0.2);

			// add it to PROJECTILE_GROUP
			PROJECTILE_GROUP.add(projectile);

			// play laser sound
			playSound(ResourceHandler.getMapping("Laser"));
		}


		// toggle ppc
		if (keyPressed(KeyEvent.VK_ENTER)) {
			collision.pixelPerfectCollision = !collision.pixelPerfectCollision;
		}


//		myBackground.setToCenter(myShip.getSprite());
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
			playersprite.setLives(playerLives-1);			
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
	
}

class ProjectileEnemyCollision2 extends BasicCollisionGroup {

	Grandius    owner;

	public ProjectileEnemyCollision2(Grandius owner) {
		this.owner = owner; // set the game owner
		// we use this for getting image and
		// adding explosion to playfield
	}

	// when projectiles (in group a) collided with enemy (in group b)
	// what to do?
	public void collided(Sprite s1, Sprite s2) {
		// we kill/remove both sprite!
		s1.setActive(false); // the projectile is set to non-active
		s2.setActive(false); // the enemy is set to non-active

		// show explosion on the center of the exploded enemy
		// we use VolatileSprite -> sprite that animates once and vanishes afterward
		BufferedImage[] images = owner.getImages(ResourceHandler.getMapping("Explosion"), 1, 1);
		VolatileSprite explosion = new VolatileSprite(images, s2.getX(), s2.getY());

		// directly add to playfield without using SpriteGroup
		// the sprite is added into a reserved extra sprite group in playfield
		// extra sprite group is used especially for animation effects in game
		owner.getPlayfield().add(explosion);
	}
}