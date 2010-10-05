package vooga.games.doodlejump;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;

import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;


public class DoodleGame extends Game {
	private Sprite piece;
	private PlayerSprite doodle;
	private PlayerSprite blue_flying_monster;
	private PlayerSprite blue_monster;
	private PlayerSprite green_flying_monster;
	private PlayerSprite green_monster;
	private PlayerSprite purple_monster;
	private PlayerSprite red_monster;

	
	public void initResources() {
		
		blue_flying_monster = new PlayerSprite("blue_flying_monster", "bfm", new Sprite(getImage("blue_flying_monster.png")));
		
		blue_monster = new PlayerSprite("blue_monster", "bm", new Sprite(getImage("blue_monster.png")));

		green_flying_monster = new PlayerSprite("green_flying_monster", "gfm", new Sprite(getImage("green_flying_monster.png")));
		
		green_monster = new PlayerSprite("green_monster", "gm", new Sprite(getImage("green_monster.png")));
		
		purple_monster = new PlayerSprite("purple_monster", "pm", new Sprite(getImage("purple_monster.png")));
		
		red_monster = new PlayerSprite("red_monster", "rm", new Sprite(getImage("red_monster.png")));
		
		
        setFPS(100);
    }


    public void update(long elapsedTime) {
        // Input Engine
        //if (keyPressed(KeyEvent.VK_SPACE)) {

            // Sound Engine
          //  playSound("sound1.wav");
        //}
    }


    public void render(Graphics2D g) {
        // Graphics Engine
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        //piece.render(g);
        blue_flying_monster.render(g);
        blue_monster.render(g);
        green_flying_monster.render(g);
        green_monster.render(g);
        purple_monster.render(g);
        red_monster.render(g);
        
    }

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new DoodleGame(), new Dimension(640,480), false);
        game.start();
    }

}
