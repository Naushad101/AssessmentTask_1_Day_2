package com.example.exception;

public class QuestionNotFoundException extends Exception{
    public QuestionNotFoundException(String msg){
        super(msg);
    }
}
