package game.physics;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public void setY(double y){
        this.y = y;
    }

    public double getDistance(Position b){
        if(b == null) return -1;
        double xDist = this.x - b.getX();
        double yDist = this.y - b.getY();
        double result = Math.sqrt(xDist*xDist + yDist*yDist);
        //System.out.println("Returning distance: " + result);
        return result;
    }

    public Direction getDirection(Position b){
        if(b == null) return null;
        double xDir = b.getX() - x;
        double yDir = b.getY() - y;
        return new Direction(xDir, yDir);
    }

}
