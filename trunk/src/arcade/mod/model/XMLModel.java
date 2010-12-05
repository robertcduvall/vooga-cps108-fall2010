package arcade.mod.model;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
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
        Document doc = xmlCreator.getDocument();
	}


	@Override
	public Collection<String> getCategories() {
		NodeList firstChildren = document.getChildNodes();
		//TODO extract tag names from firstChildren
		return null;
	}
	
	

}
