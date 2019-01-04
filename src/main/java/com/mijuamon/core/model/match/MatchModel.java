package com.mijuamon.core.model.match;

import com.mijuamon.core.model.AbstractModel;
import com.mijuamon.core.model.team.TeamModel;

import javax.persistence.*;

@Entity
@Table(name="matches")
public class MatchModel extends AbstractModel {
    @OneToOne
    @PrimaryKeyJoinColumn
    private TeamModel local;

    @OneToOne
    @PrimaryKeyJoinColumn
    private TeamModel visitor;

    @Column(name = "result")
    private String result;

    @Column(name = "journey")
    private String journey;

    @Column(name = "year")
    private String year;


    public MatchModel(String year, String journey, String result) {
        setYear(year);
        setJourney(journey);
        setResult(result);
    }

    public MatchModel(TeamModel local, TeamModel visitor, String result, String journey, String year) {
        setLocal(local);
        setVisitor(visitor);
        setResult(result);
        setJourney(journey);
        setYear(year);
    }

    public MatchModel() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public TeamModel getLocal() {
        return local;
    }

    public void setLocal(TeamModel local) {
        this.local = local;
    }

    public TeamModel getVisitor() {
        return visitor;
    }

    public void setVisitor(TeamModel visitor) {
        this.visitor = visitor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!MatchModel.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final MatchModel other = (MatchModel) obj;
        if (!this.visitor.equals(other.visitor) || !this.local.equals(other.local) || this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return local.getName() + " - " + visitor.getName() + "-->" + result;
    }
}