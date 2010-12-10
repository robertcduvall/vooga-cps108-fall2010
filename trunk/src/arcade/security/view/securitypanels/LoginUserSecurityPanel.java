package arcade.security.view.securitypanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginUserSecurityPanel extends JPanel{
	public LoginUserSecurityPanel(){
	    add(new JLabel("LogIn User Configuration"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK) );
	}
}
