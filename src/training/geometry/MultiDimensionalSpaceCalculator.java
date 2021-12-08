package training.geometry;

public final class MultiDimensionalSpaceCalculator {

    public static double getDistance(double[] point_1, double[] point_2){
        if(point_1.length != point_2.length) return -1;
        double sum = 0;
        for(int i = 0; i < point_1.length; ++i){
            sum += Math.pow(point_1[i] - point_2[i], 2);
        }
        return Math.sqrt(sum);
    }

}
