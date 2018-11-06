package com.mijuamon.core.constants;

import static com.mijuamon.core.constants.Constants.*;

import com.mijuamon.core.model.match.MatchModel;
import com.mijuamon.core.model.player.PlayerModel;
import com.mijuamon.core.model.score.ScoreModel;
import com.mijuamon.core.model.team.TeamModel;

import javax.swing.*;

public class Controller {

    public static void addPlayer(TeamModel team, String name)
    {
        PlayerModel player = new PlayerModel(name,team);

        if(team.getPlayers().stream().filter(x->x.equals(player)).findFirst().orElse(null)!=null) {
            deletePlayerID();
            JOptionPane.showMessageDialog(null, "Error");
        }
        else
        {
            team.getPlayers().add(player);
        }
    }

    public static void addScore (PlayerModel player, String scoreValue, MatchModel match)
    {
        ScoreModel score = new ScoreModel(scoreValue, match,player);

        if(player.getScores().stream().filter(x->x.equals(score)).findFirst().orElse(null)!=null) {
            deleteScoreIO();
            JOptionPane.showMessageDialog(null, "Error");
        }
        else
        {
            player.getScores().add(score);
        }
    }

    public static void addMatch (TeamModel visitor, TeamModel local, String result, String journey, String year)
    {
        MatchModel match = new MatchModel(local, visitor, result, journey, year);
    }
}
