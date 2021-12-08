package training.heuristic.implementations;

import game.TeamEnum;
import training.gamestate.PlayerDiscState;
import training.heuristic.Heuristic;
import training.gamestate.GameState;

public class SimpleHeuristic_1 extends Heuristic {
    private final int goalBonus = 10;

    public SimpleHeuristic_1(){
        super("SimpleHeuristic_1");
    }

    public double getHeuristicValue(GameState gameState, TeamEnum teamEnum){
        int heuristicValue = 0;
        // check for puck possession
        if(gameState.getPuckControllingPlayerDiscIndex() == -1) {
            //
        } else {

            if(gameState.getPlayerDiscStates()[gameState.getPuckControllingPlayerDiscIndex()].getTeamEnum() == teamEnum){
                heuristicValue += 4;
            } else {
                heuristicValue += -4;
            }

        }
        // current score
        if(teamEnum == TeamEnum.HOME) {
            heuristicValue += gameState.getNumberOfHomeGoals() * goalBonus;
            heuristicValue -= gameState.getNumberOfAwayGoals() * goalBonus;
        } else {
            heuristicValue -= gameState.getNumberOfHomeGoals() * goalBonus;
            heuristicValue += gameState.getNumberOfAwayGoals() * goalBonus;
        }
        // TODO: puck distance to goals

        return heuristicValue;
    }
}
