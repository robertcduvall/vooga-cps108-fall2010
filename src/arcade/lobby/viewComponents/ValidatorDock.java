package arcade.lobby.viewComponents;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * The dock,
 * the container holding all of the ValidatingComponents
 * 
 * @author Lobby Group
 */
public class ValidatorDock extends JPanel {

	private Map<String, ValidatingComponent<?>> myComponentMap;
	
	public ValidatorDock(){
		setLayout(new MigLayout("wrap 2"));
		myComponentMap = new TreeMap<String, ValidatingComponent<?>>();
	}
	/**
	 * 
	 * @param component the ValidatingComponent to be added to the dock
	 * @param name the name so it can be placed into a map to give acess to it
	 */
	public void addValidatingComponent(ValidatingComponent<?> component, String name){
		myComponentMap.put(name, component);
		this.add(component.getLabel());
		this.add(component.getComponent());
	}
	
	/**
	 * 
	 * @return a map of the Validating Components
	 */
	public Map<String, ValidatingComponent<?>> getComponentMap(){
		return myComponentMap;
	}
	
	/**
	 * Validates all of the components
	 * @return a map of whether the validation of each component passed or failed
	 */
	public Map<String, Boolean> validateComponents(){
		Map<String, Boolean> temp = new TreeMap<String, Boolean>();
		for(String name:myComponentMap.keySet()){
			temp.put(name, myComponentMap.get(name).validate());
		}
		return temp;
	}
}
