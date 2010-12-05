package arcade.lobby.view;

import javax.swing.JPanel;
import javax.swing.JFrame;

import arcade.lobby.view.HomePage;
import arcade.lobby.model.Profile;
import java.awt.Dimension;
import java.sql.Date;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private Menu myMenu = new Menu();

	/**
	 * This is the default constructor
	 */
	public MainFrame() {
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
		this.setTitle("Home Page");
		this.setJMenuBar(myMenu.setUpMenu());
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
			Profile bob = new Profile("bob");
			bob.setName("Bob", "Smith");
			bob.setBirthday(new Date(89, 6, 10));
			bob.setEmail("abc123@cs.duke.edu");
			bob.setAvatar("http://mystuffspace.com/graphic/blue-playboy-bunny.gif");
			jPanel = new HomePage(bob);
		}
		return jPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
