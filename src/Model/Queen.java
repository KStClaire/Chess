package Model;

public class Queen extends Piece{

	public Queen(boolean color){
		super(color);
	}
	
	@Override
	boolean pieceMovement() {
		/*
		 * Can move in every direction
		 * 
		 * Cross between a rook and bishop
		 */
		return false;
	}
}
