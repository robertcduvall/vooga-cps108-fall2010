package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.HighScoreHandler;
import vooga.engine.resource.ResourcesBeta;
import vooga.engine.state.GameState;

import com.almworks.sqlite4java.SQLiteException;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFontManager;
import com.golden.gamedev.object.background.ColorBackground;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         GameState that toggles when the game is won or lost. Example code
 *         that creates and switches to this state, which displays "LOSE" over a
 *         red background:
 * 
 *         myLoseState = new GameEndState(new ColorBackground(Color.red),
 *         "LOSE", fontManager); myGameStateManager.addGameState(myLoseState);
 *         myGameStateManager.switchTo(myLoseState);\
 */

public class GameEndState extends GameState {
	private Background myBackground;
	private OverlayString myMessage;
	private HighScoreHandler myHighScores;
	private GameFontManager myFontManager;
	private OverlayString[] myHighScoreOverlays;
	private Long myScore;

	private static final int NUM_SCORES = 5;
	private int xScoreOverlay = 300;
	private int yScoreOverlay = 600;

	/**
	 * This constructor creates a GameEndState with a background, string of
	 * text, and a fontManager used to write the string
	 * 
	 * @param background
	 *            - can be colored or an image
	 * @param messageString
	 *            - text displayed
	 * @param fontManager
	 *            - resource used to write text
	 */

	public GameEndState(Background background, String messageString,
			GameFontManager fontManager) {
		myFontManager = fontManager;
		myMessage = new OverlayString(messageString);
		myMessage.setFont(myFontManager.getFont("GAMEOVER"));
		myMessage.setLocation((ResourcesBeta.getInt("Width") - myMessage.getWidth()) / 2, 100);

		myHighScores = new HighScoreHandler(NUM_SCORES, "highscores", new File(
				"src/vooga/games/marioclone/resources/highscores.db"));

		myHighScoreOverlays = new OverlayString[NUM_SCORES + 1];

		myBackground = background;
	}

	/**
	 * This method is used to update the high scores that are displayed in the
	 * state. This should be called in the activate method of the state it is
	 * declared in, so that scores will update constantly as the state updates.
	 * 
	 */

	private void onEnter() {
		try {
			myHighScores.updateScores(myScore, System.currentTimeMillis());
			System.out.println(myScore);
		} catch (SQLiteException e) {
			System.out.println("Error with scoring");
		}

		createHighScoreOverlay(xScoreOverlay, yScoreOverlay);
	}

	/**
	 * This creates a high score overlay at a user-specified point.
	 * 
	 * @param x
	 *            : x coordinate for overlay
	 * @paramy y: y coordinate for overlay
	 */

	private void createHighScoreOverlay(int x, int y) {
		myHighScoreOverlays[0] = new OverlayString("High Scores:");
		myHighScoreOverlays[0].setLocation(x, y);
		for (int j = 0; j < myHighScores.getNames().length; j++) {
			y += 20;
			myHighScoreOverlays[j + 1] = new OverlayString(String.format(
					"%d. %d (set on %tD at %tr)", j + 1, myHighScores.getScores()[j],
					myHighScores.getTimes()[j],myHighScores.getTimes()[j]));
			myHighScoreOverlays[j + 1].setLocation(x, y);
		}
	}

	/**
	 * Used to update the game state. In this particular instance, the high
	 * scores are updated with the game state.
	 */

	public void activate() {
		onEnter();
		super.activate();
	};

	/**
	 * Renders the background, message, and overlay within the game state.
	 */

	public void render(Graphics2D g) {
		myBackground.render(g);
		for (OverlayString os : myHighScoreOverlays) {
			os.render(g);
		}
		super.render(g);
		myMessage.render(g);

	}

	
	public void setScore(Long score) {
		myScore = score;
	}
}