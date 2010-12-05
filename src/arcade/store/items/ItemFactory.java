package arcade.store.items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;

public class ItemFactory {

	
	public static Map<String, IItemInfo> getAllItems(String directory) {
		Map<String, IItemInfo> allItems = new HashMap<String, IItemInfo>();
		File f = new File(directory);
		File[] files = f.listFiles();
		for(File file : files) {
			ResourceBundle bundle = ResourceBundle.getBundle(file.getPath());
			String[] images = bundle.getString("coverartpath").split(",");
			ArrayList<ImageIcon> imageIcons = new ArrayList<ImageIcon>();
			for(String s : images) {
				imageIcons.add(new ImageIcon(s));
			}
			allItems.put(bundle.getString("gameTitle"), new ItemInfo(bundle.getString("description"),
					bundle.getString("price"),
					bundle.getString("gameTitle"),
					bundle.getString("numberOfBuyer"),
					bundle.getString("rating"),
					imageIcons, bundle.getString("genre")
			));
		}
		return allItems;
	}
	
}
