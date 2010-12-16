package arcade.core;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import arcade.core.mvc.IController;

@SuppressWarnings("serial")
public class ArcadeTab extends JSplitPane implements Tab{

	private static JSplitPane columnar;
	private static JSplitPane mainPanel;
	private static final String DELIMITER = ",";

	private static ResourceBundle resources = ResourceBundle
	.getBundle("arcade.core.componentList");
	
	public ArcadeTab(){
		GameView game = new GameView(20);		
		columnar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createPanels("leftPanel"),		
				game);		
		columnar.setOneTouchExpandable(true);	
		//					columnar.setDividerLocation(0);
		
		setName("Arcade");
		setOrientation(HORIZONTAL_SPLIT);
		columnar.setMinimumSize(new Dimension(700, 820));
		setLeftComponent(columnar);
		
		setRightComponent(createPanels("rightPanel"));
		setDividerLocation(0.9999);
		setOneTouchExpandable(true);	
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


	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
//		mainPanel.setRightComponent(createPanels("rightPanel"));
//		columnar.setLeftComponent(createPanels("leftPanel"));
		repaint();
	}
	
	/**
	 * Switches to the Arcade tab and calls for a change to the main screen of the arcade.
	 * It then calls for a refresh of the entire panel
	 * 
	 * @param gameID The ID of the selected game
	 */
	public static void play(int gameID) {
		//		ExampleGUI.setGame(gameID);
		columnar.setRightComponent(new GameView(gameID));
		columnar.setLeftComponent(createPanels("leftPanel"));
		
	}
	
	public static void refreshLeft(){
		columnar.setLeftComponent(createPanels("leftPanel"));
	}

	public static void refreshRight(){
		mainPanel.setRightComponent(createPanels("rightPanel"));
	}


}
