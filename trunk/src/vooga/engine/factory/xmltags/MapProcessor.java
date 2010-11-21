//package vooga.engine.factory.xmltags;
//
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import vooga.engine.factory.NodeListProcessor;
//import vooga.engine.factory.MapReader;
//
//public class MapProcessor {
//
//	public NodeList myMaps;
//	
//	public MapProcessor(NodeList nodeList) {
//		myMaps = nodeList;
//	}
//
//
//
//	public void process(){
//		MapReader reader;
//		for(int i = 0; i < myMaps.getLength(); i++)
//		{
//			Node currentNode = myMaps.item(i);
//
//			if(isElement(currentNode))
//			{
//				String path = currentNode.getNodeName();
//				reader = new MapReader(path, myPlayfield);
//				NodeList listOfAssociations = currentNode.getChildNodes();
//				for(int j = 0; j < listOfAssociations.getLength(); j++)
//				{
//					Element association = (Element) listOfAssociations.item(j);
//
//					String key = association.getAttribute("char");
//					String value = association.getAttribute("object");
//
//					reader.addAssociation(key, value);
//				}
//				myPlayfield = reader.processMap();
//			}
//		}
//	}
//	
//	public static boolean isElement(Node node){
//		return (node.getNodeType() == Node.ELEMENT_NODE);
//	}
//	
//	
//	@Override
//	public int getLength() {
//		return 0;
//	}
//
//	@Override
//	public Node item(int index) {
//		return null;
//	}
//	
//}