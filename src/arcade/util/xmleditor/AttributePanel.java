package arcade.util.xmleditor;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AttributePanel extends JPanel{
	
	public final int DEFAULT_TEXT_FIELD_COL = 15;
	
	private NamedNodeMap attributeMap;
	
	public AttributePanel(){	
			
	}
	
	public AttributePanel(NamedNodeMap attributeMap){
		this();		
		this.attributeMap = attributeMap;
	}
	
	public void setAttributeMap(NamedNodeMap attributeMap){
		this.attributeMap = attributeMap;
		updateAttributeDisplay();
	}
	
	private void updateAttributeDisplay(){
		removeAll();
		updateUI();
		for(int i=0; i<attributeMap.getLength(); i++){
			Node attribute = attributeMap.item(i);
			JLabel label = new JLabel(attribute.getNodeName());
			JTextField field = new JTextField(attribute.getNodeValue(), DEFAULT_TEXT_FIELD_COL);
			
			add(label);
			add(field);
		}
	}

}
