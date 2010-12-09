package arcade.security.view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
/**
 * Logout Panel
 * @author Jiaqi Yan
 *
 */
public class LogoutPanel extends ViewState{
	JLabel LogoutDisplay;
	public LogoutPanel(){
		setLayout(new MigLayout());
		LogoutDisplay = new JLabel("Logged Out!");
		add(LogoutDisplay);
	}
	@Override
	protected void addListeners() {
		
	}
	
}
