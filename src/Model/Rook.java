package Model;


public class Rook extends Piece{

	public Rook(boolean color){
		super(color);
	}
	
	@Override
	boolean pieceMovement() {
		/*
		 * Can only move in straight lines
		 * 
		 */
		return false;
	}
}
