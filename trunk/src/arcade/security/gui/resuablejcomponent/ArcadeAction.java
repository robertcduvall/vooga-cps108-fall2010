package arcade.security.gui.resuablejcomponent;


import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
/**
 * The class extends AbstractAction is used to separate functionality and state from a component.
 * For more details, please refer to <code>AbstractAction</code> class.
 * @author Meng Li
 *
 */
public abstract class ArcadeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private String[] roles;

	public ArcadeAction(String name,String... role) {
		super(name);
		this.roles=role;
		check();
	}
	
	public ArcadeAction(String name,String toolTip,String... role) {
		super(name);
		putValue(Action.SHORT_DESCRIPTION,toolTip); //this one is for the toolTip information for the button with action
		this.roles=role;
		check();
	}

	public ArcadeAction(String name, Icon icon,String... role) {
		super(name, icon);
		this.roles=role;
		check();
	}
	
	public ArcadeAction(String name,String toolTip,Icon icon,String... role){
		super(name,icon);
		putValue(Action.SHORT_DESCRIPTION,toolTip);  //this one is for the toolTip information for the button with action
		this.roles=role;
		check();
	}
	
	/**
	 * The associated JComponent(not all JComponent) will be enabled or disabled based on the roles which 
	 * is the return value from <code>checkPermission()</code> method.
	 * 
	 */
	public void check(){
		if(checkPermission(roles)){
			setEnabled(true);
			}
			else{
				setEnabled(false);
			}
	}

	/**
	 * Check if a certain type of user represented as a string has the privilege.
	 * @param role
	 * @return true iff the role has the permission.
	 */
	public abstract boolean checkPermission(String... role);


}
