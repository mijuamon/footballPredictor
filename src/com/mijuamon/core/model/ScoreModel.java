package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

public class ScoreModel extends AbstractItemModel{
    private String matchID;
    private String playerID;
    private double score;

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    @Override
    public void convert(String data) throws ConvertException {

        String[] lista = data.split(";");
        if (lista.length == 3) {
            this.setMatchID(lista[0]);
            this.setPlayerID(lista[1]);
            this.setScore(Double.parseDouble(lista[2]));
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
