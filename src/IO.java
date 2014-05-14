import java.io.*;

public class IO {

	BufferedReader reader;

	public IO(String file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		reader = new BufferedReader(isr);
		getInfoFromFile();
	}

	public void getInfoFromFile() throws IOException {
		while (reader.ready()) {
			String[] thingies = reader.readLine().split("\\s");

			StringBuilder sb = new StringBuilder();

			if (thingies.length == 1
					&& thingies[0].matches("[kqbnrp][ld][a-h][1-8]")) {
			
				if (thingies[0].substring(1, 2).equals("l")) {
					sb.append("Places a white ");
				} else {
					sb.append("Places a black ");
				}

				switch (thingies[0].substring(0, 1)) {
				case "k":
					sb.append("King on ");
					break;
				case "q":
					sb.append("Queen on ");
					break;
				case "b":
					sb.append("Bishop on ");
					break;
				case "n":
					sb.append("Knight on ");
					break;
				case "r":
					sb.append("Rook on ");
					break;
				case "p":
					sb.append("Pawn on ");
					break;
				}
				sb.append(thingies[0].substring(2, 4));

				System.out.println(sb);
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

	public void placePiece() {
		
		//static hashmap
		//map.put("q", "queen").....
		
		
		// something as parameter?

		// switch statement --probably need a hashmap too
		// String array?
		// for loop
		// [K|Q|B|N|R|P]{1} [l|d]{1} [a-h]{1} [1-8]{1}
		// sysout

		// returns piece;

		// asdfghjkl i hate javaaa
	}

	public void movePiece() {

	}

}