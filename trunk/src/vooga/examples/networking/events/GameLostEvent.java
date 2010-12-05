package vooga.engine.networking.client.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.networking.client.states.PlayState;
import vooga.engine.resource.Resources;

/**
 * The DoodleDiedEvent implement IEventHandler and activates myGameOverState
 * when Doodle Dies
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GameLostEvent implements IEventHandler {
	private PlayField field;
	private PlayState playState;

	public GameLostEvent(PlayField field, PlayState playState) {
		this.field = field;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
		playState.setLost(true);
	}

	@Override
	public boolean isTriggered() {
		SpriteGroup oGroup = field.getGroup("oGroup");
		int horizontalInARow = 0, verticalInARow = 0, diagonalInARow = 0;
		for(Sprite piece : oGroup.getSprites()){
			if(piece == null)
				continue;
			int pieceX = (int) (piece.getX() / Resources.getInt("squareDimension"));
			int pieceY = (int) (piece.getY() / Resources.getInt("squareDimension"));
			for(Sprite otherPiece : oGroup.getSprites()){
				if(otherPiece == null)
					continue;
				int otherPieceX = (int) (otherPiece.getX() / Resources.getInt("squareDimension"));
				int otherPieceY = (int) (otherPiece.getY() / Resources.getInt("squareDimension"));
				if(pieceX == otherPieceX)
					verticalInARow++;
				if(pieceY == otherPieceY)
					horizontalInARow++;
				if(Math.abs(pieceX - otherPieceX) == Math.abs(pieceY - otherPieceY))
					diagonalInARow++;
			}
			if(horizontalInARow == 3 || verticalInARow == 3 || diagonalInARow == 3){
				return true;
			}
			horizontalInARow = 0;
			verticalInARow = 0;
			diagonalInARow = 0;
			//System.out.println(xGroup.getSize());
		}
		return false;
	}
}
