package com.mijuamon.core.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class PlayerModel extends Identificable {
    private static String modelName = "PlayerModel";


    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TeamModel team;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)

    private List<ScoreModel> scores = new ArrayList<>();

    public PlayerModel(String name, TeamModel team) {
        super();
        this.name = name;
        this.team = team;
        this.scores = new ArrayList<>();
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
