package training.traininggame;

import behaviour.Behaviour;
import behaviour.puckmoveactuator.PuckMove;
import game.PlayerDisc;
import game.game.Game;
import game.physics.Impulse;
import game.physics.PlayerDiscActuation;
import game.physics.Shot;
import training.behaviourgenerator.FinalBehaviour;
import training.behaviourgenerator.RandomFinalBehaviourGenerator;
import training.gamestate.GameState;
import training.heuristic.Heuristic;
import training.heuristic.implementations.SimpleHeuristic_1;
import training.perception.Perception;

import java.util.ArrayList;

public class TrainingPlayerDisc extends PlayerDisc {
    private ArrayList<Perception> perceptions;
    private FinalBehaviour behaviour;
    private final int ticksPerPerception = 40;
    private int currentTickCycle;
    // perception parameters
    Heuristic heuristic = new SimpleHeuristic_1();
    GameState currentPerceptionGameState;

    public TrainingPlayerDisc(String name, Game game){
        super(name, game);
        perceptions = new ArrayList<>();
        behaviour = RandomFinalBehaviourGenerator.getRandomFinalBehaviour(this);
        currentTickCycle = 0;
        behaviourManager = null;

    }

    @Override
    public PlayerDiscActuation getActuation(Game game){
        if(currentPerceptionGameState == null){
            currentPerceptionGameState = new GameState(game);
        }
        // update behaviour manager
        ++currentTickCycle;
        if(currentTickCycle >= ticksPerPerception) {
            behaviour = RandomFinalBehaviourGenerator.getRandomFinalBehaviour(this);
            currentTickCycle = 0;
            // perception
            // (GameState gameState, String playerDiscName, String finalBehaviourRepresentation,
            //                      int behaviourTickTime, String heuristicName, double initialHeuristicValue,
            //                      double resultingHeuristicValue)
            perceptions.add(new Perception(currentPerceptionGameState, name, behaviour.getRepresentation(),
                    ticksPerPerception, heuristic.getName(),
                    heuristic.getHeuristicValue(currentPerceptionGameState, team.getTeamEnum()),
                    heuristic.getHeuristicValue(new GameState(game), team.getTeamEnum())));
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

    public ArrayList<Perception> getPerceptions(){
        return perceptions;
    }
}
