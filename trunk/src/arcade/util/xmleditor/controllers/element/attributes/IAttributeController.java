package arcade.util.xmleditor.controllers.element.attributes;

import javax.swing.JComponent;

import org.w3c.dom.NamedNodeMap;

public interface IAttributeController {
	
	public JComponent getView();
	
	public void setAttributeMap(NamedNodeMap attributeMap);

}
