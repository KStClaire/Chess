package Model;

import java.awt.Point;

public class King extends Piece {

	public King(boolean color){
		super(color);
	}

	@Override
	public boolean pieceMovement(Point startPosition, Point endPosition) {
		Point difference = new Point();
		difference.setLocation(endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
		
		if( (Math.abs(difference.getX()) == 1 && Math.abs(difference.getY()) == 0) ||
				(Math.abs(difference.getX()) == 0 && Math.abs(difference.getY()) == 1) ||
				(Math.abs(difference.getX()) == Math.abs(difference.getY()))){
					return true;
				}
		else if(isHasNotMoved() && (Math.abs(difference.getX()) == 2 && Math.abs(difference.getY()) == 0)){
			return true;
		}
		return false;
	}
	
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
		 * 
		 * needs to be checked for check and checkmate
		 */
	

	
	
}
