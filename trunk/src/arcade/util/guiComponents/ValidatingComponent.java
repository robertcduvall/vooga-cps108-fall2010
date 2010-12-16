package arcade.util.guiComponents;

import javax.swing.JComponent;
import javax.swing.JLabel;





/**
 * Class to help with validating fields.
 * This is the component to be added to the dock.
 * @author Lobby Group
 */
public class ValidatingComponent<T extends JComponent> {

	private T myComponent;
	private JLabel myLabel;
	private Validator<T>[] myValidators;
	
	/**
	 * 
	 * WHen the validators are added the Component will be set to each
	 * validator, so they have access to which component it is acting on.
	 * 
	 * @param component the component that you want to be added
	 * @param label the string label
	 * @param validators all the validators for the correct type
	 */
	public ValidatingComponent(T component, String label, Validator<T> ... validators ){
		myComponent = component;
		myLabel = new JLabel(label);
		if(label==null) myLabel.setVisible(false);
		myValidators = validators;
		setValidatorComponents();
	}
	
	/**
	 * Constructs a ValidatingComponent without a label.
	 * @param component the component that you want to be added
	 * @param validators all the validators for the correct type
	 */
	public ValidatingComponent(T component, Validator<T>...validators) {
		this(component,null,validators);
	}

	private void setValidatorComponents() {
		for(Validator<T> validator: myValidators){
			validator.setComponent(myComponent);
		}
		
	}

	/**
	 * Gets the component being validated.
	 * @return component
	 */
	public T getComponent() {
		return myComponent;
	}
	
	/**
	 * Label associated with this validating component.
	 * @return JLabel
	 */
	public JLabel getLabel(){
		return myLabel;
	}
	
	/**
	 * 
	 * @return Whether the component passes of fails the validation
	 */
	public boolean validate(){
		for(Validator<T> validator: myValidators){
			if(!validator.validate()){
				return false;
			}
		}
		return true;
		
	}
	
	
}
