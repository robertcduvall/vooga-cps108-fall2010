package vooga.engine.state;

import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

import vooga.engine.event.Button;

public class MenuGameState extends GameState {

	public MenuGameState (Iterable<Button> buttons){
		
		for (Button button : buttons){
			addButton(button);
		}
		
	}
	
	@Override
	public void initialize() {
		
	}
	
	public void addButton(Button button){

		myUpdateField.add(button);
		myRenderField.add(button);
		
	}
	

}
