package View;

import java.awt.*;

import Controller.Overlord;
import Model.*;

public class ChessDisplay {

	public void displayBoard(){
		for(int i = 1; i <= Board.BOARD_HEIGHT; i++){
			for (int k = 1; k <= Board.BOARD_WIDTH; k++){
				System.out.print("|");
				Piece p;
				p = Overlord.getBoard().get(new Point(k, i));
				if(p != null){
				System.out.print(p);
				}
				else {
					System.out.print('-');
				}
			}
			System.out.println();
		}
	}
	
}
