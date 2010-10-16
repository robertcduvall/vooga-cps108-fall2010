package vooga.games.marioclone;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.HighScoreHandler;
import vooga.engine.state.GameState;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

public class GameLostState extends GameState {
	private PlayField myPlayfield;
	private OverlayString myMessage;
	private HighScoreHandler myHighScores;
	
	public GameLostState(BufferedImage backgroundImage, String messageString, GameFont font) {
		myMessage = new OverlayString(messageString);
//		myMessage.setColor(Color.RED);
		myMessage.setFont(font);
		myMessage.setLocation(50, 50);
		myPlayfield.setBackground(new ImageBackground(backgroundImage));
	}
	
	
	@Override
	public void initialize() {
		myHighScores = new HighScoreHandler(10, "highscores", "src/vooga/games/marioclone/highscores.sqlite");
		myPlayfield = new PlayField();
	}
	
	@Override
	public void render(Graphics2D g) {
		myPlayfield.getBackground().render(g);
		myMessage.render(g);
		super.render(g);
	}

}
