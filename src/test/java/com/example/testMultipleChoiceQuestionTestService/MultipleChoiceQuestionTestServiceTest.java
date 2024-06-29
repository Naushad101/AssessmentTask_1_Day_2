package com.example.testMultipleChoiceQuestionTestService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Category;
import com.example.model.MultipleChoiceQuestion;
import com.example.model.SubCategory;
import com.example.repository.MultipleChoiceQuestionTestRepository;
import com.example.service.MultipleChoiceQuestionTestService;

@ExtendWith(MockitoExtension.class)
public class MultipleChoiceQuestionTestServiceTest {

    @Mock
    private MultipleChoiceQuestionTestRepository questionRepository;

    @InjectMocks
    private MultipleChoiceQuestionTestService questionService;

    private MultipleChoiceQuestion question;

    @BeforeEach
    public void setUp() {
        question = new MultipleChoiceQuestion();
        question.setQuestion_Id(1L);
        question.setQuestion("Sample question");
        question.setOptionOne("Option A");
        question.setOptionTwo("Option B");
        question.setOptionThree("Option C");
        question.setOptionFour("Option D");
        question.setCorrectOption("Option A");
        question.setPositiveMark("3");
        question.setNegativeMark("-1");
        question.setSubCategory(new SubCategory(1L,"Sample","Sample Question",new Category(1L,"Sample","Sample Question")));
    }

    @Test
    public void testSaveQuestion() {
       
        when(questionRepository.save(question)).thenReturn(question);

        
        MultipleChoiceQuestion savedQuestion = questionService.saveQuestions(question);

        
        assertNotNull(savedQuestion);
        assertEquals("Sample question", savedQuestion.getQuestion());
    }

    @Test
    public void testGetAllQuestions() {
       
        List<MultipleChoiceQuestion> questions = new ArrayList<>();
        questions.add(question);
        when(questionRepository.findAll()).thenReturn(questions);

       
        List<MultipleChoiceQuestion> retrievedQuestions = questionService.getAllQuestion();

        
        assertNotNull(retrievedQuestions);
        assertEquals(1, retrievedQuestions.size());
        assertEquals("Sample question", retrievedQuestions.get(0).getQuestion());
    }

    @Test
    public void testGetQuestionById() {
        
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

       
        Optional<MultipleChoiceQuestion> retrievedQuestion = questionService.getQuestionById(1L);

    
        assertTrue(retrievedQuestion.isPresent());
        assertEquals("Sample question", retrievedQuestion.get().getQuestion());
    }

    @Test
    public void testUpdateQuestion() {
        
        String updatedQuestion = "Updated question";
        question.setQuestion(updatedQuestion);
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionRepository.save(any())).thenReturn(question);

        
        MultipleChoiceQuestion updated = new MultipleChoiceQuestion();
        updated.setQuestion(updatedQuestion);
        MultipleChoiceQuestion updatedQuestionObj = questionService.updateQuestion(1L, updated);

      
        assertNotNull(updatedQuestionObj);
        assertEquals(updatedQuestion, updatedQuestionObj.getQuestion());
    }

    @Test
    public void testDeleteQuestion() {
        
        questionService.deleteQuestion(1L);

        
        verify(questionRepository, times(1)).deleteById(1L);
    }
}
