package vooga.games.marioclone;

import java.awt.Graphics2D;
import java.io.File;

import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.HighScoreHandler;
import vooga.engine.state.GameState;

import com.almworks.sqlite4java.SQLiteException;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFontManager;

public class GameEndState extends GameState {
	private Background myBackground;
	private OverlayString myMessage;
	private HighScoreHandler myHighScores;
	private GameFontManager myFontManager;
	private OverlayString[] myHighScoreOverlays;
	private static final int NUM_SCORES = 5;
	
	public GameEndState(Background background, String messageString, GameFontManager fontManager) {
		myFontManager = fontManager;
		myMessage = new OverlayString(messageString);
		myMessage.setFont(myFontManager.getFont("GAMEOVER"));
		myMessage.setLocation(400, 400);
		
		myHighScoreOverlays = new OverlayString[NUM_SCORES+1];
		double currentY  = 600;
		double x = 600;
		myHighScoreOverlays[0] = new OverlayString("High Scores:");
		myHighScoreOverlays[0].setLocation(x,currentY);
		for(int j = 0; j < myHighScores.getNames().length; j++) {
			currentY+=20;
			myHighScoreOverlays[j+1] = new OverlayString(String.format("%d. %s......%d",j+1,myHighScores.getNames()
					[j],myHighScores.getScores()[j]));
			myHighScoreOverlays[j+1].setLocation(x,currentY);
		}
		
		myBackground = background;
	}
	
	@Override
	public void initialize() {
		myHighScores = new HighScoreHandler(NUM_SCORES, "highscores", new File("src/vooga/games/marioclone/highscores.db"));
		try {
			myHighScores.updateScores("Test",(long) (100000 * Math.random()));
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		myBackground.render(g);
		myMessage.render(g);
		for(OverlayString os : myHighScoreOverlays) {
			os.render(g);
		}
		super.render(g);
	}

}
