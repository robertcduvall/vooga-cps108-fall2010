package arcade.security.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
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
import arcade.lobby.validators.PasswordValidator;
import arcade.lobby.validators.UsernameValidator;
import arcade.lobby.validators.WebImageValidator;
import arcade.security.control.SignUpPanelControl;
import arcade.security.gui.SecurityButton;
import arcade.security.model.SignUpProcess;
import arcade.security.resourcesbundle.LabelResources;
import arcade.util.guiComponents.ValidatingComponent;
import arcade.util.guiComponents.Validator;
import arcade.util.guiComponents.ValidatorDock;

/**
 * The sign up panel for security-related profiles of the user. Used in
 * conjunction with security.model.SignUpProcess and
 * security.control.SignUpPanelControl.
 * 
 * @author Meng Li
 * @author Jiaqi Yan
 * @author Nick Hawthorne
 * @author Lobby Group
 * 
 */
@SuppressWarnings("serial")
public class SignUpPanel extends JPanel implements IView {

	JProgressBar progressbar;
	private SecurityButton submitButton;
	// private JLabel usernameReminder;
	// private JLabel passwordReminder;
	private JLabel passwordSuggestions;
	private JPasswordField passwordField;

	private ValidatorDock myDock;
	private JLabel lobbyLabel;

	private int maxPasswordLength = 10;
	private int maxUserNameLength = 10;
	private int maxAnswerLength = 20;
	private int START_INDEX = 0;
	private SecurityButton LoginPageButton;
	private final static String QUESTIONS = "arcade.security.resources.passwordquestions";
	private final int questionNum = 3;

	// private Control controller;

	/**
	 * Constructor for the signup panel.
	 */
	@SuppressWarnings("unchecked")
	public SignUpPanel() {
		myDock = new ValidatorDock();

		// this.controller = controller;
		setName("Sign up");
		setLayout(new MigLayout("wrap 2"));

		passwordSuggestions = new JLabel("Score: will show upon password input");
		add(passwordSuggestions, "cell 2 3");
		passwordField = new JPasswordField(maxPasswordLength);
		// JLabel usernameReminder = new JLabel("Username Does not Exist");
		// JLabel passwordReminder = new JLabel("Password is not valid");

		// passwordSuggestions = new JLabel();

		PasswordValidator validator = new PasswordValidator();
		addTextField("username", LabelResources.getLabel("AskForUserName"),
				new UsernameValidator());
		addPasswordField("pass1", LabelResources.getLabel("AskForPass1"),
				validator);
		addPasswordField("pass2", LabelResources.getLabel("AskForPass2"),
				validator);

		/*
		 * PasswordConfirmField passwordConfirm = new PasswordConfirmField(
		 * maxPasswordLength); myDock.addValidatingComponent( new
		 * ValidatingComponent<PasswordConfirmField>(passwordConfirm,
		 * "Please Enter Your Password Twice", new PasswordValidator()),
		 * "password", "default", "wrap");
		 */

		// myDock.add(passwordSuggestions);
		ResourceBundle questions = ResourceBundle.getBundle(QUESTIONS);
		String[] q = new String[questionNum];
		int count = 0;
		for (String s : questions.keySet()) {
			q[count] = questions.getString(s);
			count++;
		}
		JComboBox questionChoices = new JComboBox(q);
		questionChoices.setSelectedIndex(START_INDEX);

		myDock.addValidatingComponent(new ValidatingComponent<JComponent>(
				questionChoices), "choices");

		JTextField answerField = new JTextField(maxUserNameLength);
		myDock.addValidatingComponent(new ValidatingComponent<JComponent>(
				answerField), "answer");

		submitButton = new SecurityButton(LabelResources
				.getLabel("SignUpButton"));
		LoginPageButton = new SecurityButton(LabelResources.getLabel("GoBackLoginPageButton"));
		// Lobby Input
		lobbyLabel = new JLabel("Tell us about yourself:");
		myDock.add(lobbyLabel, "span,gaptop 20px");
		addTextField("fname", "First Name", new NameValidator());
		addTextField("lname", "Last Name", new NameValidator());
		addTextField("email", "Email", new EmailValidator());
		addTextField("bday", "Birthday", new DateValidator());
		addTextField("avatar", "Avatar URL", new WebImageValidator());

		// Add it all to the panel
		addContents();
		myDock.revalidate();
		SignUpProcess model = new SignUpProcess();
		new SignUpPanelControl((IView) this, model);
	}

	/**
	 * Returns the current Validator Dock
	 * 
	 * @return
	 */
	public ValidatorDock getDock() {
		return myDock;
	}

	/**
	 * Adds a text field to the Signup panel
	 * 
	 * @param name
	 * @param label
	 * @param validators
	 */
	private void addTextField(String name, String label,
			Validator<JTextField>... validators) {
		JTextField textField = new JTextField(maxAnswerLength);
		ValidatingComponent<JTextField> vc = new ValidatingComponent<JTextField>(
				textField, label, validators);
		myDock.addValidatingComponent(vc, name);
	}

	public void addLoginPageButtonListener(ActionListener listener){
		LoginPageButton.addActionListener(listener);
	}
	
	/**
	 * Adds a password field to the Signup panel
	 * 
	 * @param name
	 * @param label
	 * @param validators
	 */
	private void addPasswordField(String name, String label,
			Validator<JPasswordField>... validators) {
		JPasswordField pwdField = new JPasswordField(maxPasswordLength);
		if (name.equals("pass1"))
			pwdField = passwordField;
		ValidatingComponent<JPasswordField> vc = new ValidatingComponent<JPasswordField>(
				pwdField, label, validators);
		myDock.addValidatingComponent(vc, name);
	}

	/**
	 * Add a listener for the password field.
	 * 
	 * @param listener
	 */
	public void addPasswordListener(KeyListener listener) {
		passwordField.addKeyListener(listener);
	}

	/**
	 * Add a listener for the submit button
	 * 
	 * @param listener
	 */
	public void addSubmitButtonListener(ActionListener listener) {
		submitButton.addActionListener(listener);
	}

	/**
	 * returns the current panel
	 * 
	 * @return
	 */
	public JPanel getCurrentPanel() {
		return this;
	}

	/**
	 * Gets the text entered by the user in the password field.
	 * 
	 * @return the text entered in the password field.
	 */
	public char[] getPasswordUserInput() {
		return passwordField.getPassword();
	}

	/**
	 * Sets the list of suggested passwords.
	 * 
	 * @param suggestions
	 *            passwords to be suggested
	 */
	public void setPasswordSuggestions(String suggestions) {
		passwordSuggestions.setText(suggestions);
	}

	/**
	 * Adds everything to the panel.
	 */
	private void addContents() {
		add(myDock, "wrap");
		add(LoginPageButton);
		add(submitButton);
	}

}
