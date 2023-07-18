package com.apper.theblogservice.exception;

public class BlogNotFoundException extends Exception {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
