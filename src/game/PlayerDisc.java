package game;

import behaviour.Behaviour;
import behaviour.puckmoveactuator.PuckMove;
import behaviourmanager.BehaviourManager;
import behaviourmanager.implementations.EmptyBehaviourManager;
import behaviourmanager.implementations.StandardBehaviourManager;
import game.physics.*;
import geometry.AngleCalculator;

import java.awt.*;
import java.util.Scanner;

public class PlayerDisc {
    private final String name;
    int width; // game width
    int height; // game height
    private Position position;
    private double speed;
    private Direction direction;
    private boolean hasPuck;
    private Puck puck;
    private int puckDirection; //direction that the puck is in relative to the playerDisc's center
    Color basicColor = Color.YELLOW;
    Color hasPuckColor = Color.BLUE;
    int radius = 20;
    int innerRadius = (int)(0.8 * radius);
    // moving properties
    double maxSpeed = 4;
    double maxAcceleration = 1;
    double maxBreak = 1;
    Game game;
    // movement
    double newX = -1;
    double newY = -1;
    double oldX = -1;
    double oldY = -1;
    double newPuckX = -1;
    double newPuckY = -1;
    // behaviour
    Position defaultPosition;
    Team team;
    BehaviourManager behaviourManager;
    // output
    boolean verbose = false;

    public PlayerDisc(String name, Game game){
        this.name = name;
        this.width = game.getWidth();
        this.height = game.getHeight();
        this.position = new Position(-1, -1);
        this.speed = 0;
        this.direction = new Direction(0, 0);
        this.hasPuck = false;
        this.puck = null;
        this.puckDirection = -1;
        this.game = game;
        //
        this.defaultPosition = null;
        this.team = null;
        behaviourManager = new StandardBehaviourManager(game, this);
    }

    public PlayerDiscActuation getActuation(Game game){
        // update behaviour manager
        Behaviour behaviour = behaviourManager.getNextBehaviour();
        if(game.isWaiting()){
            behaviour = behaviourManager.getWaitBehaviour();
        }
        // impulse
        Impulse impulse = behaviour.getImpulse();
        // shot
        Shot shot = behaviour.getShot();
        // discmove
        PuckMove puckMove = behaviour.getPuckMove();
        //
        return new PlayerDiscActuation(impulse, shot, puckMove);
    }

    public void movePlayerDisc(){
        if(hasPuck) {
            movePlayerDiscWithPuck();
            return;
        }
        int oldX = (int)newX;
        int oldY = (int)newY;
        newX = (int)(position.getX() + direction.getX() * speed);
        newY = (int)(position.getY() + direction.getY() * speed);
        if(newX <= radius){
            newX = radius;
        } else if(newX >= width - radius){
            newX = width - radius;
        }
        if(newY <= radius){
            newY = radius;
        } else if(newY >= height - radius){
            newY = height - radius;
        }
        if(!isLegalPosition(newX, newY)){
            newX = oldX;
            newY = oldY;
        }
        this.setPosition(new Position((int)newX, (int)newY));
    }

    private void movePlayerDiscWithPuck(){
        oldX = newX;
        oldY = newY;
        double oldPuckX = newPuckX;
        double oldPuckY = newPuckY;
        newX = position.getX() + direction.getX() * speed;
        newY = position.getY() + direction.getY() * speed;
        newPuckX = newX + AngleCalculator.getDirectionFromAngle(puckDirection).getX()  * (radius + puck.getRadius());
        newPuckY = newY + AngleCalculator.getDirectionFromAngle(puckDirection).getY() * (radius + puck.getRadius());
        if(newX <= radius){
            newX = radius;
        } else if(newX >= width - radius){
            newX = width - radius;
        }
        if(newY <= radius){
            newY = radius;
        } else if(newY >= height - radius){
            newY = height - radius;
        }
        if(!PuckGoalCheck.canMoveLeft(game, puck)){
            double difference = (double)puck.getRadius()  - newPuckX;
            newX = newX + difference;
        } else if(!PuckGoalCheck.canMoveRight(game, puck)){
            double difference = newPuckX - (width - (double)puck.getRadius());
            newX = newX - difference;
        }
        if(newPuckY <= puck.getRadius()){
            double difference = (double)puck.getRadius()  - newPuckY;
            newY = newY + difference;
        } else if(newPuckY >= height - puck.getRadius()){
            double difference = newPuckY - (height - (double)puck.getRadius());
            newY = newY - difference;
        }
        if(!isLegalPosition(newX, newY)){
            newX = oldX;
            newY = oldY;
            newPuckX = oldPuckX;
            newPuckY = oldPuckY;
        }
        puck.setPosition(new Position((int)newPuckX, (int)newPuckY));
        this.setPosition(new Position((int)newX, (int)newY));
    }

