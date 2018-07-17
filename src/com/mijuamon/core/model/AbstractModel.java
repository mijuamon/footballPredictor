package com.mijuamon.core.model;

public abstract class AbstractModel {

    private String ID;

    public AbstractModel(String id) {
        this.ID=id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
