package arcade.security.view;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;
import arcade.security.util.UserPrivilegesPanelHandler;
import arcade.security.gui.PrivilegeCheckBox;

/**
 * The panel for setting individual privileges.
 * 
 * @author Jiaqi Yan
 * 
 */
public class UserPrivilegesPanel extends JPanel implements IView {
	private UserPrivilegesPanelHandler model;
	private String username;
	private JLabel usernameLabel;
	private JScrollPane privilegeScrollPane;
	private static final int SECURITY_PANEL_WIDTH = 600;
	private static final int SECURITY_PANEL_HEIGHT = 300;
	private Map<String, Boolean> userPrivilegesMap;
	private JButton submitButton;
	private JButton undoButton;

	public UserPrivilegesPanel(String username) {
		model = new UserPrivilegesPanelHandler(username);
		setLayout(new MigLayout("wrap 1"));
		usernameLabel = new JLabel(username);
		add(usernameLabel);
		createScrollPane();
		setVisible(true);
		submitButton = model.createSubmitButton();
		undoButton = model.createUndoButton();
		add(submitButton);
		add(undoButton);
		setVisible(true);
	}

	private void createScrollPane() {
		userPrivilegesMap = model.getCurrentUserMap();
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 3);
		panel.setLayout(gridLayout);
		for (String privilege : userPrivilegesMap.keySet()) {
			panel.add(model.createNewPrivilegeCheckBox(privilege,
					userPrivilegesMap.get(privilege)), "align left");
		}
		privilegeScrollPane = new JScrollPane(panel);
		privilegeScrollPane.setPreferredSize(new Dimension(
				SECURITY_PANEL_WIDTH, SECURITY_PANEL_HEIGHT));
		add(privilegeScrollPane);
	}

}
