package behaviour.impulseactuator.implementations;

import behaviour.impulseactuator.ImpulseActuator;
import game.Game;
import game.PlayerDisc;
import game.physics.Impulse;

public class EmptyImpulseActuator extends ImpulseActuator {

    public EmptyImpulseActuator(Game game, PlayerDisc playerDisc){
        super("EmptyImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        return null;
    }
}
