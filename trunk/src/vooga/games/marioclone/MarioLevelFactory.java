package vooga.games.marioclone;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import vooga.engine.factory.LevelFactory;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

public class MarioLevelFactory implements LevelFactory {

	private MarioSprite myMario;

	private int myWidth;
	private int myHeight;

	public MarioLevelFactory() {
		myWidth = Resources.getInt("Width");
		myHeight = Resources.getInt("Height");
	}

	public MarioLevelFactory(MarioSprite mario) {
		this();
		myMario = mario;
	}

	private Enemy spawnEnemy(int x, int y) {
		BufferedImage[] enemyImgs = new BufferedImage[] {
				Resources.getImage("Enemy1"), Resources.getImage("Enemy2"),
				Resources.getImage("Enemy3"), Resources.getImage("Enemy4") };
		Enemy enemy = new Enemy("enemy1", "regular", enemyImgs, enemyImgs);
		enemy.setLocation(x, y);
		return enemy;
	}

	private void spawnMario(int x, int y) {
		if (myMario == null) {
			BufferedImage[] MarioR = new BufferedImage[] {
					Resources.getImage("MarioR1"),
					Resources.getImage("MarioR2"),
					Resources.getImage("MarioR3"),
					Resources.getImage("MarioR4") };
			BufferedImage[] MarioL = new BufferedImage[] {
					Resources.getImage("MarioL1"),
					Resources.getImage("MarioL2"),
					Resources.getImage("MarioL3"),
					Resources.getImage("MarioL4") };
			myMario = new MarioSprite("mario", "regular", MarioR, MarioL);
			myMario.setHealth(myMario.getMaxHealth());
			myMario.setActive(true);
		}
		myMario.setMaxX(0);
		myMario.setLocation(x, y);
	}

	@Override
	public PlayField getPlayfield(File levelFactoryFile) {

		// Template for MarioClone level factory file:
		// *variable*=*value*
		//		  
		// map=*location of level's map file* (only first is read)
		// enemy=*enemy x*,*enemy y*
		// mario=*mario x*,*mario y* (only first is read)
		// overlay=*location of overlay XML file* 
		// music=*key for sound file*
		// bg=[color/image]:[R,G,B/*key for image*]

		MarioPlayField playfield = new MarioPlayField();

		Scanner s = null;
		try {
			s = new Scanner(levelFactoryFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot load level file.");
			e.printStackTrace();
		}

		HashMap<String, List<String>> vars = new HashMap<String, List<String>>();

		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] tokens = line.split("=");
			String name = tokens[0].trim();
			String val = tokens[1].trim();
			if (!vars.containsKey(name))
				vars.put(name, new ArrayList<String>());
			vars.get(name).add(val);
		}

		// Load TileMap
		File mapFile = new File(vars.get("map").get(0));
		TileMap map = null;
		try {
			map = new TileMap(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		playfield.addTileMap(map);

		// Load Enemies
		for (String curEnemy : vars.get("enemy")) {
			int[] coords = parseCoords(curEnemy);
			playfield.getGroup("Enemy Group").add(
					spawnEnemy(coords[0], coords[1]));
		}

		// Load Mario
		String marioString = vars.get("mario").get(0);
		int[] coords = parseCoords(marioString);
		spawnMario(coords[0], coords[1]);
		playfield.setSpawnLocation(coords[0], coords[1]);
		playfield.getGroup("Mario Group").add(myMario);

		// Add Overlays
		OverlayTracker overlays = OverlayCreator.createOverlays(vars.get(
				"overlay").get(0));
		playfield.addOverlays(overlays);
		
		// Add Music
		playfield.setMusic(Resources.getSound(vars.get("music").get(0)));
		
		// Add Background
		Background background = null;
		int backgroundWidth = map.width * map.TILE_SIZE;
		int backgroundHeight = map.height * map.TILE_SIZE;
		String[] splitBG = vars.get("bg").get(0).split(":");
		String type = splitBG[0];
		String arg = splitBG[1];
		if(type.equals("image")) {
			background = new ImageBackground(Resources.getImage(arg),backgroundWidth,backgroundHeight);
		}
		else if(type.equals("color")) {
			String[] colorStrings = arg.split(",");
			int[] color = new int[3];
			for(int i = 0; i <3; i++)
				color[i] = Integer.parseInt(colorStrings[i].trim());
			background = new ColorBackground(new Color(color[0], color[1], color[2]), backgroundWidth, backgroundHeight);
		}
		background.setClip(0, 0, myWidth, myHeight);
		playfield.setBackground(background);

		return playfield;
	}

	private int[] parseCoords(String stringCoords) {
		String[] coordString = stringCoords.split(",");
		int x = Integer.parseInt(coordString[0].trim());
		int y = Integer.parseInt(coordString[1].trim());
		return new int[] { x, y };
	}
}