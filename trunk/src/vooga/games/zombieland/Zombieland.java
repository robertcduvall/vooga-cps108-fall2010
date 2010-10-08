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
	private static double DEFAULT_X = 100;
	private static double DEFAULT_Y = 100;
	
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

	private BufferedImage[] myZombieAttackFromAboveImage;
	private BufferedImage[] myZombieAttackFromBelowImage;
	private BufferedImage[] myZombieAttackFromLeftImage;
	private BufferedImage[] myZombieAttackFromRightImage;

	private BufferedImage[] myZombieDeath;

	private BufferedImage myBulletImage;
	private BufferedImage myShotGunImage;
	private BufferedImage myAssaultRifleImage;
	private BufferedImage myHealthImage;

	private ZZCollisionManager myZombieZombieManager;
	private HZCollisionManager myHumanZombieManager;
	private BZCollisionManager myBulletZombieManager;
	private WallBoundManager myEntityWallManager;

	private OverlayBar myOverlayHealthBar;
	private OverlayString myOverlayHealthString;
	private OverlayStat myOverlayScoreString;
	private OverlayString myOverlayGameOverString;
	private OverlayStat myOverlayAmmoString;
	
	public void initResources() {

		myPlayerDownImage = new BufferedImage[] {
				getImage("resources/Down1.jpg"),
				getImage("resources/Down2.jpg"),
				getImage("resources/Down3.jpg"),
				getImage("resources/Down4.jpg") };
		myPlayerUpImage = new BufferedImage[] { 
				getImage("resources/up1.jpg"),
				getImage("resources/up2.jpg"), 
				getImage("resources/up3.jpg"),
				getImage("resources/up4.jpg") };
		myPlayerLeftImage = new BufferedImage[] {
				getImage("resources/left1.jpg"),
				getImage("resources/left2.jpg"),
				getImage("resources/left3.jpg"),
				getImage("resources/left4.jpg") };
		myPlayerRightImage = new BufferedImage[] {
				getImage("resources/right1.jpg"),
				getImage("resources/right2.jpg"),
				getImage("resources/right3.jpg"),
				getImage("resources/right4.jpg") };

		myZombieDownImage = new BufferedImage[] {
				getImage("resources/ZombieDown1.jpg"),
				getImage("resources/ZombieDown2.jpg"),
				getImage("resources/ZombieDown3.jpg") };
		myZombieUpImage = new BufferedImage[] {
				getImage("resources/ZombieUp1.jpg"),
				getImage("resources/ZombieUp2.jpg"),
				getImage("resources/ZombieUp3.jpg") };
		myZombieLeftImage = new BufferedImage[] {
				getImage("resources/ZombieLeft1.jpg"),
				getImage("resources/ZombieLeft2.jpg"),
				getImage("resources/ZombieLeft3.jpg") };
		myZombieRightImage = new BufferedImage[] {
				getImage("resources/ZombieRight1.jpg"),
				getImage("resources/ZombieRight2.jpg"),
				getImage("resources/ZombieRight3.jpg") };

		myZombieAttackFromAboveImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromAbove1.jpg"),
				getImage("resources/ZombieAttackFromAbove2.jpg"),
				getImage("resources/ZombieAttackFromAbove3.jpg") };
		myZombieAttackFromBelowImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromBelow1.jpg"),
				getImage("resources/ZombieAttackFromBelow2.jpg"),
				getImage("resources/ZombieAttackFromBelow3.jpg") };
		myZombieAttackFromLeftImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromLeft1.jpg"),
				getImage("resources/ZombieAttackFromLeft2.jpg"),
				getImage("resources/ZombieAttackFromLeft3.jpg") };
		myZombieAttackFromRightImage = new BufferedImage[] {
				getImage("resources/ZombieAttackFromRight1.jpg"),
				getImage("resources/ZombieAttackFromRight2.jpg"),
				getImage("resources/ZombieAttackFromRight3.jpg") };

		myZombieDeath = new BufferedImage[] {
				getImage("resources/ZombieDeath1.jpg"),
				getImage("resources/ZombieDeath2.jpg"),
				getImage("resources/ZombieDeath3.jpg")};

		BufferedImage sandbg = getImage("resources/redbackground.jpg");
		myBackground = new ImageBackground(sandbg, GAME_WIDTH, GAME_HEIGHT);
		
		myBulletImage = getImage("resources/bullet.jpg");
		myShotGunImage = getImage("resources/shotgun.jpg");;
		myAssaultRifleImage= getImage("resources/assaultRifle.jpg");
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
		myTimerTick = new Timer(400);

		myPlayField.add(myPlayer);
		myPlayField.addGroup(myZombies);
		myPlayField.addGroup(myBullets);
		myPlayField.setBackground(myBackground);
		setListeners();

		myZombieZombieManager = new ZZCollisionManager();
		myPlayField.addCollisionGroup(myZombies, myZombies, myZombieZombieManager);

		myHumanZombieManager = new HZCollisionManager();
		myPlayers = new SpriteGroup("Players");
		myPlayers.add(myPlayer);
		myPlayField.addCollisionGroup(myPlayers , myZombies, myHumanZombieManager);

		myEntityWallManager = new WallBoundManager(myBackground);
		myPlayField.addCollisionGroup(myPlayers, myPlayers, myEntityWallManager);
		
		myBulletZombieManager = new BZCollisionManager();
		myPlayField.addCollisionGroup(myBullets, myZombies, myBulletZombieManager);
		
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
				getInitializedAnimatedSprite(myZombieRightImage), myPlayer);

		newZombie.mapNameToSprite("AttackFromLeft" , 
									getInitializedAnimatedSprite(myZombieAttackFromLeftImage));
		newZombie.mapNameToSprite("AttackFromRight" ,
									getInitializedAnimatedSprite(myZombieAttackFromRightImage));
		newZombie.mapNameToSprite("AttackFromAbove" ,
									getInitializedAnimatedSprite(myZombieAttackFromAboveImage));
		newZombie.mapNameToSprite("AttackFromBelow" , 
									getInitializedAnimatedSprite(myZombieAttackFromBelowImage));

		newZombie.mapNameToSprite("ZombieDeath", 
				getInitializedAnimatedSprite(myZombieDeath));

		newZombie.setX(DEFAULT_X);
		newZombie.setY(DEFAULT_Y);
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

	public void addRandomItem(int x, int y) {
//		double x=Math.random()*GAME_WIDTH;
//		double y=Math.random()*GAME_HEIGHT;
		int choice=(int) (Math.random()*3);
		Item item;
		switch(choice){
		case 0: 
			item=new WeaponItem(myPlayer,new Sprite(myAssaultRifleImage),1,x,y);
			break;
		case 1: 
			item=new WeaponItem(myPlayer,new Sprite(myShotGunImage),2,x,y);
			break;
		case 2: 
			item=new HealthItem(myPlayer,new Sprite(myHealthImage),100,x,y);
			break;
		default:
			item=null;
		}
		item.getCurrentSprite().setImage(myHealthImage);
		item.setActive(true);
		myItems.add(item);
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
