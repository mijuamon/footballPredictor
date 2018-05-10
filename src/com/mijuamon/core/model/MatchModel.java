package com.mijuamon.core.model;

public class MatchModel {
    private TeamModel local;
    private TeamModel visitor;
    private String result;
    private String journey;
    private String year;

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


    public boolean equalMatch(TeamModel t1, TeamModel t2, boolean explicit) {
        if (explicit) {
            return (t1.equals(local) && visitor.equals(t2));
        } else {
            return (t1.equals(local) && visitor.equals(t2)) || (t1.equals(visitor) && t2.equals(local));
        }
    }
}
