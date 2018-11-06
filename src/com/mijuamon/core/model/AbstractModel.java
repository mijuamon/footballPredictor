package com.mijuamon.core.model;

public abstract class AbstractModel {

    private static String modelName;
    private int ID;

    public static String getModelName()
    {
        return modelName;
    }
    public AbstractModel(int id) {
        this.ID=id;
    }

    public AbstractModel() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
