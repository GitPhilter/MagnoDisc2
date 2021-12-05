package behaviour.implementations.attack;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.attack.AttackImpulseActuatorOne;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.implementations.attack.AttackPuckMoveActuatorOne;
import behaviour.shotactuator.implementations.attack.AttackShotActuatorOne;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Impulse;
import game.physics.Shot;

public class AttackBehaviourOne extends Behaviour {

    public AttackBehaviourOne(Game game, PlayerDisc playerDisc){
        super("AttackBehaviourOne", game, playerDisc,
                new AttackImpulseActuatorOne(game, playerDisc), new AttackShotActuatorOne(game, playerDisc),
                new AttackPuckMoveActuatorOne(game, playerDisc));
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
