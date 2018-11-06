package com.mijuamon.core.model.player;

import com.mijuamon.core.model.AbstractModel;
import com.mijuamon.core.model.score.ScoreModel;
import com.mijuamon.core.model.team.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel extends AbstractModel {
    private String name;
    private TeamModel team;
    private List<ScoreModel> scores = new ArrayList<>();

    public PlayerModel(String name, TeamModel team) {
        this.name = name;
        this.team = team;
        this.scores = new ArrayList<>();
    }

    public PlayerModel(String id, String name) {
        setName(name);
    }

    public PlayerModel() {
    }

    public PlayerModel(String name) {
        setName(name);
    }
    public PlayerModel(TeamModel team) {
        setTeam(team);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScoreModel> getScores() {
        return scores;
    }

    public void setScores(List<ScoreModel> scores) {
        this.scores = scores;
    }


    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }


    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!PlayerModel.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final PlayerModel other = (PlayerModel) obj;
        if (this.name != other.name) {
            return false;
        }
        if (!this.team.equals(other.team)) {
            return false;
        }
        return true;
    }
}
