package arcade.lobby.view;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import arcade.core.Tab;
import arcade.lobby.controller.Main;
import arcade.lobby.controller.Validator;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

public class ProfilePanel extends Tab {

	private static final long serialVersionUID = 1L;
	private static Profile myProfile;
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
	private JButton avatarButton = null;
	private JTextField urlField = null;
	private JLabel urlLabel = null;

	/**
	 * This is the default constructor
	 */
	public ProfilePanel() {
		super();
		myProfile = ProfileSet.currentProfile;
		setToolTipText("Click here to see your user info");
		setName("Profile");
		initialize();
	}
	
	@Override
	public void refresh() {
		myProfile = ProfileSet.currentProfile;
		initialize();
	}
	
	public static void setProfile(Profile p){
		System.out.println("Profile panel profile reset");
		myProfile = p;
	}
	
	@Override
	public JComponent getContent() {
		return this;
	}

	/**
	 * This method initializes the fields.
	 * 
	 * @return void
	 */
	private void initialize() {
		getIconLabel();
		getURLLabel();
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
		this.add(getAvatarButton(), null);
		this.add(getUrlField(), null);
		this.add(urlLabel, null);
	}

	private void getURLLabel() {
		urlLabel = new JLabel();
		urlLabel.setBounds(new Rectangle(244, 107, 32, 19));
		urlLabel.setText("URL:");
	}

	private void getIconLabel() {
		iconLabel = new JLabel();
		iconLabel.setBounds(new Rectangle(280, 7, 100, 96));
		updateIcon();
	}

	private JLabel getNicknameLabel() {
		nicknameLabel = new JLabel();
		nicknameLabel.setBounds(new Rectangle(9, 95, 63, 20));
		nicknameLabel.setText("Username:");
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
			nameField.setBounds(new Rectangle(60, 5, 145, 20));
			try{
				nameField.setText(myProfile.getFullName());
			}
			catch(NullPointerException e){
				System.out.println("No name set");
			}
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
			birthdayField.setBounds(new Rectangle(60, 35, 145, 20));
			try{
				birthdayField.setText(myProfile.getBirthday());
			}
			catch(NullPointerException e){
				System.out.println("No birthday set");
			}

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
			emailField.setBounds(new Rectangle(60, 65, 145, 20));
			try{
				emailField.setText(myProfile.getEmail());
			}
			catch(NullPointerException e){
				System.out.println("No email set");
			}
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
			nicknameField.setBounds(new Rectangle(70, 95, 135, 20));
			try{
				nicknameField.setText(myProfile.getUserName());
			}
			catch(NullPointerException e){
				System.out.println("No username set!?!?!? This is bad.");
			}
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
			editNameButton.setBounds(new Rectangle(215, 5, 55, 20));
			editNameButton.setText("edit");
			editNameButton
					.addActionListener(new java.awt.event.ActionListener() {
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
			editBirthdayButton.setBounds(new Rectangle(215, 35, 55, 20));
			editBirthdayButton.setText("edit");
			editBirthdayButton
					.addActionListener(new java.awt.event.ActionListener() {
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
			editEmailButton.setBounds(new Rectangle(215, 65, 55, 20));
			editEmailButton.setText("edit");
			editEmailButton
					.addActionListener(new java.awt.event.ActionListener() {
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
			SaveButton.setBounds(new Rectangle(81, 132, 93, 34));
			SaveButton.setText("Save");
			SaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateName();
					updateEmail();
					updateBirthday();
					updateAvatar();
					Main.ProfileSet.updateProfile(myProfile);
				}

				private void updateAvatar() {
					urlField.setEditable(false);
					myProfile.setAvatar(urlField.getText());
					updateIcon();
				}

				private void updateBirthday() {
					birthdayField.setEditable(false);
					

					if (Validator.isValidDate(birthdayField.getText()))
						myProfile.setBirthday(birthdayField.getText());
					else
						birthdayField.setText(myProfile.getBirthday());

				}

				private void updateEmail() {
					emailField.setEditable(false);
					myProfile.setEmail(emailField.getText());
				}

				private void updateName() {
					nameField.setEditable(false);
					String name = nameField.getText();
					if(name.length() > 0){
						myProfile.setName(name.substring(0, name.indexOf(" ")),
								name.substring(name.indexOf(" "), name.length()));
					}
				}
			});
		}
		return SaveButton;
	}

	/**
	 * This method initializes avatarButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAvatarButton() {
		if (avatarButton == null) {
			avatarButton = new JButton();
			avatarButton.setBounds(new Rectangle(278, 133, 102, 31));
			avatarButton.setText("New Avatar");
			avatarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					urlField.setEditable(true);
				}
			});
		}
		return avatarButton;
	}

	/**
	 * This method initializes urlField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getUrlField() {
		if (urlField == null) {
			urlField = new JTextField();
			urlField.setBounds(new Rectangle(278, 105, 104, 24));
			try{
				urlField.setText(myProfile.getAvatar());
			}
			catch(NullPointerException e){
				System.out.println("No avatar url set");
			}
			urlField.setEditable(false);
		}
		return urlField;
	}

	private void updateIcon() {
		try {
			iconLabel.setIcon(new ImageIcon(ImageIO.read(new URL(myProfile
					.getAvatar()))));
		} catch (MalformedURLException mue) {
			System.out.println("Invalid avatar URL!");
		} catch (IOException ioe) {
			System.out.println("Invalid avatar image!");
		}
		catch (Exception e){
			System.out.println(e);
		}
		iconLabel.updateUI();
	}

} // @jve:decl-index=0:visual-constraint="-4,26"
