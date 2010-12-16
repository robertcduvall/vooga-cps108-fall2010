package arcade.util.xmleditor.views.element.name;

import javax.swing.JLabel;



public class ElementNamePanel extends StringPanel{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void updateString(String elementName) {
		removeAll();
		updateUI();
		
		add(new JLabel(elementName));
	}

}
