package vooga.games.marioclone;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.golden.gamedev.object.Background;

public class MarioLevel {
	private MarioPlayField myPlayField;
	private Map<String,String> myConfig;
	private Background myBackground;
	
	public MarioLevel(File mapFile) {
		myPlayField = new MarioPlayField();
		TileMap map = null;
		try {
			map = new TileMap(new File("src/vooga/games/marioclone/testmap.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myPlayField.addTileMap(map);
	}
	
	public void update(long elapsedtime) {
		
	}
	
	
}
