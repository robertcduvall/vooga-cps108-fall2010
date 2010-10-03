package vooga.engine.player.control;

import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.object.Sprite;

/**
 * PlayerSprite is designed to represent a player in a game. It keeps track of
 * score, lives, health, rank, and an item list, in addition to the features
 * provided by GameEntitySprite, such as a name and the ability to use multiple
 * Sprites at different times to represent this player. PlayerSprites contain an
 * IPlayerController instance variable that can control the movement of the
 * PlayerSprite if an IPlayerController class has been written. We provide an
 * example of a KeyboardController.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 * 
 */

@SuppressWarnings("serial")
public class PlayerSprite extends GameEntitySprite {

    // general instance variables
    private List<ItemSprite> myItemList;
    private int myScore;
    private int myLives;
    private int myHealth;
    private int myRank;
    private IPlayerController myController;

    // constants
    private final int DEFAULT_RANK = 1;
    private final int DEFAULT_SCORE = 0;
    private final int DEFAULT_HEALTH = 10;
    private final int DEFAULT_LIVES = 5;

    /**
     * 
     * @param name is any name you'd like to associate with a player.
     * @param stateName is the name that will be associated with the Sprite
     *            parameter (to switch to it).
     * @param s is a Sprite that will represent this player.
     * @param control is a class that implements the IPlayerController interface
     *            (for controlling player speed and movement).
     */
    public PlayerSprite(String name, String stateName, Sprite s,
            IPlayerController control) {
        super(name, stateName, s);
        myItemList = new ArrayList<ItemSprite>();
        myController = control;
        resetAllStatistics();
    }

    /**
     * 
     * @param name is any name you'd like to associate with a player.
     * @param stateName is the name that will be associated with the Sprite
     *            parameter (to switch to it).
     * @param s is a Sprite that will represent this player.
     * @param control is a class that implements the IPlayerController interface
     *            (for controlling player speed and movement).
     * @param playerHealth is the player's initial health.
     * @param playerRank is the player's initial rank.
     */
    public PlayerSprite(String name, String stateName, Sprite s,
            IPlayerController control, int playerHealth, int playerRank) {
        this(name, stateName, s, control);
        updateHealth(playerHealth);
        updateRank(playerRank);
    }

    /*
     * Checks for any inputs from controller class and updates player's current
     * sprite accordingly.
     */
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        setHorizontalSpeed(getHorizontalSpeed() + myController.deltaVelocityX());
        setVerticalSpeed(getVerticalSpeed() + myController.deltaVelocityY());
        if (this.getY() + myController.deltaY() < this.getBackground()
                .getHeight() && this.getY() + myController.deltaY() > 0) {
            this.move(0, myController.deltaY());
        }
        if (this.getX() + myController.deltaX() < this.getBackground()
                .getWidth() && this.getX() + myController.deltaX() > 0) {
            this.move(myController.deltaX(), 0);
        }
    }

    /**
     * Sets rank to DEFAULT_RANK, health to DEFAULT_HEALTH, score to
     * DEFAULT_SCORE, lives to DEFAULT_LIVES
     */
    public void resetAllStatistics() {
        resetRank();
        resetHealth();
        resetScore();
        resetLives();
    }

    public void resetRank() {
        myRank = DEFAULT_RANK;
    }

    public void resetHealth() {
        myHealth = DEFAULT_HEALTH;
    }

    public void resetLives() {
        myLives = DEFAULT_LIVES;
    }

    public void resetScore() {
        myScore = DEFAULT_SCORE;
    }

    /**
     * Causes the act method of an item to be executed and the item to be
     * removed from the player's list.
     * 
     * @param i is the item to be used.
     */
    public void useItem(ItemSprite i) {
        i.act();
        myItemList.remove(i);
    }

    public int getItemListSize() {
        return myItemList.size();
    }

    public void addItemToList(ItemSprite i) {
        myItemList.add(i);
    }

    public void addItemsToList(List<ItemSprite> list) {
        myItemList.addAll(list);
    }

    public int getScore() {
        return myScore;
    }

    public void updateScore(int i) {
        myScore += i;
    }

    public int getHealth() {
        return myHealth;
    }

    public int getLives() {
        return myLives;
    }

    public void updateLives(int i) {
        myLives += i;
    }

    public void updateHealth(int i) {
        myHealth += i;
    }

    public int getRank() {
        return myRank;
    }

    public void setRank(int k) {
        myRank = k;
    }

    public void updateRank(int i) {
        myRank += i;
    }

}
