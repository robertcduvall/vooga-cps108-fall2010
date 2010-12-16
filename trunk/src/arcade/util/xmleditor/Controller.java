package arcade.util.xmleditor;

import java.io.File;
import java.io.IOException;

import javax.swing.JToolBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;
import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.ModelObserver;
import arcade.util.xmleditor.model.XMLNode;
import arcade.util.xmleditor.view.AttributeController;
import arcade.util.xmleditor.view.ElementController;
import arcade.util.xmleditor.view.IBaseView;
import arcade.util.xmleditor.view.View;
import arcade.util.xmleditor.view.toolbar.AddAttributeController;
import arcade.util.xmleditor.view.toolbar.AddChildController;
import arcade.util.xmleditor.view.toolbar.DeleteElementController;
import arcade.util.xmleditor.view.toolbar.ElementToolBarButton;

public class Controller implements IBaseController, ModelListener {

	private IBaseView view;
	private Document modelDocument;
	private ModelObserver observer;

	public Controller() {
		observer = new ModelObserver();
		observer.addNewListener(this);
		
		JToolBar toolbar = createElementToolBar();
		ElementController elementController = createElementController(toolbar);
		
		view = new View(this, elementController.getView());	
		
		view.showView();
	}

	public void setModel(File file) {
		XMLDocumentCreator xmlCreator = new XMLFileParser(file);
		try {
			modelDocument = xmlCreator.getDocument();
		} catch (ParserConfigurationException e) {
			view.showError("Parser Configuration Error\n" + e.getMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			view.showError("Malformed XML\n" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			view.showError("Error Opening File\n" + e.getMessage());
			e.printStackTrace();
		}
		observer.notifyModelChanged(new XMLNode((Element) modelDocument
				.getFirstChild(), null, observer));
	}

	@Override
	public void save() {
		writeModelToFile(new File(modelDocument.getBaseURI()));
	}

	@Override
	public void save(File file) {
		writeModelToFile(file);
	}

	private JToolBar createElementToolBar() {
		JToolBar toolbar = new JToolBar();

		addElementButton(toolbar, new AddAttributeController());
		addElementButton(toolbar, new DeleteElementController());
		addElementButton(toolbar, new AddChildController());

		return toolbar;
	}

	private void addElementButton(JToolBar toolbar, ElementToolBarButton button) {
		observer.addNewListener(button);
		toolbar.add(button.getView());
	}

	private ElementController createElementController(JToolBar toolbar) {
		AttributeController attributeController = new AttributeController();
		observer.addNewListener(attributeController);

		ElementController elementController = new ElementController(this,
				toolbar, attributeController);
		return elementController;
	}

	private void writeModelToFile(File file) {
		try {
			Source source = new DOMSource(modelDocument);
			Result result = new StreamResult(file);
			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.transform(source, result);
		} catch (Throwable e) {
			view.showError("Error Saving File\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new Controller();
	}

	@Override
	public void modelChanged(XMLNode root) {
		view.reloadModel(root);		
	}

	@Override
	public void nodeSelected(XMLNode node) {
		//Do nothing		
	}

	@Override
	public void nodeUpdated(XMLNode node) {
		view.updateModel(node);		
	}

	@Override
	public void newNodeSelected(XMLNode node) {
		observer.notifyNodeSelected(node);		
	}

}
