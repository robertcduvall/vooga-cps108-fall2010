package arcade.security.control;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import arcade.core.Tab;
import arcade.security.view.*;
/**
 * This is the controller class. It doesn't do much of the controlling though. It basically sets
 * the specified panel to be valid. 
 * @author Jiaqi Yan
 *
 */
public class ControlTab extends Tab{
	
	private static final long serialVersionUID = 1L;
	static JPanel currentPanel = new JPanel();
	
	public ControlTab(){
		setName("Security");
	}
	
	public static JComponent getPanel(){
		return currentPanel;
	}
	
	@Override
	public JComponent getContent() {
		currentPanel = new LogInPanel();//Meng: keep this line, otherwise the GUI looks wierd
		setValidate("arcade.security.view.LogInPanel");
		
		return currentPanel;
	}
	/**
	 * Sets the corresponding panel to show on this tab 
	 * @param panel
	 */
	public static void setValidate(String panel) {
		currentPanel.removeAll();
		currentPanel.updateUI();
		try{
			currentPanel.add(getPanel(panel));	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		currentPanel.validate();
	}
	/**
	 * Reflection method
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static JPanel getPanel(String className) throws ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Class<?> c = Class.forName(className);
		return (JPanel) c.getConstructor().newInstance();
	}
	
	
}