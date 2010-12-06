package arcade.store.items;

import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;

public class ItemFactory {


	public static Map<String, IItemInfo> getAllItems(String directory) {
		Map<String, IItemInfo> allItems = new HashMap<String, IItemInfo>();
		File f = new File(directory);
		File[] files = f.listFiles();
		for(File file : files) {
			String name = file.getName();
			if(name.contains("properties")) {
				ResourceBundle bundle = ResourceBundle.getBundle("arcade.store.gui.resources.games."+name.substring(0, name.indexOf(".")));
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
		}
		return allItems;
	}

}
