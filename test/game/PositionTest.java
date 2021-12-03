package game;

import game.physics.Direction;
import game.physics.Position;
import geometry.AngleCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void getDistanceTest(){
        double clampMargin = 0.01;
        //
        Position position_1 = new Position(100, 500);
        Position position_2 = new Position(200, 700);
        double expected = 223.6067977;
        double actual = position_1.getDistance(position_2);
        double difference = Math.abs(actual - expected);
        if(difference < clampMargin) difference = 0.0;
        Assertions.assertEquals(0, difference);
        actual = position_2.getDistance(position_1);
        difference = Math.abs(actual - expected);
        if(difference < clampMargin) difference = 0.0;
        Assertions.assertEquals(0, difference);
        //
        position_1 = new Position(17, 4);
        position_2 = new Position(6, -3);
        expected = 13.03840481;
        actual = position_1.getDistance(position_2);
        difference = Math.abs(actual - expected);
        if(difference < clampMargin) difference = 0.0;
        Assertions.assertEquals(0, difference);
        actual = position_2.getDistance(position_1);
        difference = Math.abs(actual - expected);
        if(difference < clampMargin) difference = 0.0;
        Assertions.assertEquals(0, difference);
    }

    @Test
    public void getDirectionTest(){
        Position position_1 = new Position(100,200);
        Position position_2 = new Position(200, 100);
        Direction expectedDirection = new Direction(1, -1);
        Direction actualDirection = position_1.getDirection(position_2);
        Assertions.assertEquals(expectedDirection.getX(), actualDirection.getX());
        Assertions.assertEquals(expectedDirection.getY(), actualDirection.getY());
        expectedDirection = new Direction(-1, 1);
        actualDirection = position_2.getDirection(position_1);
        Assertions.assertEquals(expectedDirection.getX(), actualDirection.getX());
        Assertions.assertEquals(expectedDirection.getY(), actualDirection.getY());
        //
        position_1 = new Position(500,17);
        position_2 = new Position(500,500);
        expectedDirection = new Direction(0, 1);
        actualDirection = position_1.getDirection(position_2);
        Assertions.assertEquals(expectedDirection.getX(), actualDirection.getX());
        Assertions.assertEquals(expectedDirection.getY(), actualDirection.getY());
        expectedDirection = new Direction(0, -1);
        actualDirection = position_2.getDirection(position_1);
        Assertions.assertEquals(expectedDirection.getX(), actualDirection.getX());
        Assertions.assertEquals(expectedDirection.getY(), actualDirection.getY());
    }
}
