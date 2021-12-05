package behaviour.impulseactuator.implementations;

import behaviour.impulseactuator.ImpulseActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Impulse;

public class EmptyImpulseActuator extends ImpulseActuator {

    public EmptyImpulseActuator(Game game, PlayerDisc playerDisc){
        super("EmptyImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        return new Impulse(new Direction(0, 0), 0);
    }
}
