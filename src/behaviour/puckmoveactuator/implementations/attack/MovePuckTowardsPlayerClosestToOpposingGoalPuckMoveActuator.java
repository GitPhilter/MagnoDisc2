package behaviour.puckmoveactuator.implementations.attack;

import behaviour.helpers.Teammates;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import geometry.AngleCalculator;

public class MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator extends PuckMoveActuator {

    public MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator(Game game, PlayerDisc playerDisc){
        super("MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        Direction puckDirection = AngleCalculator.getDirectionFromAngle(playerDisc.getPuckDirection());
        PlayerDisc alliedPlayerDisc = Teammates.getClosestTeammate(game, playerDisc);
        if(alliedPlayerDisc == null) return PuckMove.NO_MOVE;
        Direction direction = playerDisc.getPosition().getDirection(alliedPlayerDisc.getPosition());
        double angle = AngleCalculator.getAngleFromDirection(direction, puckDirection);
        //System.out.println("angle: " + angle);
        if(angle >= -0.5 && angle <= 0.5) {
            //System.out.println("retuning NO_MOVE");
            return PuckMove.NO_MOVE;
        }
        if(angle > 180) {
            //System.out.println("retuning CLOCKWISE");
            return PuckMove.CLOCKWISE;
        }
        //System.out.println("returning COUNTERCLOCKWISE");
        return PuckMove.COUNTERCLOCKWISE;
    }
}
