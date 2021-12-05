package behaviour.shotactuator.implementations;

import behaviour.shotactuator.ShotActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Shot;

public class EmptyShotActuator extends ShotActuator {

    public EmptyShotActuator(Game game, PlayerDisc playerDisc){
        super("EmptyShotActuator", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        return null;
    }
}
