package arcade.security.view;

import javax.swing.*;

import org.apache.log4j.Logger;

import arcade.security.control.Control;

import arcade.security.exceptions.UserConfigurationNotFoundException;
import arcade.security.gui.SecurityButton;
import arcade.security.gui.UserConfigurationFrame;
import arcade.security.model.LoginProcessModel;
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


public class AdminPanel extends JPanel{
	private final static Logger log=Logger.getLogger(AdminPanel.class);
	private static final long serialVersionUID = 1L;
	private static final int SECURITY_PANEL_WIDTH = 900;
	private static final int SECURITY_PANEL_HEIGHT = 300;
	private static final String DELIM = ";";
	private JLabel adminUserName; 
	private JButton LogoutButton;
	private Control controller;
	private JScrollPane adminScollPane;
	private JPanel userRolePane;
	private static String listOfPanels;
	private ResourceBundle resources = ResourceBundle
	.getBundle("arcade.security.resources.securitypanels.panels");


	public AdminPanel(Control controller){
		this.controller = controller;
		//controller.setModel(new LoginProcessModel(controller)); //this is necessary because it will match the model with the control and view
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
		addListeners();
		setVisible(true);
	}

	public JPanel getUserRolePanel() {
		if (userRolePane == null) {
			userRolePane = new JPanel(new GridLayout(0, 1));
			userRolePane.setPreferredSize(new Dimension(SECURITY_PANEL_WIDTH,SECURITY_PANEL_HEIGHT));
			//userRolePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return userRolePane;

	}

	public void addUserSecurityPanel(){  
		listOfPanels=resources.getString("listofpanels");		
		loadPanels(listOfPanels);

	}



	public void addListeners(){
		LogoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				log.info("Before log out, Current User role: "+user.getRole());
				user.setUserAs("default");
				log.info("After log out, Current User role: "+user.getRole());
				//controller.validatePanelSwitch("arcade.security.view.LogInPanel");
				controller.switchToLogInPage();
			}

		});
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
