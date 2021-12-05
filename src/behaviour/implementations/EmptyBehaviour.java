package behaviour.implementations;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.EmptyImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import game.game.Game;
import game.PlayerDisc;

public class EmptyBehaviour extends Behaviour {

    public EmptyBehaviour(Game game, PlayerDisc playerDisc){
        super("EmptyBehaviour", game, playerDisc, new EmptyImpulseActuator(game, playerDisc),
                new EmptyShotActuator(game, playerDisc), new EmptyPuckMoveActuator(game, playerDisc));
    }


}
