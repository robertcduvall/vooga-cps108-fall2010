package arcade.security.util;

import java.util.Map;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.*;
import java.util.ArrayList;
import arcade.security.gui.AdminBox;


/**
 * Model for the AdminPanel view
 * @author Jiaqi Yan
 *
 */
public class AdminHandler {
	
	public static DataHandler userHandler = DataHandler.getInstance("User");
	private static Map<String,String> userTypeMap;
	private static Map<String,Integer> changeMap;
	private static String[] userTypes = {"Guest","User","Developer","Administrator"};
	private static ArrayList<AdminBox> AdminBoxList;
	
	public AdminHandler(){
		userTypeMap = userHandler.getUserTypeMap();
		changeMap = new HashMap<String,Integer>();
		AdminBoxList = new ArrayList<AdminBox>();
	}
	
	public JComboBox createNewAdminBox(String name){
		AdminBox box = new AdminBox(name,userTypes);
		AdminBoxList.add(box);
		return box;
	}
	
	public static void addAdminBox(AdminBox box){
		AdminBoxList.add(box);
	}
	
	public Map<String,String> getUserTypeMap(){
		return userTypeMap;
	}
	
	public static String getUserType(int index){
		return userTypes[index];
	}
	
	public static int getUserTypeIndex(String type){
		for(int i=0;i<userTypes.length;i++){
			if(type.equals(userTypes[i])) return i;
		}
		return -1;//error
	}
	
	public String[] getUserTypes(){
		return userTypes;
	}
	
	public JButton createUndoButton(){
		JButton undoButton = new JButton("Undo changes");
		undoButton.addActionListener(new UndoEvent());
		return undoButton;
	}
	
	public static void changeUserType(String username,int index){ 
		changeMap.put(username,index);
	}
	
	public JButton createSubmitButton(){
		JButton submitButton = new JButton("Submit Changes");
		submitButton.addActionListener(new SubmitEvent());
		return submitButton;
	}
	
	public class UndoEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			for(int i=0;i<AdminBoxList.size();i++){
				AdminBox box = AdminBoxList.get(i);
				String name = box.getName();
				if(changeMap.keySet().contains(name)){
					String type = userTypeMap.get(name);
					AdminBoxList.get(i).changeDisplayContent(getUserTypeIndex(type)+1);
				}
			}
			
		}
		
	}
	
	public class SubmitEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Performed");
			userHandler.updateUserTypes(changeMap);
		}
		
	}
	

}
