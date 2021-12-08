package training.behaviourgenerator;

import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;
import geometry.AngleCalculator;

public class FinalPuckMoveActuator extends PuckMoveActuator {
    private final double xDir;
    private final double yDir;

    public FinalPuckMoveActuator(PlayerDisc playerDisc, double xDir, double yDir) {
        super("FinalPuckMoveActuator", null, playerDisc);
        this.xDir = xDir;
        this.yDir = yDir;
    }

    @Override
    public PuckMove getPuckMove() {
        if (!playerDisc.hasPuck()) return PuckMove.NO_MOVE;
        Direction targetCopyDirection = new Direction(xDir, yDir);
        Direction puckDirection = AngleCalculator.getDirectionFromAngle(playerDisc.getPuckDirection());
        double angle = AngleCalculator.getAngleFromDirection(targetCopyDirection, puckDirection);
        //System.out.println("angle: " + angle);
        if (angle >= -0.5 && angle <= 0.5) {
            //System.out.println("returning NO_MOVE");
            return PuckMove.NO_MOVE;
        }
        if (angle > 180) {
            //System.out.println("returning CLOCKWISE");
            return PuckMove.CLOCKWISE;
        }
        //System.out.println("returning COUNTERCLOCKWISE");
        return PuckMove.COUNTERCLOCKWISE;
    }

    public String getRepresentationString() {
        return "[(" + xDir + "," + yDir + ")]";
    }

    public double getXDir() {
        return xDir;
    }

    public double getYDir() {
        return yDir;
    }
}
