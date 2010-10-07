package vooga.games.zombieland;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.core.Game;
import vooga.engine.player.control.*;

public class Zombieland extends Game {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;

	private AnimatedSprite shooterImage;
	private Background background;
	private Shooter player;
	
	private SpriteGroup zombies;
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
	
	private BufferedImage bulletImage;

	private double defaultX = 100;
	private double defaultY = 100;

	private ZZCollisionManager zombieZombieManager;
	

	
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

		bulletImage = getImage("resources/bullet.png");
		
		shooterImage = new AnimatedSprite(playerDownImage, 350, 250);
		player = new Shooter("Hero", "Down", shooterImage, 100, 0,this);
		player.mapNameToSprite("Up",getInitializedAnimatedSprite(playerUpImage));
		player.mapNameToSprite("Left",getInitializedAnimatedSprite(playerLeftImage));
		player.mapNameToSprite("Right",getInitializedAnimatedSprite(playerRightImage));
		player.mapNameToSprite("Down",getInitializedAnimatedSprite(playerDownImage));

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
		
		//Here's the manager in use.
//		zombieZombieManager = new ZZCollisionManager();
//		playfield.addCollisionGroup(zombies, zombies, zombieZombieManager);
		
	}

	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		control.update();
		player.update(elapsedTime);
		
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
		newZombie.setX(defaultX);
		newZombie.setY(defaultY);
		zombies.add(newZombie);
	}
	
	public void addBullet(Bullet b, double angle) {
		b.getCurrentSprite().setImage(ImageUtil.rotate(bulletImage, (int) angle));
		b.setActive(true);
		bullets.add(b);
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
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zombieland(), new Dimension(GAME_WIDTH, GAME_HEIGHT),false);
		game.start();
	}

}
