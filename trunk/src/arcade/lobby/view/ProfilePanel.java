package arcade.lobby.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import arcade.core.api.Tab;
import arcade.core.mvc.IController;
import arcade.lobby.controller.ProfileController;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

/**
 * Main viewer for user profiles.  Allows viewing and editing
 * of own profile as well as viewing of others' profiles.  This
 * is what is viewed in the profile tab.
 * @author david, segil
 */
@SuppressWarnings("serial")
public class ProfilePanel extends JPanel implements Tab, IView {
	public ProfileEditPanel myEditPanel;
	public ProfileViewPanel myViewPanel;
	private int previousUserId = -1;

	private enum State {
		view, edit, viewothers
	};

	private State mode = State.view; // @jve:decl-index=0:
	private JPanel myLeftSidebar;
	private JPanel myMainPanel;
	private JPanel myRightSidebar;
	private ResourceBundle resources; // @jve:decl-index=0:
	private Profile myProfile;
	private JButton myEditButton;
	private JComboBox myUsers;

	public ProfilePanel() {
		setToolTipText("Click here to see your user info");
		setName("Profile");
	}
	
	public void initialize() {
		resources = ResourceBundle.getBundle("arcade.lobby.resources.sidebars");
		setLayout(new MigLayout("fill"));
		myProfile = ProfileSet.getCurrentProfile();

		myLeftSidebar = createSidebar("left");
		myViewPanel = createViewPanel();
		myEditPanel = createEditPanel();
		myRightSidebar = createSidebar("right");
		myEditButton = new JButton();
		myUsers = getUserBox();
		myMainPanel = new JPanel();
		myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
		refreshContent();

		add(myLeftSidebar, "ax l, sy 2");
		add(myMainPanel, "span 2");
		add(myRightSidebar, "ax r, sy 2");
		add(myUsers, "newline,skip 1, ax c, ay c");
		add(myEditButton, "ax c,ay c");

		ProfileController profileControl = new ProfileController(myProfile,
				this);
		previousUserId = ProfileSet.getCurrentProfile().getUserId();
	}

	private JComboBox getUserBox() {
		JComboBox userBox = new JComboBox();
		final Map<String, Integer> users = ProfileSet.getUserNames();
		for (String user : users.keySet()) {
			userBox.addItem(user);
		}
		userBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String selectedUser = (String) e.getItem();
				Profile profile = ProfileSet.getProfile(users.get(selectedUser));
				myViewPanel = new ProfileViewPanel(profile);
				mode = State.viewothers;
				redraw();
			}
		});
		return userBox;
	}

	/**
	 * Adds listener to the edit button in the profile view.
	 * @param listener
	 */
	public void addEditButtonListener(ActionListener listener) {
		myEditButton.addActionListener(listener);
	}

	/**
	 * Gets the view-only profile.
	 * @return view version of profile panel
	 */
	public ProfileViewPanel getViewPanel() {
		return myViewPanel;
	}

	/**
	 * Gets the editable profile.
	 * @return edit version of profile panel
	 */
	public ProfileEditPanel getEditPanel() {
		return myEditPanel;
	}

	/**
	 * Switches between profile views: Edit,
	 * View, and Viewing other profiles
	 * @return current state / profile view
	 */
	public State changeMode() {
		switch (mode) {
		case view:
			mode = State.edit;
			break;
		case edit:
		case viewothers:
			mode = State.view;
		}
		reload();
		redraw();
		return mode;
	}

	private ProfileEditPanel createEditPanel() {
		return new ProfileEditPanel(myProfile);
	}

	private ProfileViewPanel createViewPanel() {
		return new ProfileViewPanel(myProfile);
	}

	private void refreshContent() {
		myEditButton.setText(getButtonString());
		myMainPanel.removeAll();
		myMainPanel.add(getCurentMainPanel());
	}

	private String getButtonString() {
		switch (mode) {
		case view:
			return "Edit Profile";
		case edit:
		case viewothers:
			return "View My Profile";
		default:
			return "";
		}
	}

	private JPanel getCurentMainPanel() {
		switch (mode) {
		case edit:
			return myEditPanel;
		default:
		case viewothers:
		case view:
			return myViewPanel;
		}
	}

	private JPanel createSidebar(String name) {
		JPanel sidebar = new JPanel();
		sidebar.setVisible(true);
		sidebar.setLayout(new MigLayout());
		String panelClassString = resources.getString(name);
		if (!panelClassString.isEmpty()) {
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
		}
		return sidebar;
	}

	@Override
	public void refresh() {
		int currentUserId = ProfileSet.getCurrentProfile().getUserId();
		if (currentUserId != previousUserId) {
			previousUserId = currentUserId;
			repaint();
			mode = State.view;
			reload();
			redraw();
			recreateSidebars();
		}
	}

	private void recreateSidebars() {
		myLeftSidebar.removeAll();
		myRightSidebar.removeAll();
		myLeftSidebar.add(createSidebar("left"));
		myRightSidebar.add(createSidebar("right"));
	}

	private void reload() {
		myProfile = ProfileSet.getCurrentProfile();
		myViewPanel.refresh(myProfile);
		myEditPanel.refresh(myProfile);
		setName(myProfile.getUserName() + "'s Profile");
	}

	private void redraw() {
		refreshContent();
		revalidate();
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

} // @jve:decl-index=0:visual-constraint="-4,26"
