package vooga.games.mariogame.states;

import java.awt.Graphics2D;
import java.io.File;

import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.HighScoreHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

import com.almworks.sqlite4java.SQLiteException;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

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
	private HighScoreHandler myHighScores;
//	private GameFontManager myFontManager;
	private OverlayString[] myHighScoreOverlays;
	private Long myScore = 0L;
	private SpriteGroup myOverlays;

	private static final int NUM_SCORES = 10;
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

	public GameEndState(PlayField playfield) {
		
		// The gameOver playfield should be constructed from an .xml
		// file using the LevelParser.
		
		super(playfield);
//		addPlayField(playfield);
//		myBackground = new ImageBackground(backgroundImage);
//		myFontManager = fontManager;
		myHighScores = new HighScoreHandler(NUM_SCORES, Resources
				.getString("highscoredbname"), new File(Resources
				.getString("highscorefile")));

		myHighScoreOverlays = new OverlayString[NUM_SCORES + 1];
//		OverlayCreator overlayCreator = new OverlayCreator();
//		OverlayTracker overlayTracker = overlayCreator.createOverlays("src/vooga/games/mariogame/resources/overlays/GameEndOverlays.xml");
//		myOverlays = overlayTracker.getOverlayGroup("GameEndGroup");
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
		PlayField field = getRenderField().toArray(new PlayField[0])[0];
		
		myHighScoreOverlays[0] = new OverlayString("High Scores:");
		myHighScoreOverlays[0].setLocation(x, y);
		
		for (int j = 1; j <= myHighScores.getNames().length; j++) {
			System.out.println("score"+String.valueOf(j));
			field.getOverlayTracker().getStat("score"+String.valueOf(j)).setStat(myHighScores.getScores()[j-1]);
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
	
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		super.render(g);
	}


	public void setScore(Long score) {
		myScore = score;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}