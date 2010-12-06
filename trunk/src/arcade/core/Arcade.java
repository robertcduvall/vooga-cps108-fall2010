package arcade.core;

import java.lang.reflect.*;
import java.util.ResourceBundle;

import javax.swing.*;

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
	private static final String delimiter = ",";
	private static final int defaultXSize = 640;
	private static final int defaultYSize = 480;

	// componentList contains 3 lists, panels, tabs, and windows
	private ResourceBundle resources = ResourceBundle
			.getBundle("arcade.core.componentList");

	public Arcade() {
		JTabbedPane mainWindow = createTabs();
		mainWindow.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(mainWindow);

		setSize(defaultXSize, defaultYSize);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createWindows();
	}

	/**
	 * Create all tabs in the window
	 * 
	 * @return initialized Tabbed Pane
	 */
	private JTabbedPane createTabs() {
		JTabbedPane everything = new JTabbedPane();
//		JPanel main = createPanels("panels");
//		everything.addTab("Arcade", null, main, "Arcade Main View");
		for (String classname : getSet("tabs")) {
			if (classname.isEmpty())
				continue;
			try {
				Tab t = (Tab) getObject(classname);
				everything.addTab(t.getName(), null, t,
						t.getToolTipText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return everything;
	}

	/**
	 * Create panels in the main arcade view
	 * 
	 * @param name
	 * @return
	 */
	private JPanel createPanels(String name) {
		JPanel contents = new JPanel();
		for (String classname : getSet(name)) {
			if (classname.isEmpty())
				continue;
			try {
				contents.add((JComponent) (getObject(classname)));
			} catch (ClassNotFoundException e) {
				System.out.println("Can't find a class named: " + classname);
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
				JFrame window = (JFrame) (getObject(classname));
			} catch (ClassNotFoundException e) {
				System.out.println("Can't find a class named: " + classname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		return value.split(delimiter);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Arcade arcade = new Arcade();
	}
}
