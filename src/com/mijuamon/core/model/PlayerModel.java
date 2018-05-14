package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

public class PlayerModel extends AbstractItemModel
{
    private String playerID;
    private String name;
    private String teamID;
    private ScoreModel scores;
    private double scoreMedian;

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScoreMedian() {
        return scoreMedian;
    }

    public void setScoreMedian(double scoreMedian) {
        this.scoreMedian = scoreMedian;
    }

    public ScoreModel getScores() {
        return scores;
    }

    public void setScores(ScoreModel scores) {
        this.scores = scores;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    @Override
    public void convert(String data) throws ConvertException {
        String [] lista=data.split(";");
        if(lista.length==3)
        {
            this.setPlayerID(lista[0]);
            this.setName(lista[1]);
            this.setTeamID(lista[2]);
        }
        else
        {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
