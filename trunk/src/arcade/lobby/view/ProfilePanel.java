package arcade.lobby.view;

import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.sun.org.apache.bcel.internal.generic.NEW;

import arcade.core.Tab;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

@SuppressWarnings("serial")
public class ProfilePanel extends Tab {
	public JPanel myEditPanel;
	public JPanel myViewPanel;
	private boolean editMode;
	private JPanel myLeftSidebar;
	private JPanel myMainPanel;
	private JPanel myRightSidebar;
	private ResourceBundle resources;

	public ProfilePanel() {
		super();
		setLayout(new MigLayout());
		Profile profile = ProfileSet.currentProfile;
		setToolTipText("Click here to see your user info");
		setName("Profile");
		myEditPanel = new ProfileEditPanel(profile);
		resources = ResourceBundle.getBundle("arcade.lobby.view.sidebars");
		initialize();
	}

	private void initialize() {
		myLeftSidebar = createSidebar("left");
		myViewPanel = createViewPanel();
		myEditPanel = createEditPanel();
		refreshMainPanel();
		myRightSidebar = createSidebar("right");
		add(myLeftSidebar,"cell 0 0");
		add(myMainPanel,"cell 1 0");
		add(myRightSidebar,"cell 2 0");
	}

	private JPanel createEditPanel() {
		return new JPanel();
	}

	private JPanel createViewPanel() {
		return new JPanel();
	}

	private void refreshMainPanel() {
		myMainPanel = editMode ? myEditPanel : myViewPanel;
	}

	private JPanel createSidebar(String name) {
		JPanel sidebar = new JPanel();
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
			sidebar.add(newPanel, "cell 0 "+j);
		}
		return sidebar;
	}

	@Override
	public JComponent getContent() {
		return this;
	}

} // @jve:decl-index=0:visual-constraint="-4,26"
