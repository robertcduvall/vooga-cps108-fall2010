package vooga.engine.level;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.collision.CollisionManager;
//import vooga.engine.core.Sprite;
import vooga.engine.event.EventManager;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * This class is an integration of our original level class with the rest of the API. It is where the main body of the level
 * (or the subgame) should be implemented. 
 * @author Jiaqi Yan 
 *
 */

/**
 *@author Bhawana, Cameron, Derek, Jaiqi
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
	protected ArrayList<Sprite> mySpritesList; 
	private CollisionManager collisionManager;
	private EventManager eventManager;
	private GameState gameState;
	private ResourceHandler resourceHandler;
	
	public Level(String fileToBeRead, boolean isScroller)
	{
		System.out.println("reached Level constructor");
		mySpritesList = new ArrayList<Sprite>();
		if (!isScroller) {
			try {
				System.out.println("trying try block in Level constrcutor");
				loadLevel(fileToBeRead);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Initializes all the Sprites for a particular level.
	 * The scanner passed in should be
	 * initialized with the text file for that level.
	 * 
	 * @param Scanner which scans from the level initialization file.
	 * @throws IOException 
	 */
	
	public void loadLevel(String fileToBeRead) throws IOException {

		//while (fileToBeRead.hasNextLine()) {
		//            String spriteDetails = fileToBeRead.nextLine();
		//            Scanner details = new Scanner(spriteDetails);
		//            details.useDelimiter(", *");
		//            String imageName = details.next();
		//            BufferedImage image = ResourceHandler.getImage(imageName);
		//            
		//            double xPosition = details.nextDouble();
		//            double yPosition = details.nextDouble();
		//            Sprite sprite = new Sprite(image, xPosition, yPosition);
		//            mySpritesList.add(sprite);

		ArrayList<String> lines = new ArrayList<String>();
		int size = 0;
		StringTokenizer st;
		URL url = Resources.class.getClassLoader().getResource(fileToBeRead);
		try {
			url = new File(fileToBeRead).toURI().toURL();
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL");
		}
		System.out.println(url);
		if (url == null) {
			throw new IOException("No such directory: " + fileToBeRead);
		}
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}
			if (!line.equals("") && !line.startsWith("#")) {
				lines.add(line);
			}
		}
		size = lines.size();
		for (int y=0; y<size; y++) {
			String line = lines.get(y);
//			if (line.equals("$Sprites")) {
//				animatedSpriteMode = false;
//				y++;
//				line = lines.get(y);
//			}
//			if (line.equals("$AnimatedSprites")) {
//				animatedSpriteMode = true;
//				y++;
//				line = lines.get(y);
//			}
			st = new StringTokenizer(line, ",");
			String spriteName = st.nextToken();
			double xPosition = Double.parseDouble(st.nextToken());
			double yPosition = Double.parseDouble(st.nextToken());
//			if (animatedSpriteMode) {
			Sprite newAnimatedSprite = new AnimatedSprite(Resources.getAnimation(spriteName), xPosition, yPosition);
			mySpritesList.add(newAnimatedSprite);
//			} else {
//				AnimatedSprite newSprite = new AnimatedSprite(Resources.getAnimation(spriteName), xPosition, yPosition);
//			
//			}
		}
	}
	
	/**
	 * @return A Collection of Sprites to be used by the Game.
	 */
	
	public ArrayList<ArrayList<Sprite>> getSpritesList()
	{
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		returnCollection.add(mySpritesList);
		return returnCollection;
	}
	
	/** 
	 * @return the game State of the level 
	 */
	public GameState getLevelState(){
		return gameState;
	}
	/** 
	 * @return the event manager of the level 
	 */
	public EventManager getEvents(){
		return eventManager;
	}
	/** 
	 * @return the Collision Manager of the level 
	 */
	public CollisionManager getCollisionManager(){
		return collisionManager;
	}
	/** 
	 * @return the resource handler of the level 
	 */
	public ResourceHandler getResourceHandler(){
		return resourceHandler;
	}
	
	/**
	 * Initialize the collision manager
	 */
	public void setCollision(){
	}
	/**
	 * Initialize the collision manager to cm;
	 * @param cm
	 */
	public void setCollision(CollisionManager cm){
		collisionManager = cm;
	}
	/**
	 * Initialize the gameState objects 
	 */
	public void setGameState(){
				
	}
	/**
	 * Initialize the Game State to gs
	 * @param gs
	 */
	public void setGameState(GameState gs){
		gameState = gs;
	}
	/**
	 * Initialize the event manager
	 */
	public void setEvents(){
	}
	/**
	 * Initialize the Event Manager to em
	 * @param em
	 */
	public void setEvents(EventManager em){
		eventManager = em;
	}
	/**
	 *	Initialize the resources for the game 
	 */
	public void setResources(){
	}
	/**
	 * Initialize the Resource Handler to rh
	 * @param rh
	 */
	public void setResources(ResourceHandler rh){
		resourceHandler = rh;
	}		

}
