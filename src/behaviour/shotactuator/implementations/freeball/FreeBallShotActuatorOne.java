package behaviour.shotactuator.implementations.freeball;

import behaviour.shotactuator.ShotActuator;
import game.Game;
import game.PlayerDisc;
import game.physics.Shot;

public class FreeBallShotActuatorOne extends ShotActuator {

    public FreeBallShotActuatorOne(Game game, PlayerDisc playerDisc){
        super("FreeBallShotActuatorOne", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        return null;
    }

}
