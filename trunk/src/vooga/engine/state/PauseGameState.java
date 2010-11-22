package vooga.engine.state;

import java.awt.Color;

import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayString;
/**
 * PauseGameState is a reusable extension of GameState that displays a basic pause message.
 * @author Brent
 * @author Brian (comments)
 *
 */
public class PauseGameState extends GameState {

	/**
	 * PauseOverlay is the OverlayString that will be displayed on the screen
	 */
	private OverlayString pauseOverlay;
	
	/**
	 * Default Message is the default message to be displayed
	 */
	private final String DEFAULT_MESSAGE = "Paused";
	
	/**
	 * BasicTextPlayfield is the instance of playfield for this class
	 */
	private PlayField basicTextPlayfield = new PlayField();

	/**
	 * Creates an instance of PauseGameState with a default message and color
	 * @param previousGameState
	 */
	public PauseGameState(GameState previousGameState) {

		this.addRenderState(previousGameState);

		pauseOverlay = new OverlayString(DEFAULT_MESSAGE, Color.BLACK);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		basicTextPlayfield.add(pauseOverlay);
		addPlayField(basicTextPlayfield);
	}

	/**
	 * Creates an instance of PauseGameState with a defaultcolor
	 * @param previousGameState 
	 * @param pauseMessage message to be displayed on pause
	 */
	public PauseGameState(GameState previousGameState, String pauseMessage) {

		this.addRenderState(previousGameState);

		pauseOverlay = new OverlayString(pauseMessage, Color.BLACK);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		basicTextPlayfield.add(pauseOverlay);
		addPlayField(basicTextPlayfield);
	}

	/**
	 * Creates an instance of PauseGameState
	 * @param previousGameState 
	 * @param pauseMessage message to be displayed on pause
	 * @param color represents the desired color of the pause message
	 */
	public PauseGameState(GameState previousGameState, String pauseMessage,
			Color color) {

		this.addRenderState(previousGameState);

		pauseOverlay = new OverlayString(pauseMessage, color);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		basicTextPlayfield.add(pauseOverlay);
		addPlayField(basicTextPlayfield);
	}

	/**
	 * Creates an instance of PauseGameState with a specific location for the pause message
	 * @param previousGameState 
	 * @param pauseMessage message to be displayed on pause
	 * @param x x-coordinate of the pause message
	 * @param y y-coordinate of the pause message
	 */
	public PauseGameState(GameState previousGameState, String pauseMessage,
			int x, int y) {

		this(previousGameState, pauseMessage);

		setMessagePosition(x, y);
		addPlayField(basicTextPlayfield);
	}

	/**
	 * Creates an instance of PauseGameState with a specific location for the pause message, color, and message
	 * @param previousGameState 
	 * @param pauseMessage message to be displayed on pause
	 * @param color represents the desired color of the pause message
	 * @param x x-coordinate of the pause message
	 * @param y y-coordinate of the pause message
	 */
	public PauseGameState(GameState previousGameState, String pauseMessage,
			Color color, int x, int y) {

		this(previousGameState, pauseMessage, color);

		setMessagePosition(x, y);
		addPlayField(basicTextPlayfield);
	}

	/**
	 * Changes the desired location of the pause message
	 * @param x x-coordinate of the pause message
	 * @param y y-coordinate of the pause message
	 */
	public void setMessagePosition(int x, int y) {

		pauseOverlay.setX(x);
		pauseOverlay.setY(y);

	}

	/**
	 * Initializes the PauseGameState
	 */
	@Override
	public void initialize() {

	}

}
