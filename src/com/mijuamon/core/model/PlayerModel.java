package com.mijuamon.core.model;

public class PlayerModel extends AbstractItemModel
{
    private String playerID;
    private String name;
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

    @Override
    public void convert(String data) {
        String [] lista=data.split(";");
        if(lista.length==2)
        {
            this.setPlayerID(lista[0]);
            this.setName(lista[1]);
        }
    }
}
