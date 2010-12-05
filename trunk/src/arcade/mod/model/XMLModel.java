package arcade.mod.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

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
		NodeList firstChildren = document.getChildNodes();
		for (int i = 0; i < firstChildren.getLength(); i++) {
			Node node = firstChildren.item(i);
			categories.add(node.getLocalName());			
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
	
	

}
