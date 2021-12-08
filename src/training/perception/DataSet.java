package training.perception;

public class DataSet {
    private double[] inputVectors;
    private double[] expectedOutputVectors;

    public DataSet(double[] inputVectors, double[] expectedOutputVectors){
        this.inputVectors = inputVectors;
        this.expectedOutputVectors = expectedOutputVectors;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < inputVectors.length; ++i){
            result += inputVectors[i] + "-";
        }
        result += "\n";
        for(int i = 0;i < expectedOutputVectors.length; ++i){
            result += expectedOutputVectors[i] + "-";
        }
        return result + "\n";
    }

    public double[] getInputVectors() {
        return inputVectors;
    }

    public double[] getExpectedOutputVectors() {
        return expectedOutputVectors;
    }
}
