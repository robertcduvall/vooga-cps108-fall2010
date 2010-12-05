package arcade.lobby.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel title = null;
	private JPanel informationLabelPanel = null;  
	private JLabel userNameLabel = null;
	private JLabel firstNameLabel = null;
	private JLabel lastNameLabel = null;
	private JLabel emailPanal = null;
	private JLabel passWordLabel = null;
	private JLabel passWordReEnterLabel = null;
	private Panel informationTextPanel = null;  
	private JTextField userNameText = null;
	private JTextField firstNameText = null;
	private JTextField lastNameText = null;
	private JTextField emailText = null;
	private JPasswordField passWordText = null;
	private JPasswordField passWordReEnterText = null;
	private JButton submitButton = null;  //  @jve:decl-index=0:visual-constraint="510,179"
	/**
	 * This is the default constructor
	 */
	public RegisterPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		title = new JLabel();
		title.setText("Register");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("anyThing", Font.PLAIN, 32));
		this.setSize(338, 239);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(getInformationLabelPanel(), BorderLayout.WEST);
		this.add(getInformationTextPanel(), BorderLayout.CENTER);
		this.add(getSubmitButton(), BorderLayout.SOUTH);
		
	}

	/**
	 * This method initializes informationLabelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInformationLabelPanel() {
		if (informationLabelPanel == null) {
			passWordReEnterLabel = new JLabel();
			passWordReEnterLabel.setText("Enter Password Again:     ");
			passWordLabel = new JLabel();
			passWordLabel.setText("Password:");
			emailPanal = new JLabel();
			emailPanal.setText("Email: ");
			lastNameLabel = new JLabel();
			lastNameLabel.setText("Last Name:");
			firstNameLabel = new JLabel();
			firstNameLabel.setText("Fist Name: ");
			userNameLabel = new JLabel();
			userNameLabel.setText("User Name: ");
			GridLayout gridLayout = new GridLayout(0,1);
			informationLabelPanel = new JPanel();
			informationLabelPanel.setLayout(gridLayout);
			informationLabelPanel.add(userNameLabel, null);
			informationLabelPanel.add(firstNameLabel, null);
			informationLabelPanel.add(lastNameLabel, null);
			informationLabelPanel.add(emailPanal, null);
			informationLabelPanel.add(passWordLabel, null);
			informationLabelPanel.add(passWordReEnterLabel, null);
		}
		return informationLabelPanel;
	}

	/**
	 * This method initializes informationTextPanal	
	 * 	
	 * @return java.awt.Panel	
	 */
	private Panel getInformationTextPanel() {
		if (informationTextPanel == null) {
			informationTextPanel = new Panel();
			informationTextPanel.setLayout(new GridLayout(0,1));
			informationTextPanel.add(getUserNameText(), null);
			informationTextPanel.add(getFirstNameText(), null);
			informationTextPanel.add(getLastNameText(), null);
			informationTextPanel.add(getEmailText(), null);
			informationTextPanel.add(getPassWordText(), null);
			informationTextPanel.add(getPassWordReEnterText(), null);
		}
		return informationTextPanel;
	}

	/**
	 * This method initializes userNameText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUserNameText() {
		if (userNameText == null) {
			userNameText = new JTextField();
		}
		return userNameText;
	}

	/**
	 * This method initializes firstNameText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFirstNameText() {
		if (firstNameText == null) {
			firstNameText = new JTextField();
		}
		return firstNameText;
	}

	/**
	 * This method initializes lastNameText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLastNameText() {
		if (lastNameText == null) {
			lastNameText = new JTextField();
		}
		return lastNameText;
	}

	/**
	 * This method initializes emailText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEmailText() {
		if (emailText == null) {
			emailText = new JTextField();
		}
		return emailText;
	}

	/**
	 * This method initializes passWordText	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassWordText() {
		if (passWordText == null) {
			passWordText = new JPasswordField();
		}
		return passWordText;
	}

	/**
	 * This method initializes passWordReEnterText	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassWordReEnterText() {
		if (passWordReEnterText == null) {
			passWordReEnterText = new JPasswordField();
		}
		return passWordReEnterText;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setText("Submit");
		}
		return submitButton;
	}

	
}  //  @jve:decl-index=0:visual-constraint="1,5"
