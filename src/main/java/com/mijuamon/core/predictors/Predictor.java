package com.mijuamon.core.predictors;

import com.mijuamon.core.model.match.MatchModel;
import com.mijuamon.core.model.player.PlayerModel;
import com.mijuamon.core.model.score.ScoreModel;
import com.mijuamon.core.model.team.TeamModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.mijuamon.core.constants.CalculationConstants.*;
import static com.mijuamon.core.constants.Constants.PLAYERS_MEDIAN;

public class Predictor {

    public static Map<Integer, Double> PredictMatch(final TeamModel local, final TeamModel visitor, final Map<Integer, String> conditionalLocal, final Map<Integer, String> conditionalVisitor) {

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
        double scoreConditionalLocal = calculateConditionals(conditionalLocal, true);
        double scoreConditionalVisitor = calculateConditionals(conditionalVisitor, false);
        scoreConditionalLocal += calculateIndirect(conditionalLocal.get(INDIRECT_COMPETITION), conditionalVisitor.get(INDIRECT_COMPETITION), conditionalLocal.get(LAST_MATCHS) != null ? true : false);


        //Total de puntuacion y puntuacion del equipo local
        //double totalScore = localMotivational + visitorMotivational + localPlayers + visitorPlayers+scoreConditionalLocal+scoreConditionalVisitor;
        double causalityScore = causalityRadom();
        Map<Integer, Double> systemsResults = new HashMap<>();
        for (Integer system : SYSTEM_LIST) {
            double localScore = getSystemScore(localMotivational, localPlayers, scoreConditionalLocal, causalityScore, system);
            double visitorScore = getSystemScore(localMotivational, localPlayers, scoreConditionalLocal, visitorMotivational + visitorPlayers + scoreConditionalVisitor + (causalityScore * (-1)), system);
            systemsResults.put(system, localScore / (localScore + visitorScore));

        }
        return systemsResults;
    }

    private static double getSystemScore(final double motivational, final double players, final double conditional, final double casuality, final Integer system) {
        double score = 0.0;
        switch (system) {
            case (SYSTEM_BASE):
                score = motivational * SYSTEM_BASE_TEAMS + players * SYSTEM_BASE_PLAYERS + conditional * SYSTEM_BASE_CONDITIONALS + casuality * SYSTEM_BASE_UNCERTAINTY;
                break;
            case (SYSTEM_PLAYERS):
                score = motivational * SYSTEM_PLAYERS_TEAMS + players * SYSTEM_PLAYERS_PLAYERS + conditional * SYSTEM_PLAYERS_CONDITIONALS + casuality * SYSTEM_PLAYERS_UNCERTAINTY;
                break;
            case (SYSTEM_TEAMS):
                score = motivational * SYSTEM_TEAMS_TEAMS + players * SYSTEM_TEAMS_PLAYERS + conditional * SYSTEM_TEAMS_CONDITIONALS + casuality * SYSTEM_TEAMS_UNCERTAINTY;
                break;
            case (SYSTEM_UNCERTAINTY):
                score = motivational * SYSTEM_UNCERTAINTY_TEAMS + players * SYSTEM_UNCERTAINTY_PLAYERS + conditional * SYSTEM_UNCERTAINTY_CONDITIONALS + casuality * SYSTEM_UNCERTAINTY_UNCERTAINTY;
                break;
        }
        return score;

    }

    private static double calculateConditionals(Map<Integer, String> conditional, final boolean isLocal) {
        double score = 0.0;
        for (Integer cond : conditional.keySet()) {
            switch (cond) {
                case (DERBI):
                    score += isLocal ? DERBI_LOCAL_VALUE : DERBI_VISITOR_VALUE;
                    break;
                case (COACH_CHANGE):
                    score += isLocal ? COACH_CHANGE_LOCAL_VALUE : COACH_CHANGE_VISITOR_VALUE;
                    break;
                case (HISTORIC_RIVALITY):
                    score += isLocal ? HISTORIC_RIVALITY_LOCAL_VALUE : HISTORIC_RIVALITY_VISITOR_VALUE;
                    break;
                case (COMPETITIONS_2):
                    score += isLocal ? COMPETITIONS_2_LOCAL_VALUE : COMPETITIONS_2_VISITOR_VALUE;
                    break;
                case (COMPETITIONS_3):
                    score += isLocal ? COMPETITIONS_3_LOCAL_VALUE : COMPETITIONS_3_VISITOR_VALUE;
                    break;
                case (MATCHS_OVERLOAD):
                    score += isLocal ? MATCHS_OVERLOAD_LOCAL_VALUE : MATCHS_OVERLOAD_VISITOR_VALUE;
                    break;
                case (COMPETITION_ELIMINATION):
                    score += isLocal ? COMPETITION_ELIMINATION_LOCAL_VALUE : COMPETITION_ELIMINATION_VISITOR_VALUE;
                    break;
                case (DIRECT_COMPETITION):
                    score += isLocal ? DIRECT_COMPETITION_LOCAL : DIRECT_COMPETITION_VISITOR;
                    break;
                case (ROTATIONS):
                    score += isLocal ? ROTATIONS_LOCAL_VALUE : ROTATIONS_VISITOR_VALUE;
                    break;
            }
        }
        return score;
    }

    private static double calculateIndirect(final String local, final String visitor, final boolean lastMatchs) {
        double score = INDIRECT_MATRIX.get(local + "/" + visitor);
        score += lastMatchs ? INDIRECT_COMPETITION_LAST_MATCHS : 0;
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
