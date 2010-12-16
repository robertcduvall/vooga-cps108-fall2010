package arcade.lobby.view;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.lobby.controller.ProfileController;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel implements Tab, IView {
	public ProfileEditPanel myEditPanel;
	public ProfileViewPanel myViewPanel;
	private boolean editMode = false;
	private JPanel myLeftSidebar;
	private JPanel myMainPanel;
	private JPanel myRightSidebar;
	private ResourceBundle resources;
	private Profile myProfile;
	private JButton myEditButton;

	public ProfilePanel() {
		super();
		setLayout(new MigLayout("fill"));
		myProfile = ProfileSet.getCurrentProfile();

		// Just so that there is some profile set.
		if (myProfile == null)
			myProfile = ProfileSet.getProfile(1);

		setToolTipText("Click here to see your user info");
		setName("Profile");
		resources = ResourceBundle.getBundle("arcade.lobby.resources.sidebars");
		initialize();
		ProfileController profileControl = new ProfileController(myProfile, this);
	}

	private void initialize() {
		myLeftSidebar = createSidebar("left");
		myViewPanel = createViewPanel();
		myEditPanel = createEditPanel();
		myRightSidebar = createSidebar("right");
		myEditButton = new JButton();
		myMainPanel = new JPanel();
		myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
		refreshContent();

		add(myLeftSidebar, "ax l, sy 2");
		add(myMainPanel);
		add(myRightSidebar, "ax r, sy 2");
		add(myEditButton, "newline,skip 1,ax c,ay c");
	}

	public void addEditButtonListener(ActionListener listener) {
		myEditButton.addActionListener(listener);
	}
	
//	private JButton createEditButton() {
//		JButton button = new JButton();
//		button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				editMode = !editMode;
//				reload();
//			}
//		});
//		return button;
//	}
	
	public JPanel getMainPanel() {
		return myMainPanel;
	}
	
	public ProfileViewPanel getViewPanel() {
		return myViewPanel;
	}
	
	public ProfileEditPanel getEditPanel() {
		return myEditPanel;
	}
	
	public boolean changeEditMode() {
		editMode = !editMode;
		reload();
		return editMode;
	}

	private ProfileEditPanel createEditPanel() {
		return new ProfileEditPanel(myProfile);
	}

	private ProfileViewPanel createViewPanel() {
		return new ProfileViewPanel(myProfile);
	}

	private void refreshContent() {
		myEditButton.setText(editMode ? "View Profile" : "Edit Profile");
		myMainPanel.removeAll();
		myMainPanel.add(editMode ? myEditPanel : myViewPanel);
	}

	private JPanel createSidebar(String name) {
		JPanel sidebar = new JPanel();
		sidebar.setVisible(true);
		sidebar.setLayout(new MigLayout());
		String panelClassString = resources.getString(name);
		String[] panelClasses = panelClassString.split(",");
		for (int j = 0; j < panelClasses.length; j++) {
			JPanel newPanel = null;
			try {
				newPanel = (JPanel) Class.forName(panelClasses[j])
						.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sidebar.add(newPanel, "cell 0 " + j);
		}
		return sidebar;
	}

	@Override
	public void refresh() {
		repaint();
		editMode = false;
		reload();
	}
	
	private void reload() {
		// To test with no current profile by switching between users 1 and 2:
		// myProfile = ProfileSet.getProfile((myProfile.getUserId())%2+1);
		myProfile = ProfileSet.getCurrentProfile();
		myViewPanel.refresh(myProfile);
		myEditPanel.refresh(myProfile);
		setName(myProfile.getUserName() + "'s Profile");
//		editMode = !editMode;
		refreshContent();
//		editMode = !editMode;
//		refreshContent();
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

} // @jve:decl-index=0:visual-constraint="-4,26"
