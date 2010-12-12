package arcade.mod.view.frame;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLNode;

import com.golden.gamedev.util.ImageUtil;

public class ImageListFrame extends FilepathListFrame {

	private static final int ICON_SIZE = 128;
	ImageIcon myIcon;
	private static String[] acceptableImageTags = {"jpg","gif","png","bmp","tif"};

	public ImageListFrame() {
		// do nothing
	}

	public ImageListFrame(IResourceNode node) {
		super(node);
		restrictSize(ICON_SIZE + 100);
		
	}

		public ImageListFrame newInstance(IResourceNode node) {
		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
		return new ImageListFrame(node);
		else return new ImageListFrame();
	}

	@Override
	public void handleFileChange() {
		
		try {
			myStringLabel.setText(myFilepath);
						
			myIcon = new ImageIcon(ImageUtil.resize(ImageIO.read(new File(myFilepath)), ICON_SIZE, ICON_SIZE));
			
			myStringLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			myStringLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			
			myStringLabel.setIcon(myIcon);

			this.validate();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void handleNode(IResourceNode node) {
		myName = node.getAttribute("name");
		myFilepath = node.getModelPath() + File.separatorChar + "images" + File.separatorChar + node.getAttribute("path");

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