package behaviour.puckmoveactuator.implementations;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.Game;
import game.PlayerDisc;

public class EmptyPuckMoveActuator extends PuckMoveActuator {

    public EmptyPuckMoveActuator(Game game, PlayerDisc playerDisc){
        super("EmptyPuckMoveActuator", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        return PuckMove.NO_MOVE;
    }
}
