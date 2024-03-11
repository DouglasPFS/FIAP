package com.techchallenge.restaurant.api.findfood.api.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    private ErrorMessage errorMessage = new ErrorMessage();

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(status.value());
        errorMessage.setMessage(e.getMessage());
        errorMessage.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.errorMessage);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> IllegalArgument(IllegalArgumentException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(status.value());
        errorMessage.setMessage(e.getMessage());
        errorMessage.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.errorMessage);
    }
}
