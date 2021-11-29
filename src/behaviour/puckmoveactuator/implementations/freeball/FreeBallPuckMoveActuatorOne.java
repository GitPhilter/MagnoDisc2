package behaviour.puckmoveactuator.implementations.freeball;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.Game;
import game.PlayerDisc;

public class FreeBallPuckMoveActuatorOne extends PuckMoveActuator {

    public FreeBallPuckMoveActuatorOne(Game game, PlayerDisc playerDisc){
        super("FreeBallPuckMoveActuatorOne", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        return PuckMove.NO_MOVE;
    }
}
