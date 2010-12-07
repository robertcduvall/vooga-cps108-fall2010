package arcade.wall.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RatingPanel extends JPanel implements ActionListener {

	
	public RatingPanel(int scale){
		ButtonGroup group = new ButtonGroup();
		JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 0));
        
		for (int i = 1; i < scale+1; i ++){
			JRadioButton thisButton = new JRadioButton(""+i);
			thisButton.setActionCommand(""+i);
			group.add(thisButton);
			radioPanel.add(thisButton);
		}
       
        setLayout(new BorderLayout());
        add(radioPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	
}
