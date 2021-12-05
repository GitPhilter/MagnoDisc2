package behaviour.impulseactuator.implementations.general;

import behaviour.helpers.PlayerDiscRelations;
import behaviour.impulseactuator.ImpulseActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Impulse;

public class MoveAwayFromClosestPlayerDiscImpulseActuator extends ImpulseActuator {

    public MoveAwayFromClosestPlayerDiscImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MoveAwayFromClosestPlayerDiscImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        PlayerDisc closestPlayerDisc = PlayerDiscRelations.getClosestPlayerDisc(game, playerDisc);
        Direction direction = closestPlayerDisc.getPosition().getDirection(playerDisc.getPosition());
        return new Impulse(direction, 1);
    }
}
