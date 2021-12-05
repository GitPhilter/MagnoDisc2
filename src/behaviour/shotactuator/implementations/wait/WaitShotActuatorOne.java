package behaviour.shotactuator.implementations.wait;

import behaviour.shotactuator.ShotActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Shot;

public class WaitShotActuatorOne extends ShotActuator {

    public WaitShotActuatorOne(Game game, PlayerDisc playerDisc){
        super("WaitShotActuatorOne", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        return null;
    }

}
