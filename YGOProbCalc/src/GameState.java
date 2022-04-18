import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class GameState implements Serializable {
	//What does the game state look like at any given time
	//mostly finished, waiting on a way to program effects
	enum Phase {
		DRAW,
		STANDBY,
		MAIN,
		BATTLE,
		MAIN2,
		END
	}
	public static class Zone {
		public enum Type {
			FIELD,
			SPELLTRAP,
			MONSTER,
			EMZ
		}
		public enum Ownership {
			PLAYER1,
			PLAYER2,
			NEITHER
		}
		boolean pend;
		Card card;
		Ownership ownership;
		Type type;
	}
	//both players field
	Zone[] monster = new Zone[10];
	Zone[] emz = new Zone[2];
	Zone[] spellTrap = new Zone[10];
	Zone[] field = new Zone[2];
	//both players gy
	List<Card> p1Graveyard = new ArrayList<Card>();
	List<Card> p2Graveyard = new ArrayList<Card>();
	//both players hands
	List<Card> p1Hand = new ArrayList<Card>();
	List<Card> p2Hand = new ArrayList<Card>();
	//both players decks
	List<Card> p1Deck = new ArrayList<Card>();
	List<Card> p2Deck = new ArrayList<Card>();
	List<Card> p1ExtraDeck = new ArrayList<Card>();
	List<Card> p2ExtraDeck = new ArrayList<Card>();
	//current phase
	Phase currentPhase;
	//is it turn 0
	boolean firstTurn;
	//lp
	int yourLP;
	int oppsLP;
	//initialize a chain
	Chain currentChain = new Chain();
	public GameState(YDK ydk) {
		//build deck
		p1Deck.addAll(ydk.main);
		p1ExtraDeck.addAll(ydk.extra);
	}
	public void Start() {
		//first turn
		firstTurn = true;
		//set phase
		currentPhase = Phase.DRAW;
		//shuffle and draw 5
		Actions.ShuffleDeck(p1Deck);
		Actions.Draw(p1Deck, p1Hand, 5);
		//set life to 8000
		yourLP = 8000;
		oppsLP = 8000;
		//prepare zones
		for(int i = 0; i < 5; i++) {
			monster[i].ownership = GameState.Zone.Ownership.PLAYER1;
			monster[i].type = GameState.Zone.Type.MONSTER;
			spellTrap[i].ownership = GameState.Zone.Ownership.PLAYER1;
			spellTrap[i].type = GameState.Zone.Type.SPELLTRAP;
		}
		for(int i = 5; i < 10; i++) {
			monster[i].ownership = GameState.Zone.Ownership.PLAYER2;
			monster[i].type = GameState.Zone.Type.MONSTER;
			spellTrap[i].ownership = GameState.Zone.Ownership.PLAYER2;
			spellTrap[i].type = GameState.Zone.Type.SPELLTRAP;
		}
		for(int i = 0; i < 2; i++) {
			emz[i].ownership = GameState.Zone.Ownership.NEITHER;
			emz[i].type = GameState.Zone.Type.EMZ;
			field[i].type = GameState.Zone.Type.FIELD;
		}
		field[0].ownership = GameState.Zone.Ownership.PLAYER1;
		field[1].ownership = GameState.Zone.Ownership.PLAYER2;
	}
	public void advancePhase(boolean end){
		//progress phase
		if(currentPhase == Phase.DRAW) {
			currentPhase = Phase.STANDBY;
		}else if(currentPhase == Phase.STANDBY) {
			currentPhase = Phase.MAIN;
		}else if((currentPhase == Phase.MAIN) && (end || firstTurn)) {
			currentPhase = Phase.END;
		}else if(currentPhase == Phase.MAIN) {
			currentPhase = Phase.BATTLE;
		}else if(currentPhase == Phase.BATTLE) {
			currentPhase = Phase.MAIN2;
		}else if(currentPhase == Phase.MAIN2) {
			currentPhase = Phase.END;
		}else if(currentPhase == Phase.END) {
			currentPhase = Phase.DRAW;
		}
	}
	public class Chain {
		List <Effect> chainLink = new Stack<AvailableActions>();
		public void addLink() {
			
		}
		public void resolve() {
			
		}
	}
}
