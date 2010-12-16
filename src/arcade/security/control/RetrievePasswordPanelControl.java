package arcade.security.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import arcade.security.model.AdminProcess;
import arcade.security.model.IModel;
import arcade.security.model.RetrievePasswordProcess;
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.view.AdminPanel;
import arcade.security.view.IView;
import arcade.security.view.LogInPanel;
import arcade.security.view.RetrievePasswordPanel;

/**
 * Controller for the forgotten password page. Used in conjunction with
 * security.view.RetrievePasswordPanel and
 * security.model.RetrievePasswordProcess.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class RetrievePasswordPanelControl implements IControl {
	private final static Logger log = Logger
			.getLogger(RetrievePasswordPanelControl.class);
	private RetrievePasswordProcess model;
	private RetrievePasswordPanel view;
	private String userNameInput;
	private String userAnswerInput;

	/**
	 * Constructor for the forgotten password panel controller. Takes an IView
	 * object and an IModel object
	 * 
	 * @param view
	 *            the corresponding view object
	 * @param model
	 *            the corresponding model object
	 */
	public RetrievePasswordPanelControl(IView view, IModel model) {
		this.model = (RetrievePasswordProcess) model;
		this.view = (RetrievePasswordPanel) view;
		this.view.addLoginPageButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToLogInPage();
			}

		});
		this.view.addSubmitButtonListener(new SubmitEvent());
	}

	private class SubmitEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			userNameInput = view.getUserNameUserInput();
			userAnswerInput = view.getQuestionAnswer();
			if (model.isAnswerMatched(userNameInput, userAnswerInput)) {
				JOptionPane
						.showMessageDialog(view.getCurrentPanel(),
								"Your password is :"
										+ model.getPassword(userNameInput));
			} else {
				JOptionPane
						.showMessageDialog(
								view.getCurrentPanel(),
								"Your answer and the correct answer do not match or your username does not exist.");
			}
		}
	}

	/**
	 * Switches the current panel to the login page
	 */
	private void switchToLogInPage() {
		view.removeAll();
		view.updateUI();
		LogInPanel jp = new LogInPanel();
		jp.getContent();
		view.add(jp);

	}
}
