package arcade.util.xmleditor;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ElementPanel extends JPanel implements TreeSelectionListener{
	
	Element element;
	AttributePanel attributePanel;
	ElementNamePanel namePanel;
	
	ElementPanel(){
		this.setLayout(new BorderLayout());
		attributePanel = new AttributePanel();
		namePanel = new ElementNamePanel();
		add(namePanel, BorderLayout.NORTH);
		add(attributePanel, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		element = ((XMLNode) arg0.getPath().getLastPathComponent()).getElement();
		NamedNodeMap attributeMap = element.getAttributes();
		attributePanel.setAttributeMap(attributeMap);
		namePanel.setElementName(element.getNodeName());
	}

}
