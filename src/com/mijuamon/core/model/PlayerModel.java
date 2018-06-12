package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel extends AbstractItemModel
{
    private String playerID;
    private String name;
    private String teamID;
    private TeamModel team;
    private List<ScoreModel> scores= new ArrayList<>();

    public PlayerModel() {
    }

    public PlayerModel(String playerID, String name, TeamModel team) {
        this.playerID = playerID;
        this.name = name;
        this.teamID = team.getId();
        this.team = team;
        this.scores = new ArrayList<>();
    }

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

    public List<ScoreModel> getScores() {
        return scores;
    }

    public void setScores(List<ScoreModel> scores) {
        this.scores = scores;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }

    @Override
    public void convert(String data) throws ConvertException {
        String [] lista=data.split(";");
        if(lista.length==3)
        {
            this.setPlayerID(lista[0]);
            this.setTeamID(lista[1]);
            this.setName(lista[2]);

        }
        else
        {
            throw new ConvertException(this.getClass().toString());
        }
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
        if (this.teamID != other.teamID) {
            return false;
        }
        if (!this.team.equals(other.team)) {
            return false;
        }
        return true;
    }
}
