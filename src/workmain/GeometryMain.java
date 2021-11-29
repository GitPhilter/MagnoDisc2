package workmain;

import game.physics.Direction;
import game.physics.Position;
import geometry.AngleCalculator;

public class GeometryMain {

    public static void main(String[] args){
        Direction dir1 = new Direction(-1, 0);
        Direction dir2 = new Direction(1, 0);
        int angle = (int)AngleCalculator.getAngleFromDirection(dir2, dir1);
        System.out.println("angle: " + angle);
    }
}
