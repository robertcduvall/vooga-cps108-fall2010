package arcade.wall.view.ratings;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import arcade.core.Panel;

public class IntegerSelectPanel extends JPanel implements ActionListener{
	
	public int myScale;
	public ButtonGroup myButtons;

	public IntegerSelectPanel(int scale){
		for (int i = 1; i < scale+1; i ++){
			JButton thisButton = new JButton(""+i);
			thisButton.setActionCommand(""+i);
			myButtons.add(thisButton);
			this.add(thisButton);
			
		}
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
	 * Sets image of button to the one passed in
	 * 
	 * @param image - Pathname of the image
	 */
	public void setAllImages(String image){
		ImageIcon buttonIcon = createImageIcon(image);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
