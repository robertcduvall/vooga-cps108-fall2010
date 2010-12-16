package arcade.core.ArcadeGUIElements;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import net.miginfocom.swing.MigLayout;

import arcade.core.api.Tab;
import arcade.core.mvc.IController;

/**
 * Main View for the Arcade. Current GameView is displayed and allows user to start the game.
 * Also the home for all panels. Panels can be added to the left or right side of the page in componentList.properties. 
 * 
 * @author Aaron Choi, Derek Zhou, Yang Su
 *
 */

@SuppressWarnings("serial")
public class ArcadeTab extends JSplitPane implements Tab{

	private static JSplitPane columnar;
	private static final String DELIMITER = ",";

	private static ResourceBundle resources = ResourceBundle
	.getBundle("arcade.core.componentList");
	
	public ArcadeTab(){
		setName("Arcade");
	}
	

	@Override
	public void initialize() {
		GameView game = new GameView(20);		
		columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createPanels("leftPanel"),		
				game);		
		columnar.setOneTouchExpandable(true);	
		
		setOrientation(HORIZONTAL_SPLIT);
		columnar.setMinimumSize(new Dimension(700, 820));
		setLeftComponent(columnar);

		setRightComponent(createPanels("rightPanel"));
		setOneTouchExpandable(true);	
	}
	
	private static JPanel createPanels(String name) {
		/*JPanel contents = new JPanel();
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
		*/
		JPanel sidebar = new JPanel();
		sidebar.setVisible(true);
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
			sidebar.add(newPanel, "cell 0 " + j);
		}
		return sidebar;
	}
	
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


	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		repaint();
		refreshPanels();
		setRightComponent(createPanels("rightPanel"));
	}
	
	/**
	 * Switches to the Arcade tab and calls for a change to the main screen of the arcade.
	 * It then calls for a refresh of the entire panel
	 * 
	 * @param gameID The ID of the selected game
	 */
	public static void play(int gameID) {
		columnar.setRightComponent(new GameView(gameID));
		refreshPanels();
	}
	
	/**
	 * Refreshes all the panels on the left and right side of the view.
	 */
	public static void refreshPanels(){
		columnar.setLeftComponent(createPanels("leftPanel"));
	}


}
