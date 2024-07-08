package com.example.exception;

public class CategroyIsAlreadyPresent extends RuntimeException{
    public CategroyIsAlreadyPresent(String msg){
        super(msg);
    }
}
