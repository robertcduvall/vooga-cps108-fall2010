package arcade.util.xmleditor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElementNamePanel extends JPanel implements ModelAlteredListener{
	
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

	@Override
	public void modelChanged() {
		updateElementNameDisplay();		
	}

}
