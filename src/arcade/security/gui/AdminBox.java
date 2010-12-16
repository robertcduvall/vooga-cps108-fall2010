package arcade.security.gui;

import javax.swing.JComboBox;

import arcade.security.util.AdminHandler;
import java.awt.event.*;

public class AdminBox extends JComboBox{
	private String username;
	public AdminBox(String name,String[] userTypes){
		super(userTypes);
		username = name;
		addItemListener(new AdminBoxListener());
	}
	
	public String getName(){
		return username;
	}
	
	public void changeDisplayContent(int index){
		setSelectedIndex(index);
		updateUI();
	}
	
	public class AdminBoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			AdminHandler.changeUserType(username, getSelectedIndex());		
		}
		
	}
		
	
}
