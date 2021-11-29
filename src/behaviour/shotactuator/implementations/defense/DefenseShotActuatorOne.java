package behaviour.shotactuator.implementations.defense;

import behaviour.helpers.PlayerDiscAbsolutePosition;
import behaviour.shotactuator.ShotActuator;
import game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;

public class DefenseShotActuatorOne extends ShotActuator {

    public DefenseShotActuatorOne(Game game, PlayerDisc playerDisc){
        super("DefenseShotActuatorOne", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        return null;
    }

}
