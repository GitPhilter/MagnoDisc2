package behaviour.puckmoveactuator.implementations.attack;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.game.Game;
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
        //System.out.println("puckDirection: " + puckDirection);
        //System.out.println("goalDirection: " + goalDirection);
        double angle = AngleCalculator.getAngleFromDirection(goalDirection, puckDirection);
        //System.out.println("angle: " + angle);
        if(angle >= -0.5 && angle <= 0.5) {
            //System.out.println("returning NO_MOVE");
            return PuckMove.NO_MOVE;
        }
        if(angle > 180) {
            //System.out.println("returning CLOCKWISE");
            return PuckMove.CLOCKWISE;
        }
        //System.out.println("returning COUNTERCLOCKWISE");
        return PuckMove.COUNTERCLOCKWISE;
    }
}
