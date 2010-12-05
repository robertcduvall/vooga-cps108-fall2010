package arcade.mod.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class XMLModel implements Model{
	
	Document document;
	
	public XMLModel(String pathName) throws ParserConfigurationException, SAXException, IOException{
		XMLDocumentCreator xmlCreator = new XMLFileParser(pathName);
        document = xmlCreator.getDocument();
	}
	
	public XMLModel(File file) throws ParserConfigurationException, SAXException, IOException{
		XMLDocumentCreator xmlCreator = new XMLFileParser(file);
        document = xmlCreator.getDocument();        
	}


	@Override
	public Collection<String> getCategories() {
		Collection<String> categories = new HashSet<String>();
		Node root = document.getFirstChild();
		NodeList firstChildren = root.getChildNodes();
		for (int i = 0; i < firstChildren.getLength(); i++) {
			Node node = firstChildren.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
			categories.add(node.getNodeName());	
			}
		}
		return categories;
	}

	@Override
	public List<ResourceNode> getResourcesFromCategory(String category) {
		List<ResourceNode> nodes = new ArrayList<ResourceNode>();
		NodeList categoryRoots = document.getElementsByTagName(category);
		for(int i=0; i<categoryRoots.getLength(); i++){
			ResourceNode root = new XMLNode(categoryRoots.item(i));
			nodes.addAll(root.getChildren());
		}
		return nodes;		
	}

	/**
	 * @author anonymous [http://www.exampledepot.com/egs/javax.xml.transform/WriteDom.html]
	 */
	@Override
	public void writeResources(File file) {
		    try {
		        // Prepare the DOM document for writing
		        Source source = new DOMSource(document);

		        // Prepare the output file
		        Result result = new StreamResult(file);

		        // Write the DOM document to the file
		        Transformer xformer = TransformerFactory.newInstance().newTransformer();
		        xformer.transform(source, result);
		    } catch (TransformerConfigurationException e) {
		    } catch (TransformerException e) {
		    }
	}
	
	

}
