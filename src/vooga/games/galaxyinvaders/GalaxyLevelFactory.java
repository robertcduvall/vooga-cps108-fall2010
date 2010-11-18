package vooga.games.galaxyinvaders;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.golden.gamedev.object.Background;
import vooga.engine.core.PlayField;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.factory.LevelFactory;
import vooga.engine.resource.Resources;

public class GalaxyLevelFactory implements LevelFactory {
	
	public PlayField getPlayfield(File levelFactoryFile) {
		Scanner scanner;
		try {
			 scanner = new Scanner(levelFactoryFile);
		} catch (FileNotFoundException e) {
			scanner = null;
			System.out.println("File not found error!");
		}
		int timerNum = scanner.nextInt();
		int enemyNum = scanner.nextInt();
		scanner.nextLine();
		
		ArrayList<Point> pathmap = new ArrayList<Point>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Scanner sc = new Scanner(line);
			int temp1 = sc.nextInt();
			int temp2 = sc.nextInt();
			pathmap.add(new Point(temp1, temp2));
		}
		
		PlayField playfield = new PlayField();
		playfield.addGroup(makeEnemies(enemyNum, pathmap, timerNum));
		return playfield;
	}

	private SpriteGroup makeEnemies(int numberOfEnemies, ArrayList<Point> path, int timer) {
		SpriteGroup enemies = new SpriteGroup("enemies");
		for (int i=0; i<numberOfEnemies; i++){
			EnemySprite e = new EnemySprite("default", new Sprite(Resources.getImage("enemy1"), (i%4)*50, ((int)(i/4))*50), path, timer);
			Sprite damaged = new Sprite(Resources.getImage("enemy1damage"));
			//TODO: Check to see if this ever gets used
			e.addSprite("damaged", damaged);
			enemies.add(e);
		} 
		
		return enemies;
	}

	@Override
	public PlayField getPlayfield(String filepath,
			Game currentgame) {
		// TODO Write this method
		return null;
	}

	
}
