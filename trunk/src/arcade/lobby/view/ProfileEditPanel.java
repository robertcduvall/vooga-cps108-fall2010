package arcade.lobby.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
import arcade.lobby.viewComponents.DateValidatingField;
import arcade.lobby.viewComponents.EmailValidatingField;
import arcade.lobby.viewComponents.NameValidatingField;
import arcade.lobby.viewComponents.ValidatingComponent;
import arcade.lobby.viewComponents.ValidatorDock;

@SuppressWarnings("serial")
public class ProfileEditPanel extends JPanel {
	private static final int FIELD_COLUMNS = 20;
	private Profile myProfile;
	private Map<String, JTextField> myFields;
	private JLabel myAvatar;
	private JButton mySaveButton;
	private ValidatorDock myDock;

	public ProfileEditPanel() {
		this(ProfileSet.getProfile(1));
		// this(null);
	}

	public ProfileEditPanel(Profile profile) {
		super();
		myProfile = profile;
		myFields = new HashMap<String, JTextField>();
		initialize();
	}

	private void initialize() {
		setLayout(new MigLayout());
		myDock = new ValidatorDock();
		myDock.setLayout(new MigLayout());

		myAvatar = new JLabel("");
		try {
			myAvatar = new JLabel(myProfile == null ? null : new ImageIcon(
					ImageIO.read(new URL(myProfile.getAvatar()))));
		} catch (Exception e) {
			// Do nothing if avatar cannot be displayed.
		}

		add(myAvatar);

		addTextField("WebImageValidatingField", "Avatar",
				myProfile == null ? "" : myProfile.getAvatar());
		addTextField("NameValidatingField", "First Name",
				myProfile == null ? "" : myProfile.getFirstName());
		addTextField("NameValidatingField", "Last Name", myProfile == null ? ""
				: myProfile.getLastName());
		addTextField("EmailValidatingField", "Email", myProfile == null ? ""
				: myProfile.getEmail());
		addTextField("DateValidatingField", "Birthday", myProfile == null ? ""
				: myProfile.getBirthday());

		add(myDock, "wrap");
		add(getSaveButton(), "dock south");
	}

	@SuppressWarnings("unchecked")
	private void addTextField(String className, String name, String text) {
		ValidatingComponent<JTextField> vc;
		Class<?>[] paramList = { JTextField.class, String.class };
		try {
			String fullClass = "arcade.lobby.viewComponents." + className;
			Constructor<ValidatingComponent<JTextField>> cons = (Constructor<ValidatingComponent<JTextField>>) Class
					.forName(fullClass).getConstructor(paramList);
			JTextField textField = getTextField(text);
			myFields.put(name, textField);
			vc = cons.newInstance(textField, name);
			myDock.addValidatingComponent(vc, name, null, "wrap");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
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
	}

} // @jve:decl-index=0:visual-constraint="10,10"
