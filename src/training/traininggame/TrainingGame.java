package training.traininggame;

import display.Display;
import game.PlayerDisc;
import game.Puck;
import game.game.Game;
import game.physics.Position;
import training.gamestate.PlayerDiscState;
import training.perception.Perception;

import java.util.ArrayList;
import java.util.Collections;

public class TrainingGame extends Game {
    private final int numberOfTotalTicks; // total tick-time the game will be run
    private int currentTick;
    private final boolean showGraphics = false;
    Perception[] perceptions;

    public TrainingGame(int numberOfTotalTicks){
        super();
        this.numberOfTotalTicks = numberOfTotalTicks;
        currentTick = 0;
    }

    @Override
    public void run(){
        double fps = 60;
        double NS_PER_FRAME = (double)1000000000 / (double)fps;
        running = true;
        waiting = false;
        if(showGraphics){
            this.display = new Display(this);
        }
        if(puck == null){
            puck = new Puck();
            puck.setPosition(new Position(400, 250));
        }
        double startOfTrainingGame = System.currentTimeMillis();
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
            if(puck != null && puck.getControllingPlayerDisc() != null){
                double diff = possessionCheck - startOfBallPossession;
                if(diff >= ballPossessionTimeMaximum){
                    foul();
                }
            }
            // tick
            tick();
            ++currentTick;
            if(currentTick >= numberOfTotalTicks) running = false;

            double timePassed = System.nanoTime() - start;
            if(showGraphics){
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
        //System.out.println("Perceptions:");
        ArrayList<Perception> perceptionsList = new ArrayList<>();

        for(PlayerDisc pd : getPlayerDiscs()){
            TrainingPlayerDisc tpd = (TrainingPlayerDisc)pd;
            for(Perception p : tpd.getPerceptions()){
                perceptionsList.add(p);
            }
        }
        this.perceptions = new Perception[perceptionsList.size()];
        for(int i = 0; i < perceptions.length; ++i){
            perceptions[i] = perceptionsList.get(i);
        }
        double endOfTrainingGame = System.currentTimeMillis();
        double timePassed = endOfTrainingGame - startOfTrainingGame;
        //System.out.println("The simulation took " + timePassed + " ms. " + numberOfTotalTicks + " ticks have been simulated.");
        //System.out.println("numberOfPerceptions: " + perceptions.length);
        int ticksPerMS = (int)(Math.round(numberOfTotalTicks / timePassed));
        //System.out.println("This equals ~ " + ticksPerMS + " ticks / ms.");
    }

    public Perception[] getPerceptions(){
        return perceptions;
    }

}
