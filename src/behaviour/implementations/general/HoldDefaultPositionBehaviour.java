package behaviour.implementations.general;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.general.MoveBackImpulseActuator;
import behaviour.impulseactuator.implementations.general.MoveToDefaultPositionImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import game.Game;
import game.PlayerDisc;

public class HoldDefaultPositionBehaviour extends Behaviour {

    public HoldDefaultPositionBehaviour(Game game, PlayerDisc playerDisc){
        super("HoldDefaultPositionBehaviour", game, playerDisc,
                new MoveToDefaultPositionImpulseActuator(game, playerDisc), new EmptyShotActuator(game, playerDisc),
                new EmptyPuckMoveActuator(game, playerDisc));
    }

}