    public boolean isLegalPosition(double x, double y){
        for(PlayerDisc playerDisc : game.getPlayerDiscs()){
            if(playerDisc != this){
                Position newTempPosition = new Position(x, y);
                double distance = newTempPosition.getDistance(playerDisc.getPosition());
                if(distance < radius + playerDisc.getRadius() - 1){
                    // TODO: experimental
                    //Direction correctionDirection = playerDisc.getPosition().getDirection(position);
                    //double diff = radius + playerDisc.getRadius() - (position.getDistance(playerDisc.getPosition()) - 1);
                    //System.out.println("diff:" + diff);
                    //oldX = position.getX() + correctionDirection.getX() * diff;
                    //newY = position.getY() + correctionDirection.getY() * diff;
                    //
                    transferImpulse(playerDisc);
                    return false;
                }
                if(hasPuck){
                    Position puckPosition = new Position(newPuckX, newPuckY);
                    distance = puckPosition.getDistance(playerDisc.getPosition());
                    if(distance < puck.getRadius() + playerDisc.getRadius() - 1){
                        transferImpulse(playerDisc);
                        return false;
                    }
                }
            }
        }
        // check puck
        Puck gamePuck = game.getPuck();
        if(!hasPuck && gamePuck.getControllingPlayerDisc() != null){
            Position newTempPosition = new Position(x, y);
            double distance = newTempPosition.getDistance(gamePuck.getPosition());
            if(distance < radius + gamePuck.getRadius() - 1){
                transferImpulse(gamePuck.getControllingPlayerDisc());
                return false;
            }
        }
        return true;
    }

    private void transferImpulse(PlayerDisc playerDisc){
        double xRandom = Math.random();
        double yRandom = Math.random();
        Direction randomDirection = new Direction(xRandom, yRandom);
        randomDirection.addDirection(direction);
        playerDisc.getDirection().addDirection(randomDirection);
    }

    @Override
    public String toString(){
        String result = this.name + "\n";
        result += "Position: (" + this.position.getX() + ", " + this.position.getY() + ")\n";
        result += "Direction: (" + this.direction.getX() + ", " + this.direction.getY() + ")\n";
        result += "Speed: " + this.speed + "\n";
        result += "Has puck: " + this.hasPuck;
        return result;
    }

    public void movePuckClockwise(){
        //System.out.println("movePuckClockwise()");
        //System.out.println("Current puckDirection: " + puckDirection);
        //System.out.println("Current puck position: " + puck.getPosition());
        puckDirection = puckDirection + 10;
        if(puckDirection > 359) puckDirection = 0;
        //double x = newY + AngleCalculator.getDirectionFromAngle(puckDirection).getX() * (radius + puck.getRadius());
        //double y = newY + AngleCalculator.getDirectionFromAngle(puckDirection).getY() * (radius + puck.getRadius());
        //setCorrectedNewXYWithPuck();
        //puck.setPosition(new Position(x, y));
    }

    public void movePuckCounterClockwise(){
        //System.out.println("movePuckCounterClockwise()");
        puckDirection = puckDirection - 10;
        if(puckDirection < 0) puckDirection = 359;
        //double x = newY + AngleCalculator.getDirectionFromAngle(puckDirection).getX() * (radius + puck.getRadius());
        //double y = newY + AngleCalculator.getDirectionFromAngle(puckDirection).getY() * (radius + puck.getRadius());
        //setCorrectedNewXYWithPuck();
        //puck.setPosition(new Position(x, y));
    }

    /////////////////////////
    //                     //
    //  Getters & Setters  //
    //                     //
    /////////////////////////

    public String getName(){
        return name;
    }

    public Position getPosition(){
        return position;
    }

    public double getSpeed(){
        return speed;
    }

    public void setTeam(Team team){
        this.team = team;
    }

    public Team getTeam(){
        return team;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean hasPuck(){
        return hasPuck;
    }

    public void setPosition(Position position){
        //System.out.println("Position set to " + position.toString());
        this.position = position;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void setPuck(Puck puck) {
        if(puck == null) return;
        this.puck = puck;
        this.puckDirection = AngleCalculator.getAngleFromTwoPositions(position, puck.getPosition());
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setHasPuck(boolean hasPuck){
        this.hasPuck = hasPuck;
    }

    public int getRadius(){
        return radius;
    }

    public int getInnerRadius(){
        return innerRadius;
    }

    public Color getBasicColor(){
        return basicColor;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    public double getMaxBreak() {
        return maxBreak;
    }

    public Color getHasPuckColor() {
        return hasPuckColor;
    }

    public Puck getPuck(){
        return puck;
    }

    public int getPuckDirection(){
        return puckDirection;
    }

    public void setDefaultPosition(Position position){
        defaultPosition = position;
    }

    public Position getDefaultPosition(){
        return defaultPosition;
    }
}
