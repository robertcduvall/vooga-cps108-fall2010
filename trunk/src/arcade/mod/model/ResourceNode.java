package arcade.mod.model;

import java.util.Collection;
import java.util.List;

/**
 * Contains all the available information on a Resource in the 
 * model. This may include children, attributes, and a description.
 * From these attributes are mod-able.
 * @author djk14
 *
 */
public interface ResourceNode {
	
	/**
	 * Return a List of the subnodes to this ResourceNode. Currently 
	 * all implemented Resource types do not have Children except for 
	 * a Description, so this method is currently unused.
	 * @return List of children ResourceNodes
	 */
	public List<ResourceNode> getChildren();	
	
	/**
	 * Returns Attributes of the ResourceNodes. Effectively, Attributes 
	 * are keys to String values in a map and this method retrieves a list
	 * of keys.
	 * @return Collection of all attribute types for this ResourceNode
	 */
	public Collection<String> getAttributes();
	
	/**
	 * Get the String value of an Attribute of the ResourceNode given
	 * the name of the desired Attribute.
	 * @param attributeName the name of the desired Attribute
	 * @return the String value of the requested Attribute
	 */
	public String getAttribute(String attributeName);
	
	/**
	 * Overwrites the current String value of an Attribute given
	 * the name of the desired Attribute.
	 * @param attributeName the name of the desired Attribute
	 * @param value the String to set the Attribute to
	 */
	public void setAttribute(String attributeName, String value);
	
	/**
	 * Get a short description of what this ResourceNode represents.
	 * Generally should be a phrase to two sentences in length.
	 * @return description of this ResourceNode
	 */
	public String getDescription();

}
