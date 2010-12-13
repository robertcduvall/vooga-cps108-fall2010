package arcade.lobby.viewComponents;

import javax.swing.JComponent;
import javax.swing.JLabel;


/**
 * 
 * 
 * Class to help with validating fields.
 * This is the component to be added to the dock.
 * Must create a new one for each different type of validation you want. 
 * @author Lobby Group
 */
public abstract class ValidatingComponent<T extends JComponent> {

	private T myComponent;
	private JLabel myLabel;
	
	/**
	 * 
	 * @param component the component that you want to be added
	 * @param label the string label
	 */
	public ValidatingComponent(T component, String label){
		myComponent = component;
		myLabel = new JLabel(label);
	}

	public T getComponent() {
		return myComponent;
	}
	
	public JLabel getLabel(){
		return myLabel;
	}
	
	/**
	 * 
	 * @return Whether the component passes of fails the validation
	 */
	public abstract boolean validate();
	
	
}
