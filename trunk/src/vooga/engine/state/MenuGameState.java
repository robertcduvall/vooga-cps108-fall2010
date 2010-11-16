package vooga.engine.state;

import vooga.engine.core.PlayField;
import vooga.widget.Button;

/**
 * MenuGameState is a reusable component extension of GameState which contains a series of Buttons
 * @author Brent
 * @author with a splash of Brian (comments)
 *
 */
public class MenuGameState extends GameState {

	/**
	 * menuPlayfield is the desired new instance of PlayField
	 */
	private PlayField menuPlayfield = new PlayField();

	/**
	 * Creates a new instance of MenuGameState with a specified collection of buttons
	 * @param buttons iterable collection of buttons
	 */
	public MenuGameState(Iterable<Button> buttons) {

		for (Button button : buttons) {
			addButton(button);
		}

	}

	/**
	 * Initializes MenuGameState
	 */
	@Override
	public void initialize() {

	}

	/**
	 * Adds a specified Button to the MenuGameState
	 * @param button
	 */
	public void addButton(Button button) {

		menuPlayfield.add(button);
		menuPlayfield.add(button);

		addPlayField(menuPlayfield);

	}

}
