package training.perception;


import training.gamestate.GameState;

public class Perception implements Comparable {
    private final GameState gameState; // gameState at the beginning of the perception window
    private final String playerDiscName;
    private final String finalBehaviourRepresentation;
    private final int behaviourTickTime;
    private final String heuristicName;
    private final double initialHeuristicValue;
    private final double resultingHeuristicValue;

    public Perception (GameState gameState, String playerDiscName, String finalBehaviourRepresentation,
                      int behaviourTickTime, String heuristicName, double initialHeuristicValue,
                      double resultingHeuristicValue){
        this.gameState = gameState;
        this.playerDiscName = playerDiscName;
        this.finalBehaviourRepresentation = finalBehaviourRepresentation;
        this.behaviourTickTime = behaviourTickTime;
        this.heuristicName = heuristicName;
        this.initialHeuristicValue = initialHeuristicValue;
        this. resultingHeuristicValue = resultingHeuristicValue;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getPlayerDiscName() {
        return playerDiscName;
    }

    public String getFinalBehaviourRepresentation() {
        return finalBehaviourRepresentation;
    }

    public int getBehaviourTickTime() {
        return behaviourTickTime;
    }

    public String getHeuristicName() {
        return heuristicName;
    }

    public double getInitialHeuristicValue() {
        return initialHeuristicValue;
    }

    public double getResultingHeuristicValue() {
        return resultingHeuristicValue;
    }

    @Override
    public String toString(){
        String result = "Perception: " + playerDiscName + "\n";
        result += gameState.toString();
        result += "BehaviourTickTime: " + behaviourTickTime + "\n";
        result += "Heuristic: " + heuristicName + ", ";
        result += "Initial-Value: " + initialHeuristicValue + ", Resulting Value: " + resultingHeuristicValue + "\n";
        result += "Behaviour: " + finalBehaviourRepresentation + "\n";
        return result;
    }

    public String getGainStringShort(){
        return "Perception: Gain = " + (resultingHeuristicValue - initialHeuristicValue);
    }

    @Override
    public int compareTo(Object o) {
        Perception p = (Perception)o;
        double thisGain = resultingHeuristicValue - initialHeuristicValue;
        double pGain = p.getResultingHeuristicValue() - p.getInitialHeuristicValue();
        return Double.compare(thisGain, pGain);
    }
}
