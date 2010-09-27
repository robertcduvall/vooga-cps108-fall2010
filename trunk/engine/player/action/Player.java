package engine.player.action;

import java.util.*;
import greenfoot.*;

/**
 * The Player class keeps track of basic information about a player, including its score,
 * remaining lives, whether it is its turn to act, and an inventory of items which it owns
 * 
 * @author Choi, Cue and Hawthorne
 * @version 1.0
 */
public class Player extends Actor {

    private boolean active;
    private String myName;
    private List<Item> myItems;
    private int myScore;
    private int myLife;

    /**
     * Basic constructor for the Player class, constructs a Player that only has a name
     * 
     * @param name The player's name
     */
    public Player(String name) {
        this(name, true, 0);
    }
    
    /**
     * Constructor for the Player class
     * 
     * @param name The player's name
     * @param isActive Whether or not it is the player's turn
     * @param life The number of lives or hit points a player has
     */
    public Player(String name, boolean isActive, int life) {
        myName = name;
        active = isActive;
        myItems = new ArrayList<Item>();
        myScore = 0;
        myLife = life;
    }
    
    /**
     * Toggles a Player's turn.
     */
    public void toggleTurn() {
        active = !active;
    }
    
    /**
     * Uses a specific Item in the player's inventory
     * 
     * @param itemUsed The Item to be used
     */
    private void useItem(Item itemUsed) {
        Item currItem = myItems.remove(myItems.indexOf(itemUsed));
        currItem.toggleTurn();
    }

    /**
     * Adds an item to the player's inventory
     * 
     * @param newItem The item to be added
     */
    private void addItem(Item newItem) {
        myItems.add(newItem);
    }
    
    /**
     * Gets the player's score
     * 
     * @return The players's score
     */
    public int getScore() {
        return myScore; //this method may be changed to the Overlay/Static group's interface to update visible score
    }

    /**
     * Increments the player's score by a certain amount
     * 
     * @param score The amount to change
     */
    public void incrementScore(int score) {
        myScore += score;
    }

    /**
     * Returns the player's current remaining hit points
     * 
     * @return Amount of life player has left
     */
    public int getLife() {
        return myLife; //This method may be changed to the Overlay/Static group's interface to update visible score
    }
    
    /**
     * Adds one to the player's remaining life
     */
    public void addLife() {
        myLife++;
    }
    
    /**
     * Subtracts one from the player's remaining life
     */
    public void reduceLife() {
        myLife--;
    }

    /**
     * Blank method to be overwritten in subclasses
     */
    public void act() {

    }

}
