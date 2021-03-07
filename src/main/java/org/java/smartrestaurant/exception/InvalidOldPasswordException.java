package org.java.smartrestaurant.exception;

public class InvalidOldPasswordException extends RuntimeException {
    public InvalidOldPasswordException() {
        super("Invalid Old Password");
    }
}
