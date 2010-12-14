package arcade.security.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import arcade.lobby.validators.DateValidator;
import arcade.lobby.validators.EmailValidator;
import arcade.lobby.validators.NameValidator;
import arcade.lobby.validators.PasswordConfirmField;
import arcade.lobby.validators.PasswordValidator;
import arcade.lobby.validators.WebImageValidator;
import arcade.lobby.viewComponents.ValidatingComponent;
import arcade.lobby.viewComponents.Validator;
import arcade.lobby.viewComponents.ValidatorDock;
import arcade.security.gui.SecurityButton;
import arcade.security.resourcesbundle.LabelResources;

/**
 * The sign up panel for security-related profiles of the user.
 * 
 * @author Meng Li
 * @author Jiaqi Yan
 * 
 */
public class SignUpPanel extends JPanel implements IView {

	JProgressBar progressbar;
	private JTextField usernameField, questionField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRep;
	private SecurityButton submitButton;
	private JLabel usernameReminder;
	private JLabel passwordReminder;
	private JLabel passwordSuggestions;

	private ValidatorDock myDock;
	private JLabel lobbyLabel;

	private int maxPasswordLength = 10;
	private int maxUserNameLength = 10;
	private int maxAnswerLength = 20;
	private int START_INDEX = 0;
	private JComboBox questionChoices;
	// JLabel username_label;
	// JLabel pwd_label1;
	// JLabel pwd_label2;

	// private char[] pwd_1;
	// private char[] pwd_2;
	// private String username;
	// private int questionIndex;
	// private String questionAnswer;
	private JButton LoginPageButton;

	private final static String QUESTIONS = "arcade.security.resources.passwordquestions";
	private final int questionNum = 3;

	// private Control controller;

	public SignUpPanel() {
		myDock = new ValidatorDock();

		// this.controller = controller;
		setName("Sign up");
		 setLayout(new MigLayout("wrap 2"));

		// Security Input
		// username_label = new
		// JLabel(LabelResources.getLabel("AskForUserName"));
		// pwd_label1 = new JLabel(LabelResources.getLabel("AskForPwd"));
		// pwd_label2 = new JLabel(LabelResources.getLabel("AskForPwdAgain"));
		//
		// JLabel usernameReminder = new JLabel("Username Does not Exist");
		// JLabel passwordReminder = new JLabel("Password is not valid");

//		passwordSuggestions = new JLabel();

		addTextField("username", LabelResources.getLabel("AskForUserName"));
		
		PasswordConfirmField passwordConfirm = new PasswordConfirmField(
				maxPasswordLength);
		myDock.addValidatingComponent(new ValidatingComponent<PasswordConfirmField>(
				passwordConfirm,"Please Enter Your Password Twice",new PasswordValidator()), "password", "default", "wrap");
		// addTextField("passwordRep",
		// LabelResources.getLabel("AskForPwdAgain"));

		ResourceBundle questions = ResourceBundle.getBundle(QUESTIONS);
		String[] q = new String[questionNum];
		int count = 0;
		for (String s : questions.keySet()) {
			q[count] = questions.getString(s);
			count++;
		}
		questionChoices = new JComboBox(q);
		questionChoices.setSelectedIndex(START_INDEX);

		myDock.addValidatingComponent(new ValidatingComponent<JComponent>(
				questionChoices), "choices");

		JTextField answerField = new JTextField(maxUserNameLength);
		myDock.addValidatingComponent(new ValidatingComponent<JComponent>(
				answerField), "answer");

		// usernameField = new JTextField(maxUserNameLength);
		// questionField = new JTextField(maxAnswerLength);
		// passwordField = new JPasswordField(maxPasswordLength);
		// passwordFieldRep = new JPasswordField(maxPasswordLength);
		LoginPageButton = new SecurityButton(
				LabelResources.getLabel("GoBackLoginPageButton"));
		submitButton = new SecurityButton(
				LabelResources.getLabel("SignUpButton"));

		// Lobby Input
		lobbyLabel = new JLabel("Tell us about yourself:");
		myDock.add(lobbyLabel,"span,gaptop 20px");
		addTextField("fname", "First Name", new NameValidator());
		addTextField("lname", "Last Name", new NameValidator());
		addTextField("email", "Email", new EmailValidator());
		addTextField("bday", "Birthday", new DateValidator());
		addTextField("avatar", "Avatar URL", new WebImageValidator());

		// Add it all to the panel
		addContents();
		myDock.revalidate();
	}

	public ValidatorDock getDock() {
		return myDock;
	}

	private void addTextField(String name, String label,
			Validator<JTextField>... validators) {
		JTextField textField = new JTextField(maxAnswerLength);
		ValidatingComponent<JTextField> vc = new ValidatingComponent<JTextField>(
				textField, label, validators);
		myDock.addValidatingComponent(vc, name);
	}

	public void setPasswordSuggestions(String suggestions) {
		passwordSuggestions.setText(suggestions);
	}

	public void addPasswordListener(KeyListener listener) {
		passwordField.addKeyListener(listener);
	}

	public void addSubmitButtonListener(ActionListener listener) {
		submitButton.addActionListener(listener);
	}

	public void addLoginPageButtonListener(ActionListener listener) {
		LoginPageButton.addActionListener(listener);
	}

	public JPanel getCurrentPanel() {
		return this;
	}

	public char[] getRepPasswordUserInput() {
		return passwordFieldRep.getPassword();
	}

	public char[] getPasswordUserInput() {
		return passwordField.getPassword();
	}

	public String getUserNameUserInput() {
		return usernameField.getText();
	}

	public int getQuestionSelectedIndex() {
		return questionChoices.getSelectedIndex();
	}

	public String getQuestionAnswer() {
		return questionField.getText();
	}

	private void addContents() {
		// add(username_label);
		// add(usernameField);
		// add(pwd_label1);
		// add(passwordField);
		// add(pwd_label2);
		// add(passwordFieldRep);
		// // add(passwordSuggestions);
		// add(questionChoices);
		// add(questionField);
		add(myDock, "wrap");
		add(LoginPageButton);
		add(submitButton);
	}

}
