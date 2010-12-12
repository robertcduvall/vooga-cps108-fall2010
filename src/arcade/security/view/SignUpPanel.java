package arcade.security.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
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
import arcade.security.gui.SecurityButton;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.util.SignUpHandler;

/**
 * The sign up panel for security-related profiles of the user.
 * @author Meng Li
 * @author Jiaqi Yan
 *
 */
public class SignUpPanel extends JPanel implements IView{

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
	private JButton LoginPageButton;

	private final static String QUESTIONS = "arcade.security.resources.passwordquestions";
	private final int questionNum = 3;

	//private Control controller;

	public SignUpPanel(){
		//this.controller = controller;
		setName("Sign up");
		setLayout(new MigLayout());
		username_label=new JLabel(LabelResources.getLabel("AskForUserName"));		
		pwd_label1=new JLabel(LabelResources.getLabel("AskForPwd"));
		pwd_label2 = new JLabel(LabelResources.getLabel("AskForPwdAgain"));


		JLabel usernameReminder = new JLabel("Username Does not Exist");
		JLabel passwordReminder = new JLabel("Password is not valid");

		usernameField=new JTextField(maxUserNameLength);	
		questionField = new JTextField(maxAnswerLength);
		passwordField=new JPasswordField(maxPasswordLength);
		passwordFieldRep=new JPasswordField(maxPasswordLength);
		LoginPageButton =  new SecurityButton(LabelResources.getLabel("GoBackLoginPageButton"));
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
	}

	public void addSubmitButtonListener(ActionListener listener){
		submitButton.addActionListener(listener);
	}

	public void addLoginPageButtonListener(ActionListener listener){
		LoginPageButton.addActionListener(listener);
	}

	public JPanel getCurrentPanel(){
		return this;
	}
	
	public char[] getRepPasswordUserInput(){
		return passwordFieldRep.getPassword();
	}
	
	public char[] getPasswordUserInput(){
		return passwordField.getPassword();
	}
	
	public String getUserNameUserInput(){
		return usernameField.getText();
	}

	public int getQuestionSelectedIndex(){
		return questionChoices.getSelectedIndex();
	}
	
	public String getQuestionAnswer(){
		return questionField.getText();
	}
	
	private void addContents(){
		add(username_label,"cell 0 1");
		add(pwd_label1,"cell 0 2");
		add(pwd_label2,"cell 0 3");

		add(usernameField,"cell 1 1,wrap");
		add(passwordField,"cell 1 2,wrap");
		add(passwordFieldRep,"cell 1 3,wrap");
		add(submitButton, "cell 1 5, wrap");
		add(LoginPageButton,"cell 0 5");
		add(questionChoices,"cell 0 4");
		add(questionField,"cell 1 4");
	}

}
