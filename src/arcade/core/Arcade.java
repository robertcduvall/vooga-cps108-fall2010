package arcade.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.lobby.view.Login;
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
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		
		setSize(xSize, ySize);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

				 createWindows();
	}

	private JPanel createLogin() {
		return new Login();
	}

	/**
	 * Create all tabs in the window
	 * 
	 * @return initialized Tabbed Pane
	 */
	private JTabbedPane createTabs() {
		final JTabbedPane everything = new JTabbedPane();
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
	 * Switches to the Arcade tab and calls for a change to the main screen of the arcade.
	 * It then calls for a refresh of the entire panel
	 * 
	 * @param gameID The ID of the selected game
	 */
	public static void play(int gameID) {
		switchToTab(mainWindow.getTabCount()-1);
		ArcadeTab.play(gameID);
	}


	/**
	 * Changes the view to the tab selected.
	 * 
	 * @param id The panel to switch to
	 */
	public static void switchToTab(int id) {
		mainWindow.setSelectedIndex(id);
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
