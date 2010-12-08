package arcade.security.gui.resuablejcomponent.demo;

import java.awt.event.ActionEvent;


import arcade.security.gui.resuablejcomponent.ArcadeAction;
/**
 * Every API group needs to create their own privilege components which extend ArcadeAction. These components are only allowed for a certain type
 * of user. JButton is not the only component supports this action.
 * @author Meng Li
 *
 */
public class ButtonAction extends ArcadeAction {
	
	private static final long serialVersionUID = 1L;
	final String  PERMITTED_ROLE ="admin"; //this button is only allowed for administrator to click and use.
	/**
	 * Constructor of ButtonAction
	 * @param name the name of this button 
	 * @param role the permission role which sets this button enabled. Otherwise, this button is disabled. 
	 */
	public ButtonAction(String name, String... role) {
		super(name,role);
		
	}

	
	@Override
	public boolean checkPermission(String... role) {
		String[] roles = role;
		for(String r:roles){
			if(r.equalsIgnoreCase(PERMITTED_ROLE)){  //Here I hard coded current user is an administrator.
				return true;
			}
		}
		return false;
	}

	/**
	 * To do after the event is fired
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("I am the administrator, I have the privilege to click this button.");
		
	}

}
