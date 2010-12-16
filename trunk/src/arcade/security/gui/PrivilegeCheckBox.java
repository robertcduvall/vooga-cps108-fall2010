package arcade.security.gui;

import javax.swing.JCheckBox;

import arcade.security.util.UserPrivilegesPanelHandler;

import java.awt.event.*;

/**
 * This is a checkbox which appears in the administrator panel.
 * 
 * @author Jiaqi Yan, Meng Li, Nick Hawthorne
 */
public class PrivilegeCheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;
	private boolean checked;
	private String privilege;

	/**
	 * Constructor for the checkbox.
	 * 
	 * @param privilege
	 *            The privilege to be displayed
	 * @param value
	 *            Whether the privilege is checked or unchecked
	 */
	public PrivilegeCheckBox(String privilege, boolean value) {
		this(privilege, value, null);
		addItemListener(new PrivilegeCheckBoxListener());
	}

	/**
	 * Constructor for the checkbox that takes an ItemListener.
	 * 
	 * @param privilege
	 *            The privilege to be displayed
	 * @param value
	 *            Whether the privilege is checked or unchecked
	 * @param listener
	 *            A listener for the checkbox
	 */
	public PrivilegeCheckBox(String privilege, boolean value,
			ItemListener listener) {
		super(privilege, value);
		this.privilege = privilege;
		checked = value;
		setSelected(value);
		addItemListener(listener);
	}

	/**
	 * Sets the value of the checkbox
	 * 
	 * @param value
	 *            The value to set it to
	 */
	public void setValue(boolean value) {
		// setBorderPaintedFlat(value);
		setSelected(false);
		updateUI();
	}

	/**
	 * Gets the privilege represented by the checkbox.
	 * 
	 * @return The privilege represented
	 */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * Listener for the checkbox
	 * 
	 */
	private class PrivilegeCheckBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			UserPrivilegesPanelHandler.toggle(privilege);
		}
	}
}
