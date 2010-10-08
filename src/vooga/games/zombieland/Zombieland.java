package vooga.games.zombieland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.overlay.*;
import vooga.engine.player.control.KeyboardControl;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

public class Zombieland extends Game {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;

	private AnimatedSprite shooterImage;
	private ImageBackground background;
	private Shooter player;

	private SpriteGroup zombies;
	private SpriteGroup players;
	private SpriteGroup bullets;
	private SpriteGroup items;

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
	private BufferedImage shotgunImage;
	private BufferedImage assaultRifleImage;
	private BufferedImage healthImage;

	private double defaultX = 100;
	private double defaultY = 100;

	private ZZCollisionManager zombieZombieManager;
	private HZCollisionManager humanZombieManager;
	private BZCollisionManager bulletZombieManager;

	private OverlayBar healthBar;
	private OverlayString healthString;
	private OverlayStat scoreString;
	private OverlayString gameOver;
	private OverlayStat ammoString;
	
	public void initResources() {

		playerDownImage = new BufferedImage[] {
				getImage("resources/Down1.jpg"),
				getImage("resources/Down2.jpg"),
				getImage("resources/Down3.jpg"),
				getImage("resources/Down4.jpg") };
		playerUpImage = new BufferedImage[] { 
				getImage("resources/up1.jpg"),
				getImage("resources/up2.jpg"), 
				getImage("resources/up3.jpg"),
				getImage("resources/up4.jpg") };
		playerLeftImage = new BufferedImage[] {
				getImage("resources/left1.jpg"),
				getImage("resources/left2.jpg"),
				getImage("resources/left3.jpg"),
				getImage("resources/left4.jpg") };
		playerRightImage = new BufferedImage[] {
				getImage("resources/right1.jpg"),
				getImage("resources/right2.jpg"),
				getImage("resources/right3.jpg"),
				getImage("resources/right4.jpg") };

		zombieDownImage = new BufferedImage[] {
				getImage("resources/ZombieDown1.jpg"),
				getImage("resources/ZombieDown2.jpg"),
				getImage("resources/ZombieDown3.jpg") };
		zombieUpImage = new BufferedImage[] {
				getImage("resources/ZombieUp1.jpg"),
				getImage("resources/ZombieUp2.jpg"),
				getImage("resources/ZombieUp3.jpg") };
		zombieLeftImage = new BufferedImage[] {
				getImage("resources/ZombieLeft1.jpg"),
				getImage("resources/ZombieLeft2.jpg"),
				getImage("resources/ZombieLeft3.jpg") };
		zombieRightImage = new BufferedImage[] {
				getImage("resources/ZombieRight1.jpg"),
				getImage("resources/ZombieRight2.jpg"),
				getImage("resources/ZombieRight3.jpg") };

		zombieAttackFromAboveImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromAbove1.jpg"),
				getImage("resources/ZombieAttackFromAbove2.jpg"),
				getImage("resources/ZombieAttackFromAbove3.jpg") };
		zombieAttackFromBelowImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromBelow1.jpg"),
				getImage("resources/ZombieAttackFromBelow2.jpg"),
				getImage("resources/ZombieAttackFromBelow3.jpg") };
		zombieAttackFromLeftImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromLeft1.jpg"),
				getImage("resources/ZombieAttackFromLeft2.jpg"),
				getImage("resources/ZombieAttackFromLeft3.jpg") };
		zombieAttackFromRightImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromRight1.jpg"),
				getImage("resources/ZombieAttackFromRight2.jpg"),
				getImage("resources/ZombieAttackFromRight3.jpg") };

		zombieDeath = new BufferedImage[] {
				getImage("resources/ZombieDeath1.jpg"),
				getImage("resources/ZombieDeath2.jpg"),
				getImage("resources/ZombieDeath3.jpg")};

		BufferedImage sandbg = getImage("resources/redbackground.jpg");
		background = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);
		
		bulletImage = getImage("resources/bullet.jpg");
		shotgunImage = getImage("resources/shotgun.jpg");;
		assaultRifleImage= getImage("resources/assaultRifle.jpg");
		healthImage= getImage("resources/Health.png");

		shooterImage = new AnimatedSprite(playerDownImage, 350, 250);
		player = new Shooter("Hero", "Down", shooterImage, 100, 0,this);
		player.mapNameToSprite("Up",getInitializedAnimatedSprite(playerUpImage));
		player.mapNameToSprite("Left",getInitializedAnimatedSprite(playerLeftImage));
		player.mapNameToSprite("Right",getInitializedAnimatedSprite(playerRightImage));
		player.mapNameToSprite("Down",getInitializedAnimatedSprite(playerDownImage));

		healthString = new OverlayString("Health: ", Color.BLUE);
		healthString.setLocation(5, 10);
		healthBar = new OverlayBar(player.getStatHealth(),100);
		healthBar.setColor(Color.GREEN);
		healthBar.setLocation(80, 18);
		scoreString = new OverlayStat("Kills: ", player.getStatScore());
		scoreString.setLocation(385, 12);
		ammoString = new OverlayStat("Ammo: ", player.getStatAmmo());
		ammoString.setColor(Color.BLUE);
		ammoString.setLocation(470, 12);

		zombies = new SpriteGroup("Zombies");
		bullets = new SpriteGroup("Bullets");
		items = new SpriteGroup("Items");
		playfield = new PlayField();
		control = new KeyboardControl(player, this);
		counter = new Timer(400);

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

		bulletZombieManager = new BZCollisionManager();
		playfield.addCollisionGroup(bullets, zombies, bulletZombieManager);

	}

	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		control.update();
		player.update(elapsedTime);
		healthBar.update(elapsedTime);
		healthString.update(elapsedTime);
		scoreString.update(elapsedTime);
		ammoString.update(elapsedTime);
		zombies.update(elapsedTime);
		bullets.update(elapsedTime);
		items.update(elapsedTime);

		if (counter.action(elapsedTime)) {
			addZombie();
		}

	}
	
	public void endGame(){
		gameOver = new OverlayString("GAME OVER\nFinal Score: " + player.getScore(), Color.BLACK);
		stop();
	}

	public void addZombie() {
		Zombie newZombie = new Zombie("New", "Moving", 
				getInitializedAnimatedSprite(zombieDownImage), 
				getInitializedAnimatedSprite(zombieUpImage), 
				getInitializedAnimatedSprite(zombieLeftImage), 
				getInitializedAnimatedSprite(zombieRightImage), player);

		newZombie.mapNameToSprite("AttackFromLeft" , 
									getInitializedAnimatedSprite(zombieAttackFromLeftImage));
		newZombie.mapNameToSprite("AttackFromRight" ,
									getInitializedAnimatedSprite(zombieAttackFromRightImage));
		newZombie.mapNameToSprite("AttackFromAbove" ,
									getInitializedAnimatedSprite(zombieAttackFromAboveImage));
		newZombie.mapNameToSprite("AttackFromBelow" , 
									getInitializedAnimatedSprite(zombieAttackFromBelowImage));

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

	public void addRandomItem(int x, int y) {
//		double x=Math.random()*GAME_WIDTH;
//		double y=Math.random()*GAME_HEIGHT;
		int choice=(int) (Math.random()*3);
		Item item;
		switch(choice){
		case 0: 
			item=new WeaponItem(player,new Sprite(assaultRifleImage),1,x,y);
			break;
		case 1: 
			item=new WeaponItem(player,new Sprite(shotgunImage),2,x,y);
			break;
		case 2: 
			item=new HealthItem(player,new Sprite(healthImage),100,x,y);
			break;
		default:
			item=null;
		}
		item.getCurrentSprite().setImage(healthImage);
		item.setActive(true);
		items.add(item);
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
		control.setParams(new Class[]{int.class});
		control.addInput(KeyEvent.VK_1, "switchWeapons", PLAYER_CLASS, 0);
		control.setParams(new Class[]{int.class});
		control.addInput(KeyEvent.VK_2, "switchWeapons", PLAYER_CLASS, 1);
		control.setParams(new Class[]{int.class});
		control.addInput(KeyEvent.VK_3, "switchWeapons", PLAYER_CLASS, 2);
	}

	public void render(Graphics2D g) {
		background.render(g);
		playfield.render(g);
		healthBar.render(g);
		healthString.render(g);
		scoreString.render(g);
		ammoString.render(g);
		
		if (!player.isActive()){
			gameOver = new OverlayString("GAME OVER", Color.BLACK);
			gameOver.setLocation(GAME_WIDTH/2-60, GAME_HEIGHT/2-10);
			gameOver.render(g);
			endGame();
		}
	}



	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zombieland(), new Dimension(GAME_WIDTH, GAME_HEIGHT),false);
		game.start();
	}

}
