package behaviour.puckmoveactuator.implementations.attack;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;
import geometry.AngleCalculator;

public class MovePuckTowardsOpposingGoalPuckMoveActuator extends PuckMoveActuator {

    public MovePuckTowardsOpposingGoalPuckMoveActuator(Game game, PlayerDisc playerDisc){
        super("MovePuckTowardsOpposingGoalPuckMoveActuator", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        Direction puckDirection = AngleCalculator.getDirectionFromAngle(playerDisc.getPuckDirection());
        double xGoal = 0;
        double yGoal = (double) game.getHeight() / 2;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME) xGoal = game.getWidth();
        Direction goalDirection = playerDisc.getPosition().getDirection(new Position(xGoal, yGoal));
        int currentAngle = (int)AngleCalculator.getAngleFromDirection(puckDirection, goalDirection);
        // compare to angle after moving clockwise
        playerDisc.movePuckClockwise();
        puckDirection = AngleCalculator.getDirectionFromAngle(playerDisc.getPuckDirection());
        int clockwiseAngle = (int)AngleCalculator.getAngleFromDirection(puckDirection, goalDirection);
        if(clockwiseAngle <= currentAngle) return PuckMove.CLOCKWISE;
        return PuckMove.COUNTERCLOCKWISE;
    }
}
