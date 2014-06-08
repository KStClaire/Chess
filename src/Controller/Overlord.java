package Controller;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import Model.*;
import View.*;

public class Overlord {

	private boolean isWhitesTurn = true;
	Board b;
	IO io;
	ChessDisplay cd;
	
	public Overlord(String fileName) throws IOException {
		this();
		io = new IO(this);
		io.getInfoFromFile(fileName);
		cd  = new ChessDisplay(this);
		getManip();
	}

	public Overlord() {
		b = new Board();
	}

	public void run() {
		for (Behavior bee : io.getBehaviors()) {
			// Getting Pieces
			if (bee.getPiece() != null) {
				placePiece(bee.getStartPoint(), bee.getPiece());
				System.out.println(bee.toString());
			}

			else if (isWhitesTurn == b.get(bee.getStartPoint()).getColor()) {

//			make into methods
				

				// Taking pieces
				if (this.b.get(bee.getEndPoint()) != null) {
					if (takePiece(bee.getStartPoint(), bee.getEndPoint())) {
						System.out.println(bee.toString());
					} else {
						System.err.println("Cannot take " + bee.toString());
					}
					pawnPromotion(bee.getEndPoint());
				}
				// castling
				else if (bee.getP3() != null && bee.getP4() != null) {
					if (castling(bee.getStartPoint(), bee.getEndPoint(),
							bee.getP3(), bee.getP4())) {
						System.out.println(bee.toString());
					} else {
						System.err.println("Cannot move " + bee.toString());
					}
				
				}
				// moving pieces
				else if (this.b.get(bee.getEndPoint()) == null) {
					if (movePiece(bee.getStartPoint(), bee.getEndPoint())) {
						System.out.println(bee.toString());
					} else {
						System.err.println("Cannot move " + bee.toString());
					}
					pawnPromotion(bee.getEndPoint());
					
				}
				
				isWhitesTurn = !isWhitesTurn;
				
				if(checkForCheckMate() && isWhitesTurn){
					System.out.println("Checkmate. Black wins!");
				}
				else if(checkForCheckMate() && !isWhitesTurn){
					System.out.println("Checkmate. White wins!");
				}
				else if(checkForCheckMate() && !isKingInCheck()){
					System.out.println("StaleMate.");
				}
				else if (isKingInCheck()) {
					System.out.println("The King is in check");
				}
				
				
				
			} else {
				if (isWhitesTurn) {
					System.out.println("Its not Black's turn!");
				} else if (!isWhitesTurn) {
					System.out.println("Its not White's turn!");
				}
			}
			
			cd.displayBoard();
		}
		
	}
	
	public void getManip() throws IOException{
		boolean exit = false;
		while(!exit){
			run();
			this.io = new IO(this);
			io.getInfoFromString(cd.displayMoves());
			
		}
	}

