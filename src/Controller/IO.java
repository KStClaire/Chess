package Controller;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;

import Model.*;

public class IO {

	public static final int ADJUST = 96;
	BufferedReader reader;
	Overlord o;
	ArrayList<Behavior> list;
	

	public IO(Overlord o){
		this.o = o;
		list = new ArrayList<Behavior>();
	}
	
	public void getInfoFromFile(String file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		reader = new BufferedReader(isr);
		while (reader.ready()) {
			getInfoFromString(reader.readLine());
			
		}
		
	}
		public void getInfoFromString(String str){	
			String[] pieces = str.split("\\s");

			StringBuilder sb = new StringBuilder();
			Piece p = null;
			boolean isWhite;
			
			if (pieces.length == 1
					&& pieces[0].matches("[kqbnrp][ld][a-h][1-8]")) {
			
				if (pieces[0].substring(1, 2).equals("l")) {
					sb.append("Places a white ");
					isWhite = true;
				} else {
					sb.append("Places a black ");
					isWhite = false;
				}

				switch (pieces[0].substring(0, 1)) {
				case "k":
					sb.append("King on ");
					p = new King(isWhite);
					break;
				case "q":
					sb.append("Queen on ");
					p = new Queen(isWhite);
					break;
				case "b":
					sb.append("Bishop on ");
					p = new Bishop(isWhite);
					break;
				case "n":
					sb.append("Knight on ");
					p = new Night(isWhite);
					break;
				case "r":
					sb.append("Rook on ");
					p = new Rook(isWhite);
					break;
				case "p":
					sb.append("Pawn on ");
					p = new Pawn(isWhite);
					break;
				}
				sb.append(pieces[0].substring(2, 4));

				//System.out.println(sb);
				
				Behavior b = new Behavior(new Point((int)pieces[0].charAt(2)-ADJUST, Integer.parseInt(pieces[0].substring(3,4))),null,null,null,p,sb.toString());
				list.add(b);
				
			//	o.placePiece(new Point((int)pieces[0].charAt(2)-ADJUST, Integer.parseInt(pieces[0].substring(3,4))), p);
				
				
			} else if (pieces.length == 2
					&& pieces[0].matches("[a-h][1-8]")
					&& pieces[1].matches("[a-h][1-8]")) {

				sb.append("Piece at " + pieces[0].substring(0, 2) + 
						" moved to " + pieces[1].substring(0, 2));
			//	System.out.println(sb);
				
				Point start = new Point();
				start.setLocation((int)pieces[0].charAt(0)-ADJUST, Integer.parseInt(pieces[0].substring(1,2)));
				
				Point end = new Point();
				end.setLocation((int)pieces[1].charAt(0)-ADJUST, Integer.parseInt(pieces[1].substring(1,2)));
				
			//	o.movePiece(start, end);
				
				Behavior b = new Behavior(start, end, null, null, null, sb.toString());
				list.add(b);
				
			}
			 else if (pieces.length == 2
						&& pieces[0].matches("[a-h][1-8]")
						&& pieces[1].matches("[a-h][1-8][*]")) {
				 
				 sb.append("Piece at " + pieces[0].substring(0, 2) + 
							" moved to " + pieces[1].substring(0, 2) + 
							" and takes piece");
			//		System.out.println(sb);
					
					Point start = new Point();
					start.setLocation((int)pieces[0].charAt(0)-ADJUST, Integer.parseInt(pieces[0].substring(1,2)));
					
					Point end = new Point();
					end.setLocation((int)pieces[1].charAt(0)-ADJUST, Integer.parseInt(pieces[1].substring(1,2)));
					
					//o.takePiece(start, end);
					
					Behavior b = new Behavior(start, end, null, null, null, sb.toString());
					list.add(b);
					
					
			 }
			 else if (pieces.length == 4
						&& pieces[0].matches("[a-h][1-8]")
						&& pieces[1].matches("[a-h][1-8]")
						&& pieces[2].matches("[a-h][1-8]")
						&& pieces[3].matches("[a-h][1-8]")) {
				 
				 sb.append("King at " + pieces[0].substring(0, 2) + 
							" moved to " + pieces[1].substring(0, 2) + 
							" and Rook at " + pieces[2].substring(0, 2) +
							" moved to " + pieces[3].substring(0, 2));
				//	System.out.println(sb);
				 
				 Point k1 = new Point();
				 k1.setLocation((int)pieces[0].charAt(0)-ADJUST,Integer.parseInt(pieces[0].substring(1,2)));
				 Point k2 = new Point();
				 k2.setLocation((int)pieces[1].charAt(0)-ADJUST,Integer.parseInt(pieces[1].substring(1,2)));
				 
				 Point r1 = new Point();
				 r1.setLocation((int)pieces[2].charAt(0)-ADJUST,Integer.parseInt(pieces[2].substring(1,2)));
				 Point r2 = new Point();
				 r2.setLocation((int)pieces[3].charAt(0)-ADJUST,Integer.parseInt(pieces[3].substring(1,2)));
				 
				 Behavior b = new Behavior(k1,k2,r1,r2, null, sb.toString());
				 list.add(b);
				 
				 
			 }
			 else{
				 sb.append("That is not a valid move");
				 System.err.println(sb);
			 }
		}
	
	
	public ArrayList<Behavior> getBehaviors(){
		return list;
	}

}