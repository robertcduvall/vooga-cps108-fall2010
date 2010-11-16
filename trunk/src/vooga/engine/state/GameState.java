package vooga.engine.state;

import java.awt.Graphics2D;

import java.util.*;

import vooga.engine.core.PlayField;

import com.golden.gamedev.object.SpriteGroup;

/**
 * GameState is, at its most basic conception, a container class for collections
 * of sprites. Beyond that, it should be used control state-specific behavior
 * that defines those sprites. For example, the mainGameState should include all
 * of the sprites necessary to play the game in both the render and update
 * groups. The mainGameState, when set to active, will then iterate through
 * those sprites and render and update them appropriately. Additional behavior
 * can be called in the gameState' render or update methods to provide
 * high-level functionality (i.e. functionality to be preserved across the
 * entire gameState, not a specific level).
 * 
 * GameStates show their power most of all when changing states. As an example,
 * take the change from a mainGameState into a pausedGameState. The
 * pausedGameState could be constructed using the mainGameState's sprites as
 * render-only sprites, effectively "freezing" the action by no longer updating
 * those sprites. A menu could then be added as rendered+updated sprites,
 * displayed over the "frozen" action. Returning to the main game flow is as
 * simple as toggling back to the mainGameState.
 * 
 * @author VitorOlivier & BrentSodman & BrianSimel
 */
public abstract class GameState {

	private boolean myIsActive = false;
	protected Collection<PlayField> myRenderField = new ArrayList<PlayField>();
	protected Collection<PlayField> myUpdateField = new ArrayList<PlayField>();

	/**
	 * Constructs a new GameState play
	 */
	public GameState() {

	}

	/**
	 * Constructs a new GameState using another GameState as the base.
	 * 
	 * @param gamestate
	 */
	public GameState(GameState gamestate) {

		this.addState(gamestate);

	}

	/**
	 * Creates a new GameState by importing the Sprites from a SpriteGroup
	 * 
	 * @param spritegroup
	 */
	public GameState(SpriteGroup sprites) {

		// this.addGroup(sprites);

	}

	/**
	 * Creates a new GameState from a Playfield by placing all SpriteGroups in
	 * render and update groups.
	 * 
	 * @param playfield
	 */
	public GameState(PlayField playfield) {
		this();
		addPlayField(playfield);
		// SpriteGroup[] groups = playfield.getGroups();
		// for (SpriteGroup group : groups) {
		// this.addGroup(group);
		// }
	}

	/**
	 * Sets the GameState to active. Active GameStates are rendered and updated
	 * in the main game loop.
	 * 
	 */
	public void activate() {
		this.myIsActive = true;
	}

	/**
	 * Sets the GameState to inactive. Inactive GameStates are not rendered and
	 * updated in the main game loop.
	 * 
	 */
	public void deactivate() {
		this.myIsActive = false;
	}

	/**
	 * The initialize method sets up specific variables and parameters necessary
	 * to the specific functioning of the GameState. This should include all of
	 * the necessary initialization for the GameState's specific use, rather
	 * than anything broadly required for all GameStates.
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
		for (PlayField playfield : myRenderField) {
			playfield.render(g);
		}
	}

	/**
	 * Updates all sprites stored in the GameState' updateGroups.
	 * 
	 * @param t
	 */
	public void update(long t) {

		for (PlayField playfield : myUpdateField) {
			playfield.update(t);
		}

	}

	/**
	 * Adds a SpriteGroup to the GameState's renderGroups.
	 * 
	 * @param s
	 */
	// public void addRenderGroup(SpriteGroup sg) {
	//
	// myRenderField.addGroup(sg);
	//
	// }

	/**
	 * Adds a SpriteGroup to the GameState's updateGroups.
	 * 
	 * @param s
	 */
	// public void addUpdateGroups(SpriteGroup sg) {
	//
	// myUpdateField.addGroup(sg);
	//
	// }

	/**
	 * Adds a SpriteGroup to the GameState's render and updateGroups.
	 * 
	 * @param s
	 */
	// public void addGroup(SpriteGroup sg) {
	// this.addRenderGroup(sg);
	// this.addUpdateGroups(sg);
	// }

	/**
	 * Returns the GameState's renderGroups.
	 * 
	 * @return List<SpriteGroup>
	 */
	public Collection<PlayField> getRenderField() {
		return myRenderField;
	}

	/**
	 * Returns the GameState's updateGroups.
	 * 
	 * @return List<SpriteGroup>
	 */
	public Collection<PlayField> getUpdateField() {
		return myUpdateField;
	}

	/**
	 * Adds the contents of an existing GameState to the current state's
	 * renderGroups.
	 * 
	 * @param gamestate
	 */
	public void addRenderState(GameState gamestate) {
		for (PlayField playfield : gamestate.getRenderField()) {
			addRenderPlayField(playfield);
		}
	}

	/**
	 * Adds the contents of an existing GameState to the current state's
	 * updateGroups.
	 * 
	 * @param gamestate
	 */
	public void addUpdateState(GameState gamestate) {
		for (PlayField playfield : gamestate.getUpdateField()) {
			addUpdatePlayField(playfield);
		}
	}

	/**
	 * Adds the contents of an existing GameState to the current state's render
	 * and updateGroups.
	 * 
	 * @param gamestate
	 */
	public void addState(GameState gamestate) {
		this.addUpdateState(gamestate);
		this.addRenderState(gamestate);
	}

	public void addPlayField(PlayField playfield) {
		addUpdatePlayField(playfield);
		addRenderPlayField(playfield);

	}

	public void addRenderPlayField(PlayField playfield) {
		myRenderField.add(playfield);

	}

	public void addUpdatePlayField(PlayField playfield) {
		myUpdateField.add(playfield);

	}

	/**
	 * Clears the GameState of all contents.
	 * 
	 */
	public void removeEverything() {
		myRenderField.clear();
		myUpdateField.clear();
	}

	/**
	 * GameState of specified group.
	 * 
	 */
	public void removeGroup(SpriteGroup group) {
		// myRenderField.removeGroup(group);
		// myUpdateField.removeGroup(group);
	}

	/**
	 * Equals function for GameStates
	 * 
	 * @param Object
	 * @return boolean
	 */
	/*
	 * @Override public boolean equals(Object obj){ if (this.equals(obj)){
	 * return true; } if(!(obj instanceof GameState)) return false;
	 * 
	 * GameState other = (GameState) obj; // Two GameStates are the same if they
	 * have the same update and render groups, and the same layer return myLayer
	 * == other.getLayer() && myUpdateGroups.equals(other.getUpdateGroups()) &&
	 * myRenderGroups.equals(other.getRenderGroups()); }
	 */

}
