package com.apper.estore;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody

    // List<String> to handle multiple error messages
    // RuntimeException for intercepting age validation
    // Service Error for handling messages
    public ServiceError handleInvalidField(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessages.add(fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            errorMessages.add(objectError.getDefaultMessage());
        }
        return new ServiceError(errorMessages);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleRuntimeException(RuntimeException ex) {
        return new ServiceError(List.of(ex.getMessage()));
    }
}
