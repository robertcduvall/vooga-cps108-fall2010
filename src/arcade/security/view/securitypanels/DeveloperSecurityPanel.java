package arcade.security.view.securitypanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View object for the Developer panel.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
@SuppressWarnings("serial")
public class DeveloperSecurityPanel extends JPanel {

	/**
	 * Constructor for the developer panel
	 */
	public DeveloperSecurityPanel() {
		add(new JLabel("Developer configuration"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
