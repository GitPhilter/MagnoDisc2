package behaviour.implementations.general;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.general.MoveForwardImpulseActuator;
import behaviour.impulseactuator.implementations.general.MoveTowardsPuckImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import game.Game;
import game.PlayerDisc;

public class MoveTowardsPuckBehaviour extends Behaviour {

    public MoveTowardsPuckBehaviour(Game game, PlayerDisc playerDisc){
        super("MoveTowardsPuckBehaviour", game, playerDisc,
                new MoveTowardsPuckImpulseActuator(game, playerDisc), new EmptyShotActuator(game, playerDisc),
                new EmptyPuckMoveActuator(game, playerDisc));
    }
}
