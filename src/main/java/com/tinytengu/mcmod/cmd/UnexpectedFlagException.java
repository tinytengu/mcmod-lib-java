package com.tinytengu.mcmod.cmd;

/**
 * UnexpectedFlagException class
 * @author tinytengu
 */
public class UnexpectedFlagException extends RuntimeException {
    public UnexpectedFlagException() {
    }

    public UnexpectedFlagException(String message) {
        super(message);
    }
}
