package game;

import behaviour.Behaviour;
import behaviour.puckmoveactuator.PuckMove;
import behaviourmanager.BehaviourManager;
import behaviourmanager.implementations.EmptyBehaviourManager;
import game.physics.*;
import geometry.AngleCalculator;

import java.awt.*;

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
    double maxSpeed = 2;
    double maxAcceleration = 0.5;
    double maxBreak = 1;
    Game game;
    // movement
    double newX = -1;
    double newY = -1;
    double newPuckX = -1;
    double newPuckY = -1;
    // behaviour
    Team team;
    BehaviourManager behaviourManager;

    public PlayerDisc(String name, int width, int height, Game game){
        this.name = name;
        this.width = width;
        this.height = height;
        this.position = new Position(-1, -1);
        this.speed = 0;
        this.direction = new Direction(0, 0);
        this.hasPuck = false;
        this.puck = null;
        this.puckDirection = -1;
        this.game = game;
        //
        this.team = null;
        behaviourManager = new EmptyBehaviourManager(game, this);
    }

    public PlayerDiscActuation getActuation(Game game){
        // update behaviour manager
        Behaviour behaviour = behaviourManager.getNextBehaviour();
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
        newX = position.getX() + direction.getX() * speed;
        newY = position.getY() + direction.getY() * speed;
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
        setCorrectedNewXY();
        this.setPosition(new Position(newX, newY));
    }

    private void movePlayerDiscWithPuck(){
        //puck.setDirection(direction);
        //puck.setSpeed(speed);
        newX = position.getX() + direction.getX() * speed;
        newY = position.getY() + direction.getY() * speed;
        //newPuckX = puck.getPosition().getX() + puck.getDirection().getX() * puck.getSpeed();
        //newPuckY = puck.getPosition().getY() + puck.getDirection().getY() * puck.getSpeed();
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
        } else if(newPuckX >= width - puck.getRadius()){
            double difference = newPuckX - (width - (double)puck.getRadius());
            newX = newX - difference;
        }
        if(newPuckY <= puck.getRadius()){
            double difference = (double)puck.getRadius()  - newPuckY;
            newY = newY + difference;
        } else if(newPuckY >= height - puck.getRadius()){
            double difference = newPuckY - (width - (double)puck.getRadius());
            newY = newY - difference;
        }


        //
        setCorrectedNewXYWithPuck();
        puck.setPosition(new Position(newPuckX, newPuckY));
        this.setPosition(new Position(newX, newY));
    }

    private void setCorrectedNewXY(){
        double correctedNewX = newX;
        double correctedNewY = newY;
        for(PlayerDisc playerDisc : game.getPlayerDiscs()){
            if(this == playerDisc) break;
            double distance = position.getDistance(playerDisc.getPosition());
            //System.out.println("PlayerDisc.setCorrectedNewXY: distance=" + distance);
            if(distance <= radius + playerDisc.getRadius()){
                //System.out.println("newX and newY must be corrected!");
                Direction correctionDirection = playerDisc.getPosition().getDirection(position);
                double difference = (radius + playerDisc.getRadius()) - position.getDistance(playerDisc.getPosition());
                //System.out.println("difference: " + difference);
                correctedNewX += correctionDirection.getX() * difference;
                correctedNewY += correctionDirection.getY() * difference;
                Position deleteMePosition = new Position(newX, newY);
                double deleteMeDistance = deleteMePosition.getDistance(new Position(correctedNewX, correctedNewY));
                //System.out.println("The corrected distance is: " + deleteMeDistance);
                //System.out.println("PlayerDisc collides with other PlayerDisc! Correction distance: " + deleteMeDistance);
            }
        }
        // correct if puck controlled by other player is in the way
        Puck gamePuck = game.getPuck();
        if(gamePuck.getControllingPlayerDisc() != null){
            double puckDistance = position.getDistance(gamePuck.getPosition());
            if(puckDistance <= gamePuck.getRadius() + radius) {
                //System.out.println("player collides with owned puck: newX and newY must be corrected!");
                Direction correctionDirection = gamePuck.getPosition().getDirection(position);
                double difference = (gamePuck.getRadius() + radius) - position.getDistance(gamePuck.getPosition());
                //System.out.println("player collides: difference: " + difference);
                correctedNewX += correctionDirection.getX() * difference;
                correctedNewY += correctionDirection.getY() * difference;
                //Position deleteMePosition = new Position(correctedNewX, correctedNewY);
                //double deleteMeDistance = deleteMePosition.getDistance(gamePuck.getPosition());
                //Position debugPosition = new Position(newX, newY);
                //double debugDistance = debugPosition.getDistance(new Position(correctedNewX, correctedNewY));
                //System.out.println("PlayerDisc collides with Owned Puck! Correction distance: " + debugDistance);
            }
        }

        newX = correctedNewX;
        newY = correctedNewY;
    }

    private void setCorrectedNewXYWithPuck(){
        double correctedNewX = newX;
        double correctedNewY = newY;
        double correctedNewPuckX  = newPuckX;
        double correctedNewPuckY = newPuckY;
        for(PlayerDisc playerDisc : game.getPlayerDiscs()){
            if(this == playerDisc) break;
            double distance = position.getDistance(playerDisc.getPosition());
            //System.out.println("PlayerDisc.setCorrectedNewXY: distance=" + distance);
            if(distance <= radius + playerDisc.getRadius()){
                //System.out.println("newX and newY must be corrected!");
                Direction correctionDirection = playerDisc.getPosition().getDirection(position);
                double difference = (radius + playerDisc.getRadius()) - position.getDistance(playerDisc.getPosition());
                //System.out.println("difference: " + difference);
                correctedNewX += correctionDirection.getX() * difference;
                correctedNewY += correctionDirection.getY() * difference;
                Position deleteMePosition = new Position(newX, newY);
                //double deleteMeDistance = deleteMePosition.getDistance(playerDisc.getPosition());
                //System.out.println("The corrected distance is: " + deleteMeDistance);
                //double debugDistance = deleteMePosition.getDistance(new Position(correctedNewX, correctedNewY));
                //System.out.println("PlayerDisc with Puck collides with another PlayerDisc! Correction distance: " + debugDistance);
            }
            double puckDistance = playerDisc.getPosition().getDistance(new Position(correctedNewPuckX, correctedNewPuckY));
            if(puckDistance <= puck.getRadius() + playerDisc.getRadius()){
                //System.out.println("PUCK: newX and newY must be corrected!");
                Direction correctionDirection = playerDisc.getPosition().getDirection(puck.getPosition());
                //System.out.println("CorrectionDirection: " + correctionDirection);
                double difference = (puck.getRadius() + playerDisc.getRadius()) - playerDisc.getPosition().getDistance(new Position(correctedNewPuckX, correctedNewPuckY));
                //System.out.println("PUCK: difference: " + difference);
                // correct this playerDisc as well
                //System.out.println("old x: " + newPuckX + ". new x: " + correctedNewPuckX);
                correctedNewX += correctionDirection.getX() * difference;
                correctedNewY += correctionDirection.getY() * difference;
                correctedNewPuckX += correctionDirection.getX() * difference;
                correctedNewPuckY += correctionDirection.getY() * difference;
                //System.out.println("old x: " + newPuckX + " . new x: " + correctedNewPuckX);
                //System.out.println("PUCK: The corrected distance is: " + deleteMeDistance);
                //Position debugPosition = new Position(newPuckX, newPuckY);
                //double debugDistance = debugPosition.getDistance(new Position(correctedNewPuckX, correctedNewPuckY));
                //System.out.println("Owned Puck collides with another PlayerDisc! Correction distance: " + debugDistance);
            }
        }
        newX = correctedNewX;
        newY = correctedNewY;
        newPuckX = correctedNewPuckX;
        newPuckY = correctedNewPuckY;
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
        System.out.println("Current puckDirection: " + puckDirection);
        System.out.println("Current puck position: " + puck.getPosition());
        puckDirection = puckDirection + 2;
        if(puckDirection > 359) puckDirection = 0;
    }

    public void movePuckCounterClockwise(){
        --puckDirection;
        if(puckDirection < 0) puckDirection = 359;
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
        this.puckDirection = AngleCalculator.getAngleFromTwoPositions(puck.getPosition(), position);
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
}
