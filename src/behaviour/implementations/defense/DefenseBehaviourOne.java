package behaviour.implementations.defense;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.defense.DefenseImpulseActuatorOne;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.implementations.defense.DefensePuckMoveActuatorOne;
import behaviour.shotactuator.implementations.defense.DefenseShotActuatorOne;
import game.game.Game;
import game.PlayerDisc;
import game.physics.Impulse;
import game.physics.Shot;

public class DefenseBehaviourOne extends Behaviour {

    public DefenseBehaviourOne(Game game, PlayerDisc playerDisc){
        super("DefenseBehaviourOne", game, playerDisc,
                new DefenseImpulseActuatorOne(game, playerDisc), new DefenseShotActuatorOne(game, playerDisc),
                new DefensePuckMoveActuatorOne(game, playerDisc));
    }

    @Override
    public Impulse getImpulse() {
        return impulseActuator.getImpulse();
    }

    @Override
    public Shot getShot() {
        return shotActuator.getShot();
    }

    @Override
    public PuckMove getPuckMove(){
        return puckMoveActuator.getPuckMove();
    }

}
