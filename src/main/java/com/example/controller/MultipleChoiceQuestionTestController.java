package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MultipleChoiceQuestionTest;
import com.example.service.MultipleChoiceQuestionTestService;

@RestController
public class MultipleChoiceQuestionTestController {

    @Autowired
    MultipleChoiceQuestionTestService multipleChoiceQuestionTestService;
    
    @PostMapping("/saveQuestion")
    public ResponseEntity<MultipleChoiceQuestionTest> saveQuestions(@RequestBody MultipleChoiceQuestionTest multipleChoiceQuestionTest){
        return new ResponseEntity<>(multipleChoiceQuestionTestService.saveQuestions(multipleChoiceQuestionTest),HttpStatus.CREATED);
    }

    @PutMapping("/updateQuestion")
    public ResponseEntity updateQuestion(@RequestParam("id") Long id,@RequestParam("question") String question,
                                                                    @RequestParam("optionOne") String optionOne,@RequestParam("secondOption") String secondOptioString,
                                                                    @RequestParam("optionThree") String optionThree,@RequestParam("optionFour") String optionFour){
                                                                        multipleChoiceQuestionTestService.updateQuestion(id, question, optionOne, secondOptioString, optionThree, optionFour);
                                                                        return new ResponseEntity<>(HttpStatus.OK);
                                                                    }

    @GetMapping("/getAllQuestion")
    public List<MultipleChoiceQuestionTest> getAllQuestion(){
        return multipleChoiceQuestionTestService.getAllQuestion();
    }

    @GetMapping("/getQuestionById")
    public Optional<MultipleChoiceQuestionTest> getQuestionById(@RequestParam("id") Long id){
        return multipleChoiceQuestionTestService.getQuestionById(id);
    }

    @DeleteMapping("/deleteQuestion")
    public void deleteQuestion(@RequestParam("id") Long id){
        multipleChoiceQuestionTestService.deleteQuestion(id);
    }

}
