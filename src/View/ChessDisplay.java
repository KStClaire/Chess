package View;

import java.awt.*;

import Controller.Overlord;
import Model.*;

public class ChessDisplay {

	Overlord o;
	
	public ChessDisplay(Overlord o){
		this.o = o;
	}
	
	public void displayBoard(){
		
		System.out.println("-----------------");
		for(int i = 1; i <= Board.BOARD_HEIGHT; i++){
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
	
}
