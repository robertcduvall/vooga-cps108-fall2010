package vooga.engine.state;

import vooga.engine.core.PlayField;
import vooga.widget.Button;

/**
 * MenuGameState is a reusable component extension of GameState which contains a series of Buttons
 * @author Brent
 * @author with a splash of Brian (comments)
 *
 */
public abstract class MenuGameState extends GameState {

	/**
	 * menuPlayfield is the desired new instance of PlayField
	 */
	private PlayField menuPlayfield = new PlayField();

	/**
	 * Creates a new instance of MenuGameState with a specified collection of buttons
	 * @param buttons iterable collection of buttons
	 */
	public MenuGameState(Iterable<Button> buttons) {
		//TODO suggestion: don't require buttons be passed as a parameter.
		//that way I can define how I want the buttons to be created in 
		//my menu extension rather than needing to set them up and pass 
		//them in from game --Daniel Koverman
		for (Button button : buttons) {
			addButton(button);
		}

	}
	
	/**
	 * Creates a new instance of MenuGameState
	 */
	public MenuGameState(){
		initialize();
	}

	/**
	 * Initializes MenuGameState
	 */
	@Override
	public abstract void initialize();

	/**
	 * Adds a specified Button to the MenuGameState
	 * @param button
	 */
	public void addButton(Button button) {
		System.out.println(button);
		menuPlayfield.add(button);
		menuPlayfield.add(button);

		addPlayField(menuPlayfield);

	}
	
	public PlayField getMenuPlayfield(){
		return menuPlayfield;
	}

}
