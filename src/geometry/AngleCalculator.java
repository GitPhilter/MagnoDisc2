package geometry;

import game.PlayerDisc;
import game.physics.Direction;
import game.physics.Position;
import game.physics.Shot;

public final class AngleCalculator {

    public static int getAngleFromTwoPositions(Position a, Position b){
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        Direction direction = new Direction(x, y);
        double result = getAngleFromDirection(direction);
        //System.out.println("The angle between " + a.toString() + " and " + b.toString() + " is " + result + "!");
        return (int)result;
    }

    private static double getAngleFromDirection(Direction direction){
        Direction northReference = new Direction(0, -1);
        return getAngleFromDirection(direction, northReference);
    }

    private static double getAngleFromDirection(Direction direction, Direction referenceDirection){
        double product = direction.getX() * referenceDirection.getX() + direction.getY() * referenceDirection.getY();
        double lengthProduct = direction.getLength() * referenceDirection.getLength();
        //System.out.println("product: " + product);
        //System.out.println("lenthProduct: " + lengthProduct);
        //System.out.println("direction.getX(): " +direction.getX());
        double cosX = product / lengthProduct;
        double result = -1;
        if(direction.getX() < 0){
            result = Math.acos(cosX) / Math.PI * 180 + 180;
            //System.out.println("x < 0: result: " + result);

        } else{
            result = Math.acos(cosX) / (Math.PI) * 180;
        }
        if(result == 360) result = 0;
        return result;
    }

    public static Direction getDirectionFromAngle(int angle){
        double angleDouble = (double)angle / 180 * Math.PI;
        if(angleDouble < 0 || angleDouble > 359) return null;
        double xDir = Math.sin(angleDouble);
        double yDir = -Math.cos(angleDouble);
        //if((angle >= 270 || angle <= 45)){
        //if((angle >= 270 || angle <= 90)){
          //  yDir = -1 * yDir;
        //}
        Direction direction = new Direction(xDir, yDir);
        //System.out.println("returning direction : " + direction + ", angle: " + angle);
        return direction;
    }


    public static boolean isLegalShot(Shot shot, PlayerDisc playerDisc){
        Direction centerLineForAngleCalculation = playerDisc.getPuck().getPosition().getDirection(playerDisc.getPosition());
        int shotAngle = (int)AngleCalculator.getAngleFromDirection(shot.getDirection(), centerLineForAngleCalculation);
        //System.out.println("ShotAngle = " + shotAngle);
        if(shotAngle >= 0 && shotAngle <= 90) return true;
        if(shotAngle >= 270 && shotAngle <= 359) return true;
        //System.out.println("Illegal shot was taken!!! SKANDAL!!!!1");
        return false;
    }

}
