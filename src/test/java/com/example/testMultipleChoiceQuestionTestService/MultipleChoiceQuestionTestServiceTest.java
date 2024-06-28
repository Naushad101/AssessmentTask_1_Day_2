// package com.example.testMultipleChoiceQuestionTestService;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.example.model.MultipleChoiceQuestionTest;
// import com.example.repository.MultipleChoiceQuestionTestRepository;
// import com.example.service.MultipleChoiceQuestionTestService;

// @SpringBootTest
// public class MultipleChoiceQuestionTestServiceTest {

//     @Mock
//     private MultipleChoiceQuestionTestRepository repository;

//     @InjectMocks
//     private MultipleChoiceQuestionTestService service;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testSaveQuestions() {
//         MultipleChoiceQuestionTest question = new MultipleChoiceQuestionTest(1L,"save","save question","option A","option B","option C","option D","option A","3","-1");

//         when(repository.save(question)).thenReturn(question);

//         MultipleChoiceQuestionTest savedQuestion = service.saveQuestions(question);

//         assertEquals(question, savedQuestion);
//     }

//     @Test
//     public void testGetAllQuestions() {
//         List<MultipleChoiceQuestionTest> questionList = new ArrayList<>();
//         questionList.add(new MultipleChoiceQuestionTest(1L,"save","save question","option A","option B","option C","option D","option A","3","-1"));
//         questionList.add(new MultipleChoiceQuestionTest(1L,"save1","save question1","option A1","option B1","option C1","option D1","option A1","3","-1"));

//         when(repository.findAll()).thenReturn(questionList);

//         List<MultipleChoiceQuestionTest> fetchedList = service.getAllQuestion();

//         assertEquals(questionList, fetchedList);;
//     }

//     @Test
//     public void testGetQuestionById() {
//         Long id = 1L;
//        MultipleChoiceQuestionTest question = new MultipleChoiceQuestionTest(id,"save","save question","option A","option B","option C","option D","option A","3","-1");
//         when(repository.findById(id)).thenReturn(Optional.of(question));

//         Optional<MultipleChoiceQuestionTest> fetchedQuestion = service.getQuestionById(id);

//         assertEquals(Optional.of(question), fetchedQuestion);
//     }

//     @Test
//     public void testDeleteQuestion() {
//         Long id = 1L;

//         service.deleteQuestion(id);

//         verify(repository, times(1)).deleteById(id);
//     }

//     @Test
//     public void testUpdateQuestion() {
//         Long id = 1L;
//         String question = "Updated question";
//         String optionOne = "Updated option A";
//         String optionTwo = "Updated option B";
//         String optionThree = "Updated option C";
//         String optionFour = "Updated option D";
//         String correctOption = "A";
//         String category = "Updated category";

//         service.updateQuestion(id, question, optionOne, optionTwo, optionThree, optionFour, correctOption, category);

//         verify(repository, times(1)).updateQuestion(id, question, optionOne, optionTwo, optionThree, optionFour, correctOption, category);
//     }
// }
