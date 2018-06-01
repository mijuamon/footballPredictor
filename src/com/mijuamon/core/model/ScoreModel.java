package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

public class ScoreModel extends AbstractItemModel{
    private String matchID;
    private MatchModel match;
    private String playerID;
    private PlayerModel player;
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

    public MatchModel getMatch() {
        return match;
    }

    public void setMatch(MatchModel match) {
        this.match = match;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
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

    @Override
    public String toString()
    {
        return match.getLocal().getName()+"---"+match.getVisitor().getName()+": "+match.getResult()+" ---> "+score;
    }
}
