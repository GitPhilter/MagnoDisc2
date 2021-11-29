package workmain;

import behaviour.puckmoveactuator.PuckMoveActuator;
import behaviour.puckmoveactuator.implementations.attack.MovePuckTowardsOpposingGoalPuckMoveActuator;
import game.Game;
import game.PlayerDisc;
import game.Puck;
import game.TeamEnum;
import game.physics.Position;
import game.teamfactory.TeamFactory;

public class ActuatorTestMain {

    public static void main(String[] args){
        Game game = new Game();
        PlayerDisc playerDisc = new PlayerDisc("Klaus", game);
        playerDisc.setTeam(TeamFactory.createRandomTeam(2, game, TeamEnum.HOME));
        Puck puck = new Puck();
        puck.setPosition(new Position(140, 290));
        playerDisc.setPosition(new Position(140, 250));
        playerDisc.setHasPuck(true);
        playerDisc.setPuck(puck);
        PuckMoveActuator puckMoveActuator = new MovePuckTowardsOpposingGoalPuckMoveActuator(game,playerDisc);
        System.out.println(puckMoveActuator.getPuckMove());
    }

}
