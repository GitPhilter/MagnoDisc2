package behaviourselector.implementations.wait;

import behaviour.Behaviour;
import behaviour.helpers.PlayerDiscRelations;
import behaviour.implementations.EmptyBehaviour;
import behaviour.implementations.general.HoldDefaultPositionBehaviour;
import behaviour.implementations.general.MoveAwayFromClosestPlayerDiscBehaviour;
import behaviour.implementations.general.MoveTowardsPuckBehaviour;
import behaviourselector.BehaviourSelector;
import game.Game;
import game.PlayerDisc;
import game.physics.Position;

public class StandardWaitSelector extends BehaviourSelector {
    int ticksOnSamePosition;
    int ticksOnMoveAwayFromClosestPayerDiscBehaviour;
    Position previousPosition;
    boolean holdPosition = false;
    boolean movingAwayFromClosestDisc = false;


    public StandardWaitSelector(Game game, PlayerDisc playerDisc){
        super("StandardWaitSelector", game, playerDisc);
        ticksOnSamePosition = 0;
        previousPosition = playerDisc.getPosition();
        ticksOnMoveAwayFromClosestPayerDiscBehaviour = 0;
        Behaviour[] behaviours = new Behaviour[3];
        behaviours[0] = new HoldDefaultPositionBehaviour(game, playerDisc);
        behaviours[1] = new MoveAwayFromClosestPlayerDiscBehaviour(game, playerDisc);
        behaviours[2] = new EmptyBehaviour(game, playerDisc);
        setBehaviours(behaviours);
    }

    public Behaviour getBehaviour(){

        return behaviours[0];
    }
}
