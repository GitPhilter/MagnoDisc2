package behaviour.puckmoveactuator.implementations.wait;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.game.Game;
import game.PlayerDisc;

public class WaitPuckMoveActuatorOne extends PuckMoveActuator {

    public WaitPuckMoveActuatorOne(Game game, PlayerDisc playerDisc){
        super("WaitPuckMoveActuatorOne", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        return PuckMove.NO_MOVE;
    }
}
