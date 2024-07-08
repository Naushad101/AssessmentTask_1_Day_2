package com.example.exception;


public class SubCategoryNotFoundException extends RuntimeException{
    public SubCategoryNotFoundException(String msg){
        super(msg);
    }
}
