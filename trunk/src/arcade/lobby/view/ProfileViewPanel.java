package arcade.lobby.view;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

@SuppressWarnings("serial")
public class ProfileViewPanel extends JPanel {
	private static final int AVATAR_WIDTH = 100;
	private static final String AVATAR_DEFAULT = "http://imgur.com/29J5j.png";
	private Profile myProfile;
	private JLabel nameLabel = null;
	private int myRow;

	public ProfileViewPanel() {
		this(ProfileSet.getProfile(1));
		// this(null);
	}

	public ProfileViewPanel(Profile profile) {
		super();
		myProfile = profile;
		initialize();
	}

	private void initialize() {
		setLayout(new MigLayout());

		JLabel avatar = null;
		try {
			String avatarPath = myProfile == null
					|| myProfile.getAvatar() == null
					|| myProfile.getAvatar().isEmpty() ? AVATAR_DEFAULT
					: myProfile.getAvatar();
			ImageIcon icon = new ImageIcon(ImageIO.read(new URL(avatarPath)));
			scaleImage(icon);
			avatar = new JLabel(icon);
		} catch (MalformedURLException e) {
			avatar = new JLabel("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		addField(avatar, new JLabel(myProfile.getUserName()));
		addField(new JLabel("Name:"), new JLabel(myProfile.getFullName()));
		addField(new JLabel("Email Address:"), new JLabel(myProfile.getEmail()));
		addField(new JLabel("Birthday:"), new JLabel(myProfile.getBirthday()));

		if (myProfile.getJoinDate() != 0) {
			long seconds = (System.currentTimeMillis() - myProfile
					.getJoinDate()) / 1000;
			long minutes = seconds / 60;
			long hours = minutes / 60;
			long days = hours / 24;
			String timeSinceRegister = String.format(
					"%d Days, %d Hours, and %d minutes", days, hours % 24,
					minutes % 60);
			addField(new JLabel("Member For:"), new JLabel(timeSinceRegister));
		}

	}

	private void scaleImage(ImageIcon icon) {
		Image image = icon.getImage();
		icon.setImage(image.getScaledInstance(AVATAR_WIDTH,
				AVATAR_WIDTH * icon.getIconHeight() / icon.getIconWidth(), 0));
	}

	private void addField(JLabel label, JLabel field) {
		add(label, "cell 0 " + myRow);
		add(field, "cell 1 " + myRow);
		myRow++;
	}

	public void refresh(Profile newProfile) {
		myProfile = newProfile;
		removeAll();
		initialize();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
