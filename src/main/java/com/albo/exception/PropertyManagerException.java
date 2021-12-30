package com.albo.exception;

public class PropertyManagerException extends Exception {
    public PropertyManagerException() {
        super();
    }

    public PropertyManagerException(String message) {
        super(message);
    }

    public PropertyManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyManagerException(Throwable cause) {
        super(cause);
    }
}
