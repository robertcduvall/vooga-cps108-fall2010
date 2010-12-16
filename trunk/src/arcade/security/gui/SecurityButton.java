package arcade.security.gui;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * This class extends JButton class to include all its frequently-used features and provide developer convenience
 * to use JButton.
 * @author Security Group
 *
 */
public class SecurityButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor 
	 * @param name the name for the button
	 * @param listener <code>ActionListener</code> object
	 */
	public SecurityButton(String name,ActionListener listener) {
		this(name,null,null,listener);
	}
	
	/**
	 * Constructor 
	 * @param name the name displayed on the button
	 * @param icon the icon for the button
	 * @param listener <code>ActionListener</code> object
	 */
	public SecurityButton(String name,Icon icon,ActionListener listener) {
		this(name,icon,null,listener);

	}
	
	/**
	 * Constructor
	 * @param name the name displayed on the button
	 * @param icon the icon for the button
	 * @param toolTip the tooltip for the button
	 * @param listener listener <code>ActionListener</code> object
	 */
	public SecurityButton(String name,Icon icon,String toolTip,ActionListener listener) {
		super(name,icon);
		this.setToolTipText(toolTip);
		addActionListener(listener);
	}

	/**
	 * Constructor
	 * @param icon the icon for the button
	 */
	public SecurityButton(Icon icon) {
		this(null,icon,null,null);
		
	}
	/**
	 * Constructor
	 * @param name the name displayed on the button
	 */
	public SecurityButton(String name) {
		this(name,null,null,null);
		
	}
	/**
	 * Constructor
	 * @param action <code>Action</code> object associated with this button
	 */
	public SecurityButton(Action action) {    
		super(action);
		
	}
	
	/**
	 * Constructor
	 * @param name the name displayed on the button
	 * @param icon the icon for the button
	 */
	public SecurityButton(String name, Icon icon) {
		this(name,icon,null,null);
		
	}
	/**
	 * Constructor
	 * @param name the name displayed on the button
	 * @param icon the icon for the button
	 * @param toolTip the tooltip for the button
	 */
	public SecurityButton(String name,Icon icon,String toolTip){
		this(name,icon,toolTip,null);
	}
	/**
	 * Constructor
	 * @param name the name displayed on the button
	 * @param toolTip the tooltip for the button
	 */
	public SecurityButton(String name,String toolTip){
		this(name,null,toolTip,null);
	}
}
