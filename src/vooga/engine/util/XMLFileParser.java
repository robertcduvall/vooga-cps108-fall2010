package vooga.engine.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Creates a Document directly from an XML file. Elements within the XML file
 * are represented by nodes in a tree data structure. All elements in the XML
 * document will be loaded into working memory so consider file size when using
 * this parser. Generally, this will be the XML parser used by VOOGA.
 * 
 * Can be initialized without a file, but then the file must be set before
 * attempting to parse anything. Generally it is safer to initialize with a
 * file.
 * 
 * Example code for parsing a Document using a file path:
 * <pre>XMLDocumentCreator xmlCreator = new XMLFileParser(defaultPath + resourcesXMLFilepath);
 * Document doc = xmlCreator.getDocument();
 * </pre>
 * 
 * @author Daniel Koverman
 * 
 */
public class XMLFileParser implements XMLDocumentCreator {

	private File xmlFile;

	/**
	 * Create without reference to a file. Must set a file before parsing using
	 * set methods.
	 */
	public XMLFileParser() {
	}

	/**
	 * Create with a reference to a file to be parsed
	 * 
	 * @param xmlFileLocation
	 *            String representing the file path to XML file to be parsed
	 */
	public XMLFileParser(String xmlFileLocation) {
		setParseFile(xmlFileLocation);
	}

	/**
	 * Create with a reference to a file to be parsed
	 * 
	 * @param xmlFile
	 *            XML file to be parsed
	 */
	public XMLFileParser(File xmlFile) {
		setParseFile(xmlFile);
	}

	/**
	 * Set reference to file to be parsed
	 * 
	 * @param xmlFileLocation
	 *            String representing the file path to XML file to be parsed
	 */
	public void setParseFile(String xmlFileLocation) {
		setParseFile(new File(xmlFileLocation));
	}

	/**
	 * Set reference to file to be parsed
	 * 
	 * @param xmlFileLocation
	 *            XML file to be parsed
	 */
	public void setParseFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	/**
	 * Create a DOM Document from the XML file. Automatically normalized.
	 */
	public Document getDocument() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document doc = db.parse(xmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}

}
