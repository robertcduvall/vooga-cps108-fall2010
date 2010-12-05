package arcade.lobby.view;

import javax.swing.JPanel;

import arcade.lobby.model.Profile;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

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
	private JButton jButton = null;
	private JButton editEmailButton = null;
	private JButton editNickNameButton = null;
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
		this.setSize(389, 278);
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
		this.add(getEditNickNameButton(), null);
	}

	private void getIconLabel() {
		iconLabel = new JLabel();
		iconLabel.setBounds(new Rectangle(248, 9, 136, 93));
		iconLabel.setIcon(new ImageIcon("gameOver.png"));
		iconLabel.setVisible(true);
	}

	private JLabel getNicknameLabel() {
		nicknameLabel = new JLabel();
		nicknameLabel.setBounds(new Rectangle(8, 98, 66, 24));
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
			nameLabel.setBounds(new Rectangle(9, 6, 42, 21));
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
			birthdayLabel.setBounds(new Rectangle(9, 33, 52, 23));
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
			emailLabel.setBounds(new Rectangle(9, 65, 42, 25));
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
			nameField.setBounds(new Rectangle(59, 6, 147, 24));
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
			birthdayField.setBounds(new Rectangle(57, 35, 147, 25));
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
			emailField.setBounds(new Rectangle(58, 67, 149, 24));
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
			nicknameField.setBounds(new Rectangle(68, 96, 140, 23));
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
			editNameButton.setBounds(new Rectangle(211, 8, 59, 23));
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
			editBirthdayButton.setBounds(new Rectangle(210, 37, 58, 23));
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(210, 68, 37, 24));
		}
		return jButton;
	}

	/**
	 * This method initializes editEmailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditEmailButton() {
		if (editEmailButton == null) {
			editEmailButton = new JButton();
			editEmailButton.setBounds(new Rectangle(211, 69, 57, 22));
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
	 * This method initializes editNickNameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditNickNameButton() {
		if (editNickNameButton == null) {
			editNickNameButton = new JButton();
			editNickNameButton.setBounds(new Rectangle(211, 97, 57, 21));
			editNickNameButton.setText("edit");
			editNickNameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					nicknameField.setEditable(true);
				}
			});
		}
		return editNickNameButton;
	}

}  //  @jve:decl-index=0:visual-constraint="-4,26"
