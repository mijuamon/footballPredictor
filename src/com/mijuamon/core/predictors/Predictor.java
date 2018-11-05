package com.mijuamon.core.predictors;

import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.mijuamon.core.constants.Constants.*;

public class Predictor {

    public static double PredictMatch(final TeamModel local, final TeamModel visitor, final Map<Integer, String> conditionalLocal, final Map<Integer, String> conditionalVisitor) {
        double result = 0;

        //Puntuacion de equipo por partidos
        double localMotivational = CalculateTeamScore(local, local.getMatches());
        double visitorMotivational = CalculateTeamScore(visitor, visitor.getMatches());

        //Puntuacion de juegadores
        double localPlayers = local.getPlayers().stream().mapToDouble(play -> CalculatePlayerScore(play)).sum();
        double visitorPlayers = visitor.getPlayers().stream().mapToDouble(play -> CalculatePlayerScore(play)).sum();
        if (PLAYERS_MEDIAN) {
            localPlayers = localPlayers / local.getPlayers().size();
            visitorPlayers = visitorPlayers / visitor.getPlayers().size();
        }

        //////////////////////////////////
        ///////otros condicionantes///////

        //////////////////////////////////
        double scoreConditionalLocal = calculateConditionals(conditionalLocal);
        double scoreConditionalVisitor = calculateConditionals(conditionalVisitor);


        //Total de puntuacion y puntuacion del equipo local
        double totalScore = localMotivational + visitorMotivational + localPlayers + visitorPlayers;
        double localScore = localMotivational + localPlayers;


        result = localScore * 100 / totalScore; //Calculamos el porcentaje de victoria del equipo local
        return result;
    }

    private static double calculateConditionals(Map<Integer, String> conditional) {
        double score = 0.0;
        for (Integer cond : conditional.keySet()) {
            switch (cond) {
                case (DERBI):
                    break;
                case (COACH_CHANGE):
                    break;
                case (HISTORIC_RIVALITY):
                    break;
                case (LOCAL_2_COMPETITIONS):
                    break;
                case (LOCAL_3_COMPETITIONS):
                    break;
                case (VISITOR_2_COMPETITIONS):
                    break;
                case (VISITOR_3_COMPETITIONS):
                    break;
                case (MATCHS_OVERLOAD):
                    break;
                case (COMPETITION_ELIMINATION):
                    break;
                case (DIRECT_COMPETITION):
                    break;
                case (INDIRECT_COMPETITION):
                    break;
                case (ROTATIONS):
                    break;
            }
        }
        return score;
    }

    private static double causalityRadom() {
        return -10.0 + new Random().nextDouble() * 20.0;

    }

    private static double CalculatePlayerScore(final PlayerModel player) {
        double lastFiveScore = 0;
        double seasonTotalScore = 0;

        List<ScoreModel> seasonTotal = player.getScores();


        seasonTotalScore = seasonTotal.stream().mapToDouble(score -> score.getScore()).sum() / seasonTotal.size();

        if (seasonTotal.size() > 5) {
            List<ScoreModel> lastFive = seasonTotal.subList(seasonTotal.size() - 6, seasonTotal.size() - 1);
            lastFiveScore = lastFive.stream().mapToDouble(score -> score.getScore()).sum() / 5;
        }


        return seasonTotal.size() > 5 ? lastFiveScore * 0.5 + seasonTotalScore * 0.5 : seasonTotalScore;
    }

    private static double CalculateTeamScore(final TeamModel team, final List<MatchModel> matches) {
        return matches.stream().mapToDouble(match -> CalculateMatchScore(team, match)).sum();
    }

    private static double CalculateMatchScore(final TeamModel team, final MatchModel match) {
        double score = 1;
        boolean isLocal = false;
        if (match.getLocal().equals(team)) {
            isLocal = true;
        } else if (match.getVisitor().equals(team)) {
            isLocal = false;
        } else {
            try {
                throw new Exception("Error inconsistencia de datos - calculateMatchScore - El equipo recibido no esta como visitante ni local");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int localScore = Integer.parseInt(match.getResult().split("-")[0]);
        int visitorScore = Integer.parseInt(match.getResult().split("-")[1]);

        if (isLocal && localScore > visitorScore || !isLocal && visitorScore > localScore) {
            score = 3;
        } else if (localScore != visitorScore) {
            score = -1;
        }

        return score;
    }
}
