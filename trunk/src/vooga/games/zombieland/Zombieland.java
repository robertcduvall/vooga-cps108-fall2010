package vooga.games.zombieland;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

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

/**
 * @date 8-8-10
 * @author Aaron Choi, Jimmy Mu, Yang Su
 * @description: The Zombieland game is 
 *	
 *
 */

public class Zombieland extends Game {

	private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;
	private static double defaultX = 100;
	private static double defaultY = 100;
	private static long defaultAnimationDelay=300;
	
	private AnimatedSprite myShooterImage;
	private ImageBackground myBackground;
	private Shooter myPlayer;

	private SpriteGroup myZombies;
	private SpriteGroup myPlayers;
	private SpriteGroup myBullets;
	private SpriteGroup myItems;

	private PlayField myPlayField;
	private Timer myTimerTick;
	private KeyboardControl myControl;

	private BufferedImage[] myPlayerUpImage;
	private BufferedImage[] myPlayerDownImage;
	private BufferedImage[] myPlayerLeftImage;
	private BufferedImage[] myPlayerRightImage;

	private BufferedImage[] myZombieUpImage;
	private BufferedImage[] myZombieDownImage;
	private BufferedImage[] myZombieLeftImage;
	private BufferedImage[] myZombieRightImage;

	private BufferedImage[] myZombieAttackUpImage;
	private BufferedImage[] myZombieAttackDownImage;
	private BufferedImage[] myZombieAttackLeftImage;
	private BufferedImage[] myZombieAttackRightImage;

	private BufferedImage[] myZombieDeath;

	private BufferedImage myBulletImage;
	private BufferedImage myShotGunImage;
	private BufferedImage myAssaultRifleImage;
	private BufferedImage myHealthImage;

	private ZZCollisionManager myZombieZombieManager;
	private PZCollisionManager myHumanZombieManager;
	private BZCollisionManager myBulletZombieManager;
	private WallBoundManager myEntityWallManager;
	private HICollisionManager myHumanItemManager;

	private OverlayBar myOverlayHealthBar;
	private OverlayString myOverlayHealthString;
	private OverlayStat myOverlayScoreString;
	private OverlayString myOverlayGameOverString;
	private OverlayStat myOverlayAmmoString;
	
	public void initResources() {

		myPlayerDownImage = new BufferedImage[] {
				getImage("resources/Down1.png"),
				getImage("resources/Down2.png"),
				getImage("resources/Down3.png"),
				getImage("resources/Down4.png") };
		myPlayerUpImage = new BufferedImage[] { 
				getImage("resources/up1.png"),
				getImage("resources/up2.png"), 
				getImage("resources/up3.png"),
				getImage("resources/up4.png") };
		myPlayerLeftImage = new BufferedImage[] {
				getImage("resources/left1.png"),
				getImage("resources/left2.png"),
				getImage("resources/left3.png"),
				getImage("resources/left4.png") };
		myPlayerRightImage = new BufferedImage[] {
				getImage("resources/right1.png"),
				getImage("resources/right2.png"),
				getImage("resources/right3.png"),
				getImage("resources/right4.png") };

		myZombieDownImage = new BufferedImage[] {
				getImage("resources/ZombieDown1.png"),
				getImage("resources/ZombieDown2.png"),
				getImage("resources/ZombieDown3.png") };
		myZombieUpImage = new BufferedImage[] {
				getImage("resources/ZombieUp1.png"),
				getImage("resources/ZombieUp2.png"),
				getImage("resources/ZombieUp3.png") };
		myZombieLeftImage = new BufferedImage[] {
				getImage("resources/ZombieLeft1.png"),
				getImage("resources/ZombieLeft2.png"),
				getImage("resources/ZombieLeft3.png") };
		myZombieRightImage = new BufferedImage[] {
				getImage("resources/ZombieRight1.png"),
				getImage("resources/ZombieRight2.png"),
				getImage("resources/ZombieRight3.png") };

		myZombieAttackUpImage = new BufferedImage[] {
				getImage("resources/ZombieAttackUp1.png"),
				getImage("resources/ZombieAttackUp2.png"),
				getImage("resources/ZombieAttackUp3.png") };
		myZombieAttackDownImage = new BufferedImage[] {
				getImage("resources/ZombieAttackDown1.png"),
				getImage("resources/ZombieAttackDown2.png"),
				getImage("resources/ZombieAttackDown3.png") };
		myZombieAttackLeftImage = new BufferedImage[] {
				getImage("resources/ZombieAttackLeft1.png"),
				getImage("resources/ZombieAttackLeft2.png"),
				getImage("resources/ZombieAttackLeft3.png") };
		myZombieAttackRightImage = new BufferedImage[] {
				getImage("resources/ZombieAttackRight1.png"),
				getImage("resources/ZombieAttackRight2.png"),
				getImage("resources/ZombieAttackRight3.png") };

		myZombieDeath = new BufferedImage[] {
				getImage("resources/ZombieDeath1.png"),
				getImage("resources/ZombieDeath2.png"),
				getImage("resources/ZombieDeath3.png")};

		BufferedImage sandbg = getImage("resources/sandbackground.png");
		myBackground = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);
		
