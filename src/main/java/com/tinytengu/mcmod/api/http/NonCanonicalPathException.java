package com.tinytengu.mcmod.api.http;

public class NonCanonicalPathException extends APIException {
    public NonCanonicalPathException() {
    }

    public NonCanonicalPathException(String message) {
        super(message);
    }
}
