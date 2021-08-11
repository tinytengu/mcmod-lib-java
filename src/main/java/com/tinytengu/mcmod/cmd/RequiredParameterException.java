package com.tinytengu.mcmod.cmd;

/**
 * RequiredParameterException class
 * @author tinytengu
 */
public class RequiredParameterException extends RuntimeException {
    public RequiredParameterException() {
    }

    public RequiredParameterException(String message) {
        super(message);
    }
}
