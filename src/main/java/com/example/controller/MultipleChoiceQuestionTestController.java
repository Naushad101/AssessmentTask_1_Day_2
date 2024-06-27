package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MultipleChoiceQuestionTest;

@RestController
public class MultipleChoiceQuestionTestController {
    
    @PostMapping("/saveQuestion")
    public String saveQuestions(@RequestBody MultipleChoiceQuestionTest multipleChoiceQuestionTest){
        return null;
    }

}
