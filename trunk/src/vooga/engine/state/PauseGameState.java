package vooga.engine.state;

import java.awt.Color;

import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayString;

public class PauseGameState extends GameState {

	private OverlayString pauseOverlay;
	private final String DEFAULT_MESSAGE = "Paused";
	private PlayField basicTextPlayfield = new PlayField();

	public PauseGameState(GameState previousGameState) {

		this.addRenderState(previousGameState);

		pauseOverlay = new OverlayString(DEFAULT_MESSAGE, Color.BLACK);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		addPlayField(basicTextPlayfield);
	}

	public PauseGameState(GameState previousGameState, String pauseMessage) {

		this.addRenderState(previousGameState);

		pauseOverlay = new OverlayString(pauseMessage, Color.BLACK);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		addPlayField(basicTextPlayfield);
	}

	public PauseGameState(GameState previousGameState, String pauseMessage,
			Color color) {

		this.addRenderState(previousGameState);

		pauseOverlay = new OverlayString(pauseMessage, color);

		setMessagePosition(basicTextPlayfield.getBackground().getWidth() / 2,
				basicTextPlayfield.getBackground().getHeight() / 2);
		addPlayField(basicTextPlayfield);
	}

	public PauseGameState(GameState previousGameState, String pauseMessage,
			int x, int y) {

		this(previousGameState, pauseMessage);

		setMessagePosition(x, y);
		addPlayField(basicTextPlayfield);
	}

	public PauseGameState(GameState previousGameState, String pauseMessage,
			Color color, int x, int y) {

		this(previousGameState, pauseMessage, color);

		setMessagePosition(x, y);
		addPlayField(basicTextPlayfield);
	}

	public void setMessagePosition(int x, int y) {

		pauseOverlay.setX(x);
		pauseOverlay.setY(y);

	}

	@Override
	public void initialize() {

	}

}
