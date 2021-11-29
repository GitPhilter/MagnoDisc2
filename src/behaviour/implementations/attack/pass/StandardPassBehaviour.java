package behaviour.implementations.attack.pass;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.EmptyImpulseActuator;
import behaviour.impulseactuator.implementations.attack.MoveTowardsTeammateClosestToOpposingGoalImpulseActuator;
import behaviour.impulseactuator.implementations.general.MoveForwardImpulseActuator;
import behaviour.puckmoveactuator.implementations.EmptyPuckMoveActuator;
import behaviour.puckmoveactuator.implementations.attack.MovePuckTowardsOpposingGoalPuckMoveActuator;
import behaviour.puckmoveactuator.implementations.attack.MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator;
import behaviour.shotactuator.implementations.EmptyShotActuator;
import behaviour.shotactuator.implementations.attack.ShootAccordingToDistanceFromOpposingSideShotActuator;
import behaviour.shotactuator.implementations.attack.pass.PassPuckToTeammateClosestToOpposingGoalShotActuator;
import game.Game;
import game.PlayerDisc;

public class StandardPassBehaviour extends Behaviour {

    public StandardPassBehaviour(Game game, PlayerDisc playerDisc){
        super("StandardPassBehaviour", game, playerDisc,
                new MoveTowardsTeammateClosestToOpposingGoalImpulseActuator(game, playerDisc),
                new PassPuckToTeammateClosestToOpposingGoalShotActuator(game, playerDisc),
                new MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator(game, playerDisc));
    }

}
