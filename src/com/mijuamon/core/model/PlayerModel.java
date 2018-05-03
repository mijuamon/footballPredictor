package com.mijuamon.core.model;

public class PlayerModel {
    private String playerID;
    private String name;
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
}
