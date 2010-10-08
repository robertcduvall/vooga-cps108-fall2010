package vooga.games.zombieland;

import vooga.games.zombieland.resources.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayBar;
import vooga.engine.overlay.OverlayString;
import vooga.engine.player.control.KeyboardControl;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.util.ImageUtil;

public class Zombieland extends Game {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;

	private AnimatedSprite shooterImage;
	private Background background;
	private Shooter player;
	
	private SpriteGroup zombies;
	private SpriteGroup players;
	private SpriteGroup bullets;
	
	private PlayField playfield;
	private Timer counter;
	private KeyboardControl control;

	private BufferedImage[] playerUpImage;
	private BufferedImage[] playerDownImage;
	private BufferedImage[] playerLeftImage;
	private BufferedImage[] playerRightImage;

	private BufferedImage[] zombieUpImage;
	private BufferedImage[] zombieDownImage;
	private BufferedImage[] zombieLeftImage;
	private BufferedImage[] zombieRightImage;
	
	private BufferedImage[] zombieAttackFromAboveImage;
	private BufferedImage[] zombieAttackFromBelowImage;
	private BufferedImage[] zombieAttackFromLeftImage;
	private BufferedImage[] zombieAttackFromRightImage;
	
	private BufferedImage[] zombieDeath;
	
	
	private BufferedImage bulletImage;

	private double defaultX = 100;
	private double defaultY = 100;

	private ZZCollisionManager zombieZombieManager;
	private HZCollisionManager humanZombieManager;

	private OverlayBar scoreBar;
	private OverlayString scoreString;
	
	public void initResources() {

		playerDownImage = new BufferedImage[] {
				getImage("resources/Down1.png"),
				getImage("resources/Down2.png"),
				getImage("resources/Down3.png"),
				getImage("resources/Down4.png") };
		playerUpImage = new BufferedImage[] { 
				getImage("resources/up1.png"),
				getImage("resources/up2.png"), 
				getImage("resources/up3.png"),
				getImage("resources/up4.png") };
		playerLeftImage = new BufferedImage[] {
				getImage("resources/left1.png"),
				getImage("resources/left2.png"),
				getImage("resources/left3.png"),
				getImage("resources/left4.png") };
		playerRightImage = new BufferedImage[] {
				getImage("resources/right1.png"),
				getImage("resources/right2.png"),
				getImage("resources/right3.png"),
				getImage("resources/right4.png") };

		zombieDownImage = new BufferedImage[] {
				getImage("resources/ZombieDown1.png"),
				getImage("resources/ZombieDown2.png"),
				getImage("resources/ZombieDown3.png") };
		zombieUpImage = new BufferedImage[] {
				getImage("resources/ZombieUp1.png"),
				getImage("resources/ZombieUp2.png"),
				getImage("resources/ZombieUp3.png") };
		zombieLeftImage = new BufferedImage[] {
				getImage("resources/ZombieLeft1.png"),
				getImage("resources/ZombieLeft2.png"),
				getImage("resources/ZombieLeft3.png") };
		zombieRightImage = new BufferedImage[] {
				getImage("resources/ZombieRight1.png"),
				getImage("resources/ZombieRight2.png"),
				getImage("resources/ZombieRight3.png") };

//		zombieAttackFromAboveImage = new BufferedImage[] {
//				getImage("resources/ZombieAttackFromAbove1.png"),
//				getImage("resources/ZombieAttackFromAbove2.png"),
//				getImage("resources/ZombieAttackFromAbove3.png") };
//		zombieAttackFromBelowImage = new BufferedImage[] {
//				getImage("resources/ZombieAttackFromBelow1.png"),
//				getImage("resources/ZombieAttackFromBelow2.png"),
//				getImage("resources/ZombieAttackFromBelow3.png") };
//		zombieAttackFromLeftImage = new BufferedImage[] {
//				getImage("resources/ZombieAttackFromLeft1.png"),
//				getImage("resources/ZombieAttackFromLeft2.png"),
//				getImage("resources/ZombieAttackFromLeft3.png") };
//		zombieAttackFromRightImage = new BufferedImage[] {
//				getImage("resources/ZombieAttackFromRight1.png"),
//				getImage("resources/ZombieAttackFromRight2.png"),
//				getImage("resources/ZombieAttackFromRight3.png") };
		
		zombieDeath = new BufferedImage[] {
				getImage("resources/ZombieDeath1.png"),
				getImage("resources/ZombieDeath2.png"),
				getImage("resources/ZombieDeath3.png")};
		
		
		bulletImage = getImage("resources/bullet.png");
		
		shooterImage = new AnimatedSprite(playerDownImage, 350, 250);
		player = new Shooter("Hero", "Down", shooterImage, 100, 0,this);
		player.mapNameToSprite("Up",getInitializedAnimatedSprite(playerUpImage));
		player.mapNameToSprite("Left",getInitializedAnimatedSprite(playerLeftImage));
		player.mapNameToSprite("Right",getInitializedAnimatedSprite(playerRightImage));
		player.mapNameToSprite("Down",getInitializedAnimatedSprite(playerDownImage));

		scoreString = new OverlayString("Health: ", Color.BLUE);
		scoreBar = new OverlayBar(player.getStatHealth(),100);
		scoreBar.setColor(Color.GREEN);
		scoreBar.setLocation(75, 10);
		
		zombies = new SpriteGroup("Zombies");
		bullets = new SpriteGroup("Bullets");
		
		playfield = new PlayField();
		control = new KeyboardControl(player, this);
		background = new ColorBackground(Color.white);
		counter = new Timer(1000);

		playfield.add(player);
		playfield.addGroup(zombies);
		playfield.addGroup(bullets);
		playfield.setBackground(background);
		setListeners();
		
		
		//Here's are the managers in use.
		zombieZombieManager = new ZZCollisionManager();
		playfield.addCollisionGroup(zombies, zombies, zombieZombieManager);

		humanZombieManager = new HZCollisionManager();
		players = new SpriteGroup("Players");
		players.add(player);
		playfield.addCollisionGroup(players , zombies, humanZombieManager);
	}

	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		control.update();
		player.update(elapsedTime);
		scoreBar.update(elapsedTime);
		scoreString.update(elapsedTime);
		zombies.update(elapsedTime);
		bullets.update(elapsedTime);
		
