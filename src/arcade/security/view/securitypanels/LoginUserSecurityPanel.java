package arcade.security.view.securitypanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View object for the logged in user panel.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class LoginUserSecurityPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the logged in user's security display
	 */
	public LoginUserSecurityPanel() {
		add(new JLabel("LogIn User Configuration"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
