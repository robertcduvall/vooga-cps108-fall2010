package arcade.wall.view.ratings;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

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
public class IconPanel extends AbstractButtonPanel {

	private ImageIcon myIcon;
	private String myIconPath = "src/arcade/core/RatingStar.gif"; 
	private JButton[] myIconButtons;

	
	//TODO: Allow the users to change individual images
	//TODO: Use Resources properly, remove hard-coding
	//TODO: The superclass method .getSelectedValue does NOT work with this
	//TODO: Add constructors that take in image path and scaling sizes

	
	public IconPanel(int scale) {
		super(scale);
		setDefaultIcon(myIconPath);
		scaleDefaultIcon(25,25);
		myIconButtons = new JButton[scale];
		
		for (int i = 1; i < scale+1; i ++){
			JButton thisButton = new JButton(""+i, myIcon);
			thisButton.setActionCommand(""+i);
			thisButton.addActionListener(this); // test this
			myButtons.add(thisButton);
			myIconButtons[i-1] = thisButton;
			this.add(thisButton);	
		}
		addComments();
	}
	
	/**
	 * Adds custom comments to buttons
	 */
	public void addComments(){
		myIconButtons[2].setText("3 =)");
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
    
//	/**
//	 * Adds a comment after the rating.
//	 * Ex: 4 - Awesome!
//	 * 
//	 * Note that the user would have to specify the "-".
//	 */
//	public void addComment(int buttonIndex, String comment){
//		String newLabel = myIconButtons[buttonIndex].getText() + " " + comment;
//		myRadioButtons[buttonIndex].setText(newLabel);
//	}
    

}
