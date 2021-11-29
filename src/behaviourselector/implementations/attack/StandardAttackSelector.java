package behaviourselector.implementations.attack;

import behaviour.Behaviour;
import behaviour.helpers.PuckSituation;
import behaviour.implementations.attack.pass.StandardPassBehaviour;
import behaviour.implementations.attack.score.StandardScoreBehaviour;
import behaviour.implementations.general.MoveBackBehaviour;
import behaviour.implementations.general.MoveForwardBehaviour;
import behaviourselector.BehaviourSelector;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;

public class StandardAttackSelector extends BehaviourSelector {

    public StandardAttackSelector(Game game, PlayerDisc playerDisc){
        super("StandardAttackSelector", game, playerDisc);
        Behaviour[] behaviours = new Behaviour[4];
        behaviours[0] = new StandardScoreBehaviour(game, playerDisc);
        behaviours[1] = new StandardPassBehaviour(game, playerDisc);
        behaviours[2] = new MoveForwardBehaviour(game, playerDisc);
        behaviours[3] = new MoveBackBehaviour(game, playerDisc);
        setBehaviours(behaviours);
    }

    public Behaviour getBehaviour(){
        if(playerDisc.hasPuck()){
            if(PuckSituation.puckTouchesOpposingPlayerDisc(game, playerDisc)){
                previousBehaviour = behaviours[1];
                return behaviours[1];
            }
            if(previousBehaviour == behaviours[1]) return previousBehaviour;
            previousBehaviour = behaviours[0];
            return behaviours[0];
        }
        // no puck
        double xReference = 0;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME) xReference = game.getWidth();
        if(Math.abs(playerDisc.getPosition().getX() - xReference) <= 100) {
            previousBehaviour = behaviours[3];
            return behaviours[3];
        }
        previousBehaviour = behaviours[2];
        return behaviours[2];
    }

}
