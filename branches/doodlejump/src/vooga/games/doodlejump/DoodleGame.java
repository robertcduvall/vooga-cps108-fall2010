package vooga.games.doodlejump;

import vooga.engine.player.control.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;

import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;


import com.golden.gamedev.object.background.*;


public class DoodleGame extends Game {
	private Sprite green_platform;
	private Sprite light_blue_platform;
	private PlayerSprite doodle;
	private PlayerSprite blue_flying_monster;
	private PlayerSprite blue_monster;
	private PlayerSprite green_flying_monster;
	private PlayerSprite green_monster;
	private PlayerSprite purple_monster;
	private PlayerSprite red_monster;
	private Background background;

	
	public void initResources() {
		
		blue_flying_monster = new PlayerSprite("blue_flying_monster", "bfm", new Sprite(getImage("blue_flying_monster.png")));
		
		blue_monster = new PlayerSprite("blue_monster", "bm", new Sprite(getImage("blue_monster.png")));

		green_flying_monster = new PlayerSprite("green_flying_monster", "gfm", new Sprite(getImage("green_flying_monster.png")));
		
		green_monster = new PlayerSprite("green_monster", "gm", new Sprite(getImage("green_monster.png")));
		
		purple_monster = new PlayerSprite("purple_monster", "pm", new Sprite(getImage("purple_monster.png")));
		
		red_monster = new PlayerSprite("red_monster", "rm", new Sprite(getImage("red_monster.png")));
				
		
        setFPS(100);
        BufferedImage image = getImage("green_platform.png");
        double x = 100;
        double y = 200;
        green_platform = new Sprite(image, x, y);
        BufferedImage image1 = getImage("light_blue_platform.png");
        double x1 = 200;
        double y1 = 100;
        light_blue_platform = new Sprite(image1, x1, y1);
        background = new ImageBackground(getImage("background.png"));
        doodle = new PlayerSprite("doodle", "normal", new Sprite(getImage("doodle.png")));
        doodle.setLocation(340, 411);
    }


    public void update(long elapsedTime) {
        
    }


    public void render(Graphics2D g) {
        // Graphics Engine
        
        blue_flying_monster.render(g);
        blue_monster.render(g);
        green_flying_monster.render(g);
        green_monster.render(g);
        purple_monster.render(g);
        red_monster.render(g);
        
        background.render(g);
        green_platform.render(g);
        light_blue_platform.render(g);
        doodle.render(g);
    }

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new DoodleGame(), new Dimension(695,822), false);
        game.start();
    }

}
