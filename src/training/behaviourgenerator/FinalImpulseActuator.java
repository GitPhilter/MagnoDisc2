package training.behaviourgenerator;

import behaviour.impulseactuator.ImpulseActuator;
import game.physics.Direction;
import game.physics.Impulse;

public class FinalImpulseActuator extends ImpulseActuator {
    private final double xDir;
    private final double yDir;
    private final double acceleration;

    public FinalImpulseActuator(double xDir, double yDir, double acceleration){
        super("FinalImpulseActuator", null, null);
        this.xDir = xDir;
        this.yDir = yDir;
        this.acceleration = acceleration;
    }

    @Override
    public Impulse getImpulse(){
        return new Impulse(new Direction(xDir, yDir), acceleration);
    }

    public String getRepresentationString(){
        return "[(" + xDir + "," + yDir + ")," + acceleration + "]";
    }

}
