package com.mijuamon.core.model.score;

import com.mijuamon.core.model.AbstractModel;
import com.mijuamon.core.model.match.MatchModel;
import com.mijuamon.core.model.player.PlayerModel;

import java.util.Random;

public class ScoreModel  extends AbstractModel {
    private MatchModel match;
    private PlayerModel player;
    private Integer score;

    public ScoreModel( Integer score) {
        setScore(score);
    }

    public ScoreModel(String scoreValue, MatchModel match, PlayerModel player) {
        setMatch(match);
        this.setPlayer(player);
        setScore(Integer.parseInt(scoreValue));
    }

    public ScoreModel() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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
    public String toString() {
        return match.getLocal().getName() + "---" + match.getVisitor().getName() + ": " + match.getResult() + " ---> " + score;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (!ScoreModel.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ScoreModel other = (ScoreModel) obj;
        if (!this.match.equals(other.getMatch())) {
            return false;
        }
        return true;
    }
}
