package Model;

import java.awt.Point;

public abstract class Piece {
	
	private boolean isWhite;
	private boolean hasNotMoved = true;
	
	public Piece(boolean color){
		this.isWhite = color;
	}
   	
	public String toString(){
		if(isWhite == true){
			return this.getClass().getSimpleName().substring(0,1).toUpperCase();
		}
		return this.getClass().getSimpleName().substring(0,1).toLowerCase();
	}


	public abstract boolean pieceMovement(Point startPosition, Point endPosition);
	
	public boolean getColor(){
		return isWhite;
	}
	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public boolean validCapture(Point startPosition, Point endPosition){
		if(pieceMovement(startPosition,endPosition)){
			return true;
		}
		return false;
	}

	public boolean isHasNotMoved() {
		return hasNotMoved;
	}

	public void setHasNotMoved(boolean hasNotMoved) {
		this.hasNotMoved = hasNotMoved;
	}
	

}
