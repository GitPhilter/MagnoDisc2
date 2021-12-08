package workmain;

import game.game.Game;
import game.Team;
import game.TeamEnum;
import game.teamfactory.TeamFactory;
import game.teamfactory.TrainingTeamFactory;
import sort.Merge;
import training.neuralnetwork.NeuralNetwork;
import training.neuralnetwork.NeuralNetworkLoader;
import training.neuralnetwork.NeuralNetworkSaver;
import training.neuralnetworkgame.NeuralNetworkGame;
import training.perception.DataSet;
import training.perception.Perception;
import training.perception.PerceptionToDataset;
import training.trainer.NeuralNetworkTrainer;
import training.trainer.NeuralNetworkTrainer_V1;
import training.traininggame.TrainingGame;

import javax.xml.crypto.Data;

public class WorkMain {
    public static void main(String[] args){
        System.out.println("MagnoDisc2 - WorkMain");

        NeuralNetwork neuralNetwork = new NeuralNetwork(48, 52, 9);
        NeuralNetworkTrainer_V1 trainer = new NeuralNetworkTrainer_V1(neuralNetwork);
        trainer.trainingSession(10000);
        NeuralNetworkGame game = new NeuralNetworkGame(neuralNetwork);
        game.run();

    }
}
