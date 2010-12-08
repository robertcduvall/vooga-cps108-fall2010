package arcade.store.gui;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import arcade.core.Tab;

public class StoreContainer extends Tab{

	
	private ResourceBundle resources = ResourceBundle
	.getBundle("arcade.store.gui.resources.tabList");
	
	public StoreContainer() {
		setName("Store");
	}
	
	@Override
	public JComponent getContent() {
		JTabbedPane component = createTabs();
		return component;
	}
	
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
	
	
	private String[] getSet(String name) {
		return resources.getString(name).split(",");
	}


	private Object getObject(String classname) throws ClassNotFoundException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException {
		Class<?> c = Class.forName(classname);
		return c.getConstructor().newInstance();
	}
}