	public boolean isKingInCheck() {
		
		Point kingsLocation = null;
		for (Entry<Point, Piece> p : b.entrySet()) {
			if (p.getValue() instanceof King
					&& isWhitesTurn == p.getValue().getColor()) {
				kingsLocation = p.getKey();
				
			}
		}
		for (Entry<Point, Piece> p : b.entrySet()) {
			if ((isWhitesTurn != p.getValue().getColor())
					&& validTake(p.getKey(), kingsLocation)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isWhitesTurn() {
		return isWhitesTurn;
	}

	public void setWhitesTurn(boolean isWhitesTurn) {
		this.isWhitesTurn = isWhitesTurn;
	}

	public boolean checkForCheckMate(){
		
		boolean checkMate = true;
		
		for(Entry<Point, Piece> p : b.entrySet()){
			if(isWhitesTurn == p.getValue().getColor() && validMove(p.getKey()).size() != 0){
				checkMate = false;
			}
		}
		
		return checkMate;
	}

	public void placePiece(Point point, Piece piece) {
		b.put(point, piece);
	}

	public boolean movePiece(Point startLocation, Point endLocation) {
		Piece piece = b.get(startLocation);
		if (piece.pieceMovement(startLocation, endLocation)
				&& checkIfPathIsClear(startLocation, endLocation)) {
			b.put(endLocation, b.get(startLocation));
			b.remove(startLocation);
			piece.setHasNotMoved(false);
			return true;
		}
		return false;
	}

	public boolean takePiece(Point startLocation, Point endLocation) {
		Piece piece = b.get(startLocation);
		Piece ending = b.get(endLocation);
		if (piece.validCapture(startLocation, endLocation)
				&& checkIfPathIsClear(startLocation, endLocation)
				&& piece.getColor() != ending.getColor()) {
			b.remove(endLocation);
			b.put(endLocation, b.get(startLocation));
			b.remove(startLocation);
			piece.setHasNotMoved(false);
			return true;
		}
		return false;
	}

	public boolean validTake(Point start, Point end){
		Piece piece = b.get(start);
		Piece ending = b.get(end);
		if (ending != null && piece.validCapture(start, end)
				&& checkIfPathIsClear(start, end) 
				&& piece.getColor() != ending.getColor()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Point> validMove(Point p){
		ArrayList<Point> validMoves = new ArrayList<Point>();
		Piece piece = b.get(p);
		
		
		for(int i = 1; i <= 8; i++){
			for(int j = 1; j<= 8; j++){
				Piece p2 = b.get(new Point(i,j));
				if((p2 == null && piece.pieceMovement(p, new Point(i,j)) ||
						(validTake(p, new Point(i,j)))) && 
						!isKingInCheck() && checkIfPathIsClear(p, new Point(i,j))){
					validMoves.add(new Point(i, j));
				//	System.out.println(p + " " + i + " " + j );
				}
			}
		}
		return validMoves;
	}
	
//	public Overlord createTempBoard(Point p1, Point p2){
//		Overlord o = new Overlord();
//		o.b.putAll(this.b);
//		o.b.remove(p2);
//		o.b.put(p2, this.b.get(p1));
//		o.b.remove(p1);
//		o.isWhitesTurn = this.isWhitesTurn;
//		return o;
//	}
	
	
	public boolean castling(Point k1, Point k2, Point r1, Point r2) {
		Piece k = b.get(k1);
		Piece r = b.get(r1);

		if (checkIfPathIsClear(k1, r1) && k.pieceMovement(k1, k2)
				&& r.pieceMovement(r1, r2)) {
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

	public Board getBoard() {
		return b;
	}
	
	
	public void pawnPromotion(Point p){
		
		if(b.get(p) instanceof Pawn && p.getY() == 8){
			b.remove(p);
			b.put(p, new Queen(true));
		}
		else if(b.get(p) instanceof Pawn && p.getY() == 1){
			b.remove(p);
			b.put(p, new Queen(false));
		}
		
	}
	

	public boolean checkIfPathIsClear(Point startPosition, Point endPosition) {
		boolean isPathClear = true;

		Point difference = new Point();
		difference.setLocation(
				Math.abs(endPosition.getX() - startPosition.getX()),
				Math.abs(endPosition.getY() - startPosition.getY()));

		if(difference.getX() > 0 && difference.getY() == 0)
		{
			int largerPlace = (int) (startPosition.getX() > endPosition.getX() 
					? startPosition.getX() : endPosition.getX()); 
			for(int j = (int) (largerPlace - (difference.getX() - 1)); j < largerPlace; j++)
			{
				if(b.containsKey(new Point(j, (int) startPosition.getY())))
				{
					isPathClear = false;
				}
			}
		
			
		} else if ((int) difference.getY() > 0 && (int) difference.getX() == 0) {
			int largerPlace = 0;
			if (startPosition.getY() > endPosition.getY()) {
				largerPlace = (int) startPosition.getY();
			} else {
				largerPlace = (int) endPosition.getY();
			}
			for (int i = largerPlace - ((int) difference.getY() - 1); i < largerPlace; i++) {
				if (b.containsKey(new Point((int) startPosition.getX(), i))) {
					isPathClear = false;
				}
			}
		} else if(difference.getX() == difference.getY()){
				int leftPoint = (int) (startPosition.getX() < endPosition.getX() 
						? startPosition.getY() : endPosition.getY()); 
				int rightPoint = (int) (startPosition.getX() > endPosition.getX() 
						? startPosition.getY() : endPosition.getY());
				int largerPlace = (int) (startPosition.getX() > endPosition.getX() 
						? startPosition.getX() : endPosition.getX());
				
				int slope = leftPoint - rightPoint;
				
				int y = slope > 0 ? leftPoint - 1 : leftPoint + 1;
				
				for(int j = (int) (largerPlace - (difference.getX() -1)); j < largerPlace; j++)
				{				
					if(b.containsKey(new Point(j, y)))
					{
						isPathClear = false;
					}
					y = slope > 0 ? y - 1 : y + 1;
				}
			}
			
		return isPathClear;
	}

}
