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
        // compare to angle after moving clockwise
        playerDisc.movePuckClockwise();
        puckDirection = AngleCalculator.getDirectionFromAngle(playerDisc.getPuckDirection());
        double clockwiseAngle = AngleCalculator.getAngleFromDirection(puckDirection, goalDirection);
        playerDisc.movePuckCounterClockwise();
        //System.out.println("currentAngle=" + currentAngle + ", clockwiseAngle=" + clockwiseAngle);
        if(clockwiseAngle <= currentAngle) {
            //System.out.println("returning CLOCKWISE");
            return PuckMove.CLOCKWISE;
        }
        //System.out.println("returning COUNTERCLOCKWISE");
        return PuckMove.COUNTERCLOCKWISE;
    }
}
