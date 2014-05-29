package Model;

import java.awt.Point;

public class Night extends Piece{

	public Night(boolean color){
		super(color);
	}

	@Override
	public boolean pieceMovement(Point startPosition, Point endPosition) {
		Point difference = new Point();
		difference.setLocation(endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
		
		if((Math.abs(difference.getX()) == 2 && Math.abs(difference.getY()) == 1) || 
			(Math.abs(difference.getX()) == 1 && Math.abs(difference.getY()) == 2)){
			return true;
		}
		return false;
	}

		/*
		 * Moves in a L shape
		 * 
		 * x + 2
		 * y + 1
		 * or
		 * x + 1
		 * y + 2
		 * 
		 * 
		 */

	
}
