package arcade.security.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import arcade.security.view.UserPrivilegesPanel;
import arcade.security.view.AdminPanel;
import arcade.security.util.UserPrivilegesPanelHandler;

import java.awt.event.*;

/**
 * This is a Button for the administrator panel.
 * 
 * @author Jiaqi Yan, Meng Li, Nick Hawthorne
 * 
 */
public class GoToPrivilegesButton extends JButton {
	private String username;

	/**
	 * Constructor for the button.
	 * 
	 * @param name
	 *            The username of the person whose privileges should be accessed
	 */
	public GoToPrivilegesButton(String name) {
		super("Go To " + name + "'s Privilege Settings");
		username = name;
		addActionListener(new GoToPrivilegesListener());
	}

	/**
	 * Listener for the GoToPrivilegeButton
	 */
	public class GoToPrivilegesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame privilegeFrame = new JFrame();
			UserPrivilegesPanel adminPanel = new UserPrivilegesPanel(username);
			privilegeFrame.add(adminPanel);
			privilegeFrame.pack();
			privilegeFrame.setVisible(true);
		}
	}

}
