package com.example.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.exception.QuestionIsAlreadyPresent;
import com.example.exception.QuestionNotFoundException;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.MultipleChoiceQuestion;
import com.example.service.MultipleChoiceQuestionTestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/question")
@Slf4j
public class MultipleChoiceQuestionTestController {

    private MultipleChoiceQuestionTestService multipleChoiceQuestionTestService;

    public MultipleChoiceQuestionTestController(MultipleChoiceQuestionTestService multipleChoiceQuestionTestService) {
        this.multipleChoiceQuestionTestService = multipleChoiceQuestionTestService;
    }

    @PostMapping("/saveBulkQuestion")
    public ResponseEntity<List<MultipleChoiceQuestion>> saveQuestionFromExcelFile(@RequestParam("file") MultipartFile file) {
        log.info("Received request to save questions from Excel file.");

        ResponseEntity<List<MultipleChoiceQuestion>> responseEntity = multipleChoiceQuestionTestService.saveQuestionFromExcelFile(file);

        log.info("Returning response after saving questions from Excel file.");
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<MultipleChoiceQuestion> saveQuestions(@RequestBody MultipleChoiceQuestion multipleChoiceQuestionTest)
           {
        log.info("Received request to save question: {}", multipleChoiceQuestionTest);

        ResponseEntity<MultipleChoiceQuestion> responseEntity = new ResponseEntity<>(
                multipleChoiceQuestionTestService.saveQuestions(multipleChoiceQuestionTest), HttpStatus.CREATED);

        log.info("Returning response after saving question.");
        return responseEntity;
    }

    @PutMapping("/{id}")
    public MultipleChoiceQuestion updateQuestion(@PathVariable("id") Long id,
            @RequestBody MultipleChoiceQuestion multipleChoiceQuestion) {
        log.info("Received request to update question with ID: {}", id);

        MultipleChoiceQuestion updatedQuestion = multipleChoiceQuestionTestService.updateQuestion(id,
                multipleChoiceQuestion);

        log.info("Returning updated question: {}", updatedQuestion);
        return updatedQuestion;
    }

    @GetMapping()
    public List<MultipleChoiceQuestion> getAllQuestion() {
        log.info("Received request to fetch all questions.");

        List<MultipleChoiceQuestion> questions = multipleChoiceQuestionTestService.getAllQuestion();

        log.info("Returning {} questions.", questions.size());
        return questions;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultipleChoiceQuestion> getQuestionById(@PathVariable("id") Long id)
            {
        log.info("Received request to fetch question with ID: {}", id);

        Optional<MultipleChoiceQuestion> questionOptional = multipleChoiceQuestionTestService.getQuestionById(id);

        if (questionOptional.isPresent()) {
            MultipleChoiceQuestion question = questionOptional.get();
            log.info("Returning question: {}", question);
            return ResponseEntity.ok(question);
        } else {
            log.warn("Question with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id){
        log.info("Received request to delete question with ID: {}", id);

        multipleChoiceQuestionTestService.deleteQuestion(id);

        log.info("Question with ID {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }
}
