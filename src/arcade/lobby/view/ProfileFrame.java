package arcade.lobby.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import arcade.lobby.model.Profile;

public class ProfileFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;

	/**
	 * This is the default constructor
	 */
	public ProfileFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(467, 246);
		this.setContentPane(getJContentPane());
		this.setTitle("Profile Page");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			//TODO load from database
			Profile currentUser = Main.ProfileSet.currentProfile;
			jPanel = new ProfilePanel(currentUser);
		}
		return jPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
