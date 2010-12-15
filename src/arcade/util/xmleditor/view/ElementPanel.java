package arcade.util.xmleditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ElementPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	ElementController controller;

	ElementPanel(ElementController controller, JPanel namePanel, JPanel attributePanel, JToolBar toolbar){
		this.setLayout(new BorderLayout());

		this.controller = controller;
		
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(toolbar, BorderLayout.NORTH);
		topPanel.add(namePanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
		add(attributePanel, BorderLayout.CENTER);
	}
	
	public ElementController getController(){
		return controller;
	}

}
