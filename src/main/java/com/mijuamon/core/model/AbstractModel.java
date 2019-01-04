package com.mijuamon.core.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractModel {

    private static String modelName;
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int ID;

    public AbstractModel(int id) {
        this.ID = id;
    }

    public AbstractModel() {

    }

    public static String getModelName() {
        return modelName;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
