package game;


import behaviour.helpers.PlayerDiscAbsolutePosition;
import behaviour.helpers.PlayerDiscRelations;
import behaviour.puckmoveactuator.PuckMove;
import display.Display;
import game.physics.*;
import game.teamfactory.TeamFactory;
import geometry.AngleCalculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    Arena arena;
    ArrayList<PlayerDisc> playerDiscs;
    Team homeTeam;
    Team awayTeam;
    Puck puck;
    int width;
    int height;
    double friction = 0.1;
    double ballPossessionTimeMaximum = 5000; // [ms]
    boolean running;
    boolean waiting;
    boolean thereWasAFoul;
    TeamEnum freeshotTeam;
    Display display;
    int numberOfHomeGoals;
    int numberOfAwayGoals;

    double startOfBallPossession;

    public Game(){
        this.width = 800;
        this.height = 500;
        // game
        numberOfHomeGoals = 0;
        numberOfAwayGoals = 0;
        // playerDiscs
        homeTeam = TeamFactory.createRandomTeam(4, this, TeamEnum.HOME);
        awayTeam = TeamFactory.createRandomTeam(2, this, TeamEnum.AWAY);
        //awayTeam = null;
        this.playerDiscs = new ArrayList<>();
        if(homeTeam != null){
            for(PlayerDisc playerDisc : homeTeam.getPlayerDiscs()){
                playerDiscs.add(playerDisc);
            }
        }

        if(awayTeam != null){
            for(PlayerDisc playerDisc : awayTeam.getPlayerDiscs()){
                playerDiscs.add(playerDisc);
            }
        }

        // puck
        puck = new Puck();
        puck.setPosition(new Position(400, 250));
        this.arena = new Arena();
        this.display = new Display(this);
        this.running = false;
        this.waiting = true;
        this.thereWasAFoul = false;
        this.freeshotTeam = null;
        //this.initialPuck = true;
        this.startOfBallPossession = 0;
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
                //System.out.println("Puck position: " + puck.getPosition());
                waiting = true;
                ++numberOfHomeGoals;
                goalReset();
            }
            if(PuckGoalCheck.isInLeftGoal(this, puck)){
                System.out.println("The away team scores!");
                //System.out.println("Puck position: " + puck.getPosition());
                waiting = true;
                ++numberOfAwayGoals;
                goalReset();
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
        //System.out.println("ApplyPuckMoveToPuck!");
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
            if(!waiting){
                checkPuckCollision(playerDisc);
            }

        }
    }

    private void checkPuckCollision(PlayerDisc playerDisc){
        if(playerDisc.getPosition().getDistance(puck.getPosition()) < playerDisc.getRadius() + puck.getRadius()){
            if(puck.getControllingPlayerDisc() != null){
                // collision check
            } else {
                playerDisc.setHasPuck(true);
                playerDisc.setPuck(puck);
                puck.setControllingPlayerDisc(playerDisc);
                //initialPuck = false;
                startOfBallPossession = System.currentTimeMillis();
            }

        }
    }

    // move to PlayerDisc?
    private void applyImpulseToPlayerDisc(PlayerDisc playerDisc, Impulse impulse){
        if(impulse == null) return;
        // friction
        playerDisc.setSpeed(playerDisc.getSpeed() * (1 - friction));
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
        double fps = 24;
        double NS_PER_FRAME = (double)1000000000 / (double)fps;
        running = true;
        waiting = false;
        while(running){
            if(waiting && !thereWasAFoul && waitingIsOver()){
                waiting = false;
            } else if(waiting && thereWasAFoul && waitingIsOver()) {
                waiting = false;
                thereWasAFoul = false;
                System.out.println("applying freeshot in game loop!");
                applyFreeShot();
            }
            double start = System.nanoTime();
            double possessionCheck = System.currentTimeMillis();
            if(puck.getControllingPlayerDisc() != null){
                double diff = possessionCheck - startOfBallPossession;
                //System.out.println("possessionCheck [ms]: " + possessionCheck);
                //System.out.println("startOfBallPossession [ms]: " + startOfBallPossession);
                //System.out.println(diff);
                if(diff >= ballPossessionTimeMaximum){
                    foul();
                }
            }
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

    private boolean waitingIsOver(){
        for(PlayerDisc playerDisc : playerDiscs){
            double distance = playerDisc.getPosition().getDistance(playerDisc.getDefaultPosition());
            if(distance > 4){
                //System.out.println(playerDisc.getName() + ":");
                //System.out.println("Default position not yet reached!");
                //System.out.println("Current Position: " + playerDisc.getPosition() + ". Default Position: " + playerDisc.getDefaultPosition());
                return false;
            } else {
                //System.out.println("+++ DEFAULT POSITION REACHED!!! +++");
            }
        }
        System.out.println("Game.waitingIsOver() = true!");
        return true;
    }

    private void goalReset(){
        System.out.println("Game.goalReset(), the total score is: [ " + numberOfHomeGoals + " - " + numberOfAwayGoals + " ]");
        puck.setPosition(new Position(400, 250));
        puck.setDirection(new Direction(0, 0));
        puck.setSpeed(0);
        if(puck.getControllingPlayerDisc() != null){
            puck.getControllingPlayerDisc().setPuck(null);
            puck.getControllingPlayerDisc().setHasPuck(false);
        }
        puck.setControllingPlayerDisc(null);
    }

    public void foul(){
        System.out.println("There was a foul!");
        TeamEnum foulTeamEnum = puck.getControllingPlayerDisc().getTeam().getTeamEnum();
        freeshotTeam  = TeamEnum.UNKNOWN;
        if(foulTeamEnum == TeamEnum.HOME) freeshotTeam = TeamEnum.AWAY;
        if(foulTeamEnum == TeamEnum.AWAY) freeshotTeam = TeamEnum.HOME;
        System.out.println("The " + freeshotTeam.name() + " Team gets a free shot!");
        goalReset();
        waiting = true;
        thereWasAFoul = true;
    }

    public void applyFreeShot(){
        PlayerDisc freeshotTeamPlayerDisc = null;
        for(PlayerDisc playerDisc : playerDiscs){
            if(playerDisc.getTeam().getTeamEnum() == freeshotTeam){
                freeshotTeamPlayerDisc = playerDisc;
                break;
            }
        }
        PlayerDisc recievingDisc = PlayerDiscRelations.getPlayerDiscClosestToPuckFromTeam(this, freeshotTeamPlayerDisc);
        Direction direction = puck.getPosition().getDirection(recievingDisc.getPosition());
        Shot shot = new Shot(direction, 6);
        // give puck new direction and speed
        puck.setDirection(shot.getDirection());
        puck.setSpeed(shot.getSpeed());
    }



    public Puck getPuck() {
        return puck;
    }

    public ArrayList<PlayerDisc> getPlayerDiscs(){
        return playerDiscs;
   }

   public Team getHomeTeam(){
        return this.homeTeam;
   }

   public Team getAwayTeam(){
        return  this.awayTeam;
   }

    public boolean isWaiting() {
        return waiting;
    }
}
