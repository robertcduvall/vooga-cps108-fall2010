package vooga.games.digger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class EntityMap {
	
	private static Map<EntityKey, Class> entities = null;
	
	public static void inititalize() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException{
		entities = new HashMap<EntityKey, Class>();
		File file = new File("entityMap.xml");
	    XMLDocumentCreator xmlCreator = new XMLFileParser(file);
		Document document = xmlCreator.getDocument();
		
		NodeList mappingList = document.getElementsByTagName("Mapping");
		for(int i=0; i<mappingList.getLength(); i++){
			Node node = mappingList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element) node;
				Class callingClass = Class.forName(element.getAttribute("callingClass"));
				Class requestedClass = Class.forName(element.getAttribute("requestedClass"));
				String modName = element.getAttribute("modName");
				Class resolveTo = Class.forName(element.getAttribute("resolveTo"));
				EntityKey key = new EntityKey(callingClass, requestedClass, modName);
				entities.put(key, resolveTo);
			}
		}
	}
	
	
	public static Object resolve(EntityKey key) throws InstantiationException, IllegalAccessException{
		System.out.println(entities==null);
		Class resolvedClass = entities.get(key);
		return resolvedClass.newInstance();
	}

}
