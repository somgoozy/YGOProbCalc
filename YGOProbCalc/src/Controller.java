import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.SerializationUtils;
public class Controller {
	int WEIGHT = 10;
	GameState cleanGame;
	ScoreBoard score;
	
	public void Learn(int learningCycles) {
		for(int i = 0; i < learningCycles; i++) {
			GameState copy = SerializationUtils.clone(cleanGame);
			AvailableActions myActions = new AvailableActions(copy);
			List<AvailableActions.Action> currentPath = new ArrayList<>();
			AvailableActions.Action choice;
			int bestPath = 0;
			int currentScore = 0;
			int highScore = 0;
			//loop until no paths are available
			while(myActions.moves != null && copy.currentPhase != GameState.Phase.END) {
				//choose path
				choice = Pathfind(myActions, score);
				//update gamestate with path chosen
				myActions.DoAction(copy, choice);
				//update current paths taken list
				currentPath.add(choice);
				//calculate score of node
				currentScore = EndBoard.Rate(copy);
				//compare node score to node high score
				if (currentScore > highScore) {
					//update highscore and paths taken list
					highScore = currentScore;
					bestPath = currentPath.size();
				}
				myActions = new AvailableActions(copy);
			}
			//update scoreboard
			score.Update(currentPath.subList(0, bestPath), highScore);
		}
	}
	private AvailableActions.Action Pathfind(AvailableActions actions, ScoreBoard scores) {
		//calculates the route to take by checking the scoreboard and using it
		//to randomly path falling through the 

		//remove phase change
		AvailableActions.Action phaseChange = null;
		for(AvailableActions.Action action: actions.moves) {
			if(action.action == AvailableActions.WHAT.PHASECHANGE) {
				phaseChange = action;
				actions.moves.remove(phaseChange);
			}
		}
		int total = 0;		
		//get total
		for(AvailableActions.Action scoreAction: actions.moves) {
			total += WEIGHT + scores.scoreBoard.getOrDefault(scoreAction.action, 0);
		}
		//generate random number
		int random = (int)Math.floor(Math.random() * (total + 1));
		for(AvailableActions.Action scoredAction: actions.moves) {
			//subtract weighted score from total
			random -= (WEIGHT + scores.scoreBoard.getOrDefault(scoredAction.action, 0));
			//if the total is below zero we have a random to return
			if(random < 0) {
				return scoredAction;
			}
		}
		//return advance phase
		return phaseChange;
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
