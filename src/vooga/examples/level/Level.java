package vooga.examples.level;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import vooga.engine.collision.CollisionManager;
import vooga.engine.core.Sprite;
import vooga.engine.event.EventManager;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;

/**
 * This class is an integration of our original level class with the rest of the API. It is where the main body of the level
 * (or the subgame) should be implemented. 
 * @author Jiaqi Yan 
 *
 */

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
    private CollisionManager collisionManager;
    private EventManager eventManager;
    private GameState gameState;
    private ResourceHandler resourceHandler;
    
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
        //ImageHandler resourceManager = new ImageHandler();
        
    while (fileToBeRead.hasNextLine()) {
        String spriteDetails = fileToBeRead.nextLine();
        Scanner details = new Scanner(spriteDetails);
        details.useDelimiter(", *");
        String imageName = details.next();
        BufferedImage image = (BufferedImage)ResourceHandler.getMapping(imageName);
        
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
     *      Initialize the resources for the game 
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