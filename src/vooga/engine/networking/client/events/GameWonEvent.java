package vooga.engine.networking.client.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.networking.client.states.PlayState;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * The DoodleDiedEvent implement IEventHandler and activates myGameOverState
 * when Doodle Dies
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GameWonEvent implements IEventHandler {
	private PlayField field;
	private PlayState playState;

	public GameWonEvent(PlayField field, PlayState playState) {
		this.field = field;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
		playState.setWon(true);
	}

	@Override
	public boolean isTriggered() {
		SpriteGroup xGroup = field.getGroup("xGroup");
		int horizontalInARow = 0, verticalInARow = 0, diagonalInARow = 0;
		for(Sprite piece : xGroup.getSprites()){
			if(piece == null)
				continue;
			int pieceX = (int) ((piece.getX() / Resources.getInt("xOffsetX")) * Resources.getInt("xOffsetX"));
			int pieceY = (int) ((piece.getY() / Resources.getInt("xOffsetY")) * Resources.getInt("xOffsetY"));
			for(Sprite otherPiece : xGroup.getSprites()){
				if(otherPiece == null)
					continue;
				int otherPieceX = ((int)(otherPiece.getX()) / Resources.getInt("xOffsetX")) * Resources.getInt("xOffsetX");
				int otherPieceY = ((int)(otherPiece.getY()) / Resources.getInt("xOffsetY")) * Resources.getInt("xOffsetY");
				if(pieceX == otherPieceX)
					verticalInARow++;
				else if(pieceY == otherPieceY)
					horizontalInARow++;
				else if(Math.abs(pieceX - otherPieceX) == Math.abs(pieceY - otherPieceY))
					diagonalInARow++;
			}
			if(horizontalInARow == 3 || verticalInARow == 3 || diagonalInARow == 3){
				return true;
			}
			horizontalInARow = 0;
			verticalInARow = 0;
			diagonalInARow = 0;
		}
		return false;
	}
}
