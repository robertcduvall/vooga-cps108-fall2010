package arcade.security.view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import arcade.security.model.IModel;
import arcade.security.model.RetrievePasswordProcess;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

/**
 * View object for the forgotten password panel. Used in conjunction with
 * security.model.RetrievePasswordProcess and
 * security.control.RetrievePasswordPanelControl.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class RetrievePasswordPanel extends JPanel implements IView {

	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(RetrievePasswordPanel.class);
	private final static String QUESTIONS = "arcade.security.resources.passwordquestions";

	private JButton LoginPageButton;
	private final int questionNum = 3;
	private JComboBox questionChoices;
	private JLabel usernameLabel;
	private JTextField usernameField;
	private int maxUserNameLength = 10;
	private JLabel answerLabel;
	private JTextField answerField;
	private JButton SubmitButton;

	/**
	 * Constructor for forgotten password panel, creates the GUI
	 */
	public RetrievePasswordPanel() {
		createGUIAndShow();

	}

	/**
	 * Creates the GUI, sets it to visible
	 */
	private void createGUIAndShow() {
		setLayout(new MigLayout());
		usernameLabel = new JLabel("User Name :");
		usernameField = new JTextField(maxUserNameLength);
		ResourceBundle questions = ResourceBundle.getBundle(QUESTIONS);
		String[] q = new String[questionNum];
		int count = 0;
		for (String s : questions.keySet()) {
			q[count] = questions.getString(s);
			count++;
		}
		add(usernameLabel, "cell 0 1");
		add(usernameField, "cell 1 1 , wrap");
		questionChoices = new JComboBox(q);
		add(questionChoices, "span 2, wrap");
		answerLabel = new JLabel("Your Answer : ");
		answerField = new JTextField(maxUserNameLength);
		add(answerLabel, "cell 0 3");
		add(answerField, "cell 1 3");
		SubmitButton = new JButton("Submit");
		add(SubmitButton, "cell 0 4");
		LoginPageButton = new JButton("Go back to log in page");
		add(LoginPageButton, "cell 1 4");
	}

	/**
	 * Adds listeners for the Submit button
	 * 
	 * @param listener
	 */
	public void addSubmitButtonListener(ActionListener listener) {
		SubmitButton.addActionListener(listener);
	}

	/**
	 * Adds listeners for the LoginPage button
	 * 
	 * @param listener
	 */
	public void addLoginPageButtonListener(ActionListener listener) {
		LoginPageButton.addActionListener(listener);
	}

	/**
	 * Gets the text input by the user trying to retrieve their password
	 * 
	 * @return the username input by the user
	 */
	public String getUserNameUserInput() {
		return usernameField.getText();
	}

	/**
	 * Returns the current panel
	 * 
	 * @return
	 */
	public JPanel getCurrentPanel() {
		return this;
	}

	/**
	 * Gets the text input from the answer field by the user trying to retrieve
	 * their password.
	 * 
	 * @return the answer input by the user
	 */
	public String getQuestionAnswer() {
		return answerField.getText();
	}

}
