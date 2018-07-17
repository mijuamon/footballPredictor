package com.mijuamon.core.model.DTO;

import com.mijuamon.core.exceptions.ConvertException;

public abstract class AbstractItemDTO {

    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public abstract void convert(String data) throws ConvertException;
}
