package arcade.mod.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Adapter to make a Node from an 
 * XML Document act as a ResourceNode. The 
 * children are the nested elements, the attributes 
 * are the attributes, the Description is the text held
 * in the child named "Description"
 * 
 * @author Daniel Koverman
 *
 */
public class XMLNode implements ResourceNode {

	private static final String NO_DESCRIPTION = "No description";
	public static final String DESCRIPTION_TAG = "Description";
	private Node node;
	
	private String documentFilepath = "";

	/**
	 * Creates a new XMLNode object from a node in the XMLFile
	 * @param node
	 */
	public XMLNode(Node node) {
		this.node = node;
	}
	
	/**
	 * Creates a new XMLNode object including the filepath of the XML document. Used for relative/absolute filepath correction.
	 */
	public XMLNode(Node node, String documentpath) {
		this.node = node;
		this.documentFilepath = documentpath;
	}

	/**
	 * Get this XMLNode's children
	 * @return a list of this XMLNode's children
	 */
	public List<ResourceNode> getChildren() {
		return wrapNodeList(node.getChildNodes());
	}

	/**
	 * Get all of the attributes of this XMLNode
	 * @return all of the attributes of this XMLNode
	 */
	public Collection<String> getAttributes() {
		Collection<String> attributeNames = new ArrayList<String>();
		NamedNodeMap attributeNodeList = node.getAttributes();
		for (int i = 0; i < attributeNodeList.getLength(); i++) {
			Node attribute = attributeNodeList.item(i);
			attributeNames.add(attribute.getNodeName());
		}

		return attributeNames;
	}

	private List<ResourceNode> wrapNodeList(NodeList nodeList) {
		List<ResourceNode> resourceNodes = new ArrayList<ResourceNode>();
		for (int i = 0; i < nodeList.getLength(); i++) {

			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				resourceNodes.add(new XMLNode(nodeList.item(i), documentFilepath));
			}
		}

		return resourceNodes;
	}

	/**
	 * Get a specified attribute out of the XML
	 * @return string attribute
	 */
	@Override
	public String getAttribute(String attributeName) {
		NamedNodeMap attributeNodeList = node.getAttributes();

		Node attribute = attributeNodeList.getNamedItem(attributeName);
		if (attribute != null)
			return attribute.getNodeValue();
		else
			return "";
	}

	/**
	 * Set an attribute to a value
	 * @param attribute to set
	 * @param value to set attribute to
	 */
	@Override
	public void setAttribute(String attributeName, String value) {
		NamedNodeMap attributeNodeList = node.getAttributes();
		Node attribute = attributeNodeList.getNamedItem(attributeName);
		attribute.setNodeValue(value);
	}

	/**
	 * Get description of this XMLNode
	 * @return description of the element node
	 */
	@Override
	public String getDescription() {

		Node child = node.getFirstChild();
		while (child != null
				&& !child.getNodeName().equalsIgnoreCase(DESCRIPTION_TAG)) {
			child = child.getNextSibling();
		}
		if (child != null) {
			return child.getTextContent();
		}
		else
			return NO_DESCRIPTION;
	}
	
	public String getModelPath() {
		return documentFilepath;
	}

	/**
	 * Get this node in tree
	 * @return node
	 */
	@Override
	public Node getNode() {
		return node;
	}

}
