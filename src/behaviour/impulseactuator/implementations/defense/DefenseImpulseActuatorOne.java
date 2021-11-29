package behaviour.impulseactuator.implementations.defense;

import behaviour.impulseactuator.ImpulseActuator;
import game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Impulse;
import game.physics.Position;

public class DefenseImpulseActuatorOne extends ImpulseActuator {

    public DefenseImpulseActuatorOne(Game game, PlayerDisc playerDisc){
        super("DefenseImpulseActuatorOne", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        Position puckPosition = game.getPuck().getPosition();
        Direction direction = playerDisc.getPosition().getDirection(puckPosition);
        return new Impulse(direction,1);
    }

}

