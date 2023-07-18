package com.apper.theblogservice.service;


import com.apper.theblogservice.Payload.ServiceError;
import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerListException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.exception.ExistingEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ServiceError handleInvalidInputFields(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingEmailException.class)
    @ResponseBody
    public ServiceError emailIsRegisteredException(ExistingEmailException exception) {
        return new ServiceError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BloggerNotFoundException.class)
    @ResponseBody
    public ServiceError invalidIdException(BloggerNotFoundException exception) {
        return new ServiceError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BloggerListException.class)
    @ResponseBody
    public ServiceError bloggerListEmpty(BloggerListException exception) {
        return new ServiceError(exception.getMessage());
    }

    @ExceptionHandler(BlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError BlogNotFoundException(BlogNotFoundException exception) {
        return new ServiceError(exception.getMessage());
    }

}
