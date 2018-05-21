package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

import java.util.ArrayList;
import java.util.List;

public class TeamModel extends AbstractItemModel {

    private String name;
    private String id;
    private List<PlayerModel> players= new ArrayList<>();
    private List<MatchModel> matches= new ArrayList<>();

    public TeamModel() {
    }

    public TeamModel(String id, String name) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MatchModel> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchModel> matches) {
        this.matches = matches;
    }

    @Override
    public void convert(String data) throws ConvertException {
        String[] lista = data.split(";");
        if (lista.length == 2) {
            this.setId(lista[0]);
            this.setName(lista[1]);
        } else {
            throw new ConvertException(this.getClass().toString());
        }
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
