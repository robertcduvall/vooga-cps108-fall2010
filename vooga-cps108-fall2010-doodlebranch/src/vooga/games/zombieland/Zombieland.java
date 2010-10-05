package vooga.games.zombieland;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

import vooga.engine.core.Game;
import vooga.games.jumper.Jumper;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

public class Zombieland extends Game{
	
    private static final int GAME_WIDTH = 640;
	private static final int GAME_HEIGHT = 480;

	private Sprite shooter;
	private Background background;
	private SpriteGroup zombies;
	private Shooter hero;
	private PlayField playfield;
	private KeyboardControl control;
	
	public void initResources(){
		shooter = new AnimatedSprite();
		zombies = new SpriteGroup("Zombies");
		hero = new Shooter("Hero", "Idle", shooter, 100, 0);
		playfield = new PlayField();
		control = new KeyboardControl(hero, this);
		
		playfield.add(hero);
	}
	
	public void update(long elapsedTime) {
    }


	public void setListeners(){
		control.addInput(KeyEvent.VK_LEFT, "goLeft", "Shooter", null);
	}
	
	
    public void render(Graphics2D g) {
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Zombieland(), new Dimension(GAME_WIDTH,GAME_HEIGHT), false);
        game.start();
    }

}
