package behaviour.implementations.general;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.attack.AttackImpulseActuatorOne;
import behaviour.impulseactuator.implementations.general.MoveBackImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.puckmoveactuator.implementations.attack.AttackPuckMoveActuatorOne;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import behaviour.shotactuator.implementations.attack.AttackShotActuatorOne;
import game.Game;
import game.PlayerDisc;

public class MoveBackBehaviour extends Behaviour {

    public MoveBackBehaviour(Game game, PlayerDisc playerDisc){
        super("MoveBackBehaviour", game, playerDisc,
                new MoveBackImpulseActuator(game, playerDisc), new EmptyShotActuator(game, playerDisc),
                new EmptyPuckMoveActuator(game, playerDisc));
    }
}
