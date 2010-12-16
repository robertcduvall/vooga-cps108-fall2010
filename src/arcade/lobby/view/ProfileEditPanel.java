package arcade.lobby.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.lobby.validators.DateValidator;
import arcade.lobby.validators.EmailValidator;
import arcade.lobby.validators.NameValidator;
import arcade.lobby.validators.WebImageValidator;
import arcade.util.guiComponents.ValidatingComponent;
import arcade.util.guiComponents.Validator;
import arcade.util.guiComponents.ValidatorDock;

/**
 * Allows user to edit information about his/her profile
 * and save it (to the database).
 * 
 * @author david
 * @author segil
 */
@SuppressWarnings("serial")
public class ProfileEditPanel extends JPanel implements IView {
	private static final int FIELD_COLUMNS = 20;
	private static final int AVATAR_WIDTH = 100;
	private static final String AVATAR_DEFAULT = "http://imgur.com/29J5j.png";
	private Profile myProfile;  //  @jve:decl-index=0:
	private Map<String, JTextField> myFields;
	private JButton mySaveButton;
	private ValidatorDock myDock;
	private ResourceBundle resource = ResourceBundle.getBundle("arcade.lobby.resources.resources");  //  @jve:decl-index=0:

	public ProfileEditPanel() {
		this(ProfileSet.getCurrentProfile());
	}

	public ProfileEditPanel(Profile profile) {
		super();
		myProfile = profile;
		myFields = new HashMap<String, JTextField>();
//		ProfileController profileController = new ProfileController(profile, this);
		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		setLayout(new MigLayout());
		myDock = new ValidatorDock();
//		mySaveButton = new JButton("Save");
		mySaveButton = getSaveButton();

		JLabel avatar = new JLabel("");
		try {
			String avatarPath = myProfile == null
					|| myProfile.getAvatar().isEmpty()
					|| myProfile.getAvatar() == null ? AVATAR_DEFAULT
					: myProfile.getAvatar();
			ImageIcon icon = new ImageIcon(ImageIO.read(new URL(avatarPath)));
			scaleImage(icon);
			avatar = new JLabel(icon);
		} catch (Exception e) {
			// Do nothing if avatar cannot be displayed.
		}

		add(avatar);

		addTextField(resource.getString("avatar"), myProfile == null ? "" : myProfile.getAvatar(),
				new WebImageValidator());
		addTextField(resource.getString("firstName"),
				myProfile == null ? "" : myProfile.getFirstName(),
				new NameValidator());
		addTextField(resource.getString("lastName"),
				myProfile == null ? "" : myProfile.getLastName(),
				new NameValidator());
		addTextField(resource.getString("email"), myProfile == null ? "" : myProfile.getEmail(),
				new EmailValidator());
		addTextField(resource.getString("birthday"),
				myProfile == null ? "" : myProfile.getBirthday(),
				new DateValidator());

		add(myDock, "wrap");
		add(mySaveButton, "span 2, align center");
	}

	private void scaleImage(ImageIcon icon) {
		Image image = icon.getImage();
		icon.setImage(image.getScaledInstance(AVATAR_WIDTH,
				AVATAR_WIDTH * icon.getIconHeight() / icon.getIconWidth(), 0));
	}

	private void addTextField(String name, String text,
			Validator<JTextField>... validators) {

		JTextField textField = getTextField(text);
		myFields.put(name, textField);
		ValidatingComponent<JTextField> vc = new ValidatingComponent<JTextField>(
				textField, name, validators);
		myDock.addValidatingComponent(vc, name);
	}
	
	/**
	 * Gets the string from the text field that's associated
	 * with the key.
	 * @param key
	 * @return text from textField associated with key
	 */
	public String getText(String key) {
		return myFields.get(key).getText();
	}

	private JTextField getTextField(String text) {
		JTextField textField = new JTextField(text);
		textField.setColumns(FIELD_COLUMNS);
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mySaveButton.doClick();
			}
		});

		return textField;
	}
	
	

	
	private JButton getSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println("Saved!");
				if (validateFields()) {
					myProfile.setName(myFields.get("First Name").getText(),
							myFields.get("Last Name").getText());
					myProfile.setEmail(myFields.get("Email").getText());
					myProfile.setBirthday(myFields.get("Birthday").getText());
					myProfile.setAvatar(myFields.get("Avatar URL").getText());
					ProfileSet.updateProfile(myProfile);
				}
				refresh(myProfile);
			}

			private boolean validateFields() {
				boolean isValid = true;
				String failures = "";
				Map<String, Boolean> validMap = myDock.validateComponents();
				for (String s : validMap.keySet()) {
					if (!validMap.get(s)) {
						isValid = false;
						failures += s + ", ";
					}
				}
				if (!isValid) {
					JOptionPane.showMessageDialog(
							myDock,
							"Validation has failed for: "
									+ failures.substring(0,
											failures.length() - 2));
					return false;
				}
				return true;
			}

		});
		return saveButton;
	}
	
//	public void addSaveListener(ActionListener listener) {
//		mySaveButton.addActionListener(listener);
//	}
	
	/**
	 * @return
	 */
//	public Profile getProfile() {
//		return myProfile;
//	}

	/**
	 * Recreates the panel with the newProfile, which
	 * is generally just an updated version of the
	 * previous profile.
	 * @param newProfile
	 */
	public void refresh(Profile newProfile) {
		myProfile = newProfile;
		removeAll();
		initialize();
		revalidate();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
