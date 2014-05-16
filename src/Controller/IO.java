package Controller;
import java.awt.Point;
import java.io.*;

import Model.*;

public class IO {

	private String blah;
	BufferedReader reader;
	Overlord o;
	
	public IO(String file, Overlord o) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		reader = new BufferedReader(isr);
		this.o = o;
		getInfoFromFile();
	}

	public void getInfoFromFile() throws IOException {
		while (reader.ready()) {
			String[] thingies = reader.readLine().split("\\s");

			StringBuilder sb = new StringBuilder();
			Piece p = null;
			boolean color;
			
			if (thingies.length == 1
					&& thingies[0].matches("[kqbnrp][ld][a-h][1-8]")) {
			
				if (thingies[0].substring(1, 2).equals("l")) {
					sb.append("Places a white ");
					color = true;
				} else {
					sb.append("Places a black ");
					color = false;
				}

				switch (thingies[0].substring(0, 1)) {
				case "k":
					sb.append("King on ");
					p = new King(color);
					break;
				case "q":
					sb.append("Queen on ");
					p = new Queen(color);
					break;
				case "b":
					sb.append("Bishop on ");
					p = new Bishop(color);
					break;
				case "n":
					sb.append("Knight on ");
					p = new Night(color);
					break;
				case "r":
					sb.append("Rook on ");
					p = new Rook(color);
					break;
				case "p":
					sb.append("Pawn on ");
					p = new Pawn(color);
					break;
				}
				sb.append(thingies[0].substring(2, 4));

				System.out.println(sb);
				
				o.placePiece(new Point((int)thingies[0].charAt(2)-96, Integer.parseInt(thingies[0].substring(3,4))), p);
				
				
			} else if (thingies.length == 2
					&& thingies[0].matches("[a-h][1-8]")
					&& thingies[1].matches("[a-h][1-8]")) {

				sb.append("Piece at " + thingies[0].substring(0, 2) + 
						" moved to " + thingies[0].substring(0, 2));
				System.out.println(sb);
				
			}
			 else if (thingies.length == 2
						&& thingies[0].matches("[a-h][1-8]")
						&& thingies[1].matches("[a-h][1-8][*]")) {
				 
				 sb.append("Piece at " + thingies[0].substring(0, 2) + 
							" moved to " + thingies[1].substring(0, 2) + 
							" and takes piece");
					System.out.println(sb);
			 }
			 else if (thingies.length == 4
						&& thingies[0].matches("[a-h][1-8]")
						&& thingies[1].matches("[a-h][1-8]")
						&& thingies[2].matches("[a-h][1-8]")
						&& thingies[3].matches("[a-h][1-8]")) {
				 
				 sb.append("King at " + thingies[0].substring(0, 2) + 
							" moved to " + thingies[1].substring(0, 2) + 
							" and Rook at " + thingies[2].substring(0, 2) +
							" moved to " + thingies[3].substring(0, 2));
					System.out.println(sb);
			 }
			 else{
				 sb.append("That is not a valid move");
				 System.err.println(sb);
			 }
		}
	}

}