package arcade.security.control;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.security.model.LoginProcessModel;
import arcade.security.view.AdminPanel;
import arcade.security.view.LogInPanel;
import arcade.security.view.RetrievePasswordPanel;
import arcade.security.view.SignUpPanel;

public class Control {

	
	private LoginProcessModel model;
	private JComponent view;  //or JPanel
	
	public void setModel(LoginProcessModel model){
		this.model = model ;
	}
	
	public void setView(LogInPanel view){
		this.view = view;
	}
	
	

	
//	@Override
//	public JComponent getContent() {
//		currentPanel = new LogInPanel();//Meng: keep this line, otherwise the GUI looks wierd
//		setValidate("arcade.security.view.LogInPanel");
//		
//		return currentPanel;
//	}
	
	public void switchToAdminPage(){

		view.removeAll();
		view.updateUI();
		view.add(new AdminPanel(this));  //view = new AdminPanel(this)); won't work.

		
	}
	
	public void switchToLogInPage(){
		view.removeAll();
		view.updateUI();
		view.add(new LogInPanel(this));

	}
	
	public void switchToSignUpPage(){
		view.removeAll();
		view.updateUI();
		view.add(new SignUpPanel(this));

	}

	public void switchToForgetPasswordPage() {
		view.removeAll();
		view.updateUI();
		view.add(new RetrievePasswordPanel(this));
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
