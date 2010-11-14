package vooga.engine.util;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Creates a Document following the Document 
 * Object Model (DOM) of representing an XML file. The XML 
 * elements are represented as nodes on a tree represented by 
 * the Document.
 * 
 * @author Daniel Koverman
 *
 */
public interface XMLDocumentCreator {
	
	/**
	 * Produce a Document from some XML source
	 * @return Document representing XML source
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document getDocument() throws ParserConfigurationException, SAXException, IOException;

}
