package com.mijuamon.core.model;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class MatchModel extends Identificable {
    private static String modelName = "MatchModel";
    private static String tableName = "matches";

    @ManyToOne
    @PrimaryKeyJoinColumn
    private TeamModel local;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private TeamModel visitor;

    @Column(name = "result")
    private String result;

    @Column(name = "week")
    private String week;

    @Column(name = "year")
    private String year;

    public static String getModelName() {
        return modelName;
    }

    public static String getTableName() {
        return tableName;
    }


    public MatchModel(String year, String journey, String result) {
        super();

        setYear(year);
        setWeek(journey);
        setResult(result);
    }

    public MatchModel(TeamModel local, TeamModel visitor, String result, String journey, String year) {
        super();

        setLocal(local);
        setVisitor(visitor);
        setResult(result);
        setWeek(journey);
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String journey) {
        this.week = journey;
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
        if (this.visitor == null && other.visitor == null && this.local == null && other.local == null) {
            return true;
        }
        if (this.visitor != other.visitor || this.local != other.local || this.year != other.year || this.week != other.week) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(local==null || visitor == null)
        {
            return "visitor-local--->"+result;
        }
        return local.getName() + " - " + visitor.getName() + "-->" + result;
    }
}
