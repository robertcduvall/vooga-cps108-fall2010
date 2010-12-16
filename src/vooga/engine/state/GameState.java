package vooga.engine.state;

import java.awt.Graphics2D;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import vooga.engine.core.PlayField;
import vooga.engine.networking.client.ClientConnection;

import com.golden.gamedev.object.SpriteGroup;

/**
 * GameState is, at its most basic conception, a container class for collections
 * of sprites. Beyond that, it should be used to control state-specific behavior
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
	protected ClientConnection connection;
	protected String message;

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
		this();
		addState(gamestate);
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
	}
	
	public GameState(ClientConnection connection){
		this();
		this.connection = connection;
	}
	
	public GameState(PlayField playfield, ClientConnection connection){
		this();
		addPlayField(playfield);
		this.connection = connection;
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

	// TODO suggestion: if you are going to require everyone to define an
	// initialize method, presumably you expect everyone to call it during
	// construction. If this is the case, could you make all the constructors
	// of GameState call initialize? --Daniel Koverman

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
	 * Updates all sprites stored in the GameState' updateGroups.  Also run checkMessages to see, if the developer is making a game using networking, if
	 * we have received any messages from the server that we need to execute.  Also see if we should listen to the socket for a message by calling the
	 * shouldGetData method.
	 * 
	 * @param t
	 */
	public void update(long t) {
		if(checkMessage())
			return;
		for (PlayField playfield : myUpdateField) {
			playfield.update(t);
		}
		if(connection != null && shouldGetData()){
			setMessage(connection.getData());
		}
	}
	
	/**
	 * Overridden by subclasses that want to implement networking in their game.  Determines whether or not to listen for a message from the socket.
	 * Defaults to false so networking is only implemented and checked if the developer wants to.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public boolean shouldGetData(){
		return connection.isConnected();
	}
	
	public void setConnection(ClientConnection connection){
		this.connection = connection;
	}
	
	/**
	 * Overridden by subclasses that want to implement networking in their game. Called in the update method when it receives a message from the socket.
	 * 
	 * @param data the String received from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void interpretMessage(String data){}
	
	/**
	 * If there is a message to send then call the method that corresponds with that message and then set the message to null.
	 * 
	 * @return whether or not it was sent a message
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public boolean checkMessage(){
		if(message == null || message.length() == 0 || message.equals("still")){
			return false;
		}
		else{
			try {
				String name = message.indexOf(":") == -1 ? message : message.substring(0, message.indexOf(":"));
				String param = message.indexOf(":") == -1 ? null : message.substring(message.indexOf(":") + 1);
				Method statusAction = param == null ? this.getClass().getMethod(name) : this.getClass().getMethod(name, String.class);
				message = null;
				return param == null ? ((Boolean)(statusAction.invoke(this))).booleanValue() : ((Boolean)(statusAction.invoke(this, param))).booleanValue();
			} 
			catch (Exception e) {
				System.out.println("Couldn't find method '" + message + "'. Make sure the names of your stauses match the name of your status methods!");
				System.exit(1);
			}
		}
		return false;
	}
	
	/**
	 * Sets the message. The message is initialized to anything sent through the socket that is not a Serializeable object, so just a String.  Corresponds
	 * to a method in the GameState subclass that is called the next time checkMessage is called in the update method.
	 * 
	 * @param message to set the message instance to
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void setMessage(String message){
		this.message = message;
	}

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

	/**
	 * Adds a playfield to an rendered and updated in an existing GameState
	 * 
	 * @param playfield
	 *            to be rendered and updated
	 */
	public void addPlayField(PlayField playfield) {
		addUpdatePlayField(playfield);
		addRenderPlayField(playfield);

	}

	/**
	 * Adds a playfield to an rendered in an existing GameState
	 * 
	 * @param playfield
	 *            to be rendered
	 */
	public void addRenderPlayField(PlayField playfield) {
		myRenderField.add(playfield);

	}

	/**
	 * Adds a playfield to an updated in an existing GameState
	 * 
	 * @param playfield
	 *            to be updated
	 */
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
	 * Get a SpriteGroup out of the GameState if it exists. If it does not exist
	 * than this method will return null
	 * 
	 * @param name
	 *            the name of the SpriteGroup
	 * @return SpriteGroup with that name
	 */
	public SpriteGroup getGroup(String name) {
		for (PlayField playfield : myUpdateField) {
			SpriteGroup group = playfield.getGroup(name);
			if (group != null) {
				return group;
			}
		}
		return null;// TODO:throw exception instead
	}

	public void addGroup(SpriteGroup group) {
		PlayField pf = new PlayField();
		pf.addGroup(group);
		addPlayField(pf);
	}

	/**
	 * Equals function for GameStates
	 * 
	 * @param Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GameState))
			return false;

		GameState other = (GameState) obj;
		// Two GameStates are the same if they have the same update and render
		// groups, and the same layer
		return myUpdateField.equals(other.getUpdateField())
				&& myRenderField.equals(other.getRenderField());
	}
}
