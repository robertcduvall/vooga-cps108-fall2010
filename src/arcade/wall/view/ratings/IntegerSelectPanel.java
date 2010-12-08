package arcade.wall.view.ratings;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import vooga.engine.resource.Resources;

import arcade.core.Panel;

public abstract class IntegerSelectPanel extends JPanel implements ActionListener{
	
	protected int myScale;
	protected ButtonGroup myButtons;
	protected JButton[] myEditableButtons;
	
	//TODO: Figure out how to make a ComboBox subclass (they can't handle images)

	public IntegerSelectPanel(int scale){
		
		
		for (int i = 1; i < scale+1; i ++){
			JButton thisButton = new JButton(""+i);
			thisButton.setActionCommand(""+i);
			myButtons.add(thisButton);
			myEditableButtons[i-1] = thisButton;
			this.add(thisButton);	
		}
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
	public JButton addComment(JButton button, String comment){
		String newLabel = button.getText() + " " + comment;
		button.setText(newLabel);
		return button;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
