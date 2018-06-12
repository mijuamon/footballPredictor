package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

public class MatchModel extends AbstractItemModel {
    private String matchId;
    private String localId;
    private TeamModel local;
    private TeamModel visitor;
    private String visitorId;
    private String result;
    private String journey;
    private String year;



    public MatchModel() {
        super();
    }
    public MatchModel(TeamModel local, TeamModel visitor, String result, String journey, String year) {
       this.local=local;
       this.visitor=visitor;
       this.localId= local.getId();
       this.visitorId=getVisitorId();
       this.year=year;
       this.journey=journey;
       this.result=result;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
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

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public boolean equals(Object obj)
    {
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
    public void convert(String data) throws ConvertException {
        String[] lista = data.split(";");
        if (lista.length == 6) {
            this.setMatchId(lista[0]);
            this.setLocalId(lista[1]);
            this.setVisitorId(lista[2]);
            this.setResult(lista[3]);
            this.setJourney(lista[4]);
            this.setYear(lista[5]);
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }

    @Override
    public String toString()
    {
        return local.getName()+" - "+visitor.getName()+"-->"+result;
    }
}