		myBulletImage = getImage("resources/bullet.png");
		myShotGunImage = getImage("resources/shotgun.png");;
		myAssaultRifleImage= getImage("resources/assaultRifle.png");
		myHealthImage= getImage("resources/Health.png");

		myShooterImage = new AnimatedSprite(myPlayerDownImage, 350, 250);
		myPlayer = new Shooter("Hero", "Down", myShooterImage, 100, 0,this);
		myPlayer.mapNameToSprite("Up",getInitializedAnimatedSprite(myPlayerUpImage));
		myPlayer.mapNameToSprite("Left",getInitializedAnimatedSprite(myPlayerLeftImage));
		myPlayer.mapNameToSprite("Right",getInitializedAnimatedSprite(myPlayerRightImage));
		myPlayer.mapNameToSprite("Down",getInitializedAnimatedSprite(myPlayerDownImage));

		myOverlayHealthString = new OverlayString("Health: ", Color.BLUE);
		myOverlayHealthString.setLocation(5, 10);
		myOverlayHealthBar = new OverlayBar(myPlayer.getStatHealth(),100);
		myOverlayHealthBar.setColor(Color.GREEN);
		myOverlayHealthBar.setLocation(80, 18);
		myOverlayScoreString = new OverlayStat("Kills: ", myPlayer.getStatScore());
		myOverlayScoreString.setLocation(385, 12);
		myOverlayAmmoString = new OverlayStat("Ammo: ", myPlayer.getStatAmmo());
		myOverlayAmmoString.setColor(Color.BLUE);
		myOverlayAmmoString.setLocation(470, 12);

		myZombies = new SpriteGroup("Zombies");
		myBullets = new SpriteGroup("Bullets");
		myItems = new SpriteGroup("Items");
		myPlayField = new PlayField();
		myControl = new KeyboardControl(myPlayer, this);
		myTimerTick = new Timer(2000);

		myPlayField.add(myPlayer);
		myPlayField.addGroup(myZombies);
		myPlayField.addGroup(myBullets);
		myPlayField.addGroup(myItems);
		myPlayField.setBackground(myBackground);
		setListeners();

//		myZombieZombieManager = new ZZCollisionManager();
//		myPlayField.addCollisionGroup(myZombies, myZombies, myZombieZombieManager);

		myHumanZombieManager = new PZCollisionManager();
		myPlayers = new SpriteGroup("Players");
		myPlayers.add(myPlayer);
		myPlayField.addCollisionGroup(myPlayers , myZombies, myHumanZombieManager);

		myEntityWallManager = new WallBoundManager(myBackground);
		myPlayField.addCollisionGroup(myPlayers, myPlayers, myEntityWallManager);
		
		myBulletZombieManager = new BZCollisionManager();
		myPlayField.addCollisionGroup(myBullets, myZombies, myBulletZombieManager);
		
