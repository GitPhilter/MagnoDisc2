package behaviour;

import behaviour.puckmoveactuator.PuckMove;
import game.physics.Impulse;
import game.physics.Shot;

public interface BehaviourInterface {

    public Impulse getImpulse();

    public Shot getShot();

    public PuckMove getPuckMove();

}
