//package vooga.engine.factory.xmltags;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import vooga.engine.factory.NodeListProcessor;
//
//public class SpriteGroupsProcessor extends NodeListProcessor {
//
//	public SpriteGroupsProcessor(Document xmlDocument, NodeList children) {
//		super(xmlDocument, children);
//	}
//	
//	@Override
//	public int getLength() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Node item(int index) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void process() {
//		for (int i = 0; i < myChildren.getLength(); i++) {
//			if (isElement(myChildren.item(i))) {
//				Element spriteGroupElement = (Element)myChildren.item(i);
//				if (spriteGroupElement.getTagName().equals("SpriteGroup")) {
//					SpriteGroupProcessor spriteGroupProcessor = 
//						new SpriteGroupProcessor(myXMLDocument, 
//								spriteGroupElement.getChildNodes(),
//								spriteGroupElement.getAttribute("name"));
//						spriteGroupProcessor.process();
//				}
//			}
//		}
//	}
//
//}

