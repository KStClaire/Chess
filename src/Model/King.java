package Model;

public class King extends Piece {

	public King(boolean color){
		super(color);
	}
	
	@Override
	boolean pieceMovement() {
		/*
		 * Can only move one place
		 * but can move diagonally
		 * 
		 * x + 1 --can also be -
		 * or 
		 * y + 1
		 * or
		 * x + 1
		 * y + 1
		 * 
		 * stops after one movement
		 */
		return false;
	}

	
	
}
