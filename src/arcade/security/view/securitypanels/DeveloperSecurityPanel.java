package arcade.security.view.securitypanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DeveloperSecurityPanel extends JPanel{
		
	public DeveloperSecurityPanel(){
	    add(new JLabel("Developer configuration"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK) );
	}
}
