package arcade.core;

import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GameView extends JPanel{
	
	private GameParser gameParser;
	private Map<String, String[]> gameProperties;
	
	public GameView(String game)
	{
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameParser = new GameParser();
		gameParser.parseGame(game, gameProperties);
	}

}
