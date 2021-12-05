package behaviour.implementations.general;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.general.MoveForwardImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import game.game.Game;
import game.PlayerDisc;

public class MoveForwardBehaviour extends Behaviour {

    public MoveForwardBehaviour(Game game, PlayerDisc playerDisc){
        super("MoveForwardBehaviour", game, playerDisc,
                new MoveForwardImpulseActuator(game, playerDisc), new EmptyShotActuator(game, playerDisc),
                new EmptyPuckMoveActuator(game, playerDisc));
    }

}
