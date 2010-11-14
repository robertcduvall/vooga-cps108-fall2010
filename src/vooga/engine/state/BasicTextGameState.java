package vooga.engine.state;

import java.awt.Color;

import vooga.engine.overlay.OverlayString;

public class BasicTextGameState extends GameState {

	private OverlayString textOverlay;
	
	public BasicTextGameState(String pauseMessage){
			
		textOverlay = new OverlayString(pauseMessage, Color.BLACK);
		
		setMessagePosition(myRenderField.getBackground().getWidth() / 2, myRenderField.getBackground().getHeight() / 2);
		
	}
	
	public BasicTextGameState(String pauseMessage, Color color){
				
		textOverlay = new OverlayString(pauseMessage, color);

		setMessagePosition(myRenderField.getBackground().getWidth() / 2, myRenderField.getBackground().getHeight() / 2);
		
	}
	
	public BasicTextGameState(String pauseMessage, int x, int y){
		
		this(pauseMessage);
		
		setMessagePosition(x,y);
		
	}
	
	public BasicTextGameState(String pauseMessage, Color color, int x, int y){
		
		this(pauseMessage, color);
		
		setMessagePosition(x,y);
		
	}
	
	public void setMessagePosition(int x, int y){
		
		textOverlay.setX(x);
		textOverlay.setY(y);
		
	}

	@Override
	public void initialize() {

		//do nothing
		
	}
	
}
