package com.mijuamon.core.loaders;

import com.mijuamon.core.exceptions.ConvertException;
import com.mijuamon.core.model.*;
import com.mijuamon.core.model.DTO.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.mijuamon.core.constants.Constants.*;
import static com.mijuamon.core.constants.Converters.*;

public class FileLoader {

    private static final Logger LOG = Logger.getLogger(FileLoader.class.toString());

    private FileLoader() {
        throw new IllegalStateException("Utility class");
    }

    public static List<TeamModel> loadInitialData() {


        List<TeamDTO> teamsDTO = getTeams();
        List<PlayerDTO> players = getPlayers();
        List<ScoreDTO> scores = getScores();
        List<MatchDTO> matchs = getMatches();

        List<TeamModel> teams= connectAll(teamsDTO, players, scores, matchs);

        LOG.info("Carga completada");

        NUM_MATCHES=matchs.size();
        NUM_PLAYERS=players.size();
        NUM_SCORES=scores.size();
        NUM_TEAMS=teams.size();

        return teams;
    }

    private static List<MatchDTO> getMatches() {
        LOG.info("Cargando fichero " + MATCH_DATA);
        List matchesObject = loadFile(MATCH_DATA, new MatchDTO());//NOSONNAR
        return (List<MatchDTO>) matchesObject;
    }

    private static List<ScoreDTO> getScores() {
        LOG.info("Cargando fichero " + SCORE_DATA);
        List scoresObject = loadFile(SCORE_DATA, new ScoreDTO());//NOSONNAR
        return (List<ScoreDTO>) scoresObject;
    }

    private static List<TeamDTO> getTeams() {
        LOG.info("Cargando fichero " + TEAMS_DATA);
        List teamsObject = loadFile(TEAMS_DATA, new TeamDTO());//NOSONNAR
        return (List<TeamDTO>) teamsObject;
    }

    private static List<PlayerDTO> getPlayers() {
        LOG.info("Cargando fichero " + PLAYERS_DATA);
        List playersObject = loadFile(PLAYERS_DATA, new PlayerDTO());//NOSONNAR
        return (List<PlayerDTO>) playersObject;

    }

    private static List<TeamModel> connectAll(List<TeamDTO> teamsDTO, List<PlayerDTO> players, List<ScoreDTO> scores, List<MatchDTO> matchs) {

        List<MatchModel> matchModelList=new ArrayList<>();
        List<TeamModel> teams=teamsDTO.stream().map(x->convert(x)).collect(Collectors.toList());
        List<PlayerModel> playersModel=new ArrayList<>();

        for (PlayerDTO player : players) {
            TeamModel t =teams.stream().filter(x -> player.getTeamID().equals(x.getID())).findFirst().get();
            List<ScoreModel>  scoresList = scores.stream().filter(x->player.getID().equals(x.getPlayerID())).map(x->convert(x)).collect(Collectors.toList());
            PlayerModel playerModel = convert(player);
            playersModel.add(playerModel);
            scoresList.stream().forEach(x->x.setPlayer(playerModel));
            t.addPlayer(playerModel);
            playerModel.setTeam(t);
            playerModel.setScores(scoresList);
        }

        for(MatchDTO match:matchs)
        {
            MatchModel matchModel = convert(match);
            matchModel.setLocal(teams.stream().filter(x->x.getID().equals(match.getLocalId())).findFirst().get());
            matchModel.setVisitor(teams.stream().filter(x->x.getID().equals(match.getVisitorId())).findFirst().get());
            matchModelList.add(matchModel);

        }


        for(TeamModel team:teams)
        {
            team.setMatches(matchModelList.stream().filter(x->x.getLocal().equals(team) || x.getVisitor().equals(team)).collect(Collectors.toList()));
        }

        for(PlayerModel player:playersModel)
        {
            //falta relacionar los scores a los matches



            /*ScoreModel scoreModel = new ScoreModel();
            players.stream().filter(p->score.getPlayerID().equals(p.getID()))
            matchs.stream().filter(x->x.getMatchId().equals(score.getMatchID())).forEach(x->score.setMatch(x));*/
        }
        return  teams;
    }


    private static List<AbstractItemDTO> loadFile(String url, AbstractItemDTO model) {

        ArrayList<AbstractItemDTO> output = new ArrayList<>();

        //Get file from resources folder
        File file = new File(url);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                Class<?> item = Class.forName(model.getClass().getName());
                AbstractItemDTO aux = (AbstractItemDTO) item.newInstance();

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
