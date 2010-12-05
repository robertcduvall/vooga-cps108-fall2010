package arcade.lobby.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import arcade.lobby.controller.Validator;
import arcade.lobby.model.Profile;

public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel thisPanel = this;
	private JLabel title = null;
	private JPanel informationLabelPanel = null;
	private JLabel userNameLabel = null;
	private JLabel firstNameLabel = null;
	private JLabel lastNameLabel = null;
	private JLabel emailPanal = null;
	private JLabel passWordLabel = null;
	private JLabel passWordReEnterLabel = null;
	private Panel informationTextPanel = null;
	private Map<String, JTextField> textMap; // @jve:decl-index=0:
	private String[] fieldNames = { "userName", "firstName", "lastName",
			"email", "birthday" };
	private JButton submitButton = null;
	private JLabel birthdayLabel = null;

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
		textMap = new TreeMap<String, JTextField>();
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
			birthdayLabel = new JLabel();
			birthdayLabel.setText("Birthday:");
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
			GridLayout gridLayout = new GridLayout(0, 1);
			informationLabelPanel = new JPanel();
			informationLabelPanel.setLayout(gridLayout);
			informationLabelPanel.add(userNameLabel, null);
			informationLabelPanel.add(firstNameLabel, null);
			informationLabelPanel.add(lastNameLabel, null);
			informationLabelPanel.add(emailPanal, null);
			informationLabelPanel.add(birthdayLabel, null);
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
			informationTextPanel.setLayout(new GridLayout(0, 1));
			for (int i = 0; i < fieldNames.length; i++) {
				JTextField temp = new JTextField();
				informationTextPanel.add(temp, null);
				textMap.put(fieldNames[i], temp);
			}
			for (int i = 0; i < 2; i++) {
				JPasswordField passTemp = new JPasswordField();
				informationTextPanel.add(passTemp, null);
				textMap.put("password" + (i + 1), passTemp);
			}
			textMap.get("birthday").setText("mm/dd/yyyy");
		}
		return informationTextPanel;
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
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!checkEmpty()) {
						JOptionPane.showMessageDialog(thisPanel,
								"No Fields can be left blank.");
						return;
					}
					if (!Validator.checkUsername(textMap.get("userName")
							.getText())) {
						JOptionPane.showMessageDialog(thisPanel,
								"The user name is already taken.");
						return;
					}
					if (!Validator.checkEmailFormat(textMap.get("email")
							.getText())) {
						JOptionPane.showMessageDialog(thisPanel,
								"The email address is not valid.");
						return;
					}
					if (!checkPasswords()) {
						JOptionPane.showMessageDialog(thisPanel,
								"The Passwords are not the Same");
						return;
					}
					if (!Validator.isValidDate(textMap.get("birthday")
							.getText())) {
						JOptionPane.showMessageDialog(thisPanel,
								"The birthdate is not a valid date.");
						return;
					}
					makeProfile();
				}

				private void makeProfile() {
					Profile profile = new Profile(textMap.get("userName").getText());
					profile.setBirthday(textMap.get("userName").getText());
					profile.setEmail(textMap.get("birthday").getText());
					profile.setName(textMap.get("firstName").getText(), textMap.get("firstName").getText());
					Validator.profileSet.addProfile(profile);
				}

				private boolean checkPasswords() {
					String pass1 = textMap.get("password1").getText();
					String pass2 = textMap.get("password2").getText();
					boolean toReturn = pass1.equals(pass2);
					return toReturn;
				}

				private boolean checkEmpty() {
					for (String str : textMap.keySet()) {
						JTextField temp = textMap.get(str);
						if (temp.getText().equals("")) {
							return false;
						}
					}
					return true;

				}
			});
		}
		return submitButton;
	}

} // @jve:decl-index=0:visual-constraint="1,5"
