package behaviour.implementations.wait;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.wait.WaitImpulseActuatorOne;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.implementations.wait.WaitPuckMoveActuatorOne;
import behaviour.shotactuator.implementations.wait.WaitShotActuatorOne;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Impulse;
import game.physics.Shot;

public class WaitBehaviourOne extends Behaviour {

    public WaitBehaviourOne(Game game, PlayerDisc playerDisc){
        super("WaitBehaviourOne", game, playerDisc,
                new WaitImpulseActuatorOne(game, playerDisc), new WaitShotActuatorOne(game, playerDisc),
                new WaitPuckMoveActuatorOne(game, playerDisc));
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
