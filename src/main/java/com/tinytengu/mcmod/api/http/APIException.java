package com.tinytengu.mcmod.api.http;

public class APIException extends Exception {
    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
