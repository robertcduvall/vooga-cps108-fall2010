package arcade.store.items;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

public interface IItemInfo {

	public final int COVER_IMAGE = 0;
	
	public String getDescription();
	
	public String getPrice();
	
	public String getTitle();
	
	public String getNumberOfBuyer();
		
	public String getGenre();
	
	public List<ImageIcon> getImages(); 
	
	public List<String> getTags();
	
	
}
