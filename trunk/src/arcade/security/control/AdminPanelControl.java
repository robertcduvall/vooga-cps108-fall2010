package arcade.security.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import arcade.lobby.model.ProfileSet;
import arcade.security.model.AdminProcess;
import arcade.security.model.IModel;
import arcade.security.model.LoginProcess;
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.view.AdminPanel;
import arcade.security.view.IView;
import arcade.security.view.LogInPanel;

/**
 * Controller for the Administrator Panel. Used in conjunction with
 * security.view.AdminPanel and security.model.AdminProcess.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */

public class AdminPanelControl implements IControl {

	private final static Logger log = Logger.getLogger(AdminPanelControl.class);
	private AdminProcess model;
	private AdminPanel view;

	/**
	 * Constructor for the admin panel controller. Takes an IView object and an
	 * IModel object
	 * 
	 * @param view
	 *            the corresponding view object
	 * @param model
	 *            the corresponding model object
	 */
	public AdminPanelControl(IView view, IModel model) {
		this.model = (AdminProcess) model;
		this.view = (AdminPanel) view;
		this.view.addLogoutButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				log.info("Before log out, Current User role: "
								+ user.getRole());
				user.setUserAs("default");
				log.info("After log out, Current User role: " + user.getRole());
				ProfileSet.setCurrentProfile(null);
				switchToLogInPage();
			}

		});
	}

	/**
	 * Switches the current panel to the login panel.
	 */
	private void switchToLogInPage() {

		view.removeAll();
		view.updateUI();
		LogInPanel jp = new LogInPanel();
		jp.getContent();
		view.add(jp);
	}
}
