package com.example.multipleChoiceQuestionTest;


import com.example.exception.QuestionIsAlreadyPresent;
import com.example.exception.QuestionNotFoundException;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.MultipleChoiceQuestion;
import com.example.model.SubCategory;
import com.example.repository.MultipleChoiceQuestionTestRepository;
import com.example.repository.SubCategoryRepository;
import com.example.service.Impl.MultipleChoiceQuestionTestServiceImpl;

import org.apache.poi.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MultipleChoiceQuestionTestServiceTest {

    @Mock
    private MultipleChoiceQuestionTestRepository questionRepository;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @InjectMocks
    private MultipleChoiceQuestionTestServiceImpl questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 @Test
public void testSaveQuestionFromExcelFile() throws IOException {
    String filePath = "C:\\Users\\naushad.shaikh\\Downloads\\QuestionBank.xlsx";
    
 
    File excelFile = new File(filePath);
    InputStream inputStream = new FileInputStream(excelFile);
    assertNotNull(inputStream, "Could not load QuestionBank.xlsx from path: " + filePath);

  
    byte[] content = IOUtils.toByteArray(inputStream);

  
    MultipartFile multipartFile = new MockMultipartFile("QuestionBank.xlsx", "QuestionBank.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", content);

   
    SubCategory subCategory = new SubCategory();
    subCategory.setSubCategoryId(1L);
    subCategory.setSubCategoryName("TestCategory");
    when(subCategoryRepository.findBySubCategoryName("TestCategory")).thenReturn(Optional.of(subCategory));

 
    MultipleChoiceQuestion question = new MultipleChoiceQuestion();
    question.setQuestion("Test Question");
    question.setSubCategory(subCategory);
    when(questionRepository.save(any())).thenReturn(question);

 
    ResponseEntity<List<MultipleChoiceQuestion>> response = questionService.saveQuestionFromExcelFile(multipartFile);


    assertEquals(HttpStatus.OK, response.getStatusCode());
    List<MultipleChoiceQuestion> savedQuestions = response.getBody();
    assertNotNull(savedQuestions);
    
    assertEquals("Test Question", savedQuestions.get(0).getQuestion());

    inputStream.close();
}


    @Test
    void testSaveQuestions_Success() throws QuestionIsAlreadyPresent, SubCategoryNotFoundException {
        
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestion("Test Question");
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(1L);
        question.setSubCategory(subCategory);

        when(questionRepository.findByQuestion("Test Question")).thenReturn(Optional.empty());
        when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));
        when(questionRepository.save(question)).thenReturn(question);

   
        MultipleChoiceQuestion savedQuestion = questionService.saveQuestions(question);

        
        assertEquals("Test Question", savedQuestion.getQuestion());
    }

    @Test
    void testGetAllQuestion_Success() throws QuestionNotFoundException {
      
        List<MultipleChoiceQuestion> mockQuestions = new ArrayList<>();
        mockQuestions.add(new MultipleChoiceQuestion());
        when(questionRepository.findAll()).thenReturn(mockQuestions);

      
        List<MultipleChoiceQuestion> questions = questionService.getAllQuestion();

     
        assertEquals(1, questions.size());
    }

    @Test
    void testGetQuestionById_Success() throws QuestionNotFoundException {
        
        Long questionId = 1L;
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestion_Id(questionId);

        
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        Optional<MultipleChoiceQuestion> foundQuestion = questionService.getQuestionById(questionId);

        
        assertTrue(foundQuestion.isPresent());
        assertEquals(questionId, foundQuestion.get().getQuestion_Id());
    }

    @Test
    void testDeleteQuestion_Success() throws QuestionNotFoundException {
        
        Long questionId = 1L;

        
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(new MultipleChoiceQuestion()));

       
        assertDoesNotThrow(() -> questionService.deleteQuestion(questionId));
    }

    @Test
    void testUpdateQuestion_Success() throws QuestionNotFoundException {
        
        Long questionId = 1L;
        MultipleChoiceQuestion existingQuestion = new MultipleChoiceQuestion();
        existingQuestion.setQuestion_Id(questionId);

        MultipleChoiceQuestion updatedQuestion = new MultipleChoiceQuestion();
        updatedQuestion.setQuestion("Updated Question");

      
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        when(questionRepository.save(existingQuestion)).thenReturn(updatedQuestion);

      
        MultipleChoiceQuestion result = questionService.updateQuestion(questionId, updatedQuestion);

       
        assertEquals("Updated Question", result.getQuestion());
    }


}
