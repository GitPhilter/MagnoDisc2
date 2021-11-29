package game;

import game.physics.Direction;
import game.physics.Position;

import java.awt.*;

public class Puck {
    private Position position;
    private Direction direction;
    private double speed;
    private int radius = 10;
    Color basicColor = Color.DARK_GRAY;
    private PlayerDisc controllingPlayerDisc;

    public Puck(){
        this.position = null;
        this.direction = new Direction(0.0,0.0);
        this.speed = 0.0;
        this.controllingPlayerDisc = null;
    }

    public void setPosition(Position position){
        //System.out.println("puck position set to: " + position.toString());
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public Color getBasicColor() {
        return basicColor;
    }

    public PlayerDisc getControllingPlayerDisc() {
        return controllingPlayerDisc;
    }

    public int getRadius(){
        return radius;
    }

    public void setControllingPlayerDisc(PlayerDisc controllingPlayerDisc){
        this.controllingPlayerDisc = controllingPlayerDisc;
    }
}
