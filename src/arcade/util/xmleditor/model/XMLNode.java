package arcade.util.xmleditor.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Follows the adapter design pattern allowing an Element to be treated 
 * as a TreeNode and adds functionality for notifying observers. 
 * Wraps an element in an extension of the DefaultMutableTreeNode class.
 * Forms the basis of the model of the XML document being edited. The 
 * underlying element is never accessed directly.
 * 
 * Since this program is designed with only the purpose of editing XMLDocuments,
 * this class was not abstracted with an interface. This implementation takes 
 * care of all the basic cases of Element alteration. Ideally, however, this would be 
 * shielded with an interface to allow for different implementations that may 
 * require more complex behind the scene behavior.
 * 
 * @author Daniel Koverman, Brent Sodman
 *
 */
public class XMLNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;

	private XMLNode parent;
	private Element element;
	private ModelObserver observer;
	private List<XMLNode> children;

	public XMLNode(Element element, XMLNode parent, ModelObserver observer) {
		this.parent = parent;
		this.element = element;
		this.observer = observer;
		children = convertNodeList(element.getChildNodes());
	}

	@Override
	public Enumeration children() {
		return new Enumerator(children.iterator());
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		return children.get(arg0);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode arg0) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).equals(arg0)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return children.size() == 0;
	}

	@Override
	public String toString() {
		return element.getTagName();
	}

	private List<XMLNode> convertNodeList(NodeList nodeList) {
		List<XMLNode> elementList = new ArrayList<XMLNode>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				elementList.add(new XMLNode((Element) node, this, observer));
			}
		}
		return elementList;
	}

	private class Enumerator implements Enumeration {

		Iterator iterator;

		public Enumerator(Iterator iterator) {
			this.iterator = iterator;
		}

		@Override
		public boolean hasMoreElements() {
			return iterator.hasNext();
		}

		@Override
		public Object nextElement() {
			return iterator.next();
		}

	}

	public void addAttribute(String name) {
		element.setAttribute(name, "");
		checkYourself();
	}
	
	public Document getDocument(){
		return element.getOwnerDocument();
	}
	
	public void appendChild(Element child){
		element.appendChild(child);
		checkYourself();
	}

	public void orphanSelf() {
		element.getParentNode().removeChild(element);
		parent.checkYourself();
	}

	public NamedNodeMap getAttributes() {
		return element.getAttributes();
	}

	public ModelObserver getObserver() {
		return observer;
	}

	public String getName() {
		return element.getNodeName();
	}

	/**
	 * Refreshes the list of children so that traversal 
	 * of this tree finds the most current list of children.
	 * Notifies observers that changes may have occurred. Does 
	 * the same for all children which may have been altered 
	 * incidentally. 
	 */
	public void checkYourself() {
		children = convertNodeList(element.getChildNodes());
		for (XMLNode child : children) {
			child.checkYourself();
		}
		observer.notifyNodeUpdated(this);

	}

}
