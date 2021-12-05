package training.gamestate;

import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;

public class PlayerDiscState {
    private final String name;
    private final TeamEnum teamEnum;
    private final Position position;
    private final Direction direction;
    private final double speed;

    public PlayerDiscState(PlayerDisc playerDisc){
        this.name = playerDisc.getName();
        this.teamEnum = playerDisc.getTeam().getTeamEnum();
        this.position = new Position(playerDisc.getPosition().getX(), playerDisc.getPosition().getY());
        this.direction = new Direction(playerDisc.getDirection().getX(), playerDisc.getDirection().getY());
        this.speed = playerDisc.getSpeed();
    }

    public String getName(){
        return name;
    }

    public TeamEnum getTeamEnum(){
        return teamEnum;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection(){
        return direction;
    }

    public double getSpeed(){
        return speed;
    }

    @Override
    public String toString(){
        String result = "PlayerDiscState: ";
        result += "" + name + ", " + teamEnum + ", ";
        result += "Position: " + position + ", Direction: " + direction + ", Speed: " + speed;
        return result;
    }
}
