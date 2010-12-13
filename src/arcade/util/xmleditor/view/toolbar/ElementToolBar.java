package arcade.util.xmleditor.view.toolbar;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ElementToolBar extends JToolBar{
	
	
	private static final long serialVersionUID = 1L;

	public ElementToolBar(){
		add(createNewAttributeButton());
		add(createDeleteElementButton());
	}
	
	private JButton createNewAttributeButton(){
		JButton newAttribute = new JButton("Add Attribute");
		newAttribute.addActionListener(new AddAttributeController());
		return newAttribute;
	}
	
	private JButton createDeleteElementButton(){
		JButton deleteElement = new JButton("Delete Element");
		
		return deleteElement;
	}
	

}
