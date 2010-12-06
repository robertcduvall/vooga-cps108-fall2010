package arcade.mod.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode implements ResourceNode{
	
	public static final String DESCRIPTION_TAG = "Description";
	Node node;
	
	public XMLNode(Node node){
		this.node = node;
	}
	
	public List<ResourceNode> getChildren(){
		return wrapNodeList(node.getChildNodes());
	}
	
	public Collection<String> getAttributes(){
		Collection<String> attributeNames = new ArrayList<String>();
		NamedNodeMap attributeNodeList = node.getAttributes();
		for(int i=0; i<attributeNodeList.getLength(); i++){
			Node attribute  = attributeNodeList.item(i);
			attributeNames.add(attribute.getNodeName());
		}
		return attributeNames;
	}
	
	
	private List<ResourceNode> wrapNodeList(NodeList nodeList){
		List<ResourceNode> resourceNodes = new ArrayList<ResourceNode>();
		for(int i=0; i<nodeList.getLength(); i++){
			
			if ( nodeList.item(i).getNodeType() == Node.ELEMENT_NODE ){
			resourceNodes.add(new XMLNode(nodeList.item(i)));
			}
		}
		
		return resourceNodes;
	}

	@Override
	public String getAttribute(String attributeName) {
		NamedNodeMap attributeNodeList = node.getAttributes();
		Node attribute = attributeNodeList.getNamedItem(attributeName);
		return attribute.getNodeValue();
	}

	@Override
	public void setAttribute(String attributeName, String value) {
		NamedNodeMap attributeNodeList = node.getAttributes();
		Node attribute = attributeNodeList.getNamedItem(attributeName);
		attribute.setNodeValue(value);
	}

	@Override
	public String getDescription() {
		Node child = node.getFirstChild();
		while(child!= null && child.getNodeName().equalsIgnoreCase(DESCRIPTION_TAG)){
			child = child.getNextSibling();
		}
		return child.getTextContent();
	}

}