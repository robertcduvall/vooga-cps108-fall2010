package vooga.games.zombieland;

import java.awt.*;
import java.awt.event.KeyEvent;

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

	private Sprite shooter;
	private Background background;
	private SpriteGroup zombies;
	private Shooter player;
	private PlayField playfield;
	private KeyboardControl control;
	
	public void initResources(){
		shooter = new Sprite(getImage("resources/Down1.png"), 350, 250);
		zombies = new SpriteGroup("Zombies");
		player = new Shooter("Hero", "Idle", shooter, 100, 0);
		playfield = new PlayField();
		control = new KeyboardControl(player, this);
		
		playfield.add(player);
		setListeners();
	}
	
	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		control.update();
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