		myHumanItemManager = new HICollisionManager();
		myPlayField.addCollisionGroup(myPlayers, myItems, myHumanItemManager);
		
	}

	public void update(long elapsedTime) {
		myPlayField.update(elapsedTime);
		myControl.update();
		myPlayer.update(elapsedTime);
		myOverlayHealthBar.update(elapsedTime);
		myOverlayHealthString.update(elapsedTime);
		myOverlayScoreString.update(elapsedTime);
		myOverlayAmmoString.update(elapsedTime);
		myZombies.update(elapsedTime);
		myBullets.update(elapsedTime);
		myItems.update(elapsedTime);

		if (myTimerTick.action(elapsedTime)) {
			addZombie();
		}

	}
	

	public void addZombie() {
		Zombie newZombie = new Zombie("New", "Moving", 
				getInitializedAnimatedSprite(myZombieDownImage), 
				getInitializedAnimatedSprite(myZombieUpImage), 
				getInitializedAnimatedSprite(myZombieLeftImage), 
				getInitializedAnimatedSprite(myZombieRightImage), myPlayer, this);

		newZombie.mapNameToSprite("AttackLeft" , 
									getInitializedAnimatedSprite(myZombieAttackLeftImage));
		newZombie.mapNameToSprite("AttackRight" ,
									getInitializedAnimatedSprite(myZombieAttackRightImage));
		newZombie.mapNameToSprite("AttackUp" ,
									getInitializedAnimatedSprite(myZombieAttackUpImage));
		newZombie.mapNameToSprite("AttackDown" , 
									getInitializedAnimatedSprite(myZombieAttackDownImage));

		newZombie.mapNameToSprite("ZombieDeath", 
				getInitializedAnimatedSprite(myZombieDeath,500, false));

		newZombie.setX(Math.random()*GAME_WIDTH);
		newZombie.setY(Math.random()*GAME_HEIGHT);
		
		myZombies.add(newZombie);
	}

	/**
	 * Load the image for a bullet with the correct orientation with respect to the 
	 * shooter and add it to the screen
	 * @param bullet a bullet instantiated by the shooter
	 * @param angle the orientation of the bullet (in degrees)
	 */
	public void addBullet(Bullet bullet, double angle) {
		bullet.getCurrentSprite().setImage(ImageUtil.rotate(myBulletImage, (int) angle));
		bullet.setActive(true);
		myBullets.add(bullet);
	}

	
	public void addRandomItem(double x, double y) {
		
		Random random = new Random();
		int choice = random.nextInt(3);
		
		Item item;
		
		switch(choice){
		case 0: 
			item= new WeaponItem(myPlayer,new Sprite(myAssaultRifleImage),1,x,y);
			break;
		case 1: 
			item= new WeaponItem(myPlayer,new Sprite(myShotGunImage),2,x,y);
			break;
		case 2: 
			item= new HealthItem(myPlayer,new Sprite(myHealthImage),100,x,y);
			break;
			
		default:
			item=null;
		}
		item.setActive(true);
		myItems.add(item);
	}

	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, defaultAnimationDelay, true);
		return sprite;
	}

	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images,
			long delay) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, delay, true);
		return sprite;
	}
	
	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images, long delay,
			boolean loop) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, delay,  loop);
		return sprite;
	}

	private void initializeAnimatedSprite(AnimatedSprite sprite, long delay, boolean loop) {
		sprite.getAnimationTimer().setDelay(delay);
		sprite.setAnimationFrame(0, sprite.getImages().length - 1);
		sprite.setAnimate(true);
		sprite.setLoopAnim(loop);
	}

	public void setListeners() {
		myControl.addInput(KeyEvent.VK_LEFT, "goLeft", PLAYER_CLASS, null);
		myControl.addInput(KeyEvent.VK_RIGHT, "goRight", PLAYER_CLASS, null);
		myControl.addInput(KeyEvent.VK_UP, "goUp", PLAYER_CLASS, null);
		myControl.addInput(KeyEvent.VK_DOWN, "goDown", PLAYER_CLASS, null);
		myControl.addInput(KeyEvent.VK_SPACE, "shoot", PLAYER_CLASS, null);
		myControl.setParams(new Class[]{int.class});
		myControl.addInput(KeyEvent.VK_1, "switchWeapons", PLAYER_CLASS, 0);
		myControl.setParams(new Class[]{int.class});
		myControl.addInput(KeyEvent.VK_2, "switchWeapons", PLAYER_CLASS, 1);
		myControl.setParams(new Class[]{int.class});
		myControl.addInput(KeyEvent.VK_3, "switchWeapons", PLAYER_CLASS, 2);
	}

	public void render(Graphics2D g) {
		myBackground.render(g);
		myPlayField.render(g);
		myOverlayHealthBar.render(g);
		myOverlayHealthString.render(g);
		myOverlayScoreString.render(g);
		myOverlayAmmoString.render(g);
		
		if (gameOver()){
			renderGameOver(g);
		}
	}

	private boolean gameOver()
	{
		return !(myPlayer.isActive());
	}
	
	private void renderGameOver(Graphics2D g)
	{
		myOverlayGameOverString = new OverlayString("GAME OVER", Color.BLACK);
		myOverlayGameOverString.setLocation(GAME_WIDTH/2-60, GAME_HEIGHT/2-10);
		myOverlayGameOverString.render(g);
		endGame();
	}
	
	private void endGame(){
		myOverlayGameOverString = new OverlayString("GAME OVER\nFinal Score: " + myPlayer.getScore(), Color.BLACK);
		stop();
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zombieland(), new Dimension(GAME_WIDTH, GAME_HEIGHT),false);
		game.start();
	}

}
