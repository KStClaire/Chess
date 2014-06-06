package Model;

import java.awt.Point;

public class Queen extends Piece{

	public Queen(boolean color){
		super(color);
	}

	@Override
	public boolean pieceMovement(Point startPosition, Point endPosition) {
		Point difference = new Point();
		difference.setLocation(endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
		
		if((Math.abs(difference.getX()) == 0 && Math.abs(difference.getY()) > 0) ||
				(Math.abs(difference.getY()) == 0 && Math.abs(difference.getX()) > 0) ||
				(Math.abs(difference.getX()) == Math.abs(difference.getY()))){
			return true;
		}
		return false;
	}
	

		/*
		 * Can move in every direction
		 * 
		 * Cross between a rook and bishop
		 */
	
}
