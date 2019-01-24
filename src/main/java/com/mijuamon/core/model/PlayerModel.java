package com.mijuamon.core.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "players")
public class PlayerModel extends Identificable {
    private static String modelName = "PlayerModel";


    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private TeamModel team;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)

    private Set<ScoreModel> scores = new HashSet<>();

    public PlayerModel(String name, TeamModel team) {
        super();
        this.name = name;
        this.team = team;
        this.scores = new HashSet<>();
    }

    public PlayerModel() {
    }

    public PlayerModel(String name) {
        setName(name);
    }

    public static String getModelName() {
        return modelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ScoreModel> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreModel> scores) {
        this.scores = scores;
    }


    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }

    public void addScore(final ScoreModel score)
    {
        scores.add(score);
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
