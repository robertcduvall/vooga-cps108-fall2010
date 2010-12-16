package arcade.util.xmleditor.controllers.element.attributes;

import javax.swing.JComponent;

import org.w3c.dom.NamedNodeMap;

/**
 * 
 * Keeps track of the attributes of the currently selected 
 * XMLNode.
 *  
 * @author Daniel Koverman
 *
 */
public interface IAttributeController {
	
	/**
	 * Get the view which the controller uses to display 
	 * information about the attributes of the currently 
	 * selected element. 
	 * @return JComponent displaying attribute information
	 * 
	 */
	public JComponent getView();
	
	/**
	 * Called by the element controller on which this controller is
	 * dependent to inform the the attributes have changed
	 * @param attributeMap NamedNodeMap containing the new attributes to track
	 */
	public void setAttributeMap(NamedNodeMap attributeMap);

}
