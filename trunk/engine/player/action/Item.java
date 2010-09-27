package engine.player.action;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Items are objects in the World that cannot be controlled. They can belong to 
 * a Player, or simply be there on their own.
 * 
 * @author Choi, Cue and Hawthorne
 * @version 1.0
 */
public class Item extends Actor {

    private boolean active;
    private String myName;
    private int hitPoints;
    
    /**
     * Basic constructor for the Item class, only a name is specified
     * 
     * @param name The name the item should be initialized with
     */
    public Item(String name) {
        this(name, true, 0);
    }

    /**
     * Constructor for the Item class with a specific location, hitPoints are initialized to 0
     * 
     * @param name The name the item should be initialized with
     * @param isActive Whether or not the item should be active when it is initialized
     * @param hitPoints The amount of hit points a given item should have
     */
    public Item(String name,  boolean isActive, int hitPoints) {
        myName = name;
        active = isActive;
        this.hitPoints = hitPoints;
    }
    
    /**
     * Constructor for the Item class, specifying all values
     * 
     * @param name The name the item should be initialized with
     * @param xcoord The x-position of the item
     * @param ycoord The y-position of the item
     * @param isActive Whether or not the item should be active when it is initialized
     * @param hitPoints The amount of hit points a given item should have
     */

    public Item(String name, int xcoord, int ycoord, boolean isActive, int hitPoints) {
        this(name, isActive, hitPoints);
        setLocation(xcoord, ycoord);
    }
    
    
    /**
     * Toggles an Item's turn.
     */
    public void toggleTurn() {
        active = !active;
    }
    
    /**
     * This method is called by the World every time "Act" or "Run" is pressed.
     * 
     */
    public void act() {
        // put your code here
    }

}
