package behaviour.implementations.attack.score;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.EmptyImpulseActuator;
import behaviour.impulseactuator.implementations.general.MoveForwardImpulseActuator;
import behaviour.puckmoveactuator.implementations.attack.MovePuckTowardsOpposingGoalPuckMoveActuator;
import behaviour.shotactuator.implementations.attack.ShootAccordingToDistanceFromOpposingSideShotActuator;
import game.Game;
import game.PlayerDisc;

public class StandardScoreBehaviour extends Behaviour {

    public StandardScoreBehaviour(Game game, PlayerDisc playerDisc){
        super("StandardScoreBehaviour", game, playerDisc,
                new MoveForwardImpulseActuator(game, playerDisc),
                new ShootAccordingToDistanceFromOpposingSideShotActuator(game, playerDisc),
                new MovePuckTowardsOpposingGoalPuckMoveActuator(game, playerDisc));
    }


}
