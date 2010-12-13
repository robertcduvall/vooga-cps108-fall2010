package arcade.util.xmleditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class View extends JPanel implements ModelListener{
	
	TreeViewer treeViewer;
	ElementPanel elementPanel;

	public View(ElementPanel elementPanel) throws ParserConfigurationException, SAXException,
			IOException {
		
		this.initializePanel();
		
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
		add(treeViewer, BorderLayout.WEST);
		
	}

	@Override
	public void elementSelected(Element element) {
		//Do nothing		
	}

}
