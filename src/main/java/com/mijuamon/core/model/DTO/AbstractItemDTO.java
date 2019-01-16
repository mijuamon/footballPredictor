package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

public abstract class AbstractItemDTO {

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public abstract void convert(String data) throws ConvertException;
}
