package com.mijuamon.core.model;

import com.mijuamon.core.exceptions.ConvertException;

public  abstract class AbstractItemModel {



    public abstract void convert(String data) throws ConvertException;
}
