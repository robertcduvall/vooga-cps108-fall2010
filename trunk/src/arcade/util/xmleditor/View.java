package arcade.util.xmleditor;

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

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class View extends JPanel {

	public View() throws ParserConfigurationException, SAXException,
			IOException {
		
		this.initializePanel();
		
		File file = new File("resources.xml");
		XMLDocumentCreator xmlCreator = new XMLFileParser(file);
		Document document = xmlCreator.getDocument();
		TreeViewer treeViewer = new TreeViewer(new XMLNode((Element) document
				.getFirstChild(), null));
		ElementPanel elementPanel = new ElementPanel();
		treeViewer.addTreeSelectionListener(elementPanel);

		add(treeViewer, BorderLayout.WEST);
		add(elementPanel, BorderLayout.CENTER);

	}

	private void initializePanel() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 600));
		
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		JFrame frame = new JFrame();
		frame.add(new View());
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowCloser());
		
		
	}

}