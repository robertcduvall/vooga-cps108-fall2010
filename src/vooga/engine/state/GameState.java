package vooga.engine.state;

import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import java.util.*;

import com.golden.gamedev.object.SpriteGroup;

/**
 * GameState is, at its most basic conception, a container class for collections of sprites. Beyond that, it should be used
 * control state-specific behavior that defines those sprites. For example, the mainGameState should include all of the
 * sprites necessary to play the game in both the render and update groups. The mainGameState, when set to active, will then
 * iterate through those sprites and render and update them appropriately. Additional behavior can be called in the gameState'
 * render or update methods to provide high-level functionality (i.e. functionality to be preserved across the entire gameState,
 * not a specific level). 
 * 
 * GameStates show their power most of all when changing states. As an example, take the change from a mainGameState
 * into a pausedGameState. The pausedGameState could be constructed using the mainGameState's sprites as render-only sprites, 
 * effectively "freezing" the action by no longer updating those sprites. A menu could then be added as rendered+updated sprites,
 * displayed over the "frozen" action. Returning to the main game flow is as simple as toggling back to the mainGameState. 
 * 
 * @author VitorOlivier & BrentSodman & BrianSimel
 */
public abstract class GameState implements Comparable<GameState> {

	private boolean myIsActive = false;
	private  ArrayList<SpriteGroup> myRenderGroups = new ArrayList<SpriteGroup>();
	private ArrayList<SpriteGroup> myUpdateGroups = new ArrayList<SpriteGroup>();
	private int myLayer;

	/**
	 * Constructs a new GameState 
	 */
	public GameState() {
		this.initialize();
	}

	/**
	 * Constructs a new GameState using another GameState as the base.
	 * 
	 * @param gamestate
	 */
	public GameState(GameState gamestate) {
		this();
		this.addState(gamestate);
		// this.initialize();
	}

	/**
	 * Creates a new GameState by importing the Sprites from a SpriteGroup
	 * 
	 * @param spritegroup
	 */
	public GameState(SpriteGroup sprites) {
		this();
		this.addGroup(sprites);

	}

	/**
	 * Creates a new GameState with a layer value. Layers are used to render multiple GameStates in a defined order.
	 * 
	 * @param layer
	 */
	public GameState(int layer) {
		this();
		this.setLayer(layer);
	}

	/**
	 * Creates a new GameState from an existing GameState and assigns it a layer value. Layers are used to render multiple 
	 * GameStates in a defined order.
	 * 
	 * @param gamestate
	 * @param layer
	 */
	public GameState(GameState gamestate, int layer) {
		this(gamestate);
		this.setLayer(layer);
	}

	/**
	 * Creates a new GameState from an existing SpriteGroup and assigns it a layer value. Layers are used to render multiple
	 * GameStates in a defined order.
	 * 
	 * @param spritegroup
	 * @param layer
	 */
	public GameState(SpriteGroup sprites, int layer) {
		this(sprites);
		this.setLayer(layer);
	}

	/**
	 * Sets the GameState to active. Active GameStates are rendered and updated in the main game loop.
	 * 
	 */
	public void activate() {
		this.myIsActive = true;
	}

	/**
	 * Sets the GameState to inactive. Inactive GameStates are not rendered and updated in the main game loop.
	 * 
	 */
	public void deactivate() {
		this.myIsActive = false;
	}

	/**
	 * The initialize method sets up specific variables and parameters necessary to the specific functioning 
	 * of the GameState. This should include all of the necessary initialization for the GameState's specific use,
	 * rather than anything broadly required for all GameStates.
	 * 
	 */
	public abstract void initialize();

	/**
	 * Returns the boolean value of the GameState's active variable.
	 * 
	 * @return boolean
	 */
	public boolean isActive() {
		return myIsActive;
	}

	/**
	 * Renders all sprites stored in the GameState's renderGroups.
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {

		for (SpriteGroup s : myRenderGroups) {
			s.render(g);
		}
	}

	/**
	 * Updates all sprites stored in the GameState' updateGroups.
	 * 
	 * @param t
	 */
	public void update(long t) {
		for (SpriteGroup sprite : myUpdateGroups) {
			sprite.update(t);
		}
	}

	/**
	 * Adds a SpriteGroup to the GameState's renderGroups.
	 * 
	 * @param s
	 */
	public void addRenderGroup(SpriteGroup sg) {
		myRenderGroups.add(sg);
	}

	/**
	 * Adds a SpriteGroup to the GameState's updateGroups.
	 * 
	 * @param s
	 */
	public void addUpdateGroups(SpriteGroup sprites) {
		myUpdateGroups.add(sprites);
	}

	/**
	 * Adds a SpriteGroup to the GameState's render and updateGroups.
	 * 
	 * @param s
	 */
	public void addGroup(SpriteGroup sg) {
		this.addRenderGroup(sg);
		this.addUpdateGroups(sg);
	}

	/**
	 * Returns the GameState's renderGroups.
	 * 
	 * @return List<SpriteGroup>
	 */
	public ArrayList<SpriteGroup> getRenderGroups() {
		return myRenderGroups;
	}

	/**
	 * Returns the GameState's updateGroups.
	 * 
	 * @return List<SpriteGroup>
	 */
	public ArrayList<SpriteGroup> getUpdateGroups() {
		return myUpdateGroups;
	}

	/**
	 * Adds the contents of an existing GameState to the current state's renderGroups.
	 * 
	 * @param gamestate
	 */
	public void addRenderState(GameState gamestate) {
		for (SpriteGroup group : gamestate.getRenderGroups()) {
			myRenderGroups.add(group);
		}
	}

	/**
	 * Adds the contents of an existing GameState to the current state's updateGroups.
	 * 
	 * @param gamestate
	 */
	public void addUpdateState(GameState gamestate) {
		for (SpriteGroup group : gamestate.getUpdateGroups()) {
			myUpdateGroups.add(group);
		}
	}

	/**
	 * Adds the contents of an existing GameState to the current state's render and updateGroups.
	 * 
	 * @param gamestate
	 */
	public void addState(GameState gamestate) {
		this.addUpdateState(gamestate);
		this.addRenderState(gamestate);
	}

	/**
	 * Clears the GameState of all contents.
	 * 
	 */
	public void removeAllGroups() {
		myRenderGroups.clear();
		myUpdateGroups.clear();
	}


	/**
	 * Sets the current GameState's layer value. Layers are used to render multiple GameStates in a defined order.
	 * 
	 * @param layer
	 */
	public void setLayer(int layer) {
		this.myLayer = layer;
	}

	/**
	 * Returns the current GameState's layer value.
	 * 
	 * @return GameState's layer
	 */
	public int getLayer() {
		return myLayer;
	}

	/**
	 * Compare function for GameStates. Tests the value of GameStates' layers against each other.
	 * 
	 * @param gamestate
	 * @return int
	 */
	@Override
	public int compareTo(GameState gamestate) {
		return this.getLayer() - gamestate.getLayer();
	}
	
	/**
	 * Equals function for GameStates
	 * 
	 * @param Object
	 * @return boolean
	 */
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		if (this.myLayer == ((GameState) obj).getLayer() &&
				this.myIsActive == ((GameState) obj).isActive() &&
				this.myRenderGroups.hashCode() == ((GameState) obj).getRenderGroups().hashCode() &&
				this.myUpdateGroups.hashCode() == ((GameState) obj).getUpdateGroups().hashCode()) {
			return true;
		}
		return false;
	}

	/**
	 * Hash coding for GameStates
	 * 
	 * @return int
	 */

	public int hashCode(){
		final int PRIME_NUMBER = 7;
		
		int code = (PRIME_NUMBER * myRenderGroups.hashCode()); 
		code += PRIME_NUMBER * myUpdateGroups.hashCode();
		code += PRIME_NUMBER * myLayer;
		
		return code;
	}

}
