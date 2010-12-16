package arcade.wall.views.ratings;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Creates a Rating Panel that is made up of a number of Radio Buttons
 * as provided by the user.
 * 
 * Example:
 * RadioPanel ratingPanel = new RadioPanel(5);
 * 
 * @author Cameron McCallie
 *
 */
@SuppressWarnings("serial")
public class RadioPanel extends JPanel implements ActionListener {

	private JRadioButton[] myRadioButtons;
	private ButtonGroup myButtons;
	
	public RadioPanel(int scale) {
		myRadioButtons = new JRadioButton[scale];
		myButtons = new ButtonGroup();
		for (int i = 1; i < scale+1; i ++){
			JRadioButton thisButton = new JRadioButton(""+i);
			thisButton.setActionCommand(""+i);
			thisButton.addActionListener(this);
			myButtons.add(thisButton);
			myRadioButtons[i-1] = thisButton;
			thisButton.setEnabled(true); //Enables the last button created
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
	
	/**
	 * Returns the current radio button selection
	 */
	public String getSelectedValue() {
		return myButtons.getSelection().getActionCommand();
	}
	
	/**
	 * Aligns the rating choices horizontally, from left to right
	 */
	public void setHorizontal(){
        this.setLayout(new GridLayout(1, 0));
	}
	
	/**
	 * Aligns the rating choices vertically, from top to bottom
	 */
	public void setVertical(){
        this.setLayout(new GridLayout(0, 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	

}
