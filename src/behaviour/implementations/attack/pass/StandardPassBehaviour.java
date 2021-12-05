package behaviour.implementations.attack.pass;

import behaviour.Behaviour;
import behaviour.impulseactuator.implementations.attack.MoveTowardsTeammateClosestToOpposingGoalImpulseActuator;
import behaviour.puckmoveactuator.implementations.attack.MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator;
import behaviour.shotactuator.implementations.attack.pass.PassPuckToTeammateClosestToOpposingGoalShotActuator;
import game.game.Game;
import game.PlayerDisc;

public class StandardPassBehaviour extends Behaviour {

    public StandardPassBehaviour(Game game, PlayerDisc playerDisc){
        super("StandardPassBehaviour", game, playerDisc,
                new MoveTowardsTeammateClosestToOpposingGoalImpulseActuator(game, playerDisc),
                new PassPuckToTeammateClosestToOpposingGoalShotActuator(game, playerDisc),
                new MovePuckTowardsPlayerClosestToOpposingGoalPuckMoveActuator(game, playerDisc));
    }

}
