package training.heuristic;

import game.TeamEnum;
import training.gamestate.GameState;

public interface HeuristicInterface {

    public double getHeuristicValue(GameState gameState, TeamEnum teamEnum);


}
