package arcade.security.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;


import arcade.security.model.IModel;
import arcade.security.model.LoginProcess;
import arcade.security.model.SignUpProcess;
import arcade.security.view.IView;
import arcade.security.view.LogInPanel;
import arcade.security.view.SignUpPanel;

public class SignUpPanelControl implements IControl {

	private final static Logger log=Logger.getLogger(SignUpPanelControl.class);
	private SignUpProcess model;
	private SignUpPanel view;

	public SignUpPanelControl(IView view, IModel model){
		this.model = (SignUpProcess)model;
		this.view = (SignUpPanel)view;

		this.view.addSubmitButtonListener(new SubmitEvent());
		this.view.addLoginPageButtonListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToLogInPage();
			}

		});
	}

	private class SubmitEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			char[] pwd_1 = view.getPasswordUserInput();
			char[] pwd_2 = view.getRepPasswordUserInput();
			String username = view.getUserNameUserInput();
			if(!model.isSamePassword(pwd_1, pwd_2)){
				JOptionPane.showMessageDialog(view.getCurrentPanel(),"Password are not the same");
				return;
			}
			if(!model.isValidUserName(username)){
				JOptionPane.showMessageDialog(view.getCurrentPanel(),"Useraname is not valid");
				return;
			}
			int questionIndex = view.getQuestionSelectedIndex();
			String questionAnswer = view.getQuestionAnswer();

			model.createNewUser(username,pwd_1,questionIndex,questionAnswer);
			log.info(username+" User has been created.");
		}

	}

	public void switchToLogInPage(){
		view.removeAll();
		view.updateUI();
		LogInPanel jp = new LogInPanel();
		jp.getContent();
		view.add(jp);
		//LoginProcess model = new LoginProcess();
		//LogInPanelControl controller = new LogInPanelControl((IView)jp,model);	

	}
}
