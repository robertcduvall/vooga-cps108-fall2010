package arcade.security.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import arcade.core.Arcade;
import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.model.AdminProcess;
import arcade.security.model.RetrievePasswordProcess;
import arcade.security.model.IModel;
import arcade.security.model.LoginProcess;
import arcade.security.model.SignUpProcess;
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.view.AdminPanel;
import arcade.security.view.IView;
import arcade.security.view.LogInPanel;
import arcade.security.view.RetrievePasswordPanel;
import arcade.security.view.SignUpPanel;

/**
 * Controller for the Login Panel. Used in conjunction with security.view.LogInPanel
 * and security.model.LoginProcess.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne 
 *  
 */
public class LogInPanelControl implements IControl{

	
	private final static Logger log=Logger.getLogger(LogInPanelControl.class);
	private LoginProcess model;
	private LogInPanel view; 
	
	/**
	 * Constructor for the login panel controller. Takes a view and a model object
	 * 
	 * @param view - the corresponding view object
	 * @param model - the corresponding model object
	 */
	public LogInPanelControl(IView view, IModel model){
		this.model = (LoginProcess)model;
		this.view = (LogInPanel)view;
		this.view.addSubmitButtonListeners(new SubmitEvent());
		this.view.addSignUpButtonListener(new SignUpEvent());
		this.view.addForgetButtonListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToForgetPasswordPage();
			}
		});
	}
	
	/**
	 * Switches the current panel to the forgotten password page
	 * 
	 */
	private void switchToForgetPasswordPage(){
		view.removeAll();
		view.updateUI();
		JPanel jp = new RetrievePasswordPanel();
		view.add(jp);
		RetrievePasswordProcess model = new RetrievePasswordProcess();
		RetrievePasswordPanelControl controller = new RetrievePasswordPanelControl((IView)jp,model);	
	}

	/**
	 * Checks a username and password to see if they are in the database
	 * @param username
	 * @param password
	 * @return true if the login is successful
	 */
	private boolean isSuccessfulLogin(String username, char[] password){
		return model.isSuccessfulLogin(username, password);
	}
	
	/**
	 * Switches the current panel to the administrator page
	 */
	private void switchToAdminPage(){
		view.removeAll();
		view.updateUI();
		JPanel jp = new AdminPanel();
		view.add(jp);
		AdminProcess model = new AdminProcess();
		AdminPanelControl controller = new AdminPanelControl((IView)jp,model);	
	}
	
	/**
	 * Returns the current LogInPanelControl object
	 * @return the current LogInPanelControl object
	 */
	private LogInPanelControl getCurrentController(){
		return this;
	}
	

	/**
	 * Switches the current panel to the signup page
	 */
	private void switchToSignUpPage(){
		view.removeAll();
		view.updateUI();
		JPanel jp = new SignUpPanel();
		view.add(jp);	
	}
	
	private class SignUpEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switchToSignUpPage();
		}
		
	}
	
	private class SubmitEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = new String(view.getUserNameUserInput());
			char[] password = view.getPasswordUserInput();
			if(!isSuccessfulLogin(username, password)){ //next step towards true mvc:move this to controller
			//if(!LogInHandler.successfulLogin(username, password)){
				JOptionPane.showMessageDialog(view.getCurrentPanel(),"Your username and password combination does not match");
				//usernameField.selectAll();
				//usernameField.requestFocus(true);
				return;
			}
			
			//PrivilegeMap.setCurrentUser(username);
			//JOptionPane.showMessageDialog(view.getCurrentPanel(),"privilege is"+PrivilegeMap.getPrivilegeString());
			
			model.logIn(username);
			
			if(isAdmin(username)){
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				log.info("Before log in, Current User role: "+user.getRole());
				user.setUserAs("login_user");
				log.info("After log in, Current User role: "+user.getRole());
				getCurrentController().switchToAdminPage();		
			}
			else{
				Arcade.switchToTab(0); 
			}	
			
			JOptionPane.showMessageDialog(view.getCurrentPanel(),"Successfully logged in!");

		}
	}
	/**
	 * Returns whether a user is an administrator
	 * @param username
	 * @return true if user is an admin
	 */
	private boolean isAdmin(String username) {
		return model.isAdmin(username);
	}
	
//	Below might not be the best way to handle switching between different view, we will discuss.
//	/**
//	 * Sets the corresponding panel to show on this tab 
//	 * @param panel
//	 */
//	//public  void setValidate(String panel) {
//	public  void validatePanelSwitch(String panelClassPath) {
//		view.removeAll();
//		view.updateUI();
//		try{
//			
//			view.add(getPanel(panelClassPath));	
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		view.validate();
//	}
//	/**
//	 * Reflection method
//	 * @param className
//	 * @return
//	 * @throws ClassNotFoundException
//	 * @throws IllegalArgumentException
//	 * @throws SecurityException
//	 * @throws InstantiationException
//	 * @throws IllegalAccessException
//	 * @throws InvocationTargetException
//	 * @throws NoSuchMethodException
//	 */
//	private static JComponent getPanel(String className) throws ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
//		Class<?> c = Class.forName(className);
//		return (JPanel) c.getConstructor().newInstance();
//	}
	
}
