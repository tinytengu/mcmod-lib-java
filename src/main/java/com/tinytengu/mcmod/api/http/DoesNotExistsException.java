package com.tinytengu.mcmod.api.http;

public class DoesNotExistsException extends APIException {
    public DoesNotExistsException(String message) {
        super(message);
    }
}
