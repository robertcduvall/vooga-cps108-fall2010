package arcade.core;

import arcade.core.Tab;

import java.awt.Component;
import java.awt.*;
import java.lang.reflect.*;
import java.util.ResourceBundle;

import javax.swing.*;

import arcade.util.database.Constants;
import arcade.util.database.MySqlAdapter;

/**
 * Arcade
 * 
 * Main arcade class. This creates the application window GUI.
 * 
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
@SuppressWarnings("serial")
public class Arcade extends JFrame {
	private static final String DELIMITER = ",";
	private static final int XSIZE = 1000;
	private static final int YSIZE = 820;

	// componentList contains 3 lists, panels, tabs, and windows
	private ResourceBundle resources = ResourceBundle
			.getBundle("arcade.core.componentList");
	public static MySqlAdapter myDbAdapter = new MySqlAdapter(Constants.HOST,
			Constants.DBNAME, Constants.USER, Constants.PASSWORD);

	private static JTabbedPane mainWindow;

	public Arcade() {
		setLayout(new BorderLayout());
		getContentPane().add(createLogin(), BorderLayout.NORTH);
		mainWindow = createTabs();
		getContentPane().add(mainWindow, BorderLayout.SOUTH);

		setSize(XSIZE, YSIZE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		 createWindows();
	}

	// Temporary
	// TODO: lobby group please create this
	private JPanel createLogin() {
		JPanel a = new JPanel();
		a.setLayout(new BorderLayout());
		Box box = Box.createHorizontalBox();
		JLabel userLabel = new JLabel("Username: ");
		JTextField userField = new JTextField(7);
		JLabel passLabel = new JLabel("Password: ");
		JPasswordField passField = new JPasswordField(7);
		JButton login = new JButton("Log in");
		JButton register = new JButton("Register");
		box.add(userLabel);
		box.add(userField);
		box.add(passLabel);
		box.add(passField);
		box.add(login);
		box.add(register);
		box.setMaximumSize(new Dimension(200, 5));
		a.add(box, BorderLayout.EAST);
		return a;
	}

	/**
	 * Create all tabs in the window
	 * 
	 * @return initialized Tabbed Pane
	 */
	private JTabbedPane createTabs() {
		JTabbedPane everything = new JTabbedPane();
		// JPanel main = createArcadeView();
		// everything.addTab("Arcade", null, main, "Arcade Main View");
		for (String classname : getSet("tabs")) {
			if (classname.isEmpty())
				continue;
			try {
				Tab t = (Tab) getObject(classname);
				everything.addTab(t.getName(), null, t.getContent(),
						t.getToolTipText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return everything;
	}

	private JPanel createArcadeView() {
		JPanel main = new JPanel();
		main.add(createPanels("leftPanel"));
		main.add(new GameView("zombieland"));
		main.add(createPanels("rightPanel"));
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
		return main;
	}

	/**
	 * Create panels in the main arcade view
	 * 
	 * @param name
	 * @return
	 */
	private JPanel createPanels(String name) {
		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		for (String classname : getSet(name)) {
			if (classname.isEmpty())
				continue;
			try {
				contents.add((JComponent) (getObject(classname)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contents;
	}

	/**
	 * Create windows
	 */
	private void createWindows() {
		for (String classname : getSet("windows")) {
			if (classname.isEmpty())
				continue;
			try {
				getObject(classname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * TODO
	 * 
	 * @param index
	 */
	public static void play(String gameName) {
		switchToTab(1);
		ExampleGUI.setGame(gameName);
	}

	public static void switchToTab(int id) {
		mainWindow.setSelectedIndex(id);
//		((Tab)mainWindow.getComponent(id)).refresh();
	}

	/**
	 * 
	 * @param classname
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Object getObject(String classname) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Class<?> c = Class.forName(classname);
		return c.getConstructor().newInstance();
	}

	/**
	 * Get value from the resource bundle and return a list of classnames
	 * 
	 * @param name
	 * @return
	 */
	private String[] getSet(String name) {
		String value = resources.getString(name);
		return value.split(DELIMITER);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Arcade();
	}

}
