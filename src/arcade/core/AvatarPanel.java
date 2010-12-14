package arcade.core;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import arcade.lobby.model.Profile;

@SuppressWarnings("serial")
class AvatarPanel extends JPanel {

	public AvatarPanel(Profile profile) {
		setLayout(new MigLayout("ax center, wrap 1"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel playerName = new JLabel(profile.getFullName()); 
		JLabel avatar = null;
		try {
			String avatarPath = profile.getAvatar();
			ImageIcon avatarIcon = new ImageIcon(ImageIO.read(new URL(avatarPath)));
			ExampleGUI.scaleImage(avatarIcon,100);
			avatar = new JLabel(avatarIcon);
		} catch (MalformedURLException e) {
			avatar = new JLabel("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(avatar);
		add(playerName);
	}
}