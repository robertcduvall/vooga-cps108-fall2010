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


public class TowerDefense extends Game{
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private PlayerCursor playerCursor;
	private PlayerCursorControl playerCursorControl;
	private Sprite duvall;
	private Background background;
	private PlayField playfield;
	private SpriteGroup towerGroup, enemyGroup, towerShotGroup;
	private ArrayList<PathPoint> path;
	private Enemy temp;
	int i = 0;
	int j = 0;
	
	public void initResources(){
		Resources.setGame(this);
		initBackground();
		
		playfield = new PlayField(background);
		towerGroup = playfield.addGroup(new SpriteGroup("Tower Group"));
		enemyGroup = playfield.addGroup(new SpriteGroup("Enemy Group"));
		towerShotGroup = playfield.addGroup(new SpriteGroup("Tower Shot Group"));
		initPath();
		initPlayer();
		
		
		playfield.addCollisionGroup(towerShotGroup, enemyGroup, new TowerShotEnemyCollision());
		
	}
	
	private void initPath() {
		path = new ArrayList<PathPoint>();
		File thisLevel = new File("src/vooga/games/towerdefense/resources/levels/level1.txt");
		try {
			Scanner sc = new Scanner(thisLevel);
			while(sc.hasNextInt()){
				int x = sc.nextInt();
				if(sc.hasNextInt()){
					int y = sc.nextInt();
					path.add(new PathPoint(x,y));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("failed to implement scanner");
			System.exit(0);
		};
		
		
		
	}

	private void initPlayer(){
		Resources.loadImage("duvallFace", "resources/images/duvallFace.png");
		Sprite playerSprite =  new Sprite(Resources.getImage("duvallFace"), 20, 50);
		temp = new Enemy("enemy1", "enemy", new Sprite(getImage("resources/images/duvallFace.png")), path, 50);
		enemyGroup.add(temp);
		playerCursor = new PlayerCursor("player", "playerCursor", playerSprite, towerGroup);
		playerCursorControl = new PlayerCursorControl(playerCursor, this);
		playerCursorControl.addInput("1", "buildTower", "vooga.games.towerdefense.PlayerCursor");
	}
	
	private void initBackground(){
		background = new ImageBackground(ImageUtil.resize(getImage("resources/images/grassBackground.png"),WIDTH, HEIGHT), WIDTH, HEIGHT);
	}
	
	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		playerCursorControl.update();
		enemyGroup.update(elapsedTime);
    }

	public void render(Graphics2D g) {
		playfield.render(g);
		playerCursor.render(g);
		enemyGroup.render(g);
    }
	
	public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TowerDefense(), new Dimension(WIDTH,HEIGHT), false);
        game.start();
    }
	
	protected ArrayList<PathPoint> getPath() {
		return path;
	}
	
	
	
	
}
