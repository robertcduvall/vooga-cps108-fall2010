package vooga.games.mariogame.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.HighScoreHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;

import com.almworks.sqlite4java.SQLiteException;

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
	private HighScoreHandler myHighScores;
	private PlayField myPlayfield;
	private GamePlayState myPlayState;
	private Game myGame;

	private static final int NUM_SCORES = 10;

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
	public GameEndState(Game game, PlayField playfield, GamePlayState playState) {
		
		
		super(playfield);
		myPlayfield = playfield;
		myPlayState = playState;
		myGame = game;

		myHighScores = new HighScoreHandler(NUM_SCORES, Resources
				.getString("highscoredbname"), new File(Resources
				.getString("highscorefile")));
	}

	/**
	 * This method is used to update the high scores that are displayed in the
	 * state. This should be called in the activate method of the state it is
	 * declared in, so that scores will update constantly as the state updates.
	 * 
	 */

	private void onEnter() {
		try {
			myHighScores.updateScores(myPlayState.getScore(), System.currentTimeMillis());
			myGame.updateHighScore(myPlayState.getScore());
		} catch (SQLiteException e) {
			System.out.println("Error with scoring");
		}

		createHighScoreOverlay();
	}

	/**
	 * This creates a high score overlay at a user-specified point.
	 * 
	 * @param x
	 *            : x coordinate for overlay
	 * @paramy y: y coordinate for overlay
	 */

	@SuppressWarnings("unchecked")
	private void createHighScoreOverlay() {
		PlayField field = getRenderField().toArray(new PlayField[0])[0];
		for (int j = 1; j <= myHighScores.getNames().length; j++) {
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
		SoundPlayer.playMusic(myPlayfield.getMusic(0));
	};
	
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		super.render(g);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		initControls();
	}
	
	private void initControls(){
		Control gameControl = new KeyboardControl(myGame,myGame);
		gameControl.addInput(KeyEvent.VK_SPACE, "restartGame", "vooga.games.mariogame.Blah");
		((PlayField)this.getUpdateField().toArray()[0]).addControl("start", gameControl);
	}
}