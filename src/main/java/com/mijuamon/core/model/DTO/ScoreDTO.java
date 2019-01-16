package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

public class ScoreDTO extends AbstractItemDTO{
    private int matchID;
    private int playerID;
    private Integer score;

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
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
            this.setID(Integer.parseInt(lista[0]));
            this.setMatchID(Integer.parseInt(lista[1]));
            this.setPlayerID(Integer.parseInt(lista[2]));
            this.setScore(Integer.parseInt(lista[3]));
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
