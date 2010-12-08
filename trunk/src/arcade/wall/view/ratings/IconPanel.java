package arcade.wall.view.ratings;


import javax.swing.ImageIcon;

public class IconPanel extends IntegerSelectPanel {

	private ImageIcon myIcon;
	private String myIconPath = "src/arcade/core/RatingStar.gif"; //rework for Resources
	
	//TODO: Allow the users to change individual images
	
	public IconPanel(int scale) {
		super(scale);
		setImageIcon(myIconPath);
	}
	
	/**
	 *  Returns an ImageIcon, or null if the path was invalid
	 *  
	 **/
    public void setImageIcon(String imagePath) {
    	// GET THIS TO WORK WITH RESOURCES

    	myIcon = new ImageIcon(imagePath);
    }
    
    /**
     * Sets all button icons to the class' current icon
     * 
     */
    public void setButtonIcons(){
    	for (int i = 0; i < myEditableButtons.length; i++){
    		myEditableButtons[i].setIcon(myIcon);
    	}
    }

}
