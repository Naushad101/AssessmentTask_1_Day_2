package com.example.exception;

import org.hibernate.boot.archive.scan.spi.ClassDescriptor.Categorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.model.Category;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;

@RestControllerAdvice
public class GlobalException {
    
    @org.springframework.web.bind.annotation.ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> questionNotFoundException(){
        return new ResponseEntity<>("Question is not present in database",HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<String> subCategoryNotFoundException(SubCategoryNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CategroyIsAlreadyPresent.class)
    public ResponseEntity<String> categoryIsAlreadyPresent(CategroyIsAlreadyPresent msg){
        return new ResponseEntity<>(msg.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SubCategoryIsAlreadyPresent.class)
    public ResponseEntity<String> subCategoryIsAlreadyPresent(SubCategoryIsAlreadyPresent msg){
        return new ResponseEntity<>(msg.getMessage(),HttpStatus.ALREADY_REPORTED);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(QuestionIsAlreadyPresent.class)
    public ResponseEntity<String> questionIsAlreadyPresentException(QuestionIsAlreadyPresent msg){
        return new ResponseEntity<>(msg.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

}
