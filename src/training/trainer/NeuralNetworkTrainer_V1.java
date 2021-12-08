package training.trainer;

import game.Team;
import game.TeamEnum;
import game.teamfactory.TrainingTeamFactory;
import sort.Merge;
import training.neuralnetwork.NeuralNetwork;
import training.perception.DataSet;
import training.perception.Perception;
import training.perception.PerceptionToDataset;
import training.traininggame.TrainingGame;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkTrainer_V1 extends NeuralNetworkTrainer{
    int numberOfTicksPerBatch = 40000;
    int numberOfLearningEpochsPerBatch = 4000;

    public NeuralNetworkTrainer_V1(){
        super();
    }

    public NeuralNetworkTrainer_V1(NeuralNetwork neuralNetwork){
        super(neuralNetwork);
    }

    public void trainingSession(int numberOfBatches){
        if(neuralNetwork == null){
            System.out.println("NeuralNetworkTrainer_V1.trainingSession(): neuralNetwork not set and equals null!");
            return;
        }
        for(int i = 0; i < numberOfBatches; ++i){
            System.out.println("NeuralNetworkTrainer_V1.trainingSession(): creating batch no. " + (i + 1) + " / " + numberOfBatches);
            batch();
        }
    }

    public void batch(){
        if(neuralNetwork == null){
            System.out.println("NeuralNetworkTrainer_V1.batch(): neuralNetwork not set and equals null!");
            return;
        }
        TrainingGame trainingGame = new TrainingGame(numberOfTicksPerBatch);
        Team homeTeam = TrainingTeamFactory.createRandomTeam(3, trainingGame, TeamEnum.HOME);
        Team awayTeam = TrainingTeamFactory.createRandomTeam(3, trainingGame, TeamEnum.AWAY);
        trainingGame.setHomeTeam(homeTeam);
        trainingGame.setAwayTeam(awayTeam);
        trainingGame.run();
        // get positive perceptions
        Perception[] perceptions = trainingGame.getPerceptions();
        perceptions = getPerceptionsWithPositiveHeuristicGain(perceptions);
        // train the neuralnetwork
        DataSet[] dataSets = PerceptionToDataset.getDataSets(perceptions);
        if(dataSets.length == 0) {
            //System.out.println("NeuralNetworkTrainer_V1.batch(): No positive Perceptions found. returning!");
            return;
        }
        neuralNetwork.fit(dataSets, numberOfLearningEpochsPerBatch);

    }

    private Perception[] getPerceptionsWithPositiveHeuristicGain(Perception[] perceptions){
        List<Perception> positivePerceptions = new ArrayList<>();
        for(Perception p : perceptions){
            if(p.getResultingHeuristicValue() - p.getInitialHeuristicValue() > 0){
                positivePerceptions.add(p);
            }
        }
        Perception[] resultPerceptions = new Perception[positivePerceptions.size()];
        for(int i = 0; i < resultPerceptions.length; ++i){
            resultPerceptions[i] = positivePerceptions.get(i);
        }
        return resultPerceptions;
    }
}
