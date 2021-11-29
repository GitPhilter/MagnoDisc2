package game.physics;

public class Direction {
    private double x;
    private double y;

    public Direction(double x, double y){
        this.x = x;
        this.y = y;
        normalize();
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

    public void setY(double y){
        //System.out.println("Direction y set to: " + y);
        this.y = y;
    }

    /**
     * normalizes the vector, so that its length becomes 1
     */
    private void normalize(){
        //System.out.println("### Normalizing! ###");
        double abs = Math.sqrt(this.x * this.x + this.y * this.y);
        double newX = this.x / abs;
        double newY = this.y / abs;;
        if(this.y <= 0.1 && this.y >= -0.1){
            this.setY(0.0);
        } else {
            this.setY(newY);
        }
        if(this.x <= 0.1 && this.x >= -0.1){
            this.setX(0.0);
        } else {
            this.setX(newX);
        }
    }

    public void addDirection(Direction direction){
        this.x = this.x + direction.getX();
        this.y = this.y + direction.getY();
        normalize();
    }

    public double getLength(){
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
