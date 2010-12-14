package arcade.security.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.ProfileCreatePanel;
import arcade.security.model.IModel;
import arcade.security.model.SignUpProcess;
import arcade.security.view.IView;
import arcade.security.view.LogInPanel;
import arcade.security.view.SignUpPanel;
import arcade.security.util.PasswordHandler;

public class SignUpPanelControl implements IControl {

	private final static Logger log=Logger.getLogger(SignUpPanelControl.class);
	private SignUpProcess model;
	private SignUpPanel view;
	private PasswordHandler passwordHandler;

	public SignUpPanelControl(IView view, IModel model){
		this.model = (SignUpProcess)model;
		this.view = (SignUpPanel)view;
		passwordHandler = new PasswordHandler();
		
		this.view.addPasswordListener(new PasswordListener());
		this.view.addSubmitButtonListener(new SubmitEvent());
		this.view.addLoginPageButtonListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToLoginPage();
			}

		});
	}
	
	private class PasswordListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
			char[] pwd = view.getPasswordUserInput();
			if(pwd.length>0){
				System.out.println(String.valueOf(pwd));
				int score = passwordHandler.getScore(String.valueOf(pwd));
				String suggestions = "";
				if(score<33) suggestions = "Weak password";
				if(score>=33 && score<66) suggestions = "Moderate password";
				if(score>=66) suggestions = "Strong password";
				suggestions = "Score: "+String.valueOf(score)+" "+suggestions;
				view.setPasswordSuggestions(suggestions);
			}
		}
		
	}

	private class SubmitEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// Security Information
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
			
			switchToLoginPage();

		}

	}

	public void switchToLoginPage(){
		view.removeAll();
		view.updateUI();
		LogInPanel jp = new LogInPanel();
		jp.getContent();
		view.add(jp);

	}
}
