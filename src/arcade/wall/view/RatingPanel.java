package arcade.wall.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RatingPanel extends JPanel implements ActionListener {

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
