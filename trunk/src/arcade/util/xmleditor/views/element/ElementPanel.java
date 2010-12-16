package arcade.util.xmleditor.views.element;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;


public class ElementPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public ElementPanel(JComponent nameView, JComponent attributeView, JComponent toolbar){
		this.setLayout(new BorderLayout());	
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(toolbar, BorderLayout.NORTH);
		topPanel.add(nameView, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
		add(attributeView, BorderLayout.CENTER);
	}

}
