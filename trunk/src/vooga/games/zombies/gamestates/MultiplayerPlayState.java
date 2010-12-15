package vooga.games.zombies.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.networking.client.Username;
import vooga.engine.overlay.*;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.util.SoundPlayer;
import vooga.games.zombies.*;
import vooga.games.zombies.events.*;

public class MultiplayerPlayState extends PlayState implements Constants {


	private Blah currentGame;

	private Shooter player;
	private Shooter[] otherPlayers;
	private PlayField playField;
	private Timer timer;
	private KeyboardControl control;
	private KeyboardControl control2;
	private EventPool eventPool;
	public static long seed;
	public Timer reviveTimer;
	private OverlayLabel reviveLabel;
	private SpriteGroup labels;

	AddZombieEvent addZombies;
	AddRandomItemEvent addItems;
	private OverlayTracker levelTracker;
	private int level;

	public MultiplayerPlayState(Blah game) {
		super(game);
	}

}
