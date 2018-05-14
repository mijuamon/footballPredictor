package com.mijuamon.core;

import com.mijuamon.core.exceptions.ConvertException;
import com.mijuamon.core.model.*;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.mijuamon.core.constants.Constants.*;

public class FileLoader {

    private static final Logger LOG = Logger.getLogger(FileLoader.class.toString());

    private FileLoader()
    {
        throw  new IllegalStateException("Utility class");
    }

    public static List loadInitialData() {

        List<TeamModel> teams = getTeams();
        List<PlayerModel> players = getPlayers();
        List<ScoreModel> scores = getScores();
        List<MatchModel> Matchs = getMatches();

        connectAll(teams,players);
        return teams;
    }

    private static List<MatchModel> getMatches() {
        List matchesObject = loadFile(MATCH_DATA, new MatchModel());//NOSONNAR
        return (List<MatchModel>)matchesObject;
    }

    private static List<ScoreModel> getScores() {
        List scoresObject = loadFile(SCORE_DATA, new ScoreModel());//NOSONNAR
        return (List<ScoreModel>)scoresObject;
    }

    private static List<TeamModel> getTeams() {
        List teamsObject = loadFile(PLAYERS_DATA, new TeamModel());//NOSONNAR
        return (List<TeamModel>)teamsObject;
    }

    private static List<PlayerModel> getPlayers() {
        List playersObject = loadFile(TEAMS_DATA, new PlayerModel());//NOSONNAR
        return (List<PlayerModel>)playersObject;

    }

    private static void connectAll(List<TeamModel> teams, List<PlayerModel> players) {
        for(PlayerModel player:players)
        {
            teams.stream().filter(x->player.getTeamID().equals(x.getId())).forEach(x->x.addPlayer(player));
        }
    }


    private static List<AbstractItemModel> loadFile(String url, AbstractItemModel model) {

        ArrayList<AbstractItemModel> output = new ArrayList<>();

        //Get file from resources folder
        File file = new File(url);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                model.convert(scanner.nextLine());
                output.add(model);
            }
        } catch (IOException | ConvertException e) {
            e.printStackTrace();
        }

        return output;

    }
}
