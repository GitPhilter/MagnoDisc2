package game.physics;

public class Impulse {
    private final Direction direction;
    private final double acceleration;

    public Impulse(Direction direction, double acceleration){
        this.direction = direction;
        this.acceleration = acceleration;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getAcceleration() {
        return acceleration;
    }
}
