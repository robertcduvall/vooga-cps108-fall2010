package arcade.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.core.examples.HighScore;
import arcade.lobby.model.ProfileSet;
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
	private static JSplitPane columnar;
	private static JSplitPane mainPanel;

	// componentList contains 3 lists, panels, tabs, and windows
	private static ResourceBundle resources = ResourceBundle
			.getBundle("arcade.core.componentList");
	public static MySqlAdapter myDbAdapter = new MySqlAdapter(Constants.HOST,
			Constants.DBNAME, Constants.USER, Constants.PASSWORD);

	private static JTabbedPane mainWindow;

	public Arcade() {
		setLayout(new BorderLayout());
		getContentPane().add(createLogin(), BorderLayout.NORTH);
		mainWindow = createTabs();
		getContentPane().add(mainWindow, BorderLayout.CENTER);

		setSize(XSIZE, YSIZE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		 createWindows();
	}

	// Temporary
	// TODO: security group please create this
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
		final JTabbedPane everything = new JTabbedPane();
		// JPanel main = createArcadeView();
		// everything.addTab("Arcade", null, main, "Arcade Main View");
		for (String classname : getSet("tabs")) {
			if (classname.isEmpty())
				continue;
			try {
				JComponent t =  (JComponent) getObject(classname);
				everything.addTab(((JComponent) t).getName(), null, t,
						((JComponent) t).getToolTipText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		everything.addTab("Arcade", createArcadeView());
		everything.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = everything.getSelectedIndex();
				Component selected = everything.getComponentAt(selectedIndex);
				if(selected instanceof Tab)
					((Tab) selected).refresh();
				// Update tab titles
				everything.setTitleAt(selectedIndex,selected.getName());
			}
		});
		return everything;
	}

	private JComponent createArcadeView() {		
					GameView game = new GameView(20);		
				columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createPanels("leftPanel"),		
							game);		
					columnar.setOneTouchExpandable(true);	
//					columnar.setDividerLocation(0);
				
					mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, columnar,		
					createPanels("rightPanel"));		
				mainPanel.setOneTouchExpandable(true);		
				mainPanel.setName("Arcade");
				return mainPanel;		
				}
	
	/**
	 * Create panels in the main arcade view
	 * 
	 * @param name
	 * @return
	 */
	private static JPanel createPanels(String name) {
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
	public static void play(int gameID) {
		switchToTab(mainWindow.getTabCount()-1);
//		ExampleGUI.setGame(gameID);
		columnar.setRightComponent(new GameView(gameID));
		refreshLeft();
		
	}

	public static void setPlayer(String player) {
		mainPanel.setRightComponent(createPanels("rightPanel"));
	}
	
	public static void refreshLeft(){
		columnar.setLeftComponent(createPanels("leftPanel"));
	}
	
	public static void refreshRight(){
		mainPanel.setRightComponent(createPanels("rightPanel"));
	}
	
	public static void switchToTab(int id) {
		mainWindow.setSelectedIndex(id);
//		((Tab)mainWindow.getComponent(id)).update();
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
	private static Object getObject(String classname) throws ClassNotFoundException,
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
	private static String[] getSet(String name) {
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
