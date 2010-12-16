package arcade.wall.views.ratings;


import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import arcade.wall.retired.AbstractButtonPanel;

/**
 * 
 * Creates a Rating Panel that is made up of a number of Buttons
 * as provided by the user. The Buttons will be created
 * with an icon image as provided by the user.
 * In order to change the Panel layout, please refer
 * to the methods in the ButtonPanel superclass.
 * 
 * @author Cameron McCallie
 *
 *
 */
@SuppressWarnings("serial")
public class IconPanel extends JPanel implements ActionListener {

	private ImageIcon myIcon;
	private String myIconPath = "src/arcade/core/RatingStar.gif"; 
	private JButton[] myIconButtons;
	private ButtonGroup myButtons;

	
	//TODO: Allow the users to change individual images
	//TODO: Use Resources properly, remove hard-coding
	//TODO: The superclass method .getSelectedValue does NOT work with this
	//TODO: Add constructors that take in image path and scaling sizes

	
	public IconPanel(int scale) {
		setDefaultIcon(myIconPath);
		scaleDefaultIcon(25,25);
		myIconButtons = new JButton[scale];
		myButtons = new ButtonGroup();
		
		for (int i = 1; i < scale+1; i ++){
			JButton thisButton = new JButton(""+i, myIcon);
			thisButton.setActionCommand(""+i);
			thisButton.addActionListener(this);
			myButtons.add(thisButton);
			myIconButtons[i-1] = thisButton;
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
		String newLabel = myIconButtons[buttonIndex - 1].getText() + " " + comment;
		myIconButtons[buttonIndex - 1].setText(newLabel);
	}
	
	/**
	 *  Sets default icon to the icon represented by its String path
	 *  
	 **/
    public void setDefaultIcon(String imagePath) {
    	
    	myIcon = new ImageIcon(imagePath);
    }
    
    /**
     * Scales default icon to user preferences
     */
    public void scaleDefaultIcon(int x, int y){
    	Image scaledIcon = myIcon.getImage().getScaledInstance(x, y,
				java.awt.Image.SCALE_SMOOTH);
    	myIcon = new ImageIcon(scaledIcon);
    }
    
    /**
     * Sets this button's icon to the one given from the imagePath
     * 
     */
    public JButton setButtonIcon(JButton button, String imagePath){
    	ImageIcon newImage = new ImageIcon(imagePath);
    	button.setIcon(newImage);
    	return button;
    }
    
	/**
	 * Returns the current button selection
	 */
    public String getSelectedValue() {
    	String actionCommand = " ";
    	for (int i = 0; i < myIconButtons.length; i++){
    		if (myIconButtons[i].isSelected())
    			actionCommand = myIconButtons[i].getActionCommand();
    	}
		System.out.println(actionCommand);
		return actionCommand;
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
		int correctValue = 0;
		for (int i = 0; i < myIconButtons.length; i++){
			String actionCommand = ""+i+1;
			if (actionCommand.equals(e.getActionCommand())){
				myIconButtons[i].setSelected(true);
				correctValue = i+1;
			}
		}
		for (int j = 0; j < 5; j++){
			if (j != correctValue)
				myIconButtons[j].setSelected(false);
		}
	}
    
    

}
