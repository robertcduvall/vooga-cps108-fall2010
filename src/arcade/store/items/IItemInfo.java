package arcade.store.items;

import java.util.List;
import javax.swing.ImageIcon;

/**
 * Defines the methods that an ItemInfo object must have in order
 * for the StoreModel to get the necessary information from it during
 * browsing the store catalog and making purchases.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 *
 */
public interface IItemInfo {

	//the first image is the thumbnail to be used in the store game view
	public final int COVER_IMAGE = 0;
	
	public int getId();
	
	public String getDescription();
	
	public String getPrice();
	
	public String getTitle();
	
	public String getNumberOfBuyer();
		
	public String getGenre();
	
	public List<ImageIcon> getImages(); 
	
	public List<String> getTags();
	
	
}
