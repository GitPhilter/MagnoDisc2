package training.behaviourgenerator;

import behaviour.shotactuator.ShotActuator;
import game.physics.Direction;
import game.physics.Shot;

public class FinalShotActuator extends ShotActuator {
    private final double xDir;
    private final double yDir;
    private final double speed;
    boolean empty;


    public FinalShotActuator(double xDir, double yDir, double speed, boolean empty){
        super("FinalShotActuator", null, null);
        this.xDir = xDir;
        this.yDir = yDir;
        this.speed = speed;
        this.empty = empty;
    }

    public Shot getShot() {
        if(empty) return null;
        return new Shot(new Direction(xDir, yDir), speed);
    }

    public String getRepresentationString() {
        if (empty) return "[]";
        return "[(" + xDir + "," + yDir + ")," + speed + "]";
    }
}
