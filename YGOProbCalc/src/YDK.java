import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class YDK{
	//generates array lists of cards given a ydk file
	//mostly finished
	//needs error handling
	
	//chooses what deck we add to
	private enum Deck{
		MAIN,
		EXTRA,
		SIDE
	}
	//our decks
	List<Card> main = new ArrayList<Card>();
	List<Card> side = new ArrayList<Card>();
	List<Card> extra = new ArrayList<Card>();
	//fill our decks
	public YDK(String path) {
		Deck deck = null;
		BufferedReader br;
		String line = null;  
		try {
			//open file
			br = new BufferedReader(new FileReader(path));
			//scan through file
			while((line = br.readLine()) != null) {  
				//update what type of deck are we adding to
				if(line.contains("created by")) {
					continue;
				} else if(line.isBlank()) {
					continue;
				} else if(line.contains("#main")) {
					deck = Deck.MAIN;
					continue;
				} else if(line.contains("#extra")) {
					deck = Deck.EXTRA;
					continue;
				} else if(line.contains("!side")) {
					deck = Deck.SIDE;
					continue;
				}
				
				//get new id
				int id = 0;
				try {
					id = Integer.parseInt(line);
				} catch(NumberFormatException e) {
//invalid ydk, throw error
				}

				//make and fill a placeholder
				List<Card> dek = null;
				if(deck == Deck.MAIN) {
					dek = main;
				} else if(deck == Deck.EXTRA) {
					dek = extra;
				} else if(deck == Deck.SIDE) {
					dek = side;
				} else {
//invalid ydk, throw error
				}

				//add the card to the deck
				dek.add(new Card(id));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}