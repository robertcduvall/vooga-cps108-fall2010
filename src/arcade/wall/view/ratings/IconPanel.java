package arcade.wall.view.ratings;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconPanel extends ButtonPanel {

	private ImageIcon myIcon;
	private String myIconPath = "src/arcade/core/RatingStar.gif"; 
	private JButton[] myEditableButtons;

	
	//TODO: Allow the users to change individual images
	//TODO: Use Resources properly, remove hard-coding
	//TODO: The superclass method .getSelectedValue does NOT work with this

	
	public IconPanel(int scale) {
		super(scale);
		setDefaultImageIcon(myIconPath);
		scaleDefaultIcon(25,25);
		myEditableButtons = new JButton[scale];
		
		for (int i = 1; i < scale+1; i ++){
			JButton thisButton = new JButton(""+i, myIcon);
			thisButton.setActionCommand(""+i);
			thisButton.addActionListener(this); // test this
			myButtons.add(thisButton);
			myEditableButtons[i-1] = thisButton;
			this.add(thisButton);	
		}
		
	}
	
	/**
	 *  Returns an ImageIcon, or null if the path was invalid
	 *  
	 **/
    public void setDefaultImageIcon(String imagePath) {
    	
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
    

}
