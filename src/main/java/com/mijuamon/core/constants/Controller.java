package com.mijuamon.core.constants;

import com.mijuamon.core.dao.MatchDao;
import com.mijuamon.core.dao.PlayerDao;
import com.mijuamon.core.dao.ScoreDao;
import com.mijuamon.core.dao.TeamDao;
import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import java.util.List;
import java.util.Set;

import static com.mijuamon.core.constants.Constants.deletePlayerID;
import static com.mijuamon.core.constants.Constants.deleteScoreIO;

public class Controller {


    public static List<TeamModel> getAllTeams() {
        return TeamDao.getInstance().getAll(TeamModel.getModelName());
    }

    //////////////////
    //ADD
    public static void addTeam(final TeamModel team) {
        TeamDao.getInstance().add(team);
    }

    public static boolean addPlayer(final TeamModel team, final String name) {
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

    public static void addPlayer(final PlayerModel player) {
        PlayerDao.getInstance().add(player);
    }

    public static void addScore(final ScoreModel score) {
        ScoreDao.getInstance().add(score);
    }

    public static boolean addScore(final PlayerModel player, final String scoreValue, final MatchModel match) {
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

    public static void addMatch(final MatchModel match) {
        MatchDao.getInstance().add(match);
    }

    public static boolean addMatch(final TeamModel local, final TeamModel visitor, final String result, final String week, final String season) {
        MatchModel match = new MatchModel(local, visitor, result, week, season);

        if (MatchDao.getInstance().exist(match)) {
            DialogsUtil.errorMessage("Ya existe un partido con los mismos valores");
            return false;
        } else {
            try {
                local.getMatches().add(match);
                visitor.getMatches().add(match);

                MatchDao.getInstance().add(match);

                System.out.println("test");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
    ////////////////////
    //UPDATE

    public static void updateTeam(final TeamModel team) {
        TeamDao.getInstance().update(team);
    }

    public static void updatePlayer(final PlayerModel player) {
        PlayerDao.getInstance().update(player);
    }

    public static void updateScore(final ScoreModel score) {
        ScoreDao.getInstance().update(score);
    }

    public static void updateMatch(final MatchModel match) {
        MatchDao.getInstance().update(match);
    }

    /////////////////////////////
    //DELETE
    public static void deleteTeam(final TeamModel team) {
        TeamDao.getInstance().delete(team);
    }

    public static void deletePlayer(final PlayerModel player) {
        PlayerDao.getInstance().delete(player);
    }

    public static void deleteScore(final ScoreModel score) {
        ScoreDao.getInstance().delete(score);
    }

    public static void deleteMatch(final MatchModel match) {
        //Remove the score of all players in that match
        match.getLocal().getPlayers().stream()
                .map(player -> player.getScores())
                .flatMap(scores -> scores.stream())
                .filter(score -> score.getMatch().equals(match))
                .forEach(score -> deleteScore(score));

        //Remove the score for loval and visitor teams
        match.getLocal().getMatches().removeIf(teamMatch -> match.equals(teamMatch));
        match.getVisitor().getMatches().removeIf(teamMatch -> match.equals(teamMatch));

        MatchDao.getInstance().delete(match);
    }

    ////////////////
    //GET BY ID
    public static TeamModel getTeam(final int id) {
        return TeamDao.getInstance().get(TeamModel.class, id);
    }

    public static PlayerModel getPlayer(final int id) {
        return PlayerDao.getInstance().get(PlayerModel.class, id);
    }

    ////////////////
    //GET ALL

    public static List<PlayerModel> getAllPlayers() {
        return PlayerDao.getInstance().getAll(PlayerModel.getModelName());
    }

    public static Set<PlayerModel> getAllPlayers(final int teamId) {

        return getTeam(teamId).getPlayers();
    }

    public static List<ScoreModel> getAllScores() {
        return ScoreDao.getInstance().getAll(ScoreModel.getModelName());

    }

    public static Set<ScoreModel> getAllScores(final int playerId) {
        final String sql = "from " + ScoreModel.getModelName() + " where player=" + playerId;

        return getPlayer(playerId).getScores();
    }


    public static List<MatchModel> getAllMatches() {
        return MatchDao.getInstance().getAll(MatchModel.getModelName());

    }

    public static List<MatchModel> getAllMatches(final int teamId) {
        final String sql = "from " + MatchModel.getModelName() + " where local=" + teamId + " OR visitor=" + teamId;

        return MatchDao.getInstance().search(sql);
    }


}
