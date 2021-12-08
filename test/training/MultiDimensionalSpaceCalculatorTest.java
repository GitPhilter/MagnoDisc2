package training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import training.geometry.MultiDimensionalSpaceCalculator;

public class MultiDimensionalSpaceCalculatorTest {

    @Test
    public void getDistanceTest(){
        double clampMargin = 0.01;
        // 2D
        double[] point2D_1 = {0, 0};
        double[] point2D_2 = {1, 1};
        double expectedDistance = Math.sqrt(2);
        double actualDistance = MultiDimensionalSpaceCalculator.getDistance(point2D_1, point2D_2);
        double difference = Math.abs(expectedDistance - actualDistance);
        if(difference < clampMargin) difference = 0;
        Assertions.assertEquals(0, difference);
        // 3D
        double[] point3D_1 = {0, 0 , 0};
        double[] point3D_2 = {5, 5, 5};
        expectedDistance = Math.sqrt(75);
        actualDistance = MultiDimensionalSpaceCalculator.getDistance(point3D_1, point3D_2);
        difference = Math.abs(expectedDistance - actualDistance);
        if(difference < clampMargin) difference = 0;
        Assertions.assertEquals(0, difference);
        // invalid
        double[] sevenValues = {1, 2, 3, 4, 5, 6, 7};
        double[] eightValues = {1, 2, 3, 4, 5, 6, 7, 8};
        double distance = MultiDimensionalSpaceCalculator.getDistance(sevenValues, eightValues);
        Assertions.assertEquals(-1, distance);
    }
}
