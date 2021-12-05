package workmain;

import game.game.Game;
import game.Team;
import game.TeamEnum;
import game.teamfactory.TeamFactory;
import game.teamfactory.TrainingTeamFactory;
import training.traininggame.TrainingGame;

public class WorkMain {
    public static void main(String[] args){
        System.out.println("MagnoDisc2 - WorkMain");
        TrainingGame trainingGame = new TrainingGame();
        Team homeTeam = TrainingTeamFactory.createRandomTeam(3, trainingGame, TeamEnum.HOME);
        Team awayTeam = TrainingTeamFactory.createRandomTeam(3, trainingGame, TeamEnum.AWAY);
        trainingGame.setHomeTeam(homeTeam);
        trainingGame.setAwayTeam(awayTeam);
        trainingGame.run();
    }
}
