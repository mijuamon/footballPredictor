package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerModel  extends AbstractModel
{
    private String name;
    private TeamModel team;
    private List<ScoreModel> scores= new ArrayList<>();

    public PlayerModel(String id, String name, TeamModel team) {
        super(id);
        this.name = name;
        this.team = team;
        this.scores = new ArrayList<>();
    }

    public PlayerModel(String id, String name) {
        super(id);
        setName(name);
    }

    public PlayerModel() {
        super(new Random().nextInt()+"");
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
    public String toString(){
        return getName();
    }

    @Override
    public boolean equals(Object obj)
    {
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
