package training.neuralnetwork;

import training.geometry.Matrix;
import training.perception.DataSet;

import java.util.List;

public class NeuralNetwork {
    Matrix weightsIH, weightsHO, biasH, biasO;
    double lRate = 0.01;

    public NeuralNetwork(int i, int h, int o){
        weightsIH = new Matrix(h,i);
        weightsHO = new Matrix(o,h);
        biasH = new Matrix(h,1);
        biasO = new Matrix(o,1);
    }

    public NeuralNetwork(Matrix weightsIH, Matrix weightsHO, Matrix biasH, Matrix biasO){
        // weightsIH
        this.weightsIH = new Matrix(weightsIH.rows, weightsIH.cols);
        this.weightsIH.setData(weightsIH.getData());
        // weightsHO
        this.weightsHO = new Matrix(weightsHO.rows, weightsHO.cols);
        this.weightsHO.setData(weightsHO.getData());
        // biasH
        this.biasH = new Matrix(biasH.rows, biasH.cols);
        this.biasH.setData(biasH.getData());
        // biasO
        this.biasO = new Matrix(biasO.rows, biasO.cols);
        this.biasO.setData(biasO.getData());
    }

    public List<Double> predict(double[] X){
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weightsIH, input);
        hidden.add(biasH);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weightsHO,hidden);
        output.add(biasO);
        output.sigmoid();

        return output.toArray();
    }

    public void train(double [] X,double [] Y) {
        //System.out.println("x size: " + X.length);
        //System.out.println("y size: " + Y.length);
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weightsIH, input);
        //System.out.println("hidden size: " + hidden.rows + " * " + hidden.cols);
        hidden.add(biasH);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weightsHO,hidden);
        output.add(biasO);
        output.sigmoid();

        Matrix target = Matrix.fromArray(Y);

        //System.out.println("target size: " + target.rows + " * " + target.cols);

        Matrix error = Matrix.subtract(target, output);
        //System.out.println("error size: " + error.rows + " * " + error.cols);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(lRate);

        Matrix hidden_T = Matrix.transpose(hidden);
        //System.out.println("hidden_T size: " + hidden_T.rows + " * " + hidden_T.cols);

        Matrix who_delta =  Matrix.multiply(gradient, hidden_T);

        weightsHO.add(who_delta);
        biasO.add(gradient);

        Matrix who_T = Matrix.transpose(weightsHO);
        Matrix hidden_errors = Matrix.multiply(who_T, error);

        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(lRate);

        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

        weightsIH.add(wih_delta);
        biasH.add(h_gradient);

    }

    public void fit(DataSet[] dataSets, int epochs) {
        for(int i = 0; i < epochs; i++){
            int sampleIndex =  (int)(Math.random() * dataSets.length);
            this.train(dataSets[sampleIndex].getInputVectors(), dataSets[sampleIndex].getExpectedOutputVectors());
        }
    }

    @Override
    public String toString(){
        String result = "NeuralNetwork-Matrices:\n";
        result += "weightsIH:\n" + weightsIH.toString() + "\n";
        result += "weightsHO:\n" + weightsHO.toString() + "\n";
        result += "biasH:\n" + biasH.toString() + "\n";
        result += "biasO:\n" + biasO.toString() + "\n";
        return result;
    }

    public Matrix getWeightsIH() {
        return weightsIH;
    }

    public Matrix getWeightsHO() {
        return weightsHO;
    }

    public Matrix getBiasH() {
        return biasH;
    }

    public Matrix getBiasO() {
        return biasO;
    }

    public double getlRate() {
        return lRate;
    }
}
