package arcade.security.view;

import javax.swing.*;

import org.apache.log4j.Logger;

import arcade.security.UserServiceUtil.User;
import arcade.security.UserServiceUtil.UserService;
import arcade.security.UserServiceUtil.UserServiceFactory;
import arcade.security.control.Control;
import arcade.security.control.ControlTab;
import arcade.security.gui.SecurityButton;
import arcade.security.model.LoginProcessModel;
import arcade.security.resourcesbundle.LabelResources;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;


public class AdminPanel extends JPanel{
	private final static Logger log=Logger.getLogger(AdminPanel.class);
	private static final long serialVersionUID = 1L;
	private JLabel adminUserName; 
	private JButton LogoutButton;
	private Control controller;
	
	public AdminPanel(Control controller){
		this.controller = controller;
		//controller.setModel(new LoginProcessModel(controller)); //this is necessary because it will match the model with the control and view
		this.setName("Admin page");
		setLayout(new MigLayout());
		adminUserName = new JLabel("Uesr: Me. This is the Admin page. Currently under construction");
		add(adminUserName,"cell 0 0");
		LogoutButton = new SecurityButton(LabelResources.getLabel("Logout"));
		add(LogoutButton,"cell 1 0");
		//
		
		//
		
		addListeners();
		setVisible(true);
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
}
