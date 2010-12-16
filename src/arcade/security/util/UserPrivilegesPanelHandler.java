package arcade.security.util;

import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import arcade.security.gui.PrivilegeCheckBox;
import arcade.security.util.userserviceutil.UserServiceFactory;

/**
 * Model object for the user privilege modification panel.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class UserPrivilegesPanelHandler {
	private static DataHandler privilegeHandler = DataHandler
			.getInstance("Privileges");
	private static Map<String, Boolean> currentUserMap;
	private static Map<String, Boolean> changes;
	private ArrayList<PrivilegeCheckBox> checkBoxList;
	private String username;

	/**
	 * Constructor for the Handler object.
	 * 
	 * @param username
	 *            The user who is calling up the panel.
	 */
	public UserPrivilegesPanelHandler(String username) {
		this.username = username;
		String privileges = privilegeHandler.getPrivileges(username);
		currentUserMap = UserServiceFactory.getPrivilegeMap().userPrivilegeMap(
				privileges);
		changes = new HashMap<String, Boolean>();
		checkBoxList = new ArrayList<PrivilegeCheckBox>();
	}

	/**
	 * Creates a new check box for privileges.
	 * 
	 * @param name
	 *            The name of the box
	 * @param value
	 *            If checked
	 * @return The newly created box
	 */
	public PrivilegeCheckBox createNewPrivilegeCheckBox(String name,
			boolean value) {
		PrivilegeCheckBox box = new PrivilegeCheckBox(name, value);
		checkBoxList.add(box);
		return box;
	}

	/**
	 * Toggle a certain privilege.
	 * 
	 * @param privilege
	 *            The privilege to be toggled
	 */
	public static void toggle(String privilege) {
		boolean value = false;
		if (!changes.containsKey(privilege))
			value = currentUserMap.get(privilege);
		else
			value = changes.get(privilege);
		value = (!value);
		changes.put(privilege, value);
	}

	/**
	 * Gets a map of users, and whether they are currently logged in.
	 * 
	 * @return The map of users
	 */
	public Map<String, Boolean> getCurrentUserMap() {
		return currentUserMap;
	}

	/**
	 * Creates a "Submit Changes" button
	 * 
	 * @return The newly created button
	 */
	public JButton createSubmitButton() {
		JButton submitButton = new JButton("Submit changes");
		submitButton.addActionListener(new SubmitPrivilegesListener());
		return submitButton;
	}

	/**
	 * Listener for the submit button
	 * 
	 */
	public class SubmitPrivilegesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			char[] privilegeValues = new char[currentUserMap.size()];
			for (String privilege : currentUserMap.keySet()) {
				int index = UserServiceFactory.getPrivilegeMap()
						.getPrivilegeIndex(privilege);
				privilegeValues[index] = (currentUserMap.get(privilege)) ? '1'
						: '0';
			}
			for (String privilege : changes.keySet()) {
				int index = UserServiceFactory.getPrivilegeMap()
						.getPrivilegeIndex(privilege);
				privilegeValues[index] = (changes.get(privilege)) ? '1' : '0';
			}
			privilegeHandler.setUserPrivilege(username, String
					.valueOf(privilegeValues));
			JOptionPane.showMessageDialog(null, "Changes committed");
			String privileges = privilegeHandler.getPrivileges(username);
			currentUserMap = UserServiceFactory.getPrivilegeMap()
					.userPrivilegeMap(privileges);
		}

	}

	/**
	 * Creates an "Undo changes button"
	 * 
	 * @return The new button
	 */
	public JButton createUndoButton() {
		JButton undoButton = new JButton("Undo changes");
		undoButton.addActionListener(new UndoPrivilegesListener());
		return undoButton;
	}

	/**
	 * Listener for the undo button.
	 */
	public class UndoPrivilegesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < checkBoxList.size(); i++) {
				PrivilegeCheckBox box = checkBoxList.get(i);
				String privilege = box.getPrivilege();
				if (changes.containsKey(privilege)) {
					boolean value = currentUserMap.get(privilege);
					checkBoxList.get(i).setValue(value);
				}
			}
			changes.clear();
		}

	}

}
