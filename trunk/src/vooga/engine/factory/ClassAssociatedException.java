package vooga.engine.factory;

/**
 * Exceptions for the MapReader 
 * 
 * @author Derek
 *
*/

public class ClassAssociatedException {
	public final static RuntimeException CLASS_NOT_FOUND = 
		new RuntimeException("The class you want to create is not found");

	public final static RuntimeException CHARACTER_NOT_FOUND =
		new RuntimeException("There is a character in the map that does not exist in the xml");
	
	
}
