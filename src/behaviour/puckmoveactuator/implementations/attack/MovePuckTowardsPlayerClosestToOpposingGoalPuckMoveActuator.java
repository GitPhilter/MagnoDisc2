package behaviour.puckmoveactuator.implementations.attack;

import behaviour.helpers.Teammates;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;
import geometry.AngleCalculator;

public class MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator extends PuckMoveActuator {

    public MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator(Game game, PlayerDisc playerDisc){
        super("MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator", game, playerDisc);
    }

    @Override
    public PuckMove getPuckMove(){
        Direction puckDirection = AngleCalculator.getDirectionFromAngle(playerDisc.getPuckDirection());

        PlayerDisc playerDiscClosestToOpposingGoal = Teammates.getTeammateClosestToOpposingGoal(game, playerDisc);
        if(playerDiscClosestToOpposingGoal == null) return PuckMove.NO_MOVE;
        Direction teammateDirection = playerDisc.getPosition().getDirection(playerDiscClosestToOpposingGoal.getPosition());
        double currentAngle = AngleCalculator.getAngleFromDirection(puckDirection, teammateDirection);
        int clockwiseIncreased = playerDisc.getPuckDirection() + 1;
        if(clockwiseIncreased > 359) clockwiseIncreased = 0;
        Direction clockwiseIncreasedDirection = AngleCalculator.getDirectionFromAngle(clockwiseIncreased);
        double clockwiseIncreasedAngle = AngleCalculator.getAngleFromDirection(clockwiseIncreasedDirection, teammateDirection);
        if(clockwiseIncreasedAngle < currentAngle) return PuckMove.CLOCKWISE;
        return PuckMove.COUNTERCLOCKWISE;
    }
}
