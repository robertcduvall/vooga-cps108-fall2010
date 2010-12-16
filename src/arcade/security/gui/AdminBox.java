package arcade.security.gui;

import javax.swing.JComboBox;

import arcade.security.util.AdminHandler;
import java.awt.event.*;

/**
 * AdminBox is an extension of JComboBox, which is displayed only on the
 * administrator panel.
 * 
 * @author Jiaqi Yan, Meng Li, Nick Hawthorne
 * 
 */
public class AdminBox extends JComboBox {
	private String username;

	/**
	 * Constructor for an AdminBox
	 * 
	 * @param name
	 *            The username desired
	 * @param userTypes
	 *            An array of userTypes as options
	 */
	public AdminBox(String name, String[] userTypes) {
		super(userTypes);
		username = name;
		addItemListener(new AdminBoxListener());
	}

	/**
	 * Return the username associated with this AdminBox
	 * 
	 * @return The username for this box
	 */
	public String getName() {
		return username;
	}

	/**
	 * Changes the item displayed in the box.
	 * 
	 * @param index
	 *            The index of the new item to display
	 */
	public void changeDisplayContent(int index) {
		setSelectedIndex(index);
		updateUI();
	}

	/**
	 * 
	 * Listener for the AdminBox.
	 * 
	 */
	public class AdminBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			AdminHandler.changeUserType(username, getSelectedIndex());
		}

	}

}
