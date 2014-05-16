package Model;

public class Night extends Piece{

	public Night(boolean color){
		super(color);
	}
	
	@Override
	boolean pieceMovement() {
		/*
		 * Moves in a L shape
		 * 
		 * x + 2
		 * y + 1
		 * or
		 * x + 1
		 * y + 2
		 */
		return false;
	}
}
