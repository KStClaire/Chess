package Model;

public class Bishop extends Piece{

	public Bishop(boolean color){
		super(color);
	}
	
	@Override
	boolean pieceMovement() {
		/*
		 * Moves diagonally
		 * 
		 * X + 1 --or is it +=?
		 * y + 1
		 */
		return false;
	}
}
