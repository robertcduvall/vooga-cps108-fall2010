//package vooga.engine.factory.xmltags;
//
//import java.lang.reflect.Constructor;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import vooga.engine.core.BetterSprite;
//import vooga.engine.factory.NodeListProcessor;
//import vooga.engine.overlay.Stat;
//
///**
// * 
// * Separates RegularSprites from SpawnedSprites.
// * 
// * @author Cameron McCallie
// *
// */
//
//public class SpriteProcessor extends NodeListProcessor{
//
//	public SpriteProcessor(Document xmlDocument, NodeList children) {
//		super(xmlDocument, children);
//	}
//
//	public void process(){
//		BetterSprite newSprite = null;
//		for(int i = 0; i < myChildren.getLength(); i++)
//		{
//			if (isElement(myChildren.item(i))) {
//				Element currentElement = (Element) myChildren.item(i); 
//				if (currentElement.getTagName().equals("Sprite")) {
//					String className = currentElement.getAttribute("class");
//					try {
//						Class userSprite = Class.forName(className);
//						Constructor classConstructor = userSprite.getConstructor();
//						Object returnObject = classConstructor.newInstance();
//						newSprite = (BetterSprite)returnObject;
//					} catch (Throwable e){
//						e.printStackTrace();
//					}
//	
//					if (!Boolean.parseBoolean(currentElement.getAttribute("random"))) {
//						double x = Double.parseDouble(currentElement.getAttribute("x"));
//						double y = Double.parseDouble(currentElement.getAttribute("y"));
//						double vx = Double.parseDouble(currentElement.getAttribute("vx"));
//						double vy = Double.parseDouble(currentElement.getAttribute("vy"));
//		
//						int quantity = Integer.parseInt(currentElement.getAttribute("quantity"));
//						for(int j = 0; j < quantity; j++) {
//							newSprite.setLocation(x, y);
//							newSprite.setHorizontalSpeed(vx);
//							newSprite.setVerticalSpeed(vy);	
//							newSpriteGroup.add(newSprite);
//						}
//					} else if (Boolean.parseBoolean(currentElement.getAttribute("random"))) {
//						double xMin = Double.parseDouble(currentElement.getAttribute("xMin"));
//						double yMin = Double.parseDouble(currentElement.getAttribute("yMin"));
//						double vxMin = Double.parseDouble(currentElement.getAttribute("vxMin"));
//						double vyMin = Double.parseDouble(currentElement.getAttribute("vyMin"));
//						double xMax = Double.parseDouble(currentElement.getAttribute("xMax"));
//						double yMax = Double.parseDouble(currentElement.getAttribute("yMax"));
//						double vxMax = Double.parseDouble(currentElement.getAttribute("vxMax"));
//						double vyMax = Double.parseDouble(currentElement.getAttribute("vyMax"));
//						
//						int quantity = Integer.parseInt(currentElement.getAttribute("quantity"));
//						for(int j = 0; j < quantity; j++) {
//							newSprite.setLocation(Math.random()*(xMax-xMin) + xMin,
//												  Math.random()*(yMax-yMin) + yMin);
//							newSprite.setHorizontalSpeed(Math.random()*(vxMax-vxMin) + vxMin);
//							newSprite.setVerticalSpeed(Math.random()*(vyMax-vyMin) + vyMin);	
//							newSpriteGroup.add(newSprite);
//						}
//					}
//				}
//			}
//		}
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
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
