package com.mijuamon.core.exceptions;

public class ConvertException extends Exception {

    public ConvertException(String message) {

        super("Error de conversion en "+message);
    }
}
