package behaviour.shotactuator.implementations.attack;

import behaviour.shotactuator.ShotActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;

public class ShootAccordingToDistanceFromOpposingSideShotActuator extends ShotActuator {

    public ShootAccordingToDistanceFromOpposingSideShotActuator(Game game, PlayerDisc playerDisc){
        super("ShootAccordingToDistanceFromOpposingSideShotActuator", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        double xReference = 0;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME) xReference = game.getWidth();
        Shot shot = null;
        if(Math.abs(playerDisc.getPosition().getX() - xReference) <= 100){
            double goalY = (double)game.getHeight() / 2;
            Direction direction = playerDisc.getPosition().getDirection(new Position(xReference, goalY));
            shot = new Shot(direction, 6);
        }
        return shot;
    }
}
