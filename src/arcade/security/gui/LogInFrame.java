package arcade.security.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import arcade.security.UserServiceUtil.User;
import arcade.security.UserServiceUtil.UserService;
import arcade.security.UserServiceUtil.UserServiceFactory;
import arcade.security.exceptions.UserConfigurationNotFoundException;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.PasswordHandler;
/**
 * Login Frame for Vooga arcade system.
 * @author Meng Li, Jiaqi Yan
 *
 */
@SuppressWarnings("serial")
public class LogInFrame extends JFrame {
	
	private JButton submitButton,cancelButton,signUpButton,forgotPasswordButton;
	private String username;
	private char[] password;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private int maxPasswordLength = 9;
	private int maxUserNameLength = 9;
	private PasswordHandler passwordHandler;
	

	public LogInFrame(){
		super();
		this.setAlwaysOnTop(true);
		MouseDragListener m1=new MouseDragListener(this); //inner class
		this.addMouseListener(m1);
		this.addMouseMotionListener(m1);
		
		setSize(270, 150);
		//setSize(300,255);
		this.setLayout(new MigLayout());
		this.setBackground(Color.white);
		JLabel image=new JLabel(new ImageIcon(StaticFileResources.getPath("loginimage")));
		JLabel label1=new JLabel(LabelResources.getLabel("UserId"));		
		JLabel label2=new JLabel(LabelResources.getLabel("Password"));
		
		usernameField=new JTextField(maxUserNameLength);	
		passwordField=new JPasswordField(maxPasswordLength);
		
		add(image,"cell 0 0 2 1");
		add(label1,"cell 0 1");
		add(usernameField,"cell 1 1,wrap");
		add(label2,"cell 0 2");
		add(passwordField,"wrap");
		
		submitButton = new SecurityButton(LabelResources.getLabel("LoginframeSubmit"),new ImageIcon(StaticFileResources.getPath("loginsubmit")),"Log in");
		cancelButton = new SecurityButton(LabelResources.getLabel("LoginframeCancel"),new ImageIcon(StaticFileResources.getPath("logincancel")),"Cancel");
		signUpButton = new SecurityButton(LabelResources.getLabel("LoginframeSignup"),"SignUp");
		forgotPasswordButton = new SecurityButton(LabelResources.getLabel("LoginframeForgot"),"ForgottenPassword");
		
		signUpButton.requestFocus(true);
		forgotPasswordButton.requestFocus(true);
		submitButton.requestFocus(true);
		passwordHandler = new PasswordHandler();
		
		add(submitButton,"");
		add(cancelButton,"wrap");
		add(signUpButton);
		add(forgotPasswordButton);
		
		this.setLocationRelativeTo(this);
		this.setUndecorated(true);   //no frameworks		
		pack();
		addListener();		
		setVisible(true);
	}

	class MouseDragListener extends MouseAdapter{
		JFrame frame;
		Point origin;
		Point current;
		Point frame_start_location;

		public MouseDragListener(JFrame frame){
			this.frame=frame;	

		}

		Point getabsoluteScreenLocation(MouseEvent e) {
			Point cursor=e.getPoint(); 
			Point target_location=frame.getLocationOnScreen();
			return new Point((int) (target_location.getX()) + (int)(cursor.getX()),
					(int) (target_location.getY()) + (int)(cursor.getY()));
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			current=getabsoluteScreenLocation(e);
			Point offset=new Point((int)(current.getX())-(int)frame_start_location.getX(),(int)(current.getY())-(int)frame_start_location.getY());
			this.frame.setLocation( new Point((int)(origin.getX())+(int)(offset.getX()),(int)(origin.getY())+(int)(offset.getY())));

		}

		@Override
		public void mousePressed(MouseEvent e) {
			frame_start_location = getabsoluteScreenLocation(e);
			origin=frame.getLocation();
		}

	}

	public class SubmitEvent implements ActionListener{
		JFrame jf;
		public SubmitEvent(JFrame frame){
			jf = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			username=usernameField.getText();
			password=passwordField.getPassword();
			if(checkPassword(null)){
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				user.setUserAs("admin");
				System.out.println("Current User role: "+user.getRole());
				
				try {
					MainFrame main = new MainFrame();
					jf.dispose();					
				} catch (UserConfigurationNotFoundException e1) {
					e1.printStackTrace();
				}

			}
			else{			
				passwordField.selectAll();
				passwordField.requestFocus(true);
			}
		}
	}
	
	public class SignUpEvent implements ActionListener{
		JFrame jf;
		public SignUpEvent(JFrame frame){
			jf = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				SignUpFrame frame = new SignUpFrame();
				jf.dispose();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		
	}
		
	

	public void addListener(){
		submitButton.addActionListener(new SubmitEvent(this));//{
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				username=text1.getText();
//				password=text_pass.getPassword();
//				if(checkPassword(null)){
//					
//					try {
//						MainFrame main = new MainFrame();
//						
//					} catch (UserConfigurationNotFoundException e1) {
//						e1.printStackTrace();
//					}
//	
//				}
//				else{			
//					text_pass.selectAll();
//					text_pass.requestFocus(true);
//				}
//			}
//
//		}
//		);


		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);					
			}				
		});
		signUpButton.addActionListener(new SignUpEvent(this));	
	}

	public boolean checkPassword(char[] input){
		return true;			
	}

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		System.out.println("Current User role: "+user.getRole());
		new LogInFrame();
	}
}
