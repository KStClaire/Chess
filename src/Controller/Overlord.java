package Controller;

import java.awt.*;
import java.io.IOException;

import Model.*;
import View.*;

public class Overlord {

	static Board b;
	
	public Overlord(String blah) throws IOException{
		this();
		IO io = new IO(blah, this);
		ChessDisplay cd = new ChessDisplay();
		cd.displayBoard();
	}
	
	public Overlord(){
		b = new Board();
	}
	
	public void placePiece(Point point, Piece piece) {
		b.put(point, piece);
	}

	public void movePiece() {

	}
	
	public static Board getBoard(){
		return b;
	}
	
}
