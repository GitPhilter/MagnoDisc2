package workmain;

import game.physics.Position;
import geometry.AngleCalculator;

public class GeometryMain {

    public static void main(String[] args){
        Position a = new Position(0, 0);
        Position b = new Position(0, 1);
        AngleCalculator.getAngleFromTwoPositions(b, a);
        int angle = 90;
        AngleCalculator.getDirectionFromAngle(angle);
    }
}
