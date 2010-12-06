package arcade.lobby.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Much of this code was taken from the Java swing examples on the Oracle website.
 * @author andrewbrown
 *
 */

public class Menu {
    
    public Menu(){
    }
	
	public JMenuBar setUpMenu(){
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("User");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Edit Profile Info",
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
				Main.ProfileSet.currentProfile=null;
				Main.MainFrame.setContentPane(new Login());
			}
		});
		menu.add(logOut);

		return menuBar;
	}
	
}
