package arcade.wall.view.ratings;

import javax.swing.JRadioButton;

/**
 * Creates a Rating Panel that is made up of a number of Radio Buttons
 * as provided by the user. In order to change the Panel layout, please refer
 * to the methods in the ButtonPanel superclass.
 * 
 * @author Cameron McCallie
 *
 */
@SuppressWarnings("serial")
public class RadioPanel extends AbstractButtonPanel {

	private JRadioButton[] myRadioButtons;
	
	public RadioPanel(int scale) {
		super(scale);
		myRadioButtons = new JRadioButton[scale];
		for (int i = 1; i < scale+1; i ++){
			JRadioButton thisButton = new JRadioButton(""+i);
			thisButton.setActionCommand(""+i);
			thisButton.addActionListener(this);
			myButtons.add(thisButton);
			myRadioButtons[i-1] = thisButton;
			this.add(thisButton);	
		}
	}
	
	/**
	 * Adds a comment after the rating.
	 * Ex: 4 - Awesome!
	 * 
	 * Note that the user would have to specify the "-".
	 */
	public void addComment(int buttonIndex, String comment){
		String newLabel = myRadioButtons[buttonIndex - 1].getText() + " " + comment;
		myRadioButtons[buttonIndex - 1].setText(newLabel);
	}
	

}
