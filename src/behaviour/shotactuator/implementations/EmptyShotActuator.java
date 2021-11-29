package behaviour.shotactuator.implementations;

import behaviour.helpers.PlayerDiscAbsolutePosition;
import behaviour.shotactuator.ShotActuator;
import game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;
import geometry.AngleCalculator;

public class EmptyShotActuator extends ShotActuator {

    public EmptyShotActuator(Game game, PlayerDisc playerDisc){
        super("EmptyShotActuator", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        return null;
    }
}
