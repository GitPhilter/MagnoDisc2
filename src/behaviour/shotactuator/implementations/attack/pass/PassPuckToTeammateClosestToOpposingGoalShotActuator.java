package behaviour.shotactuator.implementations.attack.pass;

import behaviour.helpers.Teammates;
import behaviour.shotactuator.ShotActuator;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Shot;

public class PassPuckToTeammateClosestToOpposingGoalShotActuator extends ShotActuator {

    public PassPuckToTeammateClosestToOpposingGoalShotActuator(Game game, PlayerDisc playerDisc){
        super("PassPuckToTeammateClosestToOpposingGoalShotActuator", game, playerDisc);
    }

    @Override
    public Shot getShot(){
        PlayerDisc teammateClosestToOpposingGoal = Teammates.getTeammateClosestToOpposingGoal(game, playerDisc);
        if(teammateClosestToOpposingGoal == null) return null;
        Direction direction = playerDisc.getPosition().getDirection(teammateClosestToOpposingGoal.getPosition());
        return new Shot(direction, playerDisc.getMaxShootingSpeed());
    }
}
