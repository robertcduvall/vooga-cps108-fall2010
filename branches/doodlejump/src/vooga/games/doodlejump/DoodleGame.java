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
	Background background;
	private PlayerSprite blue_flying_monster;
	private PlayerSprite blue_monster;
	private PlayerSprite green_flying_monster;
	private PlayerSprite green_monster;
	private PlayerSprite purple_monster;
	private PlayerSprite red_monster;

	
	public void initResources() {
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
