package training.trainer;

import training.neuralnetwork.NeuralNetwork;
import training.neuralnetwork.NeuralNetworkLoader;
import training.neuralnetwork.NeuralNetworkSaver;

public abstract class NeuralNetworkTrainer {
    NeuralNetwork neuralNetwork;

    public NeuralNetworkTrainer(){
        neuralNetwork = null;
    }

    public NeuralNetworkTrainer(NeuralNetwork neuralNetwork){
        this.neuralNetwork = neuralNetwork;
    }

    /**
     * used to load NeuralNetworks only from the res/neuralnetworks folder!
     * @param path the path to the .nnf within the res/neuralnetworks folder.
     */
    public void loadNeuralNetwork(String path){
        String actualPath = "res/neuralnetworks/" + path;
        neuralNetwork = NeuralNetworkLoader.loadFile(actualPath);
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }


    /**
     *  used to save NeuralNetworks only to the res/neuralnetworks folder!
     * @param path the path to save the .nnf within the res/neuralnetworks folder.
     */
    public void saveNeuralNetwork(String path){
        String actualPath = "res/neuralnetworks/" + path;
        NeuralNetworkSaver.saveNeuralNetwork(neuralNetwork, actualPath);
    }



}
