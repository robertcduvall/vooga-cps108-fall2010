package arcade.util.xmleditor.controllers.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;

public abstract class ElementToolBarButton implements ActionListener, ModelListener{
	
	private XMLNode node;
	private JButton view;
	
	public ElementToolBarButton(String label){
		view = new JButton(label);
		view.addActionListener(this);
	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);

	@Override
	public void nodeSelected(XMLNode node){
		this.node = node;
	}

	@Override
	public void modelChanged(XMLNode root) {
		node = null;
	}
	
	@Override
	public void nodeUpdated(XMLNode node){
		//Do nothing
	}
	
	public XMLNode getNode(){
		return node;
	}
	
	public JButton getView(){
		return view;
	}

}
