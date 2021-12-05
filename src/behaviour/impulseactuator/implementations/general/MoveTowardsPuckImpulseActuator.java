package behaviour.impulseactuator.implementations.general;

import behaviour.impulseactuator.ImpulseActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Impulse;
import game.physics.Position;

public class MoveTowardsPuckImpulseActuator extends ImpulseActuator {

    public MoveTowardsPuckImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MoveTowardsPuckImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        Position puckPosition = game.getPuck().getPosition();
        Direction direction = playerDisc.getPosition().getDirection(puckPosition);
        return new Impulse(direction,1);
    }



}
