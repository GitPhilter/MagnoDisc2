package behaviourselector.implementations.attack;

import behaviour.Behaviour;
import behaviourselector.BehaviourSelector;
import game.Game;
import game.PlayerDisc;

public class StandardAttackSelector extends BehaviourSelector {

    public StandardAttackSelector(Game game, PlayerDisc playerDisc){
        super("StandardAttackSelector", game, playerDisc);

    }

    public Behaviour getBehaviour(){
        return behaviours[0];
    }

}
