package com.example.exception;

public class QuestionIsAlreadyPresent extends Exception{
    public QuestionIsAlreadyPresent(String msg){
        super(msg);
    }
}
