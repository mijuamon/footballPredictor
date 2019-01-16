package com.mijuamon.core.model;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class ScoreModel extends Identificable {
    private static String modelName = "ScoreModel";

    @OneToOne
    @PrimaryKeyJoinColumn
    private MatchModel match;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private PlayerModel player;

    @Column(name = "score")
    private Integer score;

    public ScoreModel(Integer score) {

        super();
        setScore(score);
    }

    public ScoreModel(String scoreValue, MatchModel match, PlayerModel player) {
        super();

        setMatch(match);
        this.setPlayer(player);
        setScore(Integer.parseInt(scoreValue));
    }

    public ScoreModel() {
    }


    public static String getModelName() {
        return modelName;
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
    public boolean equals(Object obj) {
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
