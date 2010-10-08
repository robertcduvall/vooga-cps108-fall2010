package vooga.games.towerdefense;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.ImageUtil;
import com.golden.gamedev.util.Utility;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayBar;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.overlay.StatInt;
import vooga.engine.player.control.MouseControl;
import vooga.engine.resource.Resources;


public class TowerDefense extends Game{
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	private PlayerCursor playerCursor;
	private MouseControl playerCursorControl;
	private Background background;
	private PlayField playfield;
	private SpriteGroup towerGroup, enemyGroup, towerShotGroup, overlayGroup;
	private ArrayList<PathPoint> path;
	private long totalTime;
	private Stat<Integer> selfEstem;
	private StatInt score;
	private StatInt money;
	
	@Override
	public void initResources(){
		Resources.setGame(this);
		initBackground();
		
		playfield = new PlayField(background);
		towerGroup = playfield.addGroup(new SpriteGroup("Tower Group"));
		enemyGroup = playfield.addGroup(new SpriteGroup("Enemy Group"));
		towerShotGroup = playfield.addGroup(new SpriteGroup("Tower Shot Group"));
		overlayGroup = playfield.addGroup(new SpriteGroup("Overlay Group"));
		selfEstem = new Stat<Integer>(75);
		score = new StatInt(0);
		money = new StatInt(0);
		initPath();
		initPlayer();
		initOverlays();
		
		
		playfield.addCollisionGroup(towerShotGroup, enemyGroup, new TowerShotEnemyCollision());
		
	}
	
	private void initOverlays() {
		GameFont font = fontManager.getFont(getImages("src/vooga/games/towerdefense/resources/images/font.png", 20, 3),
				" !            .,0123" + "456789:   -? ABCDEFG"
						+ "HIJKLMNOPQRSTUVWXYZ ");
		OverlayString temp = new OverlayString("Selfestem".toUpperCase(), font);
		temp.setLocation(800, 50);
		OverlayBar bar = new OverlayBar(selfEstem, 100);
		bar.setMaxLength(200);
		bar.setLocation(775, 70);
		
		overlayGroup.add(temp);
		overlayGroup.add(bar);
		
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
		Resources.loadImage("duvallFace", "src/vooga/games/towerdefense/resources/images/duvallFace.png");
		Resources.loadImage("duvallFaceRed", "src/vooga/games/towerdefense/resources/images/duvallFaceRed.png");
		Resources.loadImage("duvallFaceBlue", "src/vooga/games/towerdefense/resources/images/duvallFaceBlue.png");
		//Sprite playerSprite =  new Sprite(Resources.getImage("duvallFace"), 20, 50);
		//temp = new Enemy("enemy1", "enemy", new Sprite(getImage("resources/images/duvallFace.png")), path, 50);
		//enemyGroup.add(temp);
		playerCursor = new PlayerCursor("player", "playerCursor", new Sprite(), towerGroup, this);
		playerCursorControl = new MouseControl(playerCursor, this);
		playerCursorControl.addInput(MouseEvent.BUTTON1, "buildTower", "vooga.games.towerdefense.PlayerCursor");
	}
	
	private void initBackground(){
		background = new ImageBackground(ImageUtil.resize(getImage("resources/images/grassBackground.png"),WIDTH, HEIGHT), WIDTH, HEIGHT);
	}
	
	@Override
	public void update(long elapsedTime) {
		totalTime += elapsedTime;
		if(totalTime > 2000){
			enemyGroup.add(new Enemy(path, Utility.getRandom(20,80),Utility.getRandom(1,3) , selfEstem, score, money));
			totalTime = 0;
		}
		playfield.update(elapsedTime);
		playerCursorControl.update();
		enemyGroup.update(elapsedTime);
    }

	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
		//playerCursor.render(g);
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
