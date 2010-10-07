package vooga.games.zombieland;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

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
	private PlayField playfield;
	private Timer counter;
	private KeyboardControl control;

	private BufferedImage[] downplayer;
	private BufferedImage[] upplayer;
	private BufferedImage[] leftplayer;
	private BufferedImage[] rightplayer;

	private BufferedImage[] downzombie;
	private BufferedImage[] upzombie;
	private BufferedImage[] leftzombie;
	private BufferedImage[] rightzombie;
	
	private double defaultX=100;
	private double defaultY=100;

	public void initResources() {
		downplayer = new BufferedImage[] { getImage("resources/Down1.png"),
				getImage("resources/Down2.png"),
				getImage("resources/Down3.png"),
				getImage("resources/Down4.png") };
		upplayer = new BufferedImage[] { getImage("resources/up1.png"),
				getImage("resources/up2.png"), getImage("resources/up3.png"),
				getImage("resources/up4.png") };
		leftplayer = new BufferedImage[] { getImage("resources/left1.png"),
				getImage("resources/left2.png"),
				getImage("resources/left3.png"),
				getImage("resources/left4.png") };
		rightplayer = new BufferedImage[] { getImage("resources/right1.png"),
				getImage("resources/right2.png"),
				getImage("resources/right3.png"),
				getImage("resources/right4.png") };

		downzombie = new BufferedImage[] {
				getImage("resources/ZombieDown1.png"),
				getImage("resources/ZombieDown2.png"),
				getImage("resources/ZombieDown3.png") };
		upzombie = new BufferedImage[] { getImage("resources/ZombieUp1.png"),
				getImage("resources/ZombieUp2.png"),
				getImage("resources/ZombieUp3.png") };
		leftzombie = new BufferedImage[] {
				getImage("resources/ZombieLeft1.png"),
				getImage("resources/ZombieLeft2.png"),
				getImage("resources/ZombieLeft3.png") };
		rightzombie = new BufferedImage[] {
				getImage("resources/ZombieRight1.png"),
				getImage("resources/ZombieRight2.png"),
				getImage("resources/ZombieRight3.png") };

		shooterImage = new AnimatedSprite(downplayer, 350, 250);
//		zombieImage = new AnimatedSprite(downzombie, 100, 100);

		zombies = new SpriteGroup("Zombies");
		player = new Shooter("Hero", "Down", shooterImage, 100, 0);
		player.mapNameToSprite("Up", getInitializedShooterAnimatedSprite(upplayer));
		player.mapNameToSprite("Left", getInitializedShooterAnimatedSprite(leftplayer));
		player.mapNameToSprite("Right", getInitializedShooterAnimatedSprite(rightplayer));
		player.mapNameToSprite("Down", getInitializedShooterAnimatedSprite(downplayer));
		
//		zombie1 = new Zombies("First", "Moving", zombieImage, player);
//		zombies.add(zombie1);
		
		playfield = new PlayField();
		control = new KeyboardControl(player, this);
		background = new ColorBackground(Color.white);
		counter = new Timer(1000);

		playfield.add(player);
		playfield.addGroup(zombies);
		playfield.setBackground(background);
		setListeners();
	}

	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		control.update();
		player.update(elapsedTime);
		zombies.update(elapsedTime);
		if (counter.action(elapsedTime)) {
			//addZombie();
			addZombie2();
		}
	}

	public void addZombie() {
		Zombies getZombie = new Zombies("New", "Moving",
				getInitializedZombieAnimatedSprite(downzombie, defaultX, defaultY), player);
		zombies.add(getZombie);
	}
	
	public void addZombie2() {
		Zombie1 getZombie = new Zombie1("New", "Moving",
				getInitializedZombieAnimatedSprite(upzombie, defaultX, defaultY),
				getInitializedZombieAnimatedSprite(downzombie, defaultX, defaultY),
				getInitializedZombieAnimatedSprite(leftzombie, defaultX, defaultY),
				getInitializedZombieAnimatedSprite(rightzombie, defaultX, defaultY),player);
		zombies.add(getZombie);
	}

	private AnimatedSprite getInitializedShooterAnimatedSprite(BufferedImage[] images) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite,100);
		return sprite;
	}

	private AnimatedSprite getInitializedZombieAnimatedSprite(BufferedImage[] images,
			double x, double y) {
		AnimatedSprite sprite = new AnimatedSprite(images, x, y);
		initializeAnimatedSprite(sprite,500);
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
	}

	public void render(Graphics2D g) {
		playfield.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zombieland(), new Dimension(GAME_WIDTH, GAME_HEIGHT),
				false);
		game.start();
	}

}
