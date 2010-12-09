package arcade.security.view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class LogoutPanel extends JPanel{
	JLabel LogoutDisplay;
	public LogoutPanel(){
		setLayout(new MigLayout());
		LogoutDisplay = new JLabel("Logged Out!");
		add(LogoutDisplay);
	}
}
