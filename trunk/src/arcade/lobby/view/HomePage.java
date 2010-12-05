package arcade.lobby.view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import arcade.lobby.model.Profile;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;

import java.io.File;

public class HomePage extends JPanel {

	private static final long serialVersionUID = 1L;
	private Profile myProfile;
	private JLabel nameLabel = null;
	private JLabel birthdayLabel = null;
	private JLabel emailLabel = null;
	private JLabel nicknameLabel = null;
	private JLabel iconLabel = null;
	private JTextField nameField = null;
	private JTextField birthdayField = null;
	private JTextField emailField = null;
	private JTextField nicknameField = null;
	private JButton editNameButton = null;
	private JButton editBirthdayButton = null;
	private JButton editEmailButton = null;
	private JButton SaveButton = null;
	
	private final String dateRegex = "^(\\d{4})-((0[1-9])|(1[0-2]))-(0[1-9]|[12][0-9]|3[01])$";
	/**
	 * This is the default constructor
	 */
	public HomePage(Profile profile) {
		super();
		myProfile = profile;
		initialize();
	}

	/**
	 * This method initializes the fields.
	 * 
	 * @return void
	 */
	private void initialize() {
		getIconLabel();
		this.setSize(385, 187);
		this.setLayout(null);
		this.add(getNameLabel(), null);
		this.add(getBirthdayLabel(), null);
		this.add(getEmailLabel(), null);
		this.add(getNicknameLabel(), null);
		this.add(iconLabel, null);
		this.add(getNameField(), null);
		this.add(getBirthdayField(), null);
		this.add(getEmailField(), null);
		this.add(getNicknameField(), null);
		this.add(getEditNameButton(), null);
		this.add(getEditBirthdayButton(), null);
		this.add(getEditEmailButton(), null);
		this.add(getSaveButton(), null);
	}

	private void getIconLabel() {
		iconLabel = new JLabel();
		iconLabel.setBounds(new Rectangle(274, 5, 110, 96));
		try {
			iconLabel.setIcon(new ImageIcon(ImageIO.read(new File("../VOOGA/src/vooga/games/grandius/resources/images/missile.jpg"))));
		}
		catch(Exception e) {
		}
	}

	private JLabel getNicknameLabel() {
		nicknameLabel = new JLabel();
		nicknameLabel.setBounds(new Rectangle(9, 95, 65, 20));
		nicknameLabel.setText("Nickname:");
		return nicknameLabel;
	}

	/**
	 * This method initializes nameField	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getNameLabel() {
		if (nameLabel == null) {
			nameLabel = new JLabel();
			nameLabel.setBounds(new Rectangle(9, 5, 40, 20));
			nameLabel.setText("Name:");
		}
		return nameLabel;
	}

	/**
	 * This method initializes birthdayField	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getBirthdayLabel() {
		if (birthdayLabel == null) {
			birthdayLabel = new JLabel();
			birthdayLabel.setBounds(new Rectangle(9, 35, 53, 20));
			birthdayLabel.setText("Birthday:");
		}
		return birthdayLabel;
	}

	/**
	 * This method initializes emailPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JLabel getEmailLabel() {
		if (emailLabel == null) {
			emailLabel = new JLabel();
			emailLabel.setBounds(new Rectangle(9, 65, 40, 20));
			emailLabel.setText("Email:");
		}
		return emailLabel;
	}

	/**
	 * This method initializes nameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNameField() {
		if (nameField == null) {
			nameField = new JTextField();
			nameField.setBounds(new Rectangle(59, 5, 145, 20));
			nameField.setText(myProfile.getFullName());
			nameField.setEditable(false);
		}
		return nameField;
	}

	/**
	 * This method initializes birthdayField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBirthdayField() {
		if (birthdayField == null) {
			birthdayField = new JTextField();
			birthdayField.setBounds(new Rectangle(57, 35, 145, 20));
			birthdayField.setText(myProfile.getBirthday());
			birthdayField.setEditable(false);
		}
		return birthdayField;
	}

	/**
	 * This method initializes emailField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEmailField() {
		if (emailField == null) {
			emailField = new JTextField();
			emailField.setBounds(new Rectangle(58, 65, 145, 20));
			emailField.setText(myProfile.getEmail());
			emailField.setEditable(false);
		}
		return emailField;
	}

	/**
	 * This method initializes nicknameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNicknameField() {
		if (nicknameField == null) {
			nicknameField = new JTextField();
			nicknameField.setBounds(new Rectangle(68, 95, 140, 20));
			nicknameField.setText(myProfile.getUserName());
			nicknameField.setEditable(false);
		}
		return nicknameField;
	}

	/**
	 * This method initializes editNameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditNameButton() {
		if (editNameButton == null) {
			editNameButton = new JButton();
			editNameButton.setBounds(new Rectangle(211, 5, 59, 20));
			editNameButton.setText("edit");
			editNameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					nameField.setEditable(true);
				}
			});
		}
		return editNameButton;
	}

	/**
	 * This method initializes editBirthdayButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditBirthdayButton() {
		if (editBirthdayButton == null) {
			editBirthdayButton = new JButton();
			editBirthdayButton.setBounds(new Rectangle(210, 35, 58, 20));
			editBirthdayButton.setText("edit");
			editBirthdayButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					birthdayField.setEditable(true);
				}
			});
		}
		return editBirthdayButton;
	}

	/**
	 * This method initializes editEmailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditEmailButton() {
		if (editEmailButton == null) {
			editEmailButton = new JButton();
			editEmailButton.setBounds(new Rectangle(211, 65, 57, 20));
			editEmailButton.setText("edit");
			editEmailButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					emailField.setEditable(true);
				}
			});
		}
		return editEmailButton;
	}

	/**
	 * This method initializes SaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (SaveButton == null) {
			SaveButton = new JButton();
			SaveButton.setBounds(new Rectangle(109, 143, 93, 34));
			SaveButton.setText("Save");
			SaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					nameField.setEditable(false);
					birthdayField.setEditable(false);
					emailField.setEditable(false);
					String name = nameField.getText();
					myProfile.setName(name.substring(0, name.indexOf(" ")),
							name.substring(name.indexOf(" "), name.length()));
					myProfile.setEmail(emailField.getText());
					DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
					try {
						if(Pattern.matches(dateRegex, birthdayField.getText()))
							myProfile.setBirthday(sdf.parse(birthdayField.getText()));
						else
							birthdayField.setText(myProfile.getBirthday());
					}
					catch (ParseException parseEx) {
						//TODO incorrect date format
						birthdayField.setText(myProfile.getBirthday());
					}
					//TODO save to database
				}
			});
		}
		return SaveButton;
	}

}  //  @jve:decl-index=0:visual-constraint="-4,26"
