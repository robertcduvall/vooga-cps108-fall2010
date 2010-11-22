package vooga.engine.state;

import java.awt.Color;

import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayString;

/**
 * This is a simple reusable component GameState extension that displays a String message in the middle of the screen.
 * @author Brent & commented by Brian
 *
 */
public class BasicTextGameState extends GameState {

	/**
	 * TextOverlay is the OverlayString that will be displayed on the screen
	 */
	private OverlayString textOverlay;
	
	/**
	 * BasicTextPlayfield is the playfield created for this class
	 */
	private PlayField basicTextPlayfield = new PlayField();

	/**
	 * Creates an instance of BasicTextGameState from a String message that will be displayed in black
	 * @param pauseMessage represents the message to be displayed on the screen in this GameState
	 */
	public BasicTextGameState(String pauseMessage) {

		textOverlay = new OverlayString(pauseMessage, Color.BLACK);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		basicTextPlayfield.add(textOverlay);
		addPlayField(basicTextPlayfield);

	}

	/**
	 * Creates an instance of BasicTextGameState from a String message and a specified color
	 * @param pauseMessage represents the message to be displayed on the screen in this GameState
	 * @param color represents the color that the message will be displayed in
	 */
	public BasicTextGameState(String pauseMessage, Color color) {

		textOverlay = new OverlayString(pauseMessage, color);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		basicTextPlayfield.add(textOverlay);
		addPlayField(basicTextPlayfield);
	}

	/**
	 * Creates an instance of BasicTextGameState from a String message and a location
	 * @param pauseMessage represents the message to be displayed on the screen in this GameState
	 * @param x represents the desired x-coordinate of this message
	 * @param y represents the desired y-coordinate of this message
	 */
	public BasicTextGameState(String pauseMessage, int x, int y) {

		this(pauseMessage);

		setMessagePosition(x, y);
		
		addPlayField(basicTextPlayfield);

	}

	/**
	 * Creates an instance of BasicTextGameState with a specific message, color, and location
	 * @param pauseMessage represents the message to be displayed on the screen in this GameState
	 * @param color represents the color that the message will be displayed in
	 * @param x represents the desired x-coordinate of this message
	 * @param y represents the desired y-coordinate of this message
	 */
	public BasicTextGameState(String pauseMessage, Color color, int x, int y) {

		this(pauseMessage, color);

		setMessagePosition(x, y);

		addPlayField(basicTextPlayfield);

	}

	/**
	 * Changes the position that the message will be displayed in
	 * @param x represents the desired x-coordinate of this message
	 * @param y represents the desired y-coordinate of this message
	 */
	public void setMessagePosition(int x, int y) {

		textOverlay.setX(x);
		textOverlay.setY(y);

	}

	/**
	 * Initializes this GameState
	 */
	@Override
	public void initialize() {

		// intialize and add to playfields any sprites that are specific to this gamestate

	}

}
