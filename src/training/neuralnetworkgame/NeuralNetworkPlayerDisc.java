package training.neuralnetworkgame;

import behaviour.puckmoveactuator.PuckMove;
import game.PlayerDisc;
import game.game.Game;
import game.physics.Impulse;
import game.physics.PlayerDiscActuation;
import game.physics.Shot;
import training.behaviourgenerator.FinalBehaviour;
import training.behaviourgenerator.RandomFinalBehaviourGenerator;
import training.gamestate.GameState;
import training.neuralnetwork.NeuralNetwork;
import training.perception.Perception;
import training.perception.PerceptionToDataset;

import java.util.List;

public class NeuralNetworkPlayerDisc extends PlayerDisc {
    NeuralNetwork neuralNetwork;
    private final int ticksPerFinalBehaviour = 40;
    private int currentTickCycle;
    FinalBehaviour finalBehaviour;

    public NeuralNetworkPlayerDisc(String name, Game game, NeuralNetwork neuralNetwork){
        super(name, game);
        this.neuralNetwork = neuralNetwork;
        this.currentTickCycle = 0;
        this.finalBehaviour = null;
    }

    public FinalBehaviour getFinalBehaviourFromList(List<Double> doubles, PlayerDisc playerDisc){
        double[] doublesArray = new double[doubles.size()];
        for(int i = 0; i < doublesArray.length; ++i){
            doublesArray[i] = doubles.get(i);
        }
        return new FinalBehaviour(doublesArray, playerDisc);
    }

    @Override
    public PlayerDiscActuation getActuation(Game game){
        if(finalBehaviour == null){
            double[] inputData = PerceptionToDataset.getInputDoublesFromGameState(new GameState(game), name);
            finalBehaviour = getFinalBehaviourFromList(neuralNetwork.predict(inputData), this);
        }
        // update behaviour manager
        ++currentTickCycle;
        if(currentTickCycle >= ticksPerFinalBehaviour) {
            double[] inputData = PerceptionToDataset.getInputDoublesFromGameState(new GameState(game), name);
            finalBehaviour = getFinalBehaviourFromList(neuralNetwork.predict(inputData), this);
            System.out.println("NeuralNetworkPlayerDisc.getActuation(): setting finalBehaiour: " + finalBehaviour.getRepresentationString());
            currentTickCycle = 0;
        }
        // impulse
        Impulse impulse = finalBehaviour.getImpulse();
        // shot
        Shot shot = finalBehaviour.getShot();
        // discmove
        PuckMove puckMove = finalBehaviour.getPuckMove();
        //
        return new PlayerDiscActuation(impulse, shot, puckMove);
    }
}
