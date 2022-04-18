import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Actions {
	//a class of methods to help create card effects
	//most need rework or writing
	//very incomplete
	public static boolean DeckContains(List<Card> deck, String cardName) {
		//check every card
		for(Card card: deck) {
			//if we find the card
			if(card.name == cardName) {
				return true;
			}
		}
		return false;
	}
	public static void DeckToGY(List<Card> deck, List<Card> grave, String cardName) {
		//same as searching
		Search(deck, grave, cardName);
	}
	public static void FieldToGrave(List<Card> grave, Card ... targets) {
		for(Card target: targets) {
			//add cards to grave
			grave.add(target);
			//remove from field
			target = null;
		}
		Update();
	}
	public static void Discard(List<Card> hand, int card, List<Card> grave) {
		//add card to grave
		grave.add(hand.remove(card));	
	}
	public static void Draw(List<Card> deck, List<Card> hand, int draws) {
		for(int i = 0; i < draws; i++) {
			//add card to hand
			hand.add(deck.remove(0));
		}
	}
	public static List<Card> Excavate(List<Card> deck, int num) {
		List <Card> excavated = new ArrayList<Card>();
		//draw to excavated
		Draw(deck, excavated, num);
		return excavated;
	}
	public static int RandomNumber(int min, int max) {
		//return a random number within a range
		return (int)Math.floor(Math.random() * (max - min + 1) + min);
	}
	public static void Search(List<Card> deck, List<Card> hand, String cardName) {
		//search every card
		for(Card card: deck) {
			//if found
			if(card.name == cardName) {
				//add it to hand
				hand.add(card);
				//remove it from deck
				deck.remove(card);
				//shuffle deck
				ShuffleDeck(deck);
			}
		}
	}
	public static void ShuffleDeck(List<Card> deck) {
		Collections.shuffle(deck);
	}
	public static void SummonFromDeck() {
		
	}
	public static void SummonFromGrave() {
		
	}
	public static void SummonFromHand() {
		
	}
	public static void ToBanish() {
		
	}
	public static void ToDeck() {
		
	}
	public static void ToExtraDeck() {
		
	}
	public static void ToHand() {
		
	}
	private static void Update() {
		
	}
}