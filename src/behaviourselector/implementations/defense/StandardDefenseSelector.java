package behaviourselector.implementations.defense;

import behaviour.Behaviour;
import behaviour.helpers.PlayerDiscRelations;
import behaviour.implementations.general.HoldDefaultPositionBehaviour;
import behaviour.implementations.general.MoveTowardsPuckBehaviour;
import behaviourselector.BehaviourSelector;
import game.Game;
import game.PlayerDisc;

public class StandardDefenseSelector extends BehaviourSelector {

    public StandardDefenseSelector(Game game, PlayerDisc playerDisc){
        super("StandardDefenseSelector", game, playerDisc);
        Behaviour[] behaviours = new Behaviour[2];
        behaviours[0] = new MoveTowardsPuckBehaviour(game, playerDisc);
        behaviours[1] = new HoldDefaultPositionBehaviour(game, playerDisc);
        setBehaviours(behaviours);
    }

    public Behaviour getBehaviour(){
        //System.out.println("StandardFreeballSelector.getBehaviour()");
        if(playerDisc == PlayerDiscRelations.getPlayerDiscClosestToPuckFromTeam(game, playerDisc)){
            //System.out.println("Returning behaviour 0 (puck hunting)");
            return behaviours[0];
        }
        //System.out.println("Returning behaviour 0 (holding default position)");
        return behaviours[1];
    }
}
