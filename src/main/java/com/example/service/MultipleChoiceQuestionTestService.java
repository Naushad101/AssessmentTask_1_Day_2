package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MultipleChoiceQuestion;
import com.example.repository.MultipleChoiceQuestionTestRepository;

@Service
public class MultipleChoiceQuestionTestService {
    @Autowired 
    MultipleChoiceQuestionTestRepository multipleChoiceQuestionTestRepository;

    public MultipleChoiceQuestion saveQuestions(MultipleChoiceQuestion multipleChoiceQuestionTest){
        return multipleChoiceQuestionTestRepository.save(multipleChoiceQuestionTest);
    }

    public java.util.List<MultipleChoiceQuestion> getAllQuestion(){
        return multipleChoiceQuestionTestRepository.findAll();
    }

    public Optional<MultipleChoiceQuestion> getQuestionById(Long id){
       return multipleChoiceQuestionTestRepository.findById(id);
    }

     public void deleteQuestion(Long id){
        multipleChoiceQuestionTestRepository.deleteById(id);
    }

    public MultipleChoiceQuestion updateQuestion(Long id,MultipleChoiceQuestion multipleChoiceQuestion){
        Optional<MultipleChoiceQuestion> existinqQuestion = multipleChoiceQuestionTestRepository.findById(id);
        if(existinqQuestion.isPresent()){
            MultipleChoiceQuestion multipleChoiceQuestion2 = existinqQuestion.get();
            multipleChoiceQuestion2.setQuestion(multipleChoiceQuestion.getQuestion());
            multipleChoiceQuestion2.setOptionOne(multipleChoiceQuestion.getOptionOne());
            multipleChoiceQuestion2.setOptionTwo(multipleChoiceQuestion.getOptionTwo());
            multipleChoiceQuestion2.setOptionThree(multipleChoiceQuestion.getOptionThree());
            multipleChoiceQuestion2.setOptionFour(multipleChoiceQuestion.getOptionFour());
            multipleChoiceQuestion2.setCorrectOption(multipleChoiceQuestion.getCorrectOption());
            multipleChoiceQuestion2.setPositiveMark(multipleChoiceQuestion.getPositiveMark());
            multipleChoiceQuestion2.setNegativeMark(multipleChoiceQuestion.getNegativeMark());
            multipleChoiceQuestion2.setSubCategory(multipleChoiceQuestion.getSubCategory());
            return multipleChoiceQuestionTestRepository.save(multipleChoiceQuestion2);
        }
        else return null;
    }


}
