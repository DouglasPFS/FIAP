package com.techchallenge.restaurant.api.findfood.controller.exception;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message){
        super(message);
    }
}
