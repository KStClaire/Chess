package Controller;

import java.awt.*;
import java.io.IOException;

import Model.*;
import View.*;

public class Overlord {

	static Board b;
	IO io;
	
	public Overlord(String fileName) throws IOException{
		this();
		io = new IO(fileName, this);
		run();
		ChessDisplay cd = new ChessDisplay();
		cd.displayBoard();
	}
	
	public Overlord(){
		b = new Board();
	}
	
	public void run(){
		for(Behavior bee : io.getBehaviors()){
			if(bee.getPiece() != null){
				placePiece(bee.getStartPoint(), bee.getPiece());
				System.out.println(bee.toString());
			}
			else if(this.b.get(bee.getEndPoint()) != null){
				if(takePiece(bee.getStartPoint(), bee.getEndPoint())){
					System.out.println(bee.toString());
				}
				else{
					System.err.println("Cannot take " + bee.toString());
				}
			}
			else if(bee.getP3() != null && bee.getP4() != null){
				if(castling(bee.getStartPoint(), bee.getEndPoint(), bee.getP3(), bee.getP4())){
					System.out.println(bee.toString());
				}
				else{
					System.err.println("Cannot move " + bee.toString());
				}
			}
			else if(this.b.get(bee.getEndPoint()) == null){
				if(movePiece(bee.getStartPoint(), bee.getEndPoint())){
					System.out.println(bee.toString());
				}
				else{
					System.err.println("Cannot move " + bee.toString());
				}
			}
		}
	}
	
	public void placePiece(Point point, Piece piece) {
		b.put(point, piece);
	}

	public boolean movePiece(Point startLocation, Point endLocation) {
		Piece piece = b.get(startLocation);
		if(piece.pieceMovement(startLocation, endLocation) && 
				checkIfPathIsClear(startLocation, endLocation)){
			b.put(endLocation, b.get(startLocation));
			b.remove(startLocation);
			piece.setHasNotMoved(false);
			return true;
		}
		return false;
	}
	
	public boolean takePiece(Point startLocation, Point endLocation){
		Piece piece = b.get(startLocation);
		if(piece.validCapture(startLocation, endLocation) &&
				checkIfPathIsClear(startLocation, endLocation)){
			b.remove(endLocation);
			b.put(endLocation, b.get(startLocation));
			b.remove(startLocation);
			piece.setHasNotMoved(false);
			return true;
		}
		return false;
	}
	

	public boolean castling(Point k1, Point k2, Point r1, Point r2){
		Piece k = b.get(k1);
		Piece r = b.get(r1);
		
		if(checkIfPathIsClear(k1, r1) &&
				k.pieceMovement(k1, k2) && r.pieceMovement(r1, r2)){
			b.put(k2, b.get(k1));
			b.put(r2, b.get(r1));
			b.remove(k1);
			b.remove(r1);
			k.setHasNotMoved(false);
			r.setHasNotMoved(false);
			return true;
		}
		return false;
	}
	
	public static Board getBoard(){
		return b;
	}
	
	public boolean checkIfPathIsClear(Point startPosition, Point endPosition){
		boolean isPathClear = true;
		
		Point difference = new Point();
		difference.setLocation(Math.abs(endPosition.getX() - startPosition.getX()), Math.abs(endPosition.getY() - startPosition.getY()));
	
		if((int)difference.getX() > 0 && (int)difference.getY() == 0){
			int largerPlace = 0;
			if(startPosition.getX() > endPosition.getX()){
				largerPlace = (int) startPosition.getX();
			}
			else{
				largerPlace = (int) endPosition.getX();
			}
			for(int i = largerPlace - ((int)difference.getX() - 1); i < largerPlace; i++){
				if(b.containsKey(new Point(i, (int) startPosition.getY()))){
					isPathClear = false;
				}
			}
		}
		else if((int)difference.getY() > 0 && (int)difference.getX() == 0){
			int largerPlace = 0;
			if(startPosition.getY() > endPosition.getY()){
				largerPlace = (int) startPosition.getY();
			}
			else{
				largerPlace = (int) endPosition.getY();
			}
			for(int i = largerPlace - ((int)difference.getY() - 1); i < largerPlace; i++){
				if(b.containsKey(new Point((int) startPosition.getX(), i))){
					isPathClear = false;
				}
			}
		}
		else if((int)difference.getX() == (int)difference.getY()){
			int leftPoint = 0;
			int rightPoint = 0;
			int largerPlace = 0;
			int slope = 0;
			int y = 0;
			
			if((int) startPosition.getX() < (int)endPosition.getX()){
				leftPoint = (int)startPosition.getY();
				rightPoint = (int)endPosition.getY();
				largerPlace = (int)startPosition.getX();
			}
			else{
				leftPoint =  (int)endPosition.getY();
				rightPoint = (int)startPosition.getY();
				largerPlace = (int)endPosition.getX();
			}
				slope = leftPoint - rightPoint;
				
			if(slope > 0){
				y = leftPoint - 1;
			}
			else{
				y = leftPoint + 1;
			}
			
			for(int j = largerPlace - (int)difference.getX() -1; j < largerPlace; j++){
				if(b.containsKey(new Point(j, y))){
					isPathClear = false;
				}
				if(slope > 0){
					y = y-1;
				}
				else{
					y = y+1;
				}
			}
		}
		return isPathClear;
	}
	
}
