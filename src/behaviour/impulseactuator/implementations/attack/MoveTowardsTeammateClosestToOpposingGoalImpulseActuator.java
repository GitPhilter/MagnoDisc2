package behaviour.impulseactuator.implementations.attack;

import behaviour.helpers.Teammates;
import behaviour.impulseactuator.ImpulseActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Impulse;

public class MoveTowardsTeammateClosestToOpposingGoalImpulseActuator extends ImpulseActuator {

    public MoveTowardsTeammateClosestToOpposingGoalImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MoveTowardsTeammateClosestToOpposingGoalImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        PlayerDisc closestTeammateToOpposingGoal = Teammates.getTeammateClosestToOpposingGoal(game, playerDisc);
        if(closestTeammateToOpposingGoal == null) return new Impulse(new Direction(0,0), 0);
        Direction direction = playerDisc.getPosition().getDirection(closestTeammateToOpposingGoal.getPosition());
        return new Impulse(direction, 1);
    }
}
