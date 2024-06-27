package com.example.service;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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


}
