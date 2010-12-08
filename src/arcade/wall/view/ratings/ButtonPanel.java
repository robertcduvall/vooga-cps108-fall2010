package arcade.wall.view.ratings;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import vooga.engine.resource.Resources;

import arcade.core.Panel;

/**
 * This class is currently the superclass for ImageIcon and Radio Button
 * Rating Panels. Panel layouts can currently be changed by accessing methods
 * of this class, as well returning action commands.
 * 
 * @author Cameron McCallie
 *
 */

public abstract class ButtonPanel extends JPanel implements ActionListener{
	
	protected int myScale;
	protected ButtonGroup myButtons;
	
	//TODO: Make a ComboBox subclass (they can't handle images)
	//TODO: Figure out a better implementation for adding comments

	public ButtonPanel(int scale){
		
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
	
	/**
	 * Adds a comment after the rating.
	 * Ex: 4 - Awesome!
	 * 
	 * Note that the user would have to specify the "-".
	 */
	public AbstractButton addComment(AbstractButton button, String comment){
		String newLabel = button.getText() + " " + comment;
		button.setText(newLabel);
		return button;
	}
	
	
	public String getSelectedValue() {
		return myButtons.getSelection().getActionCommand();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
