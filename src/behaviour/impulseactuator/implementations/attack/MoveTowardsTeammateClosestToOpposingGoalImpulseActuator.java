package behaviour.impulseactuator.implementations.attack;

import behaviour.helpers.Teammates;
import behaviour.impulseactuator.ImpulseActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Impulse;
import game.physics.Position;

public class MoveTowardsTeammateClosestToOpposingGoalImpulseActuator extends ImpulseActuator {

    public MoveTowardsTeammateClosestToOpposingGoalImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MoveTowardsTeammateClosestToOpposingGoalImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        PlayerDisc closestTeammateToOpposingGoal = Teammates.getTeammateClosestToOpposingGoal(game, playerDisc);
        Direction direction = playerDisc.getPosition().getDirection(closestTeammateToOpposingGoal.getPosition());
        return new Impulse(direction, 1);
    }
}
