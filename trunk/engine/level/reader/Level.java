import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import com.golden.gamedev.object.Sprite;


/**
 *@author Bhawana, Cameron, Derek
 *
 * Level class describes a static level, ie, a non-moving game space.
 * This class would be used by the LevelManager class to load the Sprites for a new static level.
 * This class is dependent on the ResourceManager to provide the image required to instantiate
 * the Sprites for any given Level.
 * 
 * Example: The following piece of code would return a Collection of Sprites
 * for the level specified by the File fileName.
 * 
 * @code
 * Level level = new Level(new Scanner(fileName));
 * Collection<Sprite> spritesList = level.getSpritesList();
 * 
 * 
 */

public class Level {
	private Collection<Sprite> mySpritesList; 
	
	public Level(Scanner fileToBeRead)
	{
		loadSprites(fileToBeRead);
		mySpritesList = new ArrayList<Sprite>();
	}
	
	/**
	 * Initializes all the Sprites for a particular level.
	 * The scanner passed in should be
	 * initialized with the text file for that level.
	 * 
	 * @param Scanner which scans from the level initialization file.
	 */
	
	public void loadSprites(Scanner fileToBeRead) {
		ResourceManager resourceManager = new ResourceManager();
		
        while (fileToBeRead.hasNextLine()) {
            String spriteDetails = fileToBeRead.nextLine();
            Scanner details = new Scanner(spriteDetails);
            details.useDelimiter(", *");
            String imageName = details.next();
            BufferedImage image = resourceManager.getImage(imageName);
//			  This line should make use of an outside API: the resource manager
//			  As of this afternoon, we were unable to unify our methods.
//            They are working on making their ResourceManager non-static, so that we will ideally 
//            be able to use it without having to instantiate it in our own package.
            
            double xPosition = details.nextDouble();
            double yPosition = details.nextDouble();
            Sprite sprite = new Sprite(image, xPosition, yPosition);
            mySpritesList.add(sprite);
        }
	}
	
	/**
	 * @return A Collection of Sprites to be used by the Game.
	 */
	
	public Collection<Sprite> getSpritesList()
	{
		return mySpritesList;
	}

}