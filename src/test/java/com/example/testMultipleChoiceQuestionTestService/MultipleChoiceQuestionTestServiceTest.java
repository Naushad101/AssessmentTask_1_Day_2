package com.example.testMultipleChoiceQuestionTestService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Category;
import com.example.model.MultipleChoiceQuestion;
import com.example.model.SubCategory;
import com.example.repository.MultipleChoiceQuestionTestRepository;
import com.example.service.MultipleChoiceQuestionTestService;

@SpringBootTest
public class MultipleChoiceQuestionTestServiceTest {


    @Mock
    private MultipleChoiceQuestionTestService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    // public void testSaveQuestions() {
    //     MultipleChoiceQuestionTest question = new MultipleChoiceQuestionTest(1L,"save","save question","option A","option B","option C","option D","option A","3","-1");

    //     when(repository.save(question)).thenReturn(question);

    //     MultipleChoiceQuestionTest savedQuestion = service.saveQuestions(question);

    //     assertEquals(question, savedQuestion);
    // }

    // @Test
    // public void testGetAllQuestions() {
    //     List<MultipleChoiceQuestionTest> questionList = new ArrayList<>();
    //     questionList.add(new MultipleChoiceQuestionTest(1L,"save","save question","option A","option B","option C","option D","option A","3","-1"));
    //     questionList.add(new MultipleChoiceQuestionTest(1L,"save1","save question1","option A1","option B1","option C1","option D1","option A1","3","-1"));

    //     when(repository.findAll()).thenReturn(questionList);

    //     List<MultipleChoiceQuestionTest> fetchedList = service.getAllQuestion();

    //     assertEquals(questionList, fetchedList);;
    // }

    @Test
    public void testGetQuestionById() {
        Long id = 1L;
        String question = "Updated question";
        String optionOne = "Updated option A";
        String optionTwo = "Updated option B";
        String optionThree = "Updated option C";
        String optionFour = "Updated option D";
        String correctOption = "A";
        Category category = new Category(1L,"Alpha","Alpha category");
        SubCategory subCategory = new SubCategory(1L,"Alphabate","Alphabate subCategory",category);
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(id,question,optionOne,optionTwo,optionThree,optionFour,correctOption,"3","-1",subCategory);
        Optional<MultipleChoiceQuestion> fetchedQuestion = service.getQuestionById(1L);

        assertEquals(Optional.of(multipleChoiceQuestion), fetchedQuestion.get());
    }

    @Test
    public void testDeleteQuestion() {
        Long id = 1L;

        service.deleteQuestion(id);

        verify(service, times(1)).deleteQuestion(id);;
    }

    @Test
    public void testUpdateQuestion() {
        Long id = 1L;
        String question = "Updated question";
        String optionOne = "Updated option A";
        String optionTwo = "Updated option B";
        String optionThree = "Updated option C";
        String optionFour = "Updated option D";
        String correctOption = "A";
        Category category = new Category(1L,"Alpha","Alpha category");
        SubCategory subCategory = new SubCategory(1L,"Alphabate","Alphabate subCategory",category);
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(id,question,optionOne,optionTwo,optionThree,optionFour,correctOption,"3","-1",subCategory);
        service.updateQuestion(id, multipleChoiceQuestion);


       verify(service,times(1)).updateQuestion(id, multipleChoiceQuestion);
    }
}
