package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.exception.QuestionNotFoundException;
import com.example.model.MultipleChoiceQuestion;
import com.example.service.MultipleChoiceQuestionTestService;

@RestController
public class MultipleChoiceQuestionTestController {

    @Autowired
    MultipleChoiceQuestionTestService multipleChoiceQuestionTestService;


    @PostMapping("/saveBulkQuestion")
    public ResponseEntity<List<MultipleChoiceQuestion>> saveQuestionFromExcelFile(@RequestParam("file") MultipartFile file){
        return multipleChoiceQuestionTestService.saveQuestionFromExcelFile(file);
    }
    
    @PostMapping("/saveQuestion")
    public ResponseEntity<MultipleChoiceQuestion> saveQuestions(@RequestBody MultipleChoiceQuestion multipleChoiceQuestionTest){
        return new ResponseEntity<>(multipleChoiceQuestionTestService.saveQuestions(multipleChoiceQuestionTest),HttpStatus.CREATED);
    }

    @PutMapping("/updateQuestion/{id}")
    public MultipleChoiceQuestion updateQuestion(@PathVariable("id") Long id,@RequestBody MultipleChoiceQuestion multipleChoiceQuestion){
        return multipleChoiceQuestionTestService.updateQuestion(id, multipleChoiceQuestion);
    }

    @GetMapping("/getAllQuestion")
    public List<MultipleChoiceQuestion> getAllQuestion(){
        if(multipleChoiceQuestionTestService.getAllQuestion()==null){
            throw new QuestionNotFoundException("Question is not present in database");
        }
        return multipleChoiceQuestionTestService.getAllQuestion();
    }

    @GetMapping("/getQuestionById")
    public Optional<MultipleChoiceQuestion> getQuestionById(@RequestParam("id") Long id){
        if(!multipleChoiceQuestionTestService.getAllQuestion().contains(id)){
            throw new QuestionNotFoundException("Question is not present in database");
        }
        return multipleChoiceQuestionTestService.getQuestionById(id);
    }

    @DeleteMapping("/deleteQuestion")
    public void deleteQuestion(@RequestParam("id") Long id){
        multipleChoiceQuestionTestService.deleteQuestion(id);
    }

}
