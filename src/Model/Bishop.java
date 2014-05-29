package Model;

import java.awt.Point;

public class Bishop extends Piece{

	public Bishop(boolean color){
		super(color);
	}
	
		/*
		 * Moves diagonally
		 * 
		 * X + 1 --or is it +=?
		 * y + 1
		 */

	@Override
	public boolean pieceMovement(Point startPosition, Point endPosition) {
		Point difference = new Point();
		difference.setLocation(endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
		
		if(Math.abs(difference.getX()) == Math.abs(difference.getY())){
			return true;
		}
		return false;
	}
}
