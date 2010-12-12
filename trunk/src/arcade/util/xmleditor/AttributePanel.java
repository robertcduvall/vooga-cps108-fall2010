package arcade.util.xmleditor;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import arcade.core.AttributeFrame;

public class AttributePanel extends JPanel implements ModelAlteredListener{
	
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
			add(new AttributeFrame(attribute));
		}
	}

	@Override
	public void modelChanged() {
		updateAttributeDisplay();		
	}

}
