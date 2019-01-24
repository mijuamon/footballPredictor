package com.mijuamon.core.util;

import com.mijuamon.core.exceptions.ConvertException;
import com.mijuamon.core.model.DTO.*;
import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mijuamon.core.constants.Constants.*;
import static com.mijuamon.core.constants.Converters.convert;

public class FileLoaderUtil {

    private static final Logger LOG = Logger.getLogger(FileLoaderUtil.class.toString());


    public static List<TeamModel> loadInitialData() {


        final List<TeamDTO> teamsDTO = getTeams();
        final List<PlayerDTO> players = getPlayers();
        final List<ScoreDTO> scores = getScores();
        final List<MatchDTO> matchs = getMatches();

        final List<TeamModel> teams = connectAll(teamsDTO, players, scores, matchs);

        LOG.info("Carga completada");

        NUM_MATCHES = matchs.size();
        NUM_PLAYERS = players.size();
        NUM_SCORES = scores.size();
        NUM_TEAMS = teams.size();

        return teams;
    }

    private static List<MatchDTO> getMatches() {
        LOG.info("Cargando fichero " + MATCH_DATA);
        final List matchesObject = loadFile(MATCH_DATA, new MatchDTO());//NOSONNAR
        return (List<MatchDTO>) matchesObject;
    }

    private static List<ScoreDTO> getScores() {
        LOG.info("Cargando fichero " + SCORE_DATA);
        final List scoresObject = loadFile(SCORE_DATA, new ScoreDTO());//NOSONNAR
        return (List<ScoreDTO>) scoresObject;
    }

    private static List<TeamDTO> getTeams() {
        LOG.info("Cargando fichero " + TEAMS_DATA);
        final List teamsObject = loadFile(TEAMS_DATA, new TeamDTO());//NOSONNAR
        return (List<TeamDTO>) teamsObject;
    }

    private static List<PlayerDTO> getPlayers() {
        LOG.info("Cargando fichero " + PLAYERS_DATA);
        final List playersObject = loadFile(PLAYERS_DATA, new PlayerDTO());//NOSONNAR
        return (List<PlayerDTO>) playersObject;

    }

    private static List<TeamModel> connectAll(List<TeamDTO> teamsDTO, List<PlayerDTO> playersDTO, List<ScoreDTO> scoreDTO, List<MatchDTO> matchsDTO) {

        final List<MatchModel> matchs = new ArrayList<>();
        final List<TeamModel> teams = teamsDTO.stream().map(x -> convert(x)).collect(Collectors.toList());
        final List<PlayerModel> players = new ArrayList<>();

        for (final PlayerDTO player : playersDTO) {
            //Load only first division players
            if (teams.stream().anyMatch(x -> x.getID() == player.getID())) {
                final TeamModel t = teams.stream().filter(x -> player.getID() == x.getID()).findFirst().get();
                final List<ScoreModel> scoresList = scoreDTO.stream().filter(x -> player.getID() == x.getPlayerID()).map(x -> convert(x)).collect(Collectors.toList());
                final PlayerModel playerModel = convert(player);
                players.add(playerModel);
                scoresList.stream().forEach(x -> x.setPlayer(playerModel));
                t.addPlayer(playerModel);
                playerModel.setTeam(t);
                //TODO: arreglar esto
               // playerModel.setScores(scoresList);
            }
        }

        for (final MatchDTO match : matchsDTO) {
            //load only first division matches
            if (teams.stream().anyMatch(x -> x.getID() == match.getLocalId()) && teams.stream().anyMatch(x -> x.getID() == match.getVisitorId())) {
                final MatchModel matchModel = convert(match);
                matchModel.setLocal(teams.stream().filter(x -> x.getID() == match.getLocalId()).findFirst().get());
                matchModel.setVisitor(teams.stream().filter(x -> x.getID() == match.getVisitorId()).findFirst().get());
                matchs.add(matchModel);
            }

        }


        for (final TeamModel team : teams) {
            //TODO: arreglar esto

            //team.setMatches(matchs.stream().filter(x -> x.getLocal().equals(team) || x.getVisitor().equals(team)).collect(Collectors.toList()));
        }

        for (final ScoreDTO score : scoreDTO) {
            final PlayerModel currentPlayer = players.stream().filter(p -> p.getID() == score.getPlayerID()).findFirst().get();
            final MatchModel currentMatch = matchs.stream().filter(m -> m.getID() == score.getMatchID()).findFirst().get();

            currentPlayer.getScores().stream().filter(s -> s.getID() == score.getID()).forEach(s -> s.setMatch(currentMatch));
        }
        return teams;
    }


    private static List<AbstractItemDTO> loadFile(final String url, final AbstractItemDTO model) {

        final ArrayList<AbstractItemDTO> output = new ArrayList<>();

        //Get file from resources folder
        final File file = new File(url);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                final Class<?> item = Class.forName(model.getClass().getName());
                final AbstractItemDTO aux = (AbstractItemDTO) item.newInstance();

                aux.convert(scanner.nextLine());
                output.add(aux);
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | ConvertException e) {
            e.printStackTrace();

        }

        if (output.isEmpty()) {
            LOG.warning("El fichero " + url + " esta vacio, continua la carga inicial");
        }

        return output;

    }
}
