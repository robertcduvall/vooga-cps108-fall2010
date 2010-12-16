package arcade.security.view.securitypanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View object for the DefaultUser panel.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class DefaultUserSecurityPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Default User security display
	 */
	public DefaultUserSecurityPanel() {
		add(new JLabel("Default User Configuration"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
