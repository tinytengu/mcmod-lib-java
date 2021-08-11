package com.tinytengu.mcmod.api.http;

public class InvalidPathException extends APIException {
    public InvalidPathException() {
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
