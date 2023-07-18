package com.apper.theblogservice.exception;

public class ExistingEmailException extends Exception {
    public ExistingEmailException(String message) {
        super(message);
    }
}