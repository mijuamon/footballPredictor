package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.ArrayList;
import java.util.List;

public class TeamModel extends AbstractModel{

    private String name;
    private List<PlayerModel> players= new ArrayList<>();
    private List<MatchModel> matches= new ArrayList<>();

    public TeamModel(String id) {
        super(id);
    }
    public TeamModel(String id, String name) {
        super(id);
        this.name = name;
    }

    public void addPlayer(PlayerModel player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }

    public List<MatchModel> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchModel> matches) {
        this.matches = matches;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof TeamModel)) {
            return false;
        }
        TeamModel team = (TeamModel) o;
        return team.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
