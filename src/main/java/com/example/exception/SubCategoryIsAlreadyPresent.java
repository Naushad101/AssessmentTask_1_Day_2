package com.example.exception;

public class SubCategoryIsAlreadyPresent extends RuntimeException{
    public SubCategoryIsAlreadyPresent(String msg){
        super(msg);
    }
}
