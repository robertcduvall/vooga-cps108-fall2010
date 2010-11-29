package vooga.games.towerdefense.levelparse;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Element;

import vooga.engine.resource.Resources;
import vooga.widget.levelparse.modules.BackgroundModule;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

public class ImageBackgroundModule extends BackgroundModule {

	@Override
	public Collection<Background> getBackgrounds(Element backgroundElement) {
		Collection<Background> backgrounds = new ArrayList<Background>();
		List<BufferedImage> imageList = processVisual(backgroundElement.getElementsByTagName("Visual"));
		String widthKey = backgroundElement.getAttribute("widthKey");
		String heightKey = backgroundElement.getAttribute("heightKey");
		for(BufferedImage image: imageList){
			ImageUtil.resize(image, Resources.getInt(widthKey), Resources.getInt(heightKey));
			backgrounds.add(new ImageBackground(image));
		}
		return backgrounds;
	}

}
