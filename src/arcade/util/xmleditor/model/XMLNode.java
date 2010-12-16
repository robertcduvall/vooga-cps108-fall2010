package arcade.util.xmleditor.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode extends DefaultMutableTreeNode{
	
	XMLNode parent;
	Element element;
	ModelObserver observer;
	List<XMLNode> children;
	
	public XMLNode(Element element, XMLNode parent, ModelObserver observer){
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
		for(int i=0; i<children.size(); i++){
			if(children.get(i).equals(arg0)){
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
		return children.size()==0;
	}
	
	@Override
	public String toString(){
		return element.getTagName();
	}
	
	private List<XMLNode> convertNodeList(NodeList nodeList){
		List<XMLNode> elementList = new ArrayList<XMLNode>();
		for(int i=0; i<nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				elementList.add(new XMLNode((Element) node, this, observer));
			}
		}
		return elementList;
	}
	
	public Element getElement(){
		return element;
	}
	
	private class Enumerator implements Enumeration{
		
		Iterator iterator;
		
		public Enumerator(Iterator iterator){
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
	
	public void addAttribute(String name){
		element.setAttribute(name, "");
		checkYourself();
	}
	
	public void appendChild(XMLNode node){
		element.appendChild(node.getElement());
		checkYourself();
	}
	
	public void orphanSelf(){
		element.getParentNode().removeChild(element);
		parent.checkYourself();
	}
	
	public NamedNodeMap getAttributes(){
		return element.getAttributes();
	}
	
	public ModelObserver getObserver(){
		return observer;
	}
	
	public void checkYourself(){
		children = convertNodeList(element.getChildNodes());
		for(XMLNode child: children){
			child.checkYourself();
		}
		observer.notifyNodeUpdated(this);

	}
	
	public XMLNode getRoot(){
		if(parent==null){
			return this;
		} else{
			return parent.getRoot();
		}
	}

}
