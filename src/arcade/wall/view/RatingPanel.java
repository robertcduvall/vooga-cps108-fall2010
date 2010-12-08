package arcade.wall.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import arcade.core.Panel;


//																Rating scale customization
//At the highest level, the rating panel should allow for users to pass in an integer value for the scale on which they want things to be rated.
//Integer values will be allowed between 1-10. Users should be able to select a percentage mode, in which they enter a percent.


//																Rating type customization
// Integer rating scales: combo box, radio buttons, custom icons. Radio but







@SuppressWarnings("serial")
public class RatingPanel extends Panel implements ActionListener {

	private ButtonGroup myGroup;
	
	public RatingPanel(int scale){
		myGroup = new ButtonGroup();
		JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 0));
        
		for (int i = 1; i < scale+1; i ++){
			JRadioButton thisButton = new JRadioButton(""+i);
			thisButton.setActionCommand(""+i);
			myGroup.add(thisButton);
			radioPanel.add(thisButton);
			
		}
       
        setLayout(new BorderLayout());
        add(radioPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
	}


	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public String getSelectedValue() {
		return myGroup.getSelection().getActionCommand();
	}
	
	
}
