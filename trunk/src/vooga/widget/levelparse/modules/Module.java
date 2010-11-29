package vooga.widget.levelparse.modules;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

public abstract class Module {
	
	private OverlayTracker overlayTracker;
	private EventPool eventPool;

	
	public void setOverlayTracker(OverlayTracker overlayTracker){
		this.overlayTracker = overlayTracker;
	}
	
	public OverlayTracker getOverlayTracker(){
		return overlayTracker;
	}
	
	public void setEventPool(EventPool eventPool){
		this.eventPool = eventPool;
	}
	
	public EventPool getEventPool(){
		return eventPool;
	}

	
	public List<BufferedImage> processVisual(NodeList visualsList) {
		List<BufferedImage> imageList = new ArrayList<BufferedImage>();
		for (int imageLocation = 0; imageLocation < visualsList.getLength(); imageLocation++) {
			if (LevelParser.isElement(visualsList.item(imageLocation))) {
				Element visualElement = (Element) visualsList
						.item(imageLocation);
				String imageName = visualElement.getAttribute("name");
				BufferedImage image = Resources.getImage(imageName);				
				imageList.add( image);
			}
		}
		return imageList;
	}
}
