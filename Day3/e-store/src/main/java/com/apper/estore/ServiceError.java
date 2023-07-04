package com.apper.estore;

import java.util.List;

public class ServiceError {
    private List<String> errorMessages;

    public ServiceError(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
