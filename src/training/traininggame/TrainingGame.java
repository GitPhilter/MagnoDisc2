package training.traininggame;

import display.Display;
import game.PlayerDisc;
import game.Puck;
import game.game.Game;
import game.physics.Position;
import training.gamestate.PlayerDiscState;
import training.perception.Perception;

import java.util.ArrayList;

public class TrainingGame extends Game {
    private final int numberOfTotalTicks = 180; // total tick-time the game will be run
    private int currentTick;


    public TrainingGame(){
        super();
        currentTick = 0;
    }

    @Override
    public void run(){
        double fps = 24;
        double NS_PER_FRAME = (double)1000000000 / (double)fps;
        running = true;
        waiting = false;
        this.display = new Display(this);
        if(puck == null){
            puck = new Puck();
            puck.setPosition(new Position(400, 250));
        }
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
        System.out.println("Perceptions:");
        for(PlayerDisc pd : getPlayerDiscs()){
            TrainingPlayerDisc tpd = (TrainingPlayerDisc)pd;
            for(Perception p : tpd.getPerceptions()){
                System.out.println(p);
            }
        }
    }

}
