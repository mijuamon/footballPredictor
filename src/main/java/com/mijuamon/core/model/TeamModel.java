package com.mijuamon.core.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class TeamModel extends Identificable {

    private static String modelName = "TeamModel";

    @Column(name = "name")
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PlayerModel> players = new ArrayList<>();
    @OneToMany(
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MatchModel> matches = new ArrayList<>();

    public TeamModel(String name) {

        super();
        this.name = name;
    }

    public TeamModel() {
        super();
    }

    public static String getModelName() {
        return modelName;
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
