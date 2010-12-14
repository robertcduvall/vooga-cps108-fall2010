package vooga.games.tictactoe.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.tictactoe.states.PlayState;
import vooga.engine.resource.Resources;

/**
 * Detects and informs the PlayState when the player has won (3 'X' pieces in a row.)
 * No network API code.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class GameWonEvent implements IEventHandler {
	private PlayField field;
	private PlayState playState;

	/**
	 * Give GameWonEvent access to the main PlayField and the PlayState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public GameWonEvent(PlayField field, PlayState playState) {
		this.field = field;
		this.playState = playState;
	}

	/**
	 * If there are 3 'X' pieces in a row then tell the PlayState it won.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void actionPerformed() {
		playState.setMessage("won");
	}

	/**
	 * @return if there are 3 'X' pieces in a row
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public boolean isTriggered() {
		SpriteGroup xGroup = field.getGroup("xGroup");
		int horizontalInARow = 0, verticalInARow = 0, diagonalPositiveInARow = 0, diagonalNegativeInARow = 0;
		for(Sprite piece : xGroup.getSprites()){
			if(piece == null)
				continue;
			int pieceX = ((int) (piece.getX())) / Resources.getInt("squareDimension");
			int pieceY = ((int) (piece.getY())) / Resources.getInt("squareDimension");
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
