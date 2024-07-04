package com.example.exception;

public class CategoryNotFoundException  extends Exception{
    public CategoryNotFoundException(String msg){
        super(msg);
    }
}
