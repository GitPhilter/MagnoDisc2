package geometry;

import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;

import java.awt.*;

public final class AngleCalculator {

    public static int getAngleFromTwoPositions(Position a, Position b){
        Direction direction = a.getDirection(b);
        double result = getAngleFromDirection(direction);
        return (int)result;
    }

    public static double getAngleFromDirection(Direction direction){
        Direction northReference = new Direction(0, -1);
        return getAngleFromDirection(northReference, direction);
    }

    public static double getAngleFromDirection(Direction referenceDirection, Direction direction){
        double result = getHalfCircleAngle(direction, referenceDirection);
        Direction rotatedDirection = getRotationByDegreesClockwise(direction, result);
        if(result == 360.0) return 0.0;
        if(result == 0.0) return 0.0;
        //System.out.println("direction: " + direction);
        //System.out.println("rotatedDirection: " + rotatedDirection);
        //System.out.println("referenceDirection: " + referenceDirection);
        if(twoDirectionsAreIdentical(rotatedDirection, referenceDirection)){
            //System.out.println("The two directions are considered identical!");
            //System.out.println("returning: 360 - " + result);
            return 360 - result;
        }
        return result;
    }

    public static boolean twoDirectionsAreIdentical(Direction a, Direction b){
        double clampMargin = 0.1;
        double xDiff = Math.abs(a.getX() - b.getX());
        if(xDiff < clampMargin) xDiff = 0.0;
        double yDiff = Math.abs(a.getY() - b.getY());
        if(yDiff < clampMargin) yDiff = 0.0;
        if(xDiff == 0.0 && yDiff == 0.0) return true;
        //System.out.println(a + " & " + b + " are NOT identical!");
        return false;
    }

    public static Direction getRotationByDegreesClockwise(Direction direction, double degrees){
        degrees = degrees / 180 * Math.PI;
        double x = Math.cos(degrees) * direction.getX() - Math.sin(degrees) * direction.getY();
        double y = Math.sin(degrees) * direction.getX() + Math.cos(degrees) * direction.getY();
        return new Direction(x, y);
    }

    public static double getHalfCircleAngle(Direction direction, Direction referenceDirection){
        double product = direction.getX() * referenceDirection.getX() + direction.getY() * referenceDirection.getY();
        double lengthProduct = direction.getLength() * referenceDirection.getLength();
        double cosX = product / lengthProduct;
        return Math.acos(cosX) / (Math.PI) * 180;
    }

    public static double getExpectedYFromDirectionAndX(Direction direction, double x){
        if(direction.getY() == 0) return 0;
        return (x * direction.getX()) / direction.getY();
    }

    public static Direction getDirectionFromAngle(int angle){
        double angleDouble = ((double)angle / 180) * Math.PI;
        if(angleDouble < 0 || angleDouble > 359) return null;
        double xDir = Math.sin(angleDouble);
        double yDir = -Math.cos(angleDouble);
        return new Direction(xDir, yDir);
    }


    public static boolean isLegalShot(Shot shot, PlayerDisc playerDisc){
        Direction centerLineForAngleCalculation = playerDisc.getPosition().getDirection(playerDisc.getPuck().getPosition());
        int shotAngle = (int)AngleCalculator.getAngleFromDirection(shot.getDirection(), centerLineForAngleCalculation);
        return shotAngle < 45;
    }

}
