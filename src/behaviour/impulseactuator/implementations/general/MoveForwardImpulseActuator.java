package behaviour.impulseactuator.implementations.general;

import behaviour.impulseactuator.ImpulseActuator;
import game.game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Impulse;

public class MoveForwardImpulseActuator extends ImpulseActuator {

    public MoveForwardImpulseActuator(Game game, PlayerDisc playerDisc){
        super("MoveHorizontallyTowardsOpposingGoalImpulseActuator", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        Direction direction = new Direction(-1,0);
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME){
            direction = new Direction(1, 0);
        }
        return new Impulse(direction, 1);
    }
}
