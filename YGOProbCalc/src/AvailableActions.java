import java.util.Set;

class AvailableActions {
	//Defines what an action is and contains a list of actions available to the controller
	//incomplete
	Set <Action> moves;
	
	//the action taken
	public enum WHAT{
		NORMAL_SUMMON,
		SPECIAL_SUMMON,
		EFFECT1,
		EFFECT2,
		EFFECT3,
		EFFECT4
	}
	//locations of the action
	public enum WHERE{
		FIELD,
		HAND,
		GY,
		DECK,
	}
	//elements of an action
	public class Action {
		Card card;
		WHAT action;
		WHERE whereTo;
		WHERE whereFrom;
		Set<Card> targets;
	}
	public AvailableActions(GameState game) {
		// TODO Auto-generated constructor stub
	}
	public void normalSummon() {
		
	}
	public void SynchroSummon() {
			
	}
	public void XYZSummon() {
		
	}
	public void PendSummon() {
		
	}
	public void LinkSummon() {
		
	}
	public void FlipSummon() {
		
	}
	public void MovePhase() {
		
	}
	public void ActivateEffect() {
		
	}
	public void ActivateCard() {
		
	}
	public void ScalePendulum() {
		
	}
	public void SetSpellTrap() {
		
	}
	public void SetMonster() {
		
	}
	public void DoAction(GameState game, AvailableActions.Action pathfind) {
		// TODO Auto-generated method stub
		
	}
}
