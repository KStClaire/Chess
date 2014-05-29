package Model;

import java.awt.Point;



public class Rook extends Piece{

	public Rook(boolean color){
		super(color);
	}

		/*
		 * probably need to change what goes into the parameters.
		 * 
		 * Can only move in straight lines
		 * 
		 * row movement = same row
		 * column movement = same column
		 * 
		 * 
		 * 
		 * if(new placement != current row || new placement != current column){
		 * 		don't move piece or false
		 * }
		 * else{
		 * 		move piece or true
		 * }
		 */

	@Override
	public boolean pieceMovement(Point startPosition, Point endPosition) {
		if(startPosition.getX() == endPosition.getX() || startPosition.getY() == endPosition.getY()){
			return true;
		}
		return false;
	}

}
