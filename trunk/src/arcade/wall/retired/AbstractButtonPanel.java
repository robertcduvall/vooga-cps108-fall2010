package arcade.wall.retired;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

/**
 * This class is currently the superclass for ImageIcon and Radio Button
 * Rating Panels. Panel layouts can currently be changed by accessing methods
 * of this class, as well returning action commands.
 * 
 * @author Cameron McCallie
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractButtonPanel extends JPanel implements ActionListener{
	
	protected int myScale;
	protected ButtonGroup myButtons;
	protected AbstractButton[] myEditableButtons;
	
	//TODO: Make a ComboBox subclass (they can't handle images)
	//TODO: Add comments for ratings from a .properties file
	//TODO: Allow for a rating panel to pop up when a Rate! button is pressed

	public AbstractButtonPanel(int scale){
		myEditableButtons = new AbstractButton[scale];
		myButtons = new ButtonGroup();
		setHorizontal();
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
	
//	/**
//	 * Adds a comment after the rating.
//	 * Ex: 4 - Awesome!
//	 * 
//	 * Note that the user would have to specify the "-".
//	 */
//	public void addComment(int buttonIndex, String comment){
//		String newLabel = myEditableButtons[buttonIndex].getText() + " " + comment;
//		myEditableButtons[buttonIndex].setText(newLabel);
//	}
	
	
	public String getSelectedValue() {
		return myButtons.getSelection().getActionCommand();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