		if (counter.action(elapsedTime)) {
			addZombie();
		}
	
	}

	public void addZombie() {
		Zombie newZombie = new Zombie("New", "Moving", 
				getInitializedAnimatedSprite(zombieDownImage), 
				getInitializedAnimatedSprite(zombieUpImage), 
				getInitializedAnimatedSprite(zombieLeftImage), 
				getInitializedAnimatedSprite(zombieRightImage), player);
		
//		newZombie.mapNameToSprite("AttackFromLeft" , 
//									getInitializedAnimatedSprite(zombieAttackFromLeftImage));
//		newZombie.mapNameToSprite("AttackFromRight" ,
//									getInitializedAnimatedSprite(zombieAttackFromRightImage));
//		newZombie.mapNameToSprite("AttackFromAbove" ,
//									getInitializedAnimatedSprite(zombieAttackFromAboveImage));
//		newZombie.mapNameToSprite("AttackFromBelow" , 
//									getInitializedAnimatedSprite(zombieAttackFromBelowImage));
		
		newZombie.mapNameToSprite("ZombieDeath", 
									getInitializedAnimatedSprite(zombieDeath));
		
		newZombie.setX(defaultX);
		newZombie.setY(defaultY);
		zombies.add(newZombie);
	}
	
	/**
	 * Load the image for a bullet with the correct orientation with respect to the 
	 * shooter and add it to the screen
	 * @param bullet a bullet instantiated by the shooter
	 * @param angle the orientation of the bullet (in degrees)
	 */
	public void addBullet(Bullet bullet, double angle) {
		bullet.getCurrentSprite().setImage(ImageUtil.rotate(bulletImage, (int) angle));
		bullet.setActive(true);
		bullets.add(bullet);
	}
	
	
	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, 300);
		return sprite;
	}

	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images,
			long delay) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, delay);
		return sprite;
	}

	private void initializeAnimatedSprite(AnimatedSprite sprite, long delay) {
		sprite.getAnimationTimer().setDelay(delay);
		sprite.setAnimationFrame(0, sprite.getImages().length - 1);
		sprite.setAnimate(true);
		sprite.setLoopAnim(true);
	}

	public void setListeners() {
		control.addInput(KeyEvent.VK_LEFT, "goLeft", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_RIGHT, "goRight", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_UP, "goUp", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_DOWN, "goDown", PLAYER_CLASS, null);
		control.addInput(KeyEvent.VK_SPACE, "shoot", PLAYER_CLASS, null);
	}

	public void render(Graphics2D g) {
		playfield.render(g);
		scoreBar.render(g);
		scoreString.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zombieland(), new Dimension(GAME_WIDTH, GAME_HEIGHT),false);
		game.start();
	}

}
