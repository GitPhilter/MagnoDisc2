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
        double distance = playerDisc.getPosition().getDistance(playerDisc.getDefaultPosition());
        if(distance <= 0.5) return new Impulse(new Direction(0, 0), 0);
        double acceleration = 1;
        if(distance <= 100){
            acceleration = acceleration * (distance / 100);
        }
        //System.out.println("acceleration: " + acceleration);
        return new Impulse(direction, acceleration);
    }
}
