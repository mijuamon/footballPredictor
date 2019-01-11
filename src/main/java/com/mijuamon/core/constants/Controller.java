package com.mijuamon.core.constants;

import com.mijuamon.core.dao.HibernateDao;
import com.mijuamon.core.dao.MatchDao;
import com.mijuamon.core.model.match.MatchModel;
import com.mijuamon.core.model.player.PlayerModel;
import com.mijuamon.core.model.score.ScoreModel;
import com.mijuamon.core.model.team.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import static com.mijuamon.core.constants.Constants.deletePlayerID;
import static com.mijuamon.core.constants.Constants.deleteScoreIO;

public class Controller {


    public static void addTeam(TeamModel team) {
        HibernateDao.getInstance().add(team);
    }

    public static void updateTeam(TeamModel team) {
        HibernateDao.getInstance().update(team);
    }


    public static boolean addPlayer(TeamModel team, String name) {
        PlayerModel player = new PlayerModel(name, team);

        if (team.getPlayers().stream().filter(x -> x.equals(player)).findFirst().orElse(null) != null) {
            deletePlayerID();
            DialogsUtil.infoMessage("Error");
            return false;
        } else {
            team.getPlayers().add(player);
            return true;
        }
    }

    public static void addPlayer(PlayerModel player) {
        HibernateDao.getInstance().add(player);
    }

    public static void updatePlayer(PlayerModel player) {
        HibernateDao.getInstance().update(player);
    }

    public static boolean addScore(PlayerModel player, String scoreValue, MatchModel match) {
        ScoreModel score = new ScoreModel(scoreValue, match, player);

        if (player.getScores().stream().filter(x -> x.equals(score)).findFirst().orElse(null) != null) {
            deleteScoreIO();
            DialogsUtil.infoMessage("Error a la hora de insertar el resultado");
            return false;
        } else {
            player.getScores().add(score);
            return true;
        }
    }

    public static boolean addMatch(TeamModel local, TeamModel visitor, String result, String week, String season) {
        MatchModel match = new MatchModel(local, visitor, result, week, season);

        if (MatchDao.getInstance().exist(match)) {
            DialogsUtil.errorMessage("Ya existe un partido con los mismos valores");
            return false;
        } else {
            HibernateDao.getInstance().add(match);
            return true;
        }
    }
}
