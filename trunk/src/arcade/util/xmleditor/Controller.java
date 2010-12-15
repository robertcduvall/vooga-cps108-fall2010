package arcade.util.xmleditor;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;
import arcade.util.xmleditor.mainmenu.MenuBar;
import arcade.util.xmleditor.model.ModelObserver;
import arcade.util.xmleditor.model.XMLNode;
import arcade.util.xmleditor.view.ElementController;
import arcade.util.xmleditor.view.View;
import arcade.util.xmleditor.view.WindowCloser;
import arcade.util.xmleditor.view.toolbar.AddAttributeController;
import arcade.util.xmleditor.view.toolbar.AddChildController;
import arcade.util.xmleditor.view.toolbar.DeleteElementController;
import arcade.util.xmleditor.view.toolbar.ElementToolBarButton;

public class Controller implements TreeSelectionListener{
	
	private View view;
	private Document modelDocument;
	private ModelObserver observer;
	private Element currentElement;
	
	public Controller(){
		observer = new ModelObserver();
		
		JToolBar toolbar = createElementToolBar();
		
		JFrame frame = new JFrame();
		ElementController elementController = new ElementController(this, toolbar);
		try {
			view = new View(this, elementController.getView());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		observer.addNewListener(view);
		frame.add(view);		
		frame.addWindowListener(new WindowCloser());
		frame.setJMenuBar(new MenuBar(this));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	

	public void setModel(File file){
		XMLDocumentCreator xmlCreator = new XMLFileParser(file);
		try {
			modelDocument = xmlCreator.getDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		observer.notifyModelChanged(modelDocument);
	}
	
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		new Controller();

	}
	
	public void save() {
		File file = new File(modelDocument.getBaseURI());
		try {
	        // Prepare the DOM document for writing
	        Source source = new DOMSource(modelDocument);

	        // Prepare the output file
	        Result result = new StreamResult(file);

	        // Write the DOM document to the file
	        Transformer xformer = TransformerFactory.newInstance().newTransformer();
	        xformer.transform(source, result);
	    } catch (TransformerConfigurationException e) {
	    } catch (TransformerException e) {
	    }
	}


	public void save(File file) {
		try {
	        // Prepare the DOM document for writing
	        Source source = new DOMSource(modelDocument);

	        // Prepare the output file
	        Result result = new StreamResult(file);

	        // Write the DOM document to the file
	        Transformer xformer = TransformerFactory.newInstance().newTransformer();
	        xformer.transform(source, result);
	    } catch (TransformerConfigurationException e) {
	    } catch (TransformerException e) {
	    }
	}
	
	public void addAttribute(){
		
	}


	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		currentElement = ((XMLNode) arg0.getPath().getLastPathComponent()).getElement();	
		observer.notifyElementSelected(currentElement);
	}
	
	private JToolBar createElementToolBar(){
		JToolBar toolbar = new JToolBar();
		
		addElementButton(toolbar, new AddAttributeController());
		addElementButton(toolbar, new DeleteElementController());
		addElementButton(toolbar, new AddChildController());
		
		return toolbar;
	}
	
	private void addElementButton(JToolBar toolbar, ElementToolBarButton button){
		observer.addNewListener(button);
		toolbar.add(button.getView());
	}

}
