package arcade.security.view;

import javax.swing.*;

import arcade.security.control.ControlTab;
import arcade.security.gui.SecurityButton;
import arcade.security.resourcesbundle.LabelResources;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;


public class AdminPanel extends ViewState{
	
	private static final long serialVersionUID = 1L;
	private JLabel adminUserName; 
	private JButton LogoutButton;
	
	public AdminPanel(){
		setLayout(new MigLayout());
		adminUserName = new JLabel("Uesr: Me. This is the Admin page. Currently under construction");
		add(adminUserName,"cell 0 0");
		LogoutButton = new SecurityButton(LabelResources.getLabel("Logout"));
		add(LogoutButton,"cell 1 0");
		//
		
		//
		addListeners();
	}
	protected void addListeners(){
		LogoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlTab.setValidate("arcade.security.view.LogoutPanel");
			}
			
		});
	}
}
