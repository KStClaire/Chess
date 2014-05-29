package Model;

import java.awt.Point;

public class Pawn extends Piece{
	
	public Pawn(boolean color){
		super(color);
		
	}

	@Override
	public boolean pieceMovement(Point startPosition, Point endPosition) {
		Point difference = new Point();
		difference.setLocation(endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
		
		
		if(this.isHasNotMoved() && this.getColor() && (difference.getX() == 0 && (difference.getY() == 1) || 
				(difference.getY() == 2)) || (!this.isHasNotMoved() && this.getColor() && 
				(difference.getX() == 0 && difference.getY() == 1))){
			return true;
		}
		else if(this.isHasNotMoved() && !this.getColor() && (difference.getX() == 0 && (difference.getY() == -1) || 
				(difference.getY() == -2)) || (!this.isHasNotMoved() && !this.getColor() && 
				(difference.getX() == 0 && difference.getY() == -1))){
			return true;
		}
		return false;
	}
	
	/*
	 * needs to take piece at diagonal
	 */
	@Override
	public boolean validCapture(Point startPosition, Point endPosition){
		Point difference = new Point();
		difference.setLocation(endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
	
		if(this.getColor() && (Math.abs(difference.getX()) == 1 && (difference.getY() == 1))){
			return true;
		}
		else if(!this.getColor() && (Math.abs(difference.getX()) == 1 && (difference.getY() == -1))){
			return true;
		}
		return false;
	}
	

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
	
}
