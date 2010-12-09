package arcade.security.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import arcade.core.Tab;
import arcade.security.control.ControlTab;
import arcade.security.gui.SecurityButton;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.util.SignUpHandler;

/**
 * The sign up panel for security-related profiles of the user.
 * @author Jiaqi Yan
 *
 */
public class SignUpPanel extends JPanel{
	
	JProgressBar progressbar;
	private JTextField usernameField,questionField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRep;
	private SecurityButton submitButton;
	private JLabel usernameReminder;
	private JLabel passwordReminder;
	
	private int maxPasswordLength = 10;
	private int maxUserNameLength = 10;
	private int maxAnswerLength = 20;
	private int START_INDEX = 0;
	private JComboBox questionChoices;
	JLabel username_label;
	JLabel pwd_label1;
	JLabel pwd_label2;
	
	private char[] pwd_1;
	private char[] pwd_2;
	private String username;
	private int questionIndex;
	private String questionAnswer;
	
	private final static String QUESTIONS = "arcade.security.resources.passwordquestions";
	private final int questionNum = 3;

	
	public SignUpPanel(){
		setLayout(new MigLayout());
		//setBackground(Color.white);
		username_label=new JLabel(LabelResources.getLabel("AskForUserName"));		
		pwd_label1=new JLabel(LabelResources.getLabel("AskForPwd"));
		pwd_label2 = new JLabel(LabelResources.getLabel("AskForPwdAgain"));
		
		
		JLabel usernameReminder = new JLabel("Username Does not Exist");
		JLabel passwordReminder = new JLabel("Password is not valid");
		
		usernameField=new JTextField(maxUserNameLength);	
		questionField = new JTextField(maxAnswerLength);
		passwordField=new JPasswordField(maxPasswordLength);
		passwordFieldRep=new JPasswordField(maxPasswordLength);
		submitButton = new SecurityButton(LabelResources.getLabel("SignUpButton"));
		
		ResourceBundle questions = ResourceBundle.getBundle(QUESTIONS);
		String[] q = new String[questionNum];
		int count = 0;
		for(String s:questions.keySet()){
			q[count]=questions.getString(s);
			count++;
		}
		questionChoices = new JComboBox(q);
		questionChoices.setSelectedIndex(START_INDEX);
		
		addContents();
		addListeners();
	}
	
	private void addListeners(){
		submitButton.addActionListener(new SubmitListener());
	}
	
	private class SubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			pwd_1 = passwordField.getPassword();
			pwd_2 = passwordFieldRep.getPassword();
			username = usernameField.getText();
			if(!SignUpHandler.samePassword(pwd_1, pwd_2)){
				JOptionPane.showMessageDialog(ControlTab.getPanel(),"Password are not the same");
				return;
			}
			if(!SignUpHandler.isValidUserName(username)){
				JOptionPane.showMessageDialog(ControlTab.getPanel(),"Useraname is not valid");
				return;
			}
			questionIndex = questionChoices.getSelectedIndex();
			questionAnswer = questionField.getText();
			SignUpHandler.createNewUser(username,pwd_1,questionIndex,questionAnswer);
		}
		
		
	}
	
	
	
	private void addContents(){
		add(username_label,"cell 0 1");
		add(pwd_label1,"cell 0 2");
		add(pwd_label2,"cell 0 3");
		
		add(usernameField,"cell 1 1,wrap");
		add(passwordField,"cell 1 2,wrap");
		add(passwordFieldRep,"cell 1 3,wrap");
		add(submitButton, "cell 1 5, wrap");
		
		add(questionChoices,"cell 0 4");
		add(questionField,"cell 1 4");
	}
	
}