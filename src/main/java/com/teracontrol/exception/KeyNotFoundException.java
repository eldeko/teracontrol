package com.teracontrol.exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(Long id) {
        super("Keychain not found with id: " + id);
    }
}