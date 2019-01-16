package com.mijuamon.core.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Identificable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int ID;

    public Identificable(int id) {
        this.ID = id;
    }

    public Identificable(int id, String modelName) {
        this.ID = id;
    }

    public Identificable() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
