package anglecalculator;

import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;
import geometry.AngleCalculator;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AngleCalculatorTest {

    @Test
    public void getAngleFromTwoPositionsTest(){
        Position position_1 = new Position(100,200);
        Position position_2 = new Position(200,100);
        Assertions.assertEquals(45, AngleCalculator.getAngleFromTwoPositions(position_1, position_2));
        Assertions.assertEquals(225, AngleCalculator.getAngleFromTwoPositions(position_2, position_1));
    }

    @Test
    public void getAngleFromDirectionTest(){
        // get angle with north reference
        Direction direction = new Direction(0.0, -1.0);
        Assertions.assertEquals(0, AngleCalculator.getAngleFromDirection(direction));
        direction = new Direction(1, -1);
        Assertions.assertEquals(45, AngleCalculator.getAngleFromDirection(direction));
        direction = new Direction(1,0);
        Assertions.assertEquals(90, AngleCalculator.getAngleFromDirection(direction));
        direction = new Direction(0,1);
        Assertions.assertEquals(180, AngleCalculator.getAngleFromDirection(direction));
        direction = new Direction(-1,0);
        Assertions.assertEquals(270, AngleCalculator.getAngleFromDirection(direction));
        // get angle with freely chosen reference
        Direction reference = new Direction(1,1);
        direction = new Direction(-1, 0);
        Assertions.assertEquals(135, AngleCalculator.getAngleFromDirection(reference, direction));
        reference = new Direction(1,1);
        direction = new Direction(1, 0);
        Assertions.assertEquals(315, AngleCalculator.getAngleFromDirection(reference, direction));

    }

    @Test
    public void twoDirectionsAreIdenticalTest(){
        Direction a = new Direction(0,0);
        Direction b = new Direction(0.0001, -0.00045);
        Assertions.assertEquals(true,AngleCalculator.twoDirectionsAreIdentical(a,b));
    }

    @Test
    public void getRotationByDegreesClockwiseTest(){
        double clampMargin = 0.001;
        //
        Direction direction = new Direction(1, -1);
        Direction expectedDirection = new Direction (1, 0);
        Direction actualDirection = AngleCalculator.getRotationByDegreesClockwise(direction, 45);
        Assertions.assertEquals(expectedDirection.getX(), actualDirection.getX());
        Assertions.assertEquals(expectedDirection.getY(), actualDirection.getY());
        //
        direction = new Direction(-1, 1);
        expectedDirection = new Direction(-1, -1);
        actualDirection = AngleCalculator.getRotationByDegreesClockwise(direction, 90);
        double xDiff = Math.abs(expectedDirection.getX() - actualDirection.getX());
        if(xDiff < clampMargin) xDiff = 0.0;
        double yDiff = Math.abs(expectedDirection.getY() - actualDirection.getY());
        if(yDiff < clampMargin) yDiff = 0.0;
        Assertions.assertEquals(0.0, xDiff);
        Assertions.assertEquals(0.0, yDiff);
        //
        direction = new Direction(-1, 0);
        expectedDirection = new Direction(0, -1);
        actualDirection = AngleCalculator.getRotationByDegreesClockwise(direction, 90);
        xDiff = Math.abs(expectedDirection.getX() - actualDirection.getX());
        if(xDiff < clampMargin) xDiff = 0.0;
        yDiff = Math.abs(expectedDirection.getY() - actualDirection.getY());
        if(yDiff < clampMargin) yDiff = 0.0;
        Assertions.assertEquals(0.0, xDiff);
        Assertions.assertEquals(0.0, yDiff);
    }

    @Test
    public void getHalfCircleAngleTest(){
        Direction reference = new Direction(1,1);
        Direction direction = new Direction(1, 0);
        Assertions.assertEquals(45, AngleCalculator.getHalfCircleAngle(direction,reference));
        Assertions.assertEquals(45, AngleCalculator.getHalfCircleAngle(reference,direction));
        //
        reference = new Direction(0,-1);
        direction = new Direction(0, 1);
        Assertions.assertEquals(180, AngleCalculator.getHalfCircleAngle(direction,reference));
        Assertions.assertEquals(180, AngleCalculator.getHalfCircleAngle(reference,direction));
        //
        reference = new Direction(0.6,1);
        direction = new Direction(0.6, 1);
        Assertions.assertEquals(0, AngleCalculator.getHalfCircleAngle(direction,reference));
        Assertions.assertEquals(0, AngleCalculator.getHalfCircleAngle(reference,direction));
        //
        reference = new Direction(-1,1);
        direction = new Direction(1, 0);
        Assertions.assertEquals(135, AngleCalculator.getHalfCircleAngle(direction,reference));
        Assertions.assertEquals(135, AngleCalculator.getHalfCircleAngle(reference,direction));
    }

    @Test
    public void getExpectedYFromDirectionAndXTest(){
        //
        Direction direction = new Direction(1, -1);
        double checkX = 3;
        Assertions.assertEquals(-3, AngleCalculator.getExpectedYFromDirectionAndX(direction, checkX));
        //
        direction = new Direction(-0.5, 0.8);
        checkX = 10;
        Assertions.assertEquals(-6.25, AngleCalculator.getExpectedYFromDirectionAndX(direction, checkX));
        //
        direction = new Direction(1, -1);
        checkX = 5;
        Assertions.assertEquals(-5, AngleCalculator.getExpectedYFromDirectionAndX(direction, checkX));
    }

    public void getDirectionFromAngleTest(){

    }

    public void isLegalShotTest(){

    }
}
