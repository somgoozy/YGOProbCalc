import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class Controller {
	//a multithreaded implementation of the genetic algorithm to do tree traversal by a score system
	//currently writing
	//incomplete
	int WEIGHT = 10;
	public void Start(GameState game, EndBoard board, int generations, int hands) {
		
	}
	private void PlayHand(GameState game, EndBoard board, ScoreBoard threadScore) {
		AvailableActions myActions = new AvailableActions(game);
		List<AvailableActions.Action> path;
		AvailableActions.Action choice;
		//loop until no paths are available
		while(myActions.moves != null) {
			//choose path
			choice = Pathfind(myActions, threadScore);
			//update gamestate with path chosen
			myActions.DoAction(game, choice);
			//update current paths taken list
			
			//calculate score of node
			//compare node score to node high score
				//if larger make new high score
				//update highscore paths taken list
			//update paths
		}
		//update scoreboard
	}
	private AvailableActions.Action Pathfind(AvailableActions actions, ScoreBoard scores) {
		//calculates the route to take by checking the scoreboard and using it
		//to randomly path falling through the tree
		int total = 0;		
		//get total
		for(AvailableActions.Action scoreAction: actions.moves) {
			total += WEIGHT + scores.scoreBoard.getOrDefault(scoreAction.action, 0);
		}
		//generate random number
		int random = (int)Math.floor(Math.random() * (total + 1));
		for(AvailableActions.Action scoredAction: actions.moves) {
			//subtract weighted score from total
			total -= (WEIGHT + scores.scoreBoard.getOrDefault(scoredAction.action, 0));
			//if the total is below zero we have a random to return
			if(total < 0) {
				return scoredAction;
			}
		}
		return null;
	}
	public class ScoreCard {
		AvailableActions.Action move;
		int score;
	}
	public static class ScoreBoard {
		//scoreboard
		int score;
		Map<AvailableActions.Action, Integer> scoreBoard = new HashMap<>();
		//update the scoreboard
		
		public void Update(List<AvailableActions.Action> moves, int score) {
			//updates the scoreboard with the given list of scores
			Iterator<AvailableActions.Action> itr = moves.iterator();
	        while(itr.hasNext()) {
	        	AvailableActions.Action move = itr.next();
	        	//update entry
	        	if(scoreBoard.containsKey(move)) {
	        		scoreBoard.put(move, score + scoreBoard.get(move));
	        	} else {
	        		scoreBoard.put(move, score);
	        	}
	        }
		}
	}
}
