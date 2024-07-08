package com.example.exception;

public class QuestionIsAlreadyPresent extends RuntimeException{
    public QuestionIsAlreadyPresent(String msg){
        super(msg);
    }
}
