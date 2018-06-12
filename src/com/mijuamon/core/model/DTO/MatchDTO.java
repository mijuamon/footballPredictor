package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

public class MatchDTO extends AbstractItemDTO{
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

    @Override
    public void convert(String data) throws ConvertException {
        String[] lista = data.split(";");
        if (lista.length == 6) {
            this.setID(lista[0]);
            this.setLocalId(lista[1]);
            this.setVisitorId(lista[2]);
            this.setResult(lista[3]);
            this.setJourney(lista[4]);
            this.setYear(lista[5]);
        } else {
            throw new ConvertException(this.getClass().toString());
        }
    }
}
