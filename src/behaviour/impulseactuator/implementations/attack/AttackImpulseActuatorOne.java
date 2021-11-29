package behaviour.impulseactuator.implementations.attack;

import behaviour.impulseactuator.ImpulseActuator;
import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Impulse;
import game.physics.Position;

public class AttackImpulseActuatorOne extends ImpulseActuator {

    public AttackImpulseActuatorOne(Game game, PlayerDisc playerDisc){
        super("AttackImpulseActuatorOne", game, playerDisc);
    }

    @Override
    public Impulse getImpulse(){
        double goalX = -1;
        double goalY = (double)game.getHeight() / 2;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME){
            goalX = 800;
        } else {
            goalX = 0;
        }
        Position goalPosition = new Position(goalX, goalY);
        Direction direction = null;
        direction = playerDisc.getPosition().getDirection(goalPosition);
        return new Impulse(direction, 1);
    }

}
