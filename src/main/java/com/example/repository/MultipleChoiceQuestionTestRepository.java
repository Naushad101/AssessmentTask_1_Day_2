package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.MultipleChoiceQuestionTest;

@Repository
public interface MultipleChoiceQuestionTestRepository extends JpaRepository<MultipleChoiceQuestionTest,Long> {
    
}
