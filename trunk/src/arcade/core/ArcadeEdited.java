package arcade.core;


import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

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
public class ArcadeEdited extends JFrame {
	private static final String DELIMITER = ",";
	private static final int XSIZE = 1000;
	private static final int YSIZE = 500;

	// componentList contains 3 lists, panels, tabs, and windows
	private ResourceBundle resources = ResourceBundle
			.getBundle("arcade.core.componentListTest");
	public static MySqlAdapter myDbAdapter = new MySqlAdapter(Constants.HOST,
			Constants.DBNAME, Constants.USER, Constants.PASSWORD);

	private static JTabbedPane mainWindow;

	public ArcadeEdited() {
		setLayout(new BorderLayout());
		mainWindow = createTabs();
		getContentPane().add(mainWindow);

		setSize(XSIZE, YSIZE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//createWindows();
	}

	/**
	 * Create all tabs in the window
	 * 
	 * @return initialized Tabbed Pane
	 */
	private JTabbedPane createTabs() {
		JTabbedPane everything = new JTabbedPane();
		// JPanel main = createArcadeView();
		everything.addTab("Arcade", null, createArcadeView(), "Arcade Main View");
		/*
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
		*/
		return everything;
	}

	private JComponent createArcadeView() {
		GameView game; //= new GameView("zombieland");
		JSplitPane columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createPanels("leftPanel"),
				null);
		columnar.setOneTouchExpandable(true);

		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, columnar,
				createPanels("rightPanel"));
		mainPanel.setOneTouchExpandable(true);

		return mainPanel;
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
		GameView.setGame(gameName);
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
		new ArcadeEdited();
	}

}
