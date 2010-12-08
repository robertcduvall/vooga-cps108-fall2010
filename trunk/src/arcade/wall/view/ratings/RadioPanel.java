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
public class RadioPanel extends ButtonPanel {

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
		JRadioButton buttonTwo = (JRadioButton) addComment(myRadioButtons[2], " =)");
	}
	

}
