package com.apper.theblogservice.exception;

public class BloggerNotFoundException extends Exception {
    public BloggerNotFoundException(String message) {
        super(message);
    }
}
