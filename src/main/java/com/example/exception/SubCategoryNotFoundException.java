package com.example.exception;

import java.security.PublicKey;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(String msg){
        super(msg);
    }
}
