package com.example.multipleChoiceQuestionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import com.example.controller.MultipleChoiceQuestionTestController;
import com.example.exception.QuestionIsAlreadyPresent;
import com.example.exception.QuestionNotFoundException;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.MultipleChoiceQuestion;
import com.example.service.MultipleChoiceQuestionTestService;

class MultipleChoiceQuestionTestControllerTest {

    @Mock
    private MultipleChoiceQuestionTestService questionService;

    @InjectMocks
    private MultipleChoiceQuestionTestController questionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveQuestionFromExcelFile() throws IOException {
       
        MockMultipartFile file = new MockMultipartFile("file", "QuestionBank.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "test data".getBytes());

   
        List<MultipleChoiceQuestion> savedQuestions = new ArrayList<>();
        when(questionService.saveQuestionFromExcelFile(file)).thenReturn(new ResponseEntity<>(savedQuestions, HttpStatus.OK));

        
        ResponseEntity<List<MultipleChoiceQuestion>> responseEntity = questionController.saveQuestionFromExcelFile(file);

     
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedQuestions, responseEntity.getBody());
    }

    @Test
    void testSaveQuestions() throws QuestionIsAlreadyPresent, SubCategoryNotFoundException {
        
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestion("Sample Question");

        
        when(questionService.saveQuestions(question)).thenReturn(question);

        
        ResponseEntity<MultipleChoiceQuestion> responseEntity = questionController.saveQuestions(question);

        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(question, responseEntity.getBody());
    }

    @Test
    void testUpdateQuestion() throws QuestionNotFoundException {
      
        Long id = 1L;
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestion("Updated Question");

        
        when(questionService.updateQuestion(id, question)).thenReturn(question);

       
        MultipleChoiceQuestion updatedQuestion = questionController.updateQuestion(id, question);

        
        assertEquals(question.getQuestion(), updatedQuestion.getQuestion());
    }

    @Test
    void testGetAllQuestion() throws QuestionNotFoundException {
    
        List<MultipleChoiceQuestion> questions = new ArrayList<>();
        questions.add(new MultipleChoiceQuestion());
        questions.add(new MultipleChoiceQuestion());

        
        when(questionService.getAllQuestion()).thenReturn(questions);

        
        List<MultipleChoiceQuestion> fetchedQuestions = questionController.getAllQuestion();

        
        assertEquals(questions.size(), fetchedQuestions.size());
    }

    @Test
    void testGetQuestionById() throws QuestionNotFoundException {
        
        Long id = 1L;
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestion("Sample Question");

        
        when(questionService.getQuestionById(id)).thenReturn(Optional.of(question));

        
        ResponseEntity<MultipleChoiceQuestion> responseEntity = questionController.getQuestionById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(question, responseEntity.getBody());
    }

    @Test
    void testGetQuestionById_NotFound() throws QuestionNotFoundException {
        
        Long id = 1L;

       
        when(questionService.getQuestionById(id)).thenReturn(Optional.empty());

       
        ResponseEntity<MultipleChoiceQuestion> responseEntity = questionController.getQuestionById(id);

       
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteQuestion() throws QuestionNotFoundException {
       
        Long id = 1L;

        ResponseEntity<Void> responseEntity = questionController.deleteQuestion(id);
        
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(questionService, times(1)).deleteQuestion(id);
    }
}

