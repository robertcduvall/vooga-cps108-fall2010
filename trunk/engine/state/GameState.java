package engine.state;

import java.awt.Graphics2D;
import engine.core.Sprite;
import java.util.*;

/**
 * @author VitorOlivier & BrentSodman & BrianSimel
 * 
 */
public abstract class GameState implements Comparable<GameState> {

	private boolean isActive = false;
	private List<Sprite> renderGroups = new ArrayList<Sprite>();
	private List<Sprite> updateGroups = new ArrayList<Sprite>();
	private int myLayer;

	/**
	 * Constructs a new GameState 
	 */
	public GameState() {
		this.initialize();
	}

	/**
	 * @param gamestate
	 */
	public GameState(GameState gamestate) {
		this();
		this.addState(gamestate);
		// this.initialize();
	}

	/**
	 * @param spritegroup
	 */
	public GameState(Sprite spritegroup) {
		this();
		this.addGroup(spritegroup);

	}

	/**
	 * @param layer
	 */
	public GameState(int layer) {
		this();
		this.setLayer(layer);
		// this.initialize();
	}

	/**
	 * @param gamestate
	 * @param layer
	 */
	public GameState(GameState gamestate, int layer) {
		this(gamestate);
		// this.addState(gamestate);
		this.setLayer(layer);
		this.initialize();
	}

	/**
	 * @param spritegroup
	 * @param layer
	 */
	public GameState(Sprite spritegroup, int layer) {
		this(spritegroup);
		this.setLayer(layer);
		this.initialize();

	}

	/**
	 * 
	 */
	public void activate() {
		this.isActive = true;
	}

	/**
	 * 
	 */
	public void deactivate() {
		this.isActive = false;
	}

	/**
	 * Specific state setup is done in this method
	 * 
	 */
	public abstract void initialize();

	/**
	 * @return
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param g
	 */
	public void render(Graphics2D g) {

		for (Sprite s : renderGroups) {
			s.render(g);
		}
	}

	/**
	 * @param t
	 * @param inputManager
	 */
	public void update(long t) {
		for (Sprite s : updateGroups) {
			s.update(t);
		}
	}

	/**
	 * @param s
	 */
	public void addRenderGroup(Sprite s) {
		renderGroups.add(s);
	}

	/**
	 * @param s
	 */
	public void addUpdateGroups(Sprite s) {
		updateGroups.add(s);
	}

	/**
	 * @param s
	 */
	public void addGroup(Sprite s) {
		this.addRenderGroup(s);
		this.addUpdateGroups(s);
	}

	/**
	 * @return
	 */
	public List<Sprite> getRenderGroups() {
		return renderGroups;
	}

	/**
	 * @return
	 */
	public List<Sprite> getUpdateGroups() {
		return updateGroups;
	}

	/**
	 * @param gamestate
	 */
	public void addRenderState(GameState gamestate) {
		for (Sprite group : gamestate.getRenderGroups()) {
			renderGroups.add(group);
		}
	}

	/**
	 * @param gamestate
	 */
	public void addUpdateState(GameState gamestate) {
		for (Sprite group : gamestate.getUpdateGroups()) {
			updateGroups.add(group);
		}
	}

	/**
	 * @param gamestate
	 */
	public void addState(GameState gamestate) {
		this.addUpdateState(gamestate);
		this.addRenderState(gamestate);
	}

	/**
	 * 
	 */
	public void removeAllGroups() {
		renderGroups.clear();
		updateGroups.clear();
	}

	/**
	 * Deactivates this and activates gamestate
	 * 
	 * @param gamestate
	 */
	public void switchTo(GameState gamestate) {
		this.deactivate();
		gamestate.activate();

	}

	/**
	 * Deactivates a GameState, transfers all sprite groups to another GameState
	 * which is then activated.
	 * 
	 * @param gamestate
	 */
	public void switchToAndTransferAll(GameState gamestate) {
		gamestate.addState(this);
		this.deactivate();
		gamestate.activate();

	}

	/**
	 * Deactivates this GameState, transfers update sprite group to gamestate
	 * which is then activated
	 * 
	 * @param gamestate
	 */
	public void switchToAndTransferUpdate(GameState gamestate) {
		gamestate.addUpdateState(this);
		this.deactivate();
		gamestate.activate();

	}

	/**
	 * Deactivates this GameState, transfers update sprite group to gamestate
	 * which is then activated
	 * 
	 * @param gamestate
	 */
	public void switchToAndTransferRender(GameState gamestate) {
		gamestate.addRenderState(this);
		this.deactivate();
		gamestate.activate();

	}

	/**
	 * 
	 * @param layer
	 */
	public void setLayer(int layer) {
		this.myLayer = layer;
	}

	/**
	 * @return GameState's layer
	 */
	public int getLayer() {
		return myLayer;
	}

	/**
	 * @param gamestate
	 * @return
	 */
	public int compareTo(GameState gamestate) {
		return this.getLayer() - gamestate.getLayer();

	}

}
