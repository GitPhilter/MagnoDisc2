package training.heuristic;

public abstract class Heuristic implements HeuristicInterface {
    private final String name;

    public Heuristic(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
