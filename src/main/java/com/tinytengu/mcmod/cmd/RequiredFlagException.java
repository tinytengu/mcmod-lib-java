package com.tinytengu.mcmod.cmd;

/**
 * RequiredFlagException class
 * @author tinytengu
 */
public class RequiredFlagException extends RuntimeException {
    public RequiredFlagException() {
    }

    public RequiredFlagException(String message) {
        super(message);
    }
}
