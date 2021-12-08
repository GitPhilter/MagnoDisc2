package training.neuralnetworkgame;

import display.Display;
import game.PlayerDisc;
import game.Puck;
import game.Team;
import game.TeamEnum;
import game.game.Game;
import game.physics.Position;
import game.teamfactory.NeuralNetworkGameTeamFactory;
import training.neuralnetwork.NeuralNetwork;
import training.perception.Perception;
import training.traininggame.TrainingPlayerDisc;

import java.util.ArrayList;

public class NeuralNetworkGame extends Game {

    public NeuralNetworkGame(NeuralNetwork neuralNetwork){
        super();
        Team homeTeam = NeuralNetworkGameTeamFactory.createRandomTeam(3, this, TeamEnum.HOME, neuralNetwork);
        Team awayTeam = NeuralNetworkGameTeamFactory.createRandomTeam(3, this, TeamEnum.AWAY, neuralNetwork);
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    @Override
    public void run(){
        double fps = 60;
        double NS_PER_FRAME = (double)1000000000 / (double)fps;
        running = true;
        waiting = false;

        this.display = new Display(this);

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
}
