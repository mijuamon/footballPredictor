package com.mijuamon.core.loaders;

import com.mijuamon.core.exceptions.ConvertException;
import com.mijuamon.core.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mijuamon.core.constants.Constants.*;

public class FileLoader {

    private static final Logger LOG = Logger.getLogger(FileLoader.class.toString());

    private FileLoader() {
        throw new IllegalStateException("Utility class");
    }

    public static List<TeamModel> loadInitialData() {

        List<TeamModel> teams = getTeams();
        List<PlayerModel> players = getPlayers();
        List<ScoreModel> scores = getScores();
        List<MatchModel> matchs = getMatches();

        connectAll(teams, players, scores, matchs);

        LOG.info("Carga completada");
        return teams;
    }

    private static List<MatchModel> getMatches() {
        LOG.info("Cargando fichero " + MATCH_DATA);
        List matchesObject = loadFile(MATCH_DATA, new MatchModel());//NOSONNAR
        return (List<MatchModel>) matchesObject;
    }

    private static List<ScoreModel> getScores() {
        LOG.info("Cargando fichero " + SCORE_DATA);
        List scoresObject = loadFile(SCORE_DATA, new ScoreModel());//NOSONNAR
        return (List<ScoreModel>) scoresObject;
    }

    private static List<TeamModel> getTeams() {
        LOG.info("Cargando fichero " + TEAMS_DATA);
        List teamsObject = loadFile(TEAMS_DATA, new TeamModel());//NOSONNAR
        return (List<TeamModel>) teamsObject;
    }

    private static List<PlayerModel> getPlayers() {
        LOG.info("Cargando fichero " + PLAYERS_DATA);
        List playersObject = loadFile(PLAYERS_DATA, new PlayerModel());//NOSONNAR
        return (List<PlayerModel>) playersObject;

    }

    private static void connectAll(List<TeamModel> teams, List<PlayerModel> players, List<ScoreModel> scores, List<MatchModel> matchs) {
        TeamModel t;
        List<ScoreModel> scoresList;
        for (PlayerModel player : players) {
            t=teams.stream().filter(x -> player.getTeamID().equals(x.getId())).findFirst().get();
            scoresList = scores.stream().filter(x->player.getPlayerID().equals(x.getPlayerID())).collect(Collectors.toList());
            scoresList.stream().forEach(x->x.setPlayer(player));
            t.addPlayer(player);
            player.setTeam(t);
            player.setScores(scoresList);
        }

        for(TeamModel team:teams)
        {
            matchs.stream().filter(x->x.getLocalId().equals(team.getId())).forEach(x->x.setLocal(team));
            matchs.stream().filter(x->x.getVisitorId().equals(team.getId())).forEach(x->x.setVisitor(team));
        }
        for(TeamModel team:teams)
        {
            List<MatchModel> matchList;
            if(matchs!=null && !matchs.isEmpty()) {
                matchList = matchs.stream().filter(x -> x.getVisitor().equals(team)).collect(Collectors.toList());
                matchList.addAll(matchs.stream().filter(x -> x.getLocal().equals(team)).collect(Collectors.toList()));
                team.setMatches(matchList);
            }
        }
        for(ScoreModel score:scores)
        {
            matchs.stream().filter(x->x.getMatchId().equals(score.getMatchID())).forEach(x->score.setMatch(x));
        }
    }


    private static List<AbstractItemModel> loadFile(String url, AbstractItemModel model) {

        ArrayList<AbstractItemModel> output = new ArrayList<>();

        //Get file from resources folder
        File file = new File(url);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                Class<?> item = Class.forName(model.getClass().getName());
                AbstractItemModel aux = (AbstractItemModel) item.newInstance();

                aux.convert(scanner.nextLine());
                output.add(aux);
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | ConvertException e) {
            e.printStackTrace();

        }

        if (output.size() == 0) {
            LOG.warning("El fichero " + url + " esta vacio, continua la carga inicial");
        }

        return output;

    }
}
