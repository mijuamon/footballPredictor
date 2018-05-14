package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

public class MatchModel extends AbstractItemModel{
    private String localId;
    private String visitorId;
    private String result;
    private String journey;
    private String year;

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


    public boolean equalMatch(TeamModel t1, TeamModel t2, boolean explicit) {
        if (explicit) {
            return (t1.equals(localId) && visitorId.equals(t2));
        } else {
            return (t1.equals(localId) && visitorId.equals(t2)) || (t1.equals(visitorId) && t2.equals(localId));
        }
    }

    @Override
    public void convert(String data) throws ConvertException {
        String[] lista = data.split(";");
        if (lista.length == 5) {
            this.setLocalId(lista[0]);
            this.setVisitorId(lista[1]);
            this.setResult(lista[2]);
            this.setJourney(lista[3]);
            this.setYear(lista[4]);
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
