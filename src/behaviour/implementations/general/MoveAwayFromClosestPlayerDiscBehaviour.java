package behaviour.implementations.general;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.general.MoveAwayFromClosestPlayerDiscImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import game.game.Game;
import game.PlayerDisc;

public class MoveAwayFromClosestPlayerDiscBehaviour extends Behaviour {

    public MoveAwayFromClosestPlayerDiscBehaviour(Game game, PlayerDisc playerDisc){
        super("MoveAwayFromClosestPlayerDiscBehaviour", game, playerDisc,
                new MoveAwayFromClosestPlayerDiscImpulseActuator(game, playerDisc), new EmptyShotActuator(game, playerDisc),
                new EmptyPuckMoveActuator(game, playerDisc));
    }
}
