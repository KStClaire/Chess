package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import Controller.IO;
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
	 *
	 */
	public String displayMoves(){
		System.out.println("Select piece to move: ");
		Scanner scan = new Scanner(System.in);
		
		StringBuilder boardManip = new StringBuilder();
		
		ArrayList list = new ArrayList();
		for(Entry<Point, Piece> p : o.getBoard().entrySet()){
			if(p.getValue().getColor() == o.isWhitesTurn() && 
					o.validMove(p.getKey()).size() > 0){
				list.add(p.getKey());
				System.out.println(((char)(p.getKey().getX() + IO.ADJUST)) + "" + ((int)p.getKey().getY()));
			}
		}
		
		String input = scan.nextLine();
		Point place = new Point(((int)input.charAt(0))-IO.ADJUST, Integer.parseInt(input.substring(1, 2)));
		if(list.contains(place)){
			list.clear();
			for(Point p : o.validMove(place)){
				// print each point
				// get input for move
				// validate with.contains
				// i'll be back
				
				System.out.println(((char)(p.getX() + IO.ADJUST)) + "" + ((int)p.getY()));
				list.add(p);
				
			}
			String goTo = scan.nextLine();
			place = new Point(((int)goTo.charAt(0))-IO.ADJUST, Integer.parseInt(goTo.substring(1, 2)));
			if(list.contains(place)){
				boardManip.append(input + " " + goTo);
				if(o.getBoard().get(place) != null){
					boardManip.append("*");

				}
			}
		}
		return boardManip.toString();
		
	}
	
}
