package arcade.util.xmleditor.view.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.w3c.dom.Element;

import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;

public abstract class ElementToolBarButton implements ActionListener, ModelListener{
	
	private Element element;
	private JButton view;
	
	public ElementToolBarButton(String label){
		view = new JButton(label);
		view.addActionListener(this);
	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);

	@Override
	public void elementSelected(Element element){
		this.element = element;
	}

	@Override
	public void modelChanged(XMLNode root) {
		element = null;	
	}
	
	public Element getElement(){
		return element;
	}
	
	public JButton getView(){
		return view;
	}

}
