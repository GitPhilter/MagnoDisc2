package behaviour.impulseactuator.implementations.defense;

import behaviour.helpers.PlayerDiscRelations;
import behaviour.impulseactuator.ImpulseActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Impulse;
import game.physics.Position;

public class MarkNearestPlayerDiscImpulseActuator extends ImpulseActuator {

    public MarkNearestPlayerDiscImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MarkNearestPlayerDiscImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        Position puckPosition = game.getPuck().getPosition();
        PlayerDisc nearestPlayerDisc = null;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME){
            nearestPlayerDisc = PlayerDiscRelations.getNearestOpponentPlayerDisc(playerDisc, game.getAwayTeam());
        } else {
            nearestPlayerDisc = PlayerDiscRelations.getNearestOpponentPlayerDisc(playerDisc, game.getHomeTeam());
        }
        Direction directionFromNearestPlayerToPuck = nearestPlayerDisc.getPosition().getDirection(game.getPuck().getPosition());
        double focusX = nearestPlayerDisc.getPosition().getX() + directionFromNearestPlayerToPuck.getX() * (playerDisc.getRadius() + nearestPlayerDisc.getRadius());
        double focusY = nearestPlayerDisc.getPosition().getY() + directionFromNearestPlayerToPuck.getY() * (playerDisc.getRadius() + nearestPlayerDisc.getRadius());
        Position focusPosition = new Position(focusX, focusY);
        Direction focusDirection = playerDisc.getPosition().getDirection(focusPosition);
        return new Impulse(focusDirection,1);
    }
}
