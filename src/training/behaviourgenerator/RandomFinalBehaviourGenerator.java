package training.behaviourgenerator;

import game.PlayerDisc;

public final class RandomFinalBehaviourGenerator{

    public static FinalBehaviour getRandomFinalBehaviour(PlayerDisc playerDisc){
        return new FinalBehaviour(RandomFinalActuatorGenerator.getRandomFinalImpulseActuator(),
                RandomFinalActuatorGenerator.getRandomFinalPuckMoveActuator(playerDisc),
                RandomFinalActuatorGenerator.getRandomFinalShotActuator());
    }

}
