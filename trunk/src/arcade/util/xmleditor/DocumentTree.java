package arcade.util.xmleditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class DocumentTree extends JTree{
	

	private static final long serialVersionUID = 1L;
	
	private Document document;
	private Element root;
	
	public DocumentTree(){
	}
	
	public DocumentTree(File file){
       setReadFile(file);
	}
	
	public void setReadFile(File file){
		 try {
	        	XMLDocumentCreator xmlCreator = new XMLFileParser(file);
				document = xmlCreator.getDocument();
				root = (Element) document.getFirstChild();
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
	}
	
	public Element getRoot(){
		return root;
	}
	
	public List<Element> getChildrenElements(Element parent){
		return convertNodeListToListOfElements(parent.getChildNodes());
	}
	
	public List<Element> getElementsByTagName(String name){
		return convertNodeListToListOfElements(document.getElementsByTagName(name));
	}
	
	private List<Element> convertNodeListToListOfElements(NodeList nodeList){
		List<Element> elementList = new ArrayList<Element>();
		for(int i=0; i<nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				elementList.add((Element) node);
			}
		}
		return elementList;
	}

}
