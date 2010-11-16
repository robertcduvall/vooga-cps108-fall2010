package vooga.engine.state;

import java.awt.Color;

import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayString;

public class BasicTextGameState extends GameState {

	private OverlayString textOverlay;
	private PlayField basicTextPlayfield = new PlayField();

	public BasicTextGameState(String pauseMessage) {

		textOverlay = new OverlayString(pauseMessage, Color.BLACK);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);

		addPlayField(basicTextPlayfield);

	}

	public BasicTextGameState(String pauseMessage, Color color) {

		textOverlay = new OverlayString(pauseMessage, color);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);

		addPlayField(basicTextPlayfield);
	}

	public BasicTextGameState(String pauseMessage, int x, int y) {

		this(pauseMessage);

		setMessagePosition(x, y);

		addPlayField(basicTextPlayfield);

	}

	public BasicTextGameState(String pauseMessage, Color color, int x, int y) {

		this(pauseMessage, color);

		setMessagePosition(x, y);

		addPlayField(basicTextPlayfield);

	}

	public void setMessagePosition(int x, int y) {

		textOverlay.setX(x);
		textOverlay.setY(y);

	}

	@Override
	public void initialize() {

		// intialize and add to playfields any sprites that are specific to this gamestate

	}

}
