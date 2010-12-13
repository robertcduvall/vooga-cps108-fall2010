package arcade.lobby.viewComponents;

import javax.swing.JComponent;

/**
 * 
 * 
 * Must extend this to use it.  
 * All you have to do is to create a Validator of a certain type, and when you create a validating component
 * pass as many validators to it as you want
 * @author Lobby Group
 *
 * @param <T>
 */
public abstract class Validator<T extends JComponent> {
	
	private T myComponent;
	
	public Validator(){
		myComponent = null;
	}
	/**
	 * 
	 * @return whether the field passes or fails the validation.
	 * You have access to the component that this is validating.
	 * Just use the getComponent method to get it.
	 * It is set when it is added to the ValidatorComponent.
	 * 
	 */
	public abstract boolean validate();
	
	protected void setComponent(T component){
		myComponent = component;
	}
	
	protected T getComponent(){
		return myComponent;
	}
		
	

}
