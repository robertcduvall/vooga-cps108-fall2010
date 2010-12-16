package arcade.security.util;

import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import arcade.security.gui.PrivilegeCheckBox;
import arcade.security.util.userserviceutil.UserServiceFactory;

public class UserPrivilegesPanelHandler {
	private static DataHandler privilegeHandler = DataHandler.getInstance("Privileges");
	private static Map<String,Boolean> currentUserMap;
	private static Map<String,Boolean> changes;
	private ArrayList<PrivilegeCheckBox> checkBoxList;
	private String username;
	
	
	public UserPrivilegesPanelHandler(String username){
		this.username = username;
		String privileges = privilegeHandler.getPrivileges(username);
		currentUserMap = UserServiceFactory.getPrivilegeMap().userPrivilegeMap(privileges);
		changes = new HashMap<String,Boolean>();
		checkBoxList = new ArrayList<PrivilegeCheckBox>();
	}
	
	public PrivilegeCheckBox createNewPrivilegeCheckBox(String name,boolean value){
		PrivilegeCheckBox box = new PrivilegeCheckBox(name,value);
		checkBoxList.add(box);
		return box;
	}
	
	public static void toggle(String privilege){
		boolean value = false;
		if(!changes.containsKey(privilege)) value = currentUserMap.get(privilege);
		else value = changes.get(privilege);
		value = (!value);
		changes.put(privilege, value);
	}
	
	public Map<String,Boolean> getCurrentUserMap(){
		return currentUserMap;
	}
	
	public JButton createSubmitButton(){
		JButton submitButton = new JButton("Submit changes");
		submitButton.addActionListener(new SubmitPrivilegesListener());
		return submitButton;
	}
	
	public class SubmitPrivilegesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			char[] privilegeValues = new char[currentUserMap.size()];
			for(String privilege: currentUserMap.keySet()){
				int index = UserServiceFactory.getPrivilegeMap().getPrivilegeIndex(privilege);
				privilegeValues[index] = (currentUserMap.get(privilege))? '1':'0';
			}
			for(String privilege: changes.keySet()){
				int index = UserServiceFactory.getPrivilegeMap().getPrivilegeIndex(privilege);
				privilegeValues[index] = (changes.get(privilege))? '1':'0';
			}
			privilegeHandler.setUserPrivilege(username,String.valueOf(privilegeValues));
			JOptionPane.showMessageDialog(null,"Changes committed");
			String privileges = privilegeHandler.getPrivileges(username);
			currentUserMap = UserServiceFactory.getPrivilegeMap().userPrivilegeMap(privileges);
		}
		
	}
	
	public JButton createUndoButton(){
		JButton undoButton = new JButton("Undo changes");
		undoButton.addActionListener(new UndoPrivilegesListener());
		return undoButton;
	}
	public class UndoPrivilegesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<checkBoxList.size();i++){
				PrivilegeCheckBox box = checkBoxList.get(i);
				String privilege = box.getPrivilege();
				if(changes.containsKey(privilege)){
					boolean value = currentUserMap.get(privilege);
					checkBoxList.get(i).setValue(value);
				}
			}
			changes.clear();
		}
		
	}
	
	
	
}
