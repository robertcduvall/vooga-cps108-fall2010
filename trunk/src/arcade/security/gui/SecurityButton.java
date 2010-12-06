package arcade.security.gui;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * 
 * @author Meng Li
 *
 */
public class SecurityButton extends JButton{
	private static final long serialVersionUID = 1L;

	public SecurityButton(String name,ActionListener listener) {
		super(name);
		addActionListener(listener);
	}
	public SecurityButton(String name,Icon icon,ActionListener listener) {
		super(name,icon);
		addActionListener(listener);
	}
	
	public SecurityButton(String name,Icon icon,String toolTip,ActionListener listener) {
		super(name,icon);
		this.setToolTipText(toolTip);
		addActionListener(listener);
	}

	public SecurityButton(Icon icon) {
		super(icon);
		
	}

	public SecurityButton(String name) {
		super(name);
		
	}
/**
 * Abstract action
 * @param a
 */
	public SecurityButton(Action a) {    
		super(a);
		
	}

	public SecurityButton(String arg0, Icon arg1) {
		super(arg0, arg1);
		
	}
	
	public SecurityButton(String name,Icon icon,String toolTip){
		super(name,icon);
		this.setToolTipText(toolTip);
	}
	
	public SecurityButton(String name,String toolTip){
		super(name);
		this.setToolTipText(toolTip);
	}
}
