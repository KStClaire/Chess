package Model;

public class Pawn extends Piece{

	public Pawn(boolean color){
		super(color);
	}
	
	@Override
	boolean pieceMovement() {
		/*
		 * Can move two places if 
		 * never moved before.
		 * Only one place after starting
		 * 
		 * X + 2 -- when starting
		 * or
		 * X + 1 
		 * 
		 * Can only claim pieces on diagonal
		 * 
		 * Cannot move backwards
		 */
		return false;
	}
}
