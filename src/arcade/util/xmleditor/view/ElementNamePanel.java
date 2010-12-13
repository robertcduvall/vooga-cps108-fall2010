package arcade.util.xmleditor.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElementNamePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public void update(String elementName){
		removeAll();
		updateUI();
		
		add(new JLabel(elementName));
	}

}
