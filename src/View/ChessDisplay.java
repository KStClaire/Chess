package View;

import java.awt.*;
import java.util.Map.Entry;

import Controller.Overlord;
import Model.*;

public class ChessDisplay {

	Overlord o;
	
	public ChessDisplay(Overlord o){
		this.o = o;
	}
	
	public void displayBoard(){
		
		System.out.println("-----------------");
		for(int i = Board.BOARD_HEIGHT; i > 0; i--){
			for (int k = 1; k <= Board.BOARD_WIDTH; k++){

				System.out.print(" ");
				Piece p;
				p = o.getBoard().get(new Point(k, i));
				if(p != null){
				System.out.print(p);
				}
				else {
					System.out.print('-');
				}
				
			}
			System.out.println(" | " + i);
		}
		System.out.println("-----------------");
		System.out.println(" a b c d e f g h");
	}
	
	/*
	 * make it to where
	 */
	public void displayMoves(){
		for(Entry<Point, Piece> p : o.getBoard().entrySet()){
			if(p.getValue().getColor() == o.isWhitesTurn() && 
					o.validMove(p.getKey()).size() > 0){
				System.out.println(p.getKey());
				System.out.println("has");
				for(Point point : o.validMove(p.getKey())){
					System.out.println(point);
				}
			}
		}
	}
	
}
