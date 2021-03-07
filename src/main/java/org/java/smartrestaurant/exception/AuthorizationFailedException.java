package org.java.smartrestaurant.exception;

public class AuthorizationFailedException extends RuntimeException {
    public AuthorizationFailedException() {
        super("Authorization required");
    }
}
