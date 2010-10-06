package vooga.games.zombieland;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

import vooga.engine.core.Game;
import vooga.games.jumper.Jumper;
import vooga.engine.player.control.*;

public class Zombieland extends Game{
	
    private static final String PLAYER_CLASS = "vooga.games.zombieland.Shooter";
	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;

	private AnimatedSprite shooter;
	private Sprite zombie;
	private Background background;
	private SpriteGroup zombies;
	private Shooter player;
	private Zombies zombie1;
	private PlayField playfield;
	private Timer counter;
	private KeyboardControl control;
	private BufferedImage[] downplayer;
	private BufferedImage[] upplayer;
	private BufferedImage[] leftplayer;
	private BufferedImage[] rightplayer;
	
	public void initResources(){
		downplayer = new BufferedImage[]{getImage("resources/Down1.png"), getImage("resources/Down2.png"), getImage("resources/Down3.png"), getImage("resources/Down4.png")};
		upplayer = new BufferedImage[]{getImage("resources/up1.png"), getImage("resources/up2.png"), getImage("resources/up3.png"), getImage("resources/up4.png")};
		leftplayer = new BufferedImage[]{getImage("resources/left1.png"), getImage("resources/left2.png"), getImage("resources/left3.png"), getImage("resources/left4.png")};
		rightplayer = new BufferedImage[]{getImage("resources/right1.png"), getImage("resources/right2.png"), getImage("resources/right3.png"), getImage("resources/right4.png")};
		
		shooter = new AnimatedSprite(downplayer, 350, 250);
		zombie = new Sprite(getImage("resources/Zombie Down1.png"), 100, 100);
		zombies = new SpriteGroup("Zombies");
		player = new Shooter("Hero", "Down", shooter, 100, 0);
		player.mapNameToSprite("Up", new AnimatedSprite(upplayer));
		player.mapNameToSprite("Left", new AnimatedSprite(leftplayer));
		player.mapNameToSprite("Right", new AnimatedSprite(rightplayer));
		zombie1 = new Zombies("First", "Moving", zombie, player);
		zombies.add(zombie1);
		playfield = new PlayField();
		control = new KeyboardControl(player, this);
		background = new ColorBackground(Color.black);
		counter = new Timer(1000);
		
		playfield.add(player);
		playfield.addGroup(zombies);
		playfield.setBackground(background);
		setListeners();
	}
	
	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		control.update();
		zombies.update(elapsedTime);
		if (counter.action(elapsedTime))
		{
			addZombie();
		}
    }

	public void addZombie(){
		Sprite newZombie = new Sprite(getImage("resources/Zombie Down1.png"), 100, 100);
		Zombies getZombie = new Zombies("New", "Moving", newZombie, player);
		zombies.add(getZombie);
	}
	
	public void setListeners(){
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
        game.setup(new Zombieland(), new Dimension(GAME_WIDTH,GAME_HEIGHT), false);
        game.start();
    }

}
