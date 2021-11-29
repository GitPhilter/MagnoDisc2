package game.physics;

public class Shot {
    private Direction direction;
    private double speed;

    public Shot(Direction direction, double speed){
        this.direction = direction;
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString(){
        return "[(" + direction.getX() + ", " + direction.getY() + "), " + speed + "]";
    }
}
