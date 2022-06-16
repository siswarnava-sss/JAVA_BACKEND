package com.example.deep.enpensetracker.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static  final long serialVerstionUID = 1L;
    public ResourceNotFoundException(String message){
        super(message);
    }
}
