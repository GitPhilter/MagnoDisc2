package behaviour.impulseactuator.implementations.wait;

import behaviour.impulseactuator.ImpulseActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Impulse;

public class WaitImpulseActuatorOne extends ImpulseActuator {

    public WaitImpulseActuatorOne(Game game, PlayerDisc playerDisc){
        super("WaitImpulseActuatorOne", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        Direction direction = new Direction(0, 0);
        return new Impulse(direction,0);
    }
}
