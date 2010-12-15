package arcade.lobby.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.lobby.viewComponents.ValidatingComponent;
import arcade.util.guiComponents.ValidatorDock;

@SuppressWarnings("serial")
public class ProfileCreatePanel extends JPanel {
	private static final int FIELD_COLUMNS = 20;
	private static final int AVATAR_WIDTH = 100;
	private static final String AVATAR_DEFAULT = "http://imgur.com/29J5j.png";
	private Map<String, JTextField> myFields;
	private JButton myCreateButton;
	private ValidatorDock myDock;
	private Map<String, ValidatingComponent<JTextField>> myValidatingComponents;


	public ProfileCreatePanel() {
		super();
		myFields = new HashMap<String, JTextField>();
		initialize();
	}

	private void initialize() {
		setLayout(new MigLayout());
		myDock = new ValidatorDock();

//		JLabel avatar = new JLabel("");
//		try {
//			String avatarPath = myProfile == null
//					|| myProfile.getAvatar().equals("") ? AVATAR_DEFAULT
//					: myProfile.getAvatar();
//			ImageIcon icon = new ImageIcon(ImageIO.read(new URL(avatarPath)));
//			scaleImage(icon);
//			avatar = new JLabel(icon);
//		} catch (Exception e) {
//			// Do nothing if avatar cannot be displayed.
//		}
//
//		add(avatar);

		addTextField("WebImageValidatingField", "Avatar");
		addTextField("NameValidatingField", "First Name");
		addTextField("NameValidatingField", "Last Name");
		addTextField("EmailValidatingField", "Email");
		addTextField("DateValidatingField", "Birthday");

		add(myDock, "wrap");
		add(getCreateButton(), "dock south");
	}

//	private void scaleImage(ImageIcon icon) {
//		Image image = icon.getImage();
//		icon.setImage(image.getScaledInstance(AVATAR_WIDTH,
//				AVATAR_WIDTH * icon.getIconHeight() / icon.getIconWidth(), 0));
//	}

	@SuppressWarnings("unchecked")
	private void addTextField(String className, String name) {
		ValidatingComponent<JTextField> vc;
		Class<?>[] paramList = { JTextField.class, String.class };
		try {
			String fullClass = "arcade.lobby.viewComponents." + className;
			Constructor<ValidatingComponent<JTextField>> cons = (Constructor<ValidatingComponent<JTextField>>) Class
					.forName(fullClass).getConstructor(paramList);
			JTextField textField = getTextField();
			myFields.put(name, textField);
			vc = cons.newInstance(textField, name);
			myDock.addValidatingComponent(vc, name);
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

	private JTextField getTextField() {
		JTextField textField = new JTextField();
		textField.setColumns(FIELD_COLUMNS);
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myCreateButton.doClick();
			}
		});

		return textField;
	}

	private JButton getCreateButton() {
		myCreateButton = new JButton("Create");
		myCreateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (validate()) {
					Profile profile = ProfileSet.getCurrentProfile();
					profile.setName(myFields.get("First Name").getText(),
							myFields.get("Last Name").getText());
					profile.setEmail(myFields.get("Email").getText());
					profile.setBirthday(myFields.get("Birthday").getText());
					profile.setAvatar(myFields.get("Avatar").getText());
					ProfileSet.updateProfile(profile);
					JOptionPane.showMessageDialog(myDock,"Thank you for registering!");
				}
//				refresh(myProfile);
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
		return myCreateButton;
	}

	public void refresh(Profile newProfile) {
//		myProfile = newProfile;
		removeAll();
		initialize();
		revalidate();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
