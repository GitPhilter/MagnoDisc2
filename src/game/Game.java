package game;


import behaviour.puckmoveactuator.PuckMove;
import display.Display;
import game.physics.*;
import geometry.AngleCalculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    Arena arena;
    ArrayList<PlayerDisc> playerDiscs;
    PlayerDisc[] homeTeam;
    PlayerDisc[] awayTeam;
    Puck puck;
    int width;
    int height;
    boolean running;
    Display display;
    int numberOfHomeGoals;
    int numberOfAwayGoals;

    public Game(){
        this.width = 800;
        this.height = 500;
        // game
        numberOfHomeGoals = 0;
        numberOfAwayGoals = 0;
        // playerDiscs
        Team homeTeam = new Team("Home-Team", TeamEnum.HOME, Color.YELLOW);
        Team awayTeam = new Team("Away-Team", TeamEnum.AWAY, Color.RED);
        this.playerDiscs = new ArrayList<>();
        this.playerDiscs.add(new PlayerDisc("Disc_1", width, height, this));
        this.playerDiscs.get(0).setPosition(new Position(500, 150));
        this.playerDiscs.get(0).setTeam(awayTeam);
        this.playerDiscs.add(new PlayerDisc("Disc_2", width, height, this));
        this.playerDiscs.get(1).setPosition(new Position(500, 350));
        this.playerDiscs.get(1).setTeam(awayTeam);
        this.playerDiscs.add(new PlayerDisc("Disc_3", width, height, this));
        this.playerDiscs.get(2).setPosition(new Position(700, 250));
        this.playerDiscs.get(2).setTeam(awayTeam);
        this.awayTeam = new PlayerDisc[3];
        this.awayTeam[0] = playerDiscs.get(0);
        this.awayTeam[1] = playerDiscs.get(1);
        this.awayTeam[2] = playerDiscs.get(2);
        this.playerDiscs.add(new PlayerDisc("Disc_4", width, height, this));
        this.playerDiscs.get(3).setPosition(new Position(300, 150));
        this.playerDiscs.get(3).setTeam(homeTeam);
        this.playerDiscs.add(new PlayerDisc("Disc_5", width, height, this));
        this.playerDiscs.get(4).setPosition(new Position(300, 350));
        this.playerDiscs.get(4).setTeam(homeTeam);
        this.playerDiscs.add(new PlayerDisc("Disc_6", width, height, this));
        this.playerDiscs.get(5).setPosition(new Position(100, 250));
        this.playerDiscs.get(5).setTeam(homeTeam);
        this.homeTeam = new PlayerDisc[3];
        this.homeTeam[0] = playerDiscs.get(3);
        this.homeTeam[1] = playerDiscs.get(4);
        this.homeTeam[2] = playerDiscs.get(5);
        // puck
        puck = new Puck();
        puck.setPosition(new Position(400, 250));
        this.arena = new Arena();
        this.display = new Display(this);
        this.running = false;
    }

    public int getWidth(){
            return width;
    }

    public int getHeight(){
        return height;
    }

    public Arena getArena(){
        return arena;
    }

    /**
     * goes through all playerDiscs and takes their Actuation and
     * acts it out.
     */
    public void tick(){
        // debug
        //double distance = playerDiscs.get(0).getPosition().getDistance(playerDiscs.get(1).getPosition());
        //System.out.println("The distance between the two discs is: " + distance);

        // puck
        // goal check
        if(puck != null){
            if(PuckGoalCheck.isInRightGoal(this, puck)) {
                System.out.println("The home team scores!");
                running = false;
                ++numberOfHomeGoals;
            }
            if(PuckGoalCheck.isInLeftGoal(this, puck)){
                System.out.println("The away team scores!");
                running = false;
                ++numberOfAwayGoals;
            }

        }
        // collision check & moving
        if(puck != null && puck.getControllingPlayerDisc() == null) {
            int newX = (int) puck.getPosition().getX() + (int) (puck.getDirection().getX() * puck.getSpeed());
            int newY = (int) puck.getPosition().getY() + (int) (puck.getDirection().getY() * puck.getSpeed());
            if (!PuckGoalCheck.canMoveLeft(this, puck)) {
                puck.getDirection().setX(0.0);
                newX = puck.getRadius();
            } else if (!PuckGoalCheck.canMoveRight(this, puck)) {
                puck.getDirection().setX(0.0);
                newX = (int) (width - puck.getRadius());
            }
            if (newY < puck.getRadius()) {
                puck.getDirection().setY(0.0);
                newY = puck.getRadius();
            } else if (newY > (height - puck.getRadius())) {
                puck.getDirection().setY(0.0);
                newY = (int) ((height - puck.getRadius()));
            }
            puck.setPosition(new Position(newX, newY));
        }
        Collections.shuffle(playerDiscs);
        //System.out.println("The first disc to act is: " + playerDiscs.get(0));
        for(PlayerDisc pd : playerDiscs){
            //System.out.println("game.tick(): " + pd.getName());
            movePlayerDisc(pd);
            PlayerDiscActuation pda = pd.getActuation(this);
            applyImpulseToPlayerDisc(pd, pda.getImpulse());
            if(pd.hasPuck()){
                applyShotToPuck(pd, pda.getShot());
                applyPuckMoveToPuck(pd, pda.getPuckMove());
            }
        }

    }

    private void applyPuckMoveToPuck(PlayerDisc playerDisc, PuckMove puckMove){
        if(puckMove == PuckMove.CLOCKWISE) {
            playerDisc.movePuckClockwise();
        } else if(puckMove == PuckMove.COUNTERCLOCKWISE) playerDisc.movePuckCounterClockwise();
    }

    /**
     * moves a playerDisc according to its current state
     * also makes boundary checks etc.
     * @param playerDisc the playerDisc that will be moved if legal
     */
    private void movePlayerDisc(PlayerDisc playerDisc){
        //System.out.println("movePlayerDisc: ");
        //System.out.println(playerDisc.toString());
        if(puck != null && playerDisc.hasPuck()){
            playerDisc.movePlayerDisc();
        } else{
            playerDisc.movePlayerDisc();
            checkPuckCollision(playerDisc);
        }
    }

    private void checkPuckCollision(PlayerDisc playerDisc){
        if(playerDisc.getPosition().getDistance(puck.getPosition()) < playerDisc.getRadius() + puck.getRadius()){
            //System.out.println("Collision! Puck and " + playerDisc.getName());
            if(puck.getControllingPlayerDisc() != null){
                // collision check
            } else {
                playerDisc.setHasPuck(true);
                playerDisc.setPuck(puck);
                puck.setControllingPlayerDisc(playerDisc);
            }

        }
    }

    // move to PlayerDisc?
    private void applyImpulseToPlayerDisc(PlayerDisc playerDisc, Impulse impulse){
        // TODO:apply friction
        //

        if(impulse == null) return;
        // direction
        playerDisc.getDirection().addDirection(impulse.getDirection());
        // speed
        double acceleration = impulse.getAcceleration();
        if(acceleration >= 0){
            double newSpeed = playerDisc.getSpeed() + playerDisc.getMaxAcceleration() * acceleration;
            if(newSpeed > playerDisc.getMaxSpeed()){
                playerDisc.setSpeed(playerDisc.getMaxSpeed());
            } else {
                playerDisc.setSpeed(newSpeed);
            }
        } else {
            double newSpeed = playerDisc.getSpeed() + playerDisc.getMaxBreak() * acceleration;
            if(newSpeed < 0){
                playerDisc.setSpeed(0);
            } else {
                playerDisc.setSpeed(newSpeed);
            }
        }
    }

    private void applyShotToPuck(PlayerDisc playerDisc, Shot shot){
        if(shot == null) return;
        if(!AngleCalculator.isLegalShot(shot, playerDisc)) return;
        //System.out.println("applyShotToPuck! shot is not zero! 1 mississipi, 2 mississipi....BAM!");
        // delete puck possession
        playerDisc.setHasPuck(false);
        playerDisc.setPuck(null);
        puck.setControllingPlayerDisc(null);
        // give puck new direction and speed
        puck.setDirection(shot.getDirection());
        puck.setSpeed(shot.getSpeed());
    }

    public void run(){
        double fps = 60;
        double NS_PER_FRAME = (double)1000000000 / (double)fps;
        this.running = true;
        while(running){
            double start = System.nanoTime();
            tick();
            double timePassed = System.nanoTime() - start;
            this.display.repaint();
            timePassed = System.nanoTime() - start;
            if(timePassed < NS_PER_FRAME){
                try {
                    //System.out.println("Sleep " + (NS_PER_FRAME - timePassed));
                    Thread.sleep((long)((NS_PER_FRAME - timePassed) / (double)1000000));
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    private double getYSect(Direction direction, Position position, double x){
        double yPerX = direction.getY() / direction.getX();
        double xDelta = position.getX() - x;
        return position.getY() - (yPerX * xDelta);
    }

    private double getXSect(Direction direction, Position position, double y){
        double xPerY = direction.getX() / direction.getY();
        double yDelta = position.getY() - y;
        return position.getX() - (xPerY * yDelta);
    }

    public Puck getPuck() {
        return puck;
    }

    public ArrayList<PlayerDisc> getPlayerDiscs(){
        return playerDiscs;
   }

   public PlayerDisc[] getHomeTeam(){
        return this.homeTeam;
   }

   public PlayerDisc[] getAwayTeam(){
        return  this.awayTeam;
   }
}
