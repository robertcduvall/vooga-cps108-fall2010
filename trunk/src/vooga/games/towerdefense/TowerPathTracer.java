package vooga.games.towerdefense;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.core.Game;
import vooga.engine.player.control.Control;
import vooga.engine.player.control.ItemSprite;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;

/**
 * Helper program for rapidly tracing a path on a background. Used 
 * only for making new levels, not during actual game play.
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */
public class TowerPathTracer extends Game{
	
	public static final int WIDTH = 1050;
	public static final int HEIGHT = 600;
	
	private PlayerCursor playerCursor;
	private PathTracer playerCursorControl;
	private Background background;

	private PlayField playfield;
	private Sprite temp;
	int i = 0;
	int j = 0;
	
	public void initResources(){
		Resources.setGame(this);
		initBackground();
		initPlayer();
		
		playfield = new PlayField(background);
	}
		
		
		
	

	private void initPlayer(){
		Resources.loadImage("duvallFace", "resources/images/duvallFace.png");
		Sprite playerSprite =  new Sprite(Resources.getImage("duvallFace"), 20, 50);
		temp = new Sprite(getImage("resources/images/duvallFace.png"));
		//playerCursor = new PlayerCursor("player", "playerCursor", playerSprite, null, null, null);
		playerCursorControl = new PathTracer(new PlayerSprite("a","b", new Sprite()), this);
		//playerCursorControl.addInput("1", "buildTower", "vooga.games.towerdefense.PlayerCursor");
	}
	
	private void initBackground(){
		background = new ImageBackground(ImageUtil.resize(getImage("resources/images/gameOver.gif"),WIDTH, HEIGHT), WIDTH, HEIGHT);
	}
	
	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		playerCursorControl.update();
		/*if(i<path.size() && j % 4 == 0){
			PathPoint point = path.get(i);
			System.out.println("a");
			temp.setLocation(point.getX(), point.getY());
			i++;
		}
		j++;*/
		
    }

	public void render(Graphics2D g) {
		playfield.render(g);
		//playerCursor.render(g);
		//temp.render(g);
    }
	
	public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TowerPathTracer(), new Dimension(WIDTH,HEIGHT), false);
        game.start();
    }
	
	
}
