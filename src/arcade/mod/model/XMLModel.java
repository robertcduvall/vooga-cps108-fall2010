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

/**
 * Model created from an XML file. The 
 * ResourceNodes are created from the Elements 
 * of the DOM parsed Document.
 * 
 * @author Daniel Koverman
 *
 */
public class XMLModel implements Model{
	
	Document document;
	String documentFilepath;
	
	/**
	 * Creates a new instance of XMLModel from a filePath
	 * @param pathName of the file from which to create the new XMLModel
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public XMLModel(String pathName) throws ParserConfigurationException, SAXException, IOException{
		documentFilepath = pathName;
		
		XMLDocumentCreator xmlCreator = new XMLFileParser(pathName);
        document = xmlCreator.getDocument();
	}
	
	/**
	 * Creates a new instance of XMLModel from a given file
	 * @param file from which to create the new XMLModel
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public XMLModel(File file) throws ParserConfigurationException, SAXException, IOException{
		documentFilepath = file.getParent();
			
		XMLDocumentCreator xmlCreator = new XMLFileParser(file);
        document = xmlCreator.getDocument();        
	}

	/**
	 * Returns a collection of strings which are all available categories in the XML file
	 * @return a collection of strings which are all available categories in the XML file
	 */
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

	/**
	 * Return the ResourceNodes that exist as children of a category
	 * @param string category which is the category from which to draw children
	 * @return List<ResourceNode> children within a specified category
	 */
	@Override
	public List<ResourceNode> getResourcesFromCategory(String category) {
		List<ResourceNode> nodes = new ArrayList<ResourceNode>();
		NodeList categoryRoots = document.getElementsByTagName(category);
		for(int i=0; i<categoryRoots.getLength(); i++){
			ResourceNode root = new XMLNode(categoryRoots.item(i), documentFilepath);
			nodes.addAll(root.getChildren());
		}
		return nodes;		
	}

	/**
	 * Write resources to a specified file
	 * @param File on which to write resources
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
