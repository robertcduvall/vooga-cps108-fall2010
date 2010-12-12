package arcade.util.xmleditor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElementNamePanel extends JPanel{
	
	private String elementName;
	
	public ElementNamePanel(){
		
	}
	
	public ElementNamePanel(String elementName){
		this.elementName = elementName;
	}
	
	public void setElementName(String elementName){
		this.elementName = elementName;
		updateElementNameDisplay();
	}
	
	private void updateElementNameDisplay(){
		removeAll();
		updateUI();
		
		add(new JLabel(elementName));
	}

}
