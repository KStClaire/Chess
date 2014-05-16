package Model;

public abstract class Piece {
	
	private boolean isWhite;
	
	public Piece(boolean color){
		this.isWhite = color;
	}
   	
	abstract boolean pieceMovement();
	
	public String toString(){
		if(isWhite == true){
			return this.getClass().getSimpleName().substring(0,1).toUpperCase();
		}
		return this.getClass().getSimpleName().substring(0,1).toLowerCase();
	}
	
}
