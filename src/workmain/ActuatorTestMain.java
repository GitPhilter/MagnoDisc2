package workmain;

import behaviour.puckmoveactuator.PuckMoveActuator;
import behaviour.puckmoveactuator.implementations.attack.MovePuckTowardsOpposingGoalPuckMoveActuator;
import game.game.Game;
import game.PlayerDisc;
import game.Puck;
import game.TeamEnum;
import game.physics.Position;
import game.teamfactory.TeamFactory;
import training.behaviourgenerator.FinalBehaviour;
import training.behaviourgenerator.RandomFinalActuatorGenerator;
import training.behaviourgenerator.RandomFinalBehaviourGenerator;

public class ActuatorTestMain {

    public static void main(String[] args){
        Game game = new Game();
        PlayerDisc playerDisc = new PlayerDisc("Klaus", game);
        for(int i = 0; i < 100; ++i){
            FinalBehaviour fb = RandomFinalBehaviourGenerator.getRandomFinalBehaviour(playerDisc);
            System.out.println(fb.getRepresentation());
        }
    }

}
