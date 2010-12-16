package arcade.security.gui;

import javax.swing.JCheckBox;

import arcade.security.util.UserPrivilegesPanelHandler;

import java.awt.event.*;

public class PrivilegeCheckBox extends JCheckBox{
	private boolean checked;
	private String privilege;
	public PrivilegeCheckBox(String privilege,boolean value){
		super(privilege,value);
		this.privilege = privilege;
		checked = value;
		setSelected(value);
		addItemListener(new PrivilegeCheckBoxListener());
	}
	
	public void setValue(boolean value){
		//setBorderPaintedFlat(value);
		setSelected(false);
		updateUI();
	}
	
	public String getPrivilege(){
		return privilege;
	}
	
	public class PrivilegeCheckBoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			UserPrivilegesPanelHandler.toggle(privilege);
		}
	}
}
