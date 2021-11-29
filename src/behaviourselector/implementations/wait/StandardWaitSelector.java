package behaviourselector.implementations.wait;

import behaviour.Behaviour;
import behaviour.helpers.PlayerDiscRelations;
import behaviour.implementations.general.HoldDefaultPositionBehaviour;
import behaviour.implementations.general.MoveTowardsPuckBehaviour;
import behaviourselector.BehaviourSelector;
import game.Game;
import game.PlayerDisc;

public class StandardWaitSelector extends BehaviourSelector {

    public StandardWaitSelector(Game game, PlayerDisc playerDisc){
        super("StandardWaitSelector", game, playerDisc);
        Behaviour[] behaviours = new Behaviour[1];
        behaviours[0] = new HoldDefaultPositionBehaviour(game, playerDisc);
        setBehaviours(behaviours);
    }

    public Behaviour getBehaviour(){
        return behaviours[0];
    }
}
