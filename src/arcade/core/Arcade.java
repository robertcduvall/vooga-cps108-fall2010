package arcade.core;

import java.awt.Component;
import java.lang.reflect.*;
import java.util.ResourceBundle;

import javax.swing.*;

/**
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
@SuppressWarnings("serial")
public class Arcade extends JFrame {
	private static final String delimiter = ",";
	private static final int defaultXSize = 640;
	private static final int defaultYSize = 480;

	private ResourceBundle resources = ResourceBundle
			.getBundle("arcade.core.componentList");

	public Arcade() {
		JTabbedPane everything = new JTabbedPane();
		JPanel main = init("panels");
		// JPanel main = new JPanel();
		everything.addTab("Arcade", null, main, "Arcade Main View");
		for (String s : getSet("tabs")) {
			try {
				Class<?> c = Class.forName(s);
				Tab t = (Tab) c.getConstructor().newInstance();
				everything.addTab(t.getComponentName(), null, t,
						t.getComponentDescription());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		add(everything);

		setSize(defaultXSize, defaultYSize);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		for (String classname : getSet("windows")) {
			if(classname.isEmpty())
				continue;
			try {
				Class<?> c = Class.forName(classname);
				Constructor<?> con = c.getConstructor();
				JFrame window=(JFrame) (con.newInstance());
			} catch (ClassNotFoundException e) {
				System.out.println("Can't find a class named: " + classname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String[] getSet(String name) {
		String value = resources.getString(name);
		return value.split(delimiter);
	}

	private JPanel init(String name) {
		JPanel contents = new JPanel();
		for (String classname : getSet(name)) {
			if(classname.isEmpty())
				continue;
			try {
				Class<?> c = Class.forName(classname);
				Constructor<?> con = c.getConstructor();
				contents.add((JComponent) (con.newInstance()));
			} catch (ClassNotFoundException e) {
				System.out.println("Can't find a class named: " + classname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contents;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Arcade arcade = new Arcade();
	}

}
