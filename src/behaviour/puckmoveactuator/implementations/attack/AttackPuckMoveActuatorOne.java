package behaviour.puckmoveactuator.implementations.attack;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.game.Game;
import game.PlayerDisc;

public class AttackPuckMoveActuatorOne extends PuckMoveActuator {

    public AttackPuckMoveActuatorOne(Game game, PlayerDisc playerDisc){
        super("AttackPuckMoveActuatorOne", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        return PuckMove.CLOCKWISE;
    }
}
