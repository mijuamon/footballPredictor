package com.mijuamon.core.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
public class TeamModel extends Identificable {

    private static String modelName = "TeamModel";

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlayerModel> players = new HashSet<>();
    @ManyToMany (cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<MatchModel> matches = new HashSet<>();

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

    public Set<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerModel> players) {
        this.players = players;
    }

    public Set<MatchModel> getMatches() {
        return matches;
    }

    public void setMatches(Set<MatchModel> matches) {
        this.matches = matches;
    }

    public void addMatch(final MatchModel match) {
        matches.add(match);
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
