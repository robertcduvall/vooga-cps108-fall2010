package arcade.lobby.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import arcade.lobby.controller.Main;
import arcade.lobby.model.ProfileSet;

/**
 * Much of this code was taken from the Java swing examples on the Oracle website.
 * @author andrewbrown
 *
 */

public class Menu extends JMenuBar{
    
	private static final long serialVersionUID = 1L;

    public Menu(){
    	super();
    	initialize();
    }
    
    private void initialize(){
    	//Build the first menu.
		JMenu menu = new JMenu("User");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		this.add(menu);

		//a group of JMenuItems
		JMenuItem menuItem = new JMenuItem("Edit Profile Info",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Edit your profile information");
		menuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//TODO Create new Profile
				new ProfileFrame().setVisible(true);
				System.out.println("Open preferences");
			}
		});
		menu.add(menuItem);
		
		JMenuItem logOut = new JMenuItem("Logout");
		logOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ProfileSet.setCurrentProfile(null);
				Main.MainFrame.setContentPane(new LoginPanel());
			}
		});
		menu.add(logOut);
    }
	
}
