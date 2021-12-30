package com.albo.exception;

public class AnswersNotFoundException extends Exception{
    public AnswersNotFoundException(String message) {
        super(message);
    }

    public AnswersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnswersNotFoundException(Throwable cause) {
        super(cause);
    }
}
