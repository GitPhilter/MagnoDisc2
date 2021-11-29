package behaviour.impulseactuator.implementations.general;

import behaviour.impulseactuator.ImpulseActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Impulse;

public class MoveToDefaultPositionImpulseActuator extends ImpulseActuator {

    public MoveToDefaultPositionImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MoveToDefaultPositionImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        Direction direction = playerDisc.getPosition().getDirection(playerDisc.getDefaultPosition());
        return new Impulse(direction, 1);
    }
}
