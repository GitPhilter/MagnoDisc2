package behaviour.shotactuator.implementations.attack;

import behaviour.helpers.PlayerDiscAbsolutePosition;
import behaviour.shotactuator.ShotActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;

public class AttackShotActuatorOne extends ShotActuator {

    public AttackShotActuatorOne(Game game, PlayerDisc playerDisc){
        super("ShotActuatorOne", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        if(!playerDisc.hasPuck()) return null;
        //System.out.println("GET SHOT!");
        double distanceToGoal;
        double goalX = -1;
        double goalY = (double)game.getHeight() / 2;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME){
            //System.out.println("HOME team: shooting at the right goal!");
            distanceToGoal = PlayerDiscAbsolutePosition.getDistanceToRightGoalCenter(game, playerDisc);
            goalX = 800;
        } else {
            //System.out.println("AWAY team: shooting at the left goal!");
            distanceToGoal = PlayerDiscAbsolutePosition.getDistanceToLeftGoalCenter(game, playerDisc);
            goalX = 0;
        }
        if(distanceToGoal <= 250){
            //System.out.println("Distance to goal is <= 250 ");
            Direction direction = playerDisc.getPosition().getDirection(new Position(goalX, goalY));
            return new Shot(direction, 10);
        }
        //System.out.println("ShotActuatorOne is NOT returning a shot! (pussy lol)");
        return null;
    }
}
