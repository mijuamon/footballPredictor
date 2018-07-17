package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

public class ScoreDTO extends AbstractItemDTO{
    private String matchID;
    private String playerID;
    private Integer score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public void convert(String data) throws ConvertException {

        String[] lista = data.split(";");
        if (lista.length == 4) {
            this.setID(lista[0]);
            this.setMatchID(lista[1]);
            this.setPlayerID(lista[2]);
            this.setScore(Integer.parseInt(lista[3]));
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
