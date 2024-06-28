package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MultipleChoiceQuestionTest;
import com.example.repository.MultipleChoiceQuestionTestRepository;

@Service
public class MultipleChoiceQuestionTestService {
    @Autowired 
    MultipleChoiceQuestionTestRepository multipleChoiceQuestionTestRepository;

    public MultipleChoiceQuestionTest saveQuestions(MultipleChoiceQuestionTest multipleChoiceQuestionTest){
        return multipleChoiceQuestionTestRepository.save(multipleChoiceQuestionTest);
    }

    public java.util.List<MultipleChoiceQuestionTest> getAllQuestion(){
        return multipleChoiceQuestionTestRepository.findAll();
    }

    public Optional<MultipleChoiceQuestionTest> getQuestionById(Long id){
       return multipleChoiceQuestionTestRepository.findById(id);
    }

     public void deleteQuestion(Long id){
        multipleChoiceQuestionTestRepository.deleteById(id);
    }

    // public void updateQuestion(Long id,String question,String optionOne,String secondOptioString,String optionThree,String optionFour,String correctOption,String category){
                                                                        //multipleChoiceQuestionTestRepository.updateQuestion(id, question, optionOne,secondOptioString, optionThree,optionFour,correctOption,category);
                                                                   // }


}
