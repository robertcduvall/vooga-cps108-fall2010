package arcade.security.gui;

import javax.swing.*;

import arcade.security.resourcesbundle.LabelResources;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class SignUpFrame extends JFrame{
	JProgressBar progressbar;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRep;
	
	private int maxPasswordLength = 5;
	private int maxUserNameLength = 5;
	
	
	public SignUpFrame(){
		super();
		//setSize(270, 150);
		this.setLayout(new MigLayout());
		this.setBackground(Color.white);
		JLabel username_label=new JLabel(LabelResources.getLabel("AskForUserName"));		
		JLabel pwd_label1=new JLabel(LabelResources.getLabel("AskForPwd"));
		JLabel pwd_label2 = new JLabel(LabelResources.getLabel("AskForPwdAgain"));
		add(username_label,"cell 0 1");
		add(pwd_label1,"cell 0 2");
		add(pwd_label2,"cell 0 3");
		usernameField=new JTextField(maxUserNameLength);	
		passwordField=new JPasswordField(maxPasswordLength);
		passwordFieldRep=new JPasswordField(maxPasswordLength);
		add(usernameField,"cell 1 1,wrap");
		add(passwordField,"cell 1 2");
		add(passwordFieldRep,"cell 1 3");
		
		pack();
		setVisible(true);
	}
	
}
