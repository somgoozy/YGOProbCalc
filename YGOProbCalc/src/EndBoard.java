import java.util.HashSet;
import java.util.Set;

public class EndBoard {
	//what the user wants their end board to look like with an integrated scoring system to allow
	//for multiple boards depending on the hand
	//still being conceptualized
	Set <BoardPiece> endBoard = new HashSet<>(); 
	public class BoardPiece {
		int score;
		String cardName;
		//do we care about this, do we care about how many, is it there
		boolean[] inHand = new boolean[3];
		boolean[] inGrave = new boolean[3];
		boolean[] onField = new boolean[3];
		//how many
		int numInHand;
		int numInGrave;
		int numOnField;
		//do we care about this, do we care what zone
		boolean[] monsterZone = new boolean[2];
		boolean[] inSTZone = new boolean[2];
		int MonsterZone;
		int STZone;
		//do we care about this, do we care what zone, which zone
		boolean[] inEMZ = new boolean[3];
		//do we care about this, do we care about count, do we care about name
		boolean[] counters = new boolean[3];
		int counterCount;
		String counterName;
		public BoardPiece(int score, String name) {
			this.score = score;
			this.cardName = name;
		}
	}
}
