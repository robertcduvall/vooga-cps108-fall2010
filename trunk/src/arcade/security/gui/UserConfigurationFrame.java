package arcade.security.gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
/**
 * 
 * @author Meng Li
 *
 */
@SuppressWarnings("serial")
public abstract class UserConfigurationFrame extends JInternalFrame {

	private JFrame parentFrame;
	
	public UserConfigurationFrame(String name){
		this(name,true,true,true,true);
	}
	
	public UserConfigurationFrame(String name,boolean resizable,boolean closable,boolean maximizable, boolean iconifiable){
		super(name,resizable,closable,maximizable,iconifiable);
	}
    
	public void setParentFrame(JFrame parentFrame){
		this.parentFrame=parentFrame;
	}
	
	public JFrame getParentFrame(){
		return parentFrame;
	}
}
