package com.mijuamon.core.predictors;

import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import java.util.List;

public class Predictor {

    public static double PredictMatch(final TeamModel local, final TeamModel visitor) {
        double result = 0;

        //Puntuacion de equipo por partidos
        double localMotivational = CalculateTeamMotivational(local, local.getMatches());
        double visitorMotivational = CalculateTeamMotivational(visitor, visitor.getMatches());

        //Puntuacion de juegadores
        double localPlayers = local.getPlayers().stream().mapToDouble(play->CalculatePlayerScore(play)).sum();
        double visitorPlayers = visitor.getPlayers().stream().mapToDouble(play->CalculatePlayerScore(play)).sum();

        //////////////////////////////////
        ///////otros condicionantes///////

        //////////////////////////////////





        //Total de puntuacion y puntuacion del equipo local
        double totalScore=localMotivational+visitorMotivational+localPlayers+visitorPlayers;
        double localScore=localMotivational+localPlayers;


        result=localScore*100/totalScore; //Calculamos el porcentaje de victoria del equipo local
        return result;
    }

    private static double CalculatePlayerScore(final PlayerModel player) {

        double lastFiveScore =0;
        double seasonTotalScore=0;

        List<ScoreModel> seasonTotal = player.getScores();
        seasonTotalScore=seasonTotal.stream().mapToDouble(score->score.getScore()).sum()/seasonTotal.size();

        if(seasonTotal.size()>5) {
            List<ScoreModel> lastFive = seasonTotal.subList(seasonTotal.size() - 6, seasonTotal.size() - 1);
            lastFiveScore=lastFive.stream().mapToDouble(score->score.getScore()).sum()/5;
        }

        return lastFiveScore+seasonTotalScore;
    }

    private static double CalculateTeamMotivational(final TeamModel team, final List<MatchModel> matches) {
        return matches.stream().mapToDouble(match->CalculateMatchScore(team,match)).sum();
    }

    private static double CalculateMatchScore(final TeamModel team, final  MatchModel match) {
        double score=1;
        boolean isLocal=false;
        if(match.getLocal().equals(team))
        {
            isLocal=true;
        }
        else if (match.getVisitor().equals(team))
        {
            isLocal=false;
        }
        else{
            try {
                throw new Exception("Error inconsistencia de datos - calculateMatchScore - El equipo recibido no esta como visitante ni local");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int localScore=Integer.parseInt(match.getResult().split("-")[0]);
        int visitorScore=Integer.parseInt(match.getResult().split("-")[1]);

        if(isLocal&&localScore>visitorScore || !isLocal&& visitorScore>localScore )
        {
            score=3;
        }
        else if(localScore!=visitorScore)
        {
            score=-1;
        }

        return score;
    }
}
