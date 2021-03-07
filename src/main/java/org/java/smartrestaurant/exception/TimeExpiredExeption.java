package org.java.smartrestaurant.exception;

public class TimeExpiredExeption extends RuntimeException {
    public TimeExpiredExeption (String message) {
        super(message);
    }
}
