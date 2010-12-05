package vooga.examples.networking.tictactoe.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.examples.networking.tictactoe.states.PlayState;
import vooga.engine.resource.Resources;

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
		System.out.println("WINNER");
		playState.setWon(true);
	}

	@Override
	public boolean isTriggered() {
		SpriteGroup xGroup = field.getGroup("xGroup");
		int horizontalInARow = 0, verticalInARow = 0, diagonalPositiveInARow = 0, diagonalNegativeInARow = 0;
		for(Sprite piece : xGroup.getSprites()){
			if(piece == null)
				continue;
			int pieceX = ((int) (piece.getX())) / Resources.getInt("squareDimension");
			int pieceY = ((int) (piece.getY())) / Resources.getInt("squareDimension");
			System.out.println("PIECEY:" + pieceY);
			for(Sprite otherPiece : xGroup.getSprites()){
				if(otherPiece == null)
					continue;
				int otherPieceX = (int) (otherPiece.getX() / Resources.getInt("squareDimension"));
				int otherPieceY = (int) (otherPiece.getY() / Resources.getInt("squareDimension"));
				if(pieceX == otherPieceX)
					verticalInARow++;
				if(pieceY == otherPieceY)
					horizontalInARow++;
				if(Math.abs(pieceX - otherPieceX) == Math.abs(pieceY - otherPieceY)){
					if(pieceX - otherPieceX == pieceY - otherPieceY)
						diagonalPositiveInARow++;
					if(pieceX == otherPieceX || (pieceX - otherPieceX != pieceY - otherPieceY))
						diagonalNegativeInARow++;
				}
			}
			if(horizontalInARow == 3 || verticalInARow == 3 || diagonalPositiveInARow == 3 || diagonalNegativeInARow == 3){
				return true;
			}
			horizontalInARow = 0;
			verticalInARow = 0;
			diagonalPositiveInARow = 0;
			diagonalNegativeInARow = 0;
		}
		return false;
	}
}
