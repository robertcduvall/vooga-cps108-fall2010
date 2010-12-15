package arcade.security.view;

import javax.swing.*;

import org.apache.log4j.Logger;

import arcade.security.control.AdminPanelControl;
import arcade.security.control.IControl;

import arcade.security.exceptions.UserConfigurationNotFoundException;
import arcade.security.gui.SecurityButton;
import arcade.security.gui.UserConfigurationFrame;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class AdminPanel extends JPanel implements IView{
	private final static Logger log=Logger.getLogger(AdminPanel.class);
	private static final long serialVersionUID = 1L;
	private static final int SECURITY_PANEL_WIDTH = 900;
	private static final int SECURITY_PANEL_HEIGHT = 300;
	private static final String DELIM = ";";
	private JLabel adminUserName; 
	private JButton LogoutButton;
	//private AdminPanelControl controller;
	private JScrollPane adminScollPane;
	private JPanel userRolePane;
	private static String listOfPanels;
	private ResourceBundle resources = ResourceBundle
	.getBundle("arcade.security.resources.securitypanels.panels");


	public AdminPanel(){
		setName("Admin page"); 
		setLayout(new MigLayout("wrap 2"));
		adminUserName = new JLabel("Admin page. Currently under construction");
		add(adminUserName);
		LogoutButton = new SecurityButton(LabelResources.getLabel("Logout"));
		add(LogoutButton,"gapleft 585");

		adminScollPane = new JScrollPane();
		adminScollPane.setViewportView(getUserRolePanel());		
		addUserSecurityPanel();
		add(adminScollPane,"span 2");
		setVisible(true);
	}

	public JPanel getUserRolePanel() {
		if (userRolePane == null) {
			userRolePane = new JPanel(new GridLayout(0, 1));
			userRolePane.setPreferredSize(new Dimension(SECURITY_PANEL_WIDTH,SECURITY_PANEL_HEIGHT));

		}
		return userRolePane;

	}

	public void addUserSecurityPanel(){  
		listOfPanels=resources.getString("listofpanels");		
		loadPanels(listOfPanels);

	}

	public void addLogoutButtonListener(ActionListener listener){
		LogoutButton.addActionListener(listener);
	}


	private void loadPanels(String listOfPanels) {
		String[] panel = listOfPanels.split(DELIM);
		for(String p: panel){
			JPanel temp=createSecurityPane(p);
			getUserRolePanel().add(temp);					
		}				
	}

	private JPanel createSecurityPane(String classPath)
	{			
		try{
			Object securityPanel=Class.forName(classPath).newInstance();  		    	 	
			return (JPanel)securityPanel;      
		}
		catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		catch(InstantiationException e2){
			e2.printStackTrace();
		}
		catch(IllegalAccessException e3){
			e3.printStackTrace();
		}
		return null;					    
	}

}