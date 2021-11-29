package behaviour.puckmoveactuator.implementations.defense;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.Game;
import game.PlayerDisc;

public class DefensePuckMoveActuatorOne extends PuckMoveActuator {

    public DefensePuckMoveActuatorOne(Game game, PlayerDisc playerDisc){
        super("DefensePuckMoveActuatorOne", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        return PuckMove.NO_MOVE;
    }
}
