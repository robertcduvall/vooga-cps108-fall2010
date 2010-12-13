package arcade.util.xmleditor.view;

import javax.swing.JPanel;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AttributePanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;

	public final int DEFAULT_TEXT_FIELD_COL = 15;
	
	public void update(NamedNodeMap attributeMap){
		removeAll();
		updateUI();
		for(int i=0; i<attributeMap.getLength(); i++){
			Node attribute = attributeMap.item(i);
			add(new AttributeFrame(attribute));
		}
	}

}
