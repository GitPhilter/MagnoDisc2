package training.gamestate;

import game.game.Game;
import game.physics.Direction;
import game.physics.Position;

public class GameState {
    // general
    private final int gameWidth;
    private final int gameHeight;
    private final int numberOfHomeGoals;
    private final int numberOfAwayGoals;
    //puck
    private final Position puckPosition;
    private final Direction puckDirection;
    private final double puckSpeed;
    private final String puckControllingPlayerDiscName;
    // playerDiscs
    private final PlayerDiscState[] playerDiscStates;

    public GameState(Game game){
        // general
        this.gameWidth = game.getWidth();
        this.gameHeight = game.getHeight();
        this.numberOfHomeGoals = game.getNumberOfHomeGoals();
        this.numberOfAwayGoals = game.getNumberOfAwayGoals();
        // puck
        this.puckPosition = new Position(game.getPuck().getPosition().getX(),
                game.getPuck().getPosition().getY());
        this.puckDirection = new Direction(game.getPuck().getDirection().getX(),
                game.getPuck().getPosition().getY());
        this.puckSpeed = game.getPuck().getSpeed();
        if(game.getPuck().getControllingPlayerDisc() == null){
            this.puckControllingPlayerDiscName = null;
        } else {
            this.puckControllingPlayerDiscName = game.getPuck().getControllingPlayerDisc().getName();
        }
        // player discs
        int numberOfDiscs = game.getPlayerDiscs().size();
        this.playerDiscStates = new PlayerDiscState[numberOfDiscs];
        for(int i = 0;i < game.getPlayerDiscs().size(); ++i){
            playerDiscStates[i] = new PlayerDiscState(game.getPlayerDiscs().get(i));
        }
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public int getNumberOfHomeGoals(){
        return numberOfHomeGoals;
    }

    public int getNumberOfAwayGoals(){
        return numberOfAwayGoals;
    }

    public Position getPuckPosition() {
        return puckPosition;
    }

    public Direction getPuckDirection() {
        return puckDirection;
    }

    public double getPuckSpeed() {
        return puckSpeed;
    }

    public String getPuckControllingPlayerDiscName() {
        return puckControllingPlayerDiscName;
    }

    public PlayerDiscState[] getPlayerDiscStates() {
        return playerDiscStates;
    }

    @Override
    public String toString(){
        String result = "### GameState: ###";
        // general
        result += "(" + gameWidth + " * " + gameHeight + ")\n";
        // puck
        result += "Puck Position: " + puckPosition + ", Direction: " + puckDirection + ", Speed: " + puckSpeed;
        result += "Puck controlled by: " + puckControllingPlayerDiscName;
        for(PlayerDiscState pds : playerDiscStates){
            result += "" + pds + "\n";
        }
        return result;
    }
}
