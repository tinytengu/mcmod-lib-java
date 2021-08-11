package com.tinytengu.mcmod.cmd;

/**
 * UnexpectedParameterException class
 * @author tinytengu
 */
public class UnexpectedParameterException extends RuntimeException {
    public UnexpectedParameterException() {
    }

    public UnexpectedParameterException(String message) {
        super(message);
    }
}
