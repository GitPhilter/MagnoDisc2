package behaviour.implementations.freeball;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.freeball.FreeBallImpulseActuatorOne;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.implementations.freeball.FreeBallPuckMoveActuatorOne;
import behaviour.shotactuator.implementations.freeball.FreeBallShotActuatorOne;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Impulse;
import game.physics.Shot;

public class FreeBallBehaviourOne extends Behaviour {

    public FreeBallBehaviourOne(Game game, PlayerDisc playerDisc){
        super("FreeBallBehaviourOne", game, playerDisc,
                new FreeBallImpulseActuatorOne(game, playerDisc), new FreeBallShotActuatorOne(game, playerDisc),
                new FreeBallPuckMoveActuatorOne(game, playerDisc));
    }

    @Override
    public Impulse getImpulse() {
        return impulseActuator.getImpulse();
    }

    @Override
    public Shot getShot() {
        return shotActuator.getShot();
    }

    @Override
    public PuckMove getPuckMove(){
        return puckMoveActuator.getPuckMove();
    }
}
