package arcade.util.xmleditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import arcade.util.xmleditor.Controller;
import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;

public class View extends JPanel implements ModelListener{
	
	private static final long serialVersionUID = 1L;
	TreeViewer treeViewer;
	ElementPanel elementPanel;
	Controller controller;

	public View(Controller controller, ElementPanel elementPanel) throws ParserConfigurationException, SAXException,
			IOException {
		
		this.initializePanel();
		this.controller = controller;
		
		
		treeViewer = new TreeViewer();
		
		
		this.elementPanel = elementPanel;
		treeViewer.addTreeSelectionListener(elementPanel.getController());

		add(treeViewer, BorderLayout.WEST);
		add(elementPanel, BorderLayout.CENTER);

	}

	private void initializePanel() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 600));
		
	}

	@Override
	public void modelChanged(XMLNode root) {
		remove(treeViewer);
		updateUI();
		treeViewer = new TreeViewer(root);
		treeViewer.addTreeSelectionListener(elementPanel.getController());
		treeViewer.addTreeSelectionListener(controller);
		add(treeViewer, BorderLayout.WEST);
		
	}

	@Override
	public void nodeSelected(XMLNode node) {
		//Do nothing		
	}

	@Override
	public void nodeUpdated(XMLNode node) {
		
		treeViewer.reload(node);
		validate();
		
//		remove(treeViewer);
//		updateUI();
//		treeViewer = new TreeViewer(controller.getRoot());
//		treeViewer.addTreeSelectionListener(elementPanel.getController());
//		treeViewer.addTreeSelectionListener(controller);
//		add(treeViewer, BorderLayout.WEST);
	}

}
