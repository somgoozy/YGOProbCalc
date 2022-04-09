import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class YDK {
	Map<String, Card> mainDeck = new HashMap<>();
	private enum DECK{
		MAIN,
		EXTRA,
		SIDE
	}
	public YDK(String path) {
		BufferedReader br;
		String line = null;  
		DECK deck = null;
		Map<Integer, Integer> main = new HashMap<>();
		Map<Integer, Integer> side = new HashMap<>();
		Map<Integer, Integer> extra = new HashMap<>();
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
					deck = DECK.MAIN;
					continue;
				} else if(line.contains("#extra")) {
					deck = DECK.EXTRA;
					continue;
				} else if(line.contains("!side")) {
					deck = DECK.SIDE;
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
				Map<Integer, Integer> dek = null;
				if(deck == DECK.MAIN) {
					dek = main;
				} else if(deck == DECK.EXTRA) {
					dek = extra;
				} else if(deck == DECK.SIDE) {
					dek = side;
				} else {
//invalid ydk, throw error
				}

				//is it in the placeholder already
				if(dek.containsKey(id)) {
					//increment count
					dek.put(id, dek.get(id) + 1);
				} else {
					//add it and set count to 1
					dek.put(id, 1);
				}
				
				//update decks using placeholder
				if(deck == DECK.MAIN) {
					main = dek;
				} else if(deck == DECK.EXTRA) {
					extra = dek;
				} else if(deck == DECK.SIDE) {
					side = dek;
				}
			}
			br.close();
			//make cards for main
			Card card;
			Iterator<Map.Entry<Integer, Integer>> itr = main.entrySet().iterator();
	        while(itr.hasNext()) {
	        	Map.Entry<Integer, Integer> entry = itr.next();
	        	card = new Card(entry.getKey().intValue(), entry.getValue().intValue());
	        	mainDeck.put(card.name, card);
	        }
	        //add extra
/*			itr = extra.entrySet().iterator();
	        while(itr.hasNext()) {
	        	Map.Entry<Integer, Integer> entry = itr.next();
//insert card to extra?
	        }
*/
			//do something with side?
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}