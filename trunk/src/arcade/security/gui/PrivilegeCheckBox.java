package arcade.security.gui;

import javax.swing.JCheckBox;

import arcade.security.util.UserPrivilegesPanelHandler;

import java.awt.event.*;


public class PrivilegeCheckBox extends JCheckBox{
	
	private static final long serialVersionUID = 1L;
	private boolean checked;
	private String privilege;
	
	public PrivilegeCheckBox(String privilege,boolean value){
		this(privilege, value, null);
		addItemListener(new PrivilegeCheckBoxListener());
	}
	
	public PrivilegeCheckBox(String privilege,boolean value, ItemListener listener){
		super(privilege,value);
		this.privilege = privilege;
		checked = value;
		setSelected(value);
		addItemListener(listener);
	}
	
	public void setValue(boolean value){
		//setBorderPaintedFlat(value);
		setSelected(false);
		updateUI();
	}
	
	public String getPrivilege(){
		return privilege;
	}
	
	private class PrivilegeCheckBoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			UserPrivilegesPanelHandler.toggle(privilege);
		}
	}
}
