package arcade.lobby.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
import arcade.lobby.viewComponents.ValidatingComponent;
import arcade.util.guiComponents.Validator;
import arcade.util.guiComponents.ValidatorDock;

@SuppressWarnings("serial")
public class ProfileEditPanel extends JPanel implements IView {
	private static final int FIELD_COLUMNS = 20;
	private static final int AVATAR_WIDTH = 100;
	private static final String AVATAR_DEFAULT = "http://imgur.com/29J5j.png";
	private Profile myProfile;
	private Map<String, JTextField> myFields;
	private JButton mySaveButton;
	private ValidatorDock myDock;

	public ProfileEditPanel() {
		this(ProfileSet.getCurrentProfile());
		// this(null);
	}

	public ProfileEditPanel(Profile profile) {
		super();
		myProfile = profile;
		myFields = new HashMap<String, JTextField>();
		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		setLayout(new MigLayout());
		myDock = new ValidatorDock();

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

		addTextField("Avatar", myProfile == null ? "" : myProfile.getAvatar(),
				new WebImageValidator());
		addTextField("First Name",
				myProfile == null ? "" : myProfile.getFirstName(),
				new NameValidator());
		addTextField("Last Name",
				myProfile == null ? "" : myProfile.getLastName(),
				new NameValidator());
		addTextField("Email", myProfile == null ? "" : myProfile.getEmail(),
				new EmailValidator());
		addTextField("Birthday",
				myProfile == null ? "" : myProfile.getBirthday(),
				new DateValidator());

		add(myDock, "wrap");
		add(getSaveButton(), "dock south");
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
		mySaveButton = new JButton("Save");
		mySaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (validate()) {
					myProfile.setName(myFields.get("First Name").getText(),
							myFields.get("Last Name").getText());
					myProfile.setEmail(myFields.get("Email").getText());
					myProfile.setBirthday(myFields.get("Birthday").getText());
					myProfile.setAvatar(myFields.get("Avatar").getText());
					ProfileSet.updateProfile(myProfile);
				}
				refresh(myProfile);
			}

			private boolean validate() {
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
		return mySaveButton;
	}

	public void refresh(Profile newProfile) {
		myProfile = newProfile;
		removeAll();
		initialize();
		revalidate();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
