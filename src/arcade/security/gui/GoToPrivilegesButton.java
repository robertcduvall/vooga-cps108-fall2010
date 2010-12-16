package arcade.security.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import arcade.security.view.UserPrivilegesPanel;
import arcade.security.view.AdminPanel;
import arcade.security.util.UserPrivilegesPanelHandler;

import java.awt.event.*;

public class GoToPrivilegesButton extends JButton{
	private String username;
	
	public GoToPrivilegesButton(String name){
		super("Go To "+name+"'s Privilege Settings");
		username = name;
		addActionListener(new GoToPrivilegesListener());
	}
	
	public class GoToPrivilegesListener implements ActionListener{

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
