package arcade.mod.view;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLNode;

import com.golden.gamedev.util.ImageUtil;

public class ImageListFrame extends FilepathListFrame {

	private static final int ICON_SIZE = 128;
	ImageIcon myIcon;
	private static Collection<String> acceptableImageTags = new HashSet<String>();

	public ImageListFrame() {
		// do nothing
	}

	public ImageListFrame(IResourceNode node) {
		super(node);
		
		acceptableImageTags.add("jpg");
		acceptableImageTags.add("gif");
	}
	

	public ImageListFrame newInstance(IResourceNode node) {
		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
		return new ImageListFrame(node);
		else return new ImageListFrame();
	}

	@Override
	public void handleFileChange() {
		
		try {
			stringLabel.setText(myFilepath);
			
			myIcon = new ImageIcon(ImageUtil.resize(ImageIO.read(new File(myFilepath)), ICON_SIZE, ICON_SIZE));

			restrictSize(myIcon.getIconHeight() + 100);
			
			stringLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			stringLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			
			stringLabel.setIcon(myIcon);

			this.validate();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void handleNode(IResourceNode node) {
		myName = node.getAttribute("name");
		myFilepath = node.getModelPath() + "\\images\\" + node.getAttribute("path");
		myDescription = node.getDescription();
	}
	
	@Override
	public boolean confirmValidity(){
		if (endsWithImageTag()){
			return true;
		}
		return false;
	}

	private boolean endsWithImageTag() {
		for (String tag: acceptableImageTags)
		{
			if (myFilepath.endsWith(tag))
				return true;
		}
		return false;
	}

}
