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
        double currentAngle = AngleCalculator.getAngleFromDirection(puckDirection, goalDirection);
        int clockwiseIncreased = playerDisc.getPuckDirection() + 1;
        if(clockwiseIncreased > 359) clockwiseIncreased = 0;
        Direction clockwiseIncreasedDirection = AngleCalculator.getDirectionFromAngle(clockwiseIncreased);
        double clockwiseIncreasedAngle = AngleCalculator.getAngleFromDirection(clockwiseIncreasedDirection, goalDirection);
        if(clockwiseIncreasedAngle < currentAngle) return PuckMove.CLOCKWISE;
        return PuckMove.COUNTERCLOCKWISE;
    }
}
