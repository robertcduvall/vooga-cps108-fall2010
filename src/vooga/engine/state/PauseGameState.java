package vooga.engine.state;

import java.awt.Color;

import vooga.engine.overlay.OverlayString;

public class PauseGameState extends GameState {

	private OverlayString pauseOverlay;
	private final String DEFAULT_MESSAGE = "Paused";
	
	public PauseGameState(GameState previousGameState){
		
		this.addRenderState(previousGameState);
		
		pauseOverlay = new OverlayString(DEFAULT_MESSAGE, Color.BLACK);
		
		setMessagePosition(myRenderField.getBackground().getWidth() / 2, myRenderField.getBackground().getHeight() / 2);
		
	}
	
	public PauseGameState(GameState previousGameState, String pauseMessage){
	
		this.addRenderState(previousGameState);
		
		pauseOverlay = new OverlayString(pauseMessage, Color.BLACK);
		
		setMessagePosition(myRenderField.getBackground().getWidth() / 2, myRenderField.getBackground().getHeight() / 2);
		
	}
	
	public PauseGameState(GameState previousGameState, String pauseMessage, Color color){
		
		this.addRenderState(previousGameState);
		
		pauseOverlay = new OverlayString(pauseMessage, color);

		setMessagePosition(myRenderField.getBackground().getWidth() / 2, myRenderField.getBackground().getHeight() / 2);
		
	}
	
	public PauseGameState(GameState previousGameState, String pauseMessage, int x, int y){
		
		this(previousGameState, pauseMessage);
		
		setMessagePosition(x,y);
		
	}
	
	public PauseGameState(GameState previousGameState, String pauseMessage, Color color, int x, int y){
		
		this(previousGameState, pauseMessage, color);
		
		setMessagePosition(x,y);
		
	}
	
	public void setMessagePosition(int x, int y){
		
		pauseOverlay.setX(x);
		pauseOverlay.setY(y);
		
	}
	
	@Override
	public void initialize() {

		//do nothing
		
	}

}
