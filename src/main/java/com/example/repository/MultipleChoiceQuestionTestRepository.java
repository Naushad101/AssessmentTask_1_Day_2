package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.MultipleChoiceQuestionTest;

import jakarta.transaction.Transactional;

@Repository
public interface MultipleChoiceQuestionTestRepository extends JpaRepository<MultipleChoiceQuestionTest, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE MultipleChoiceQuestionTest q " +
           "SET q.question = :question, " +
           "    q.optionOne = :optionOne, " +
           "    q.optionTwo = :optionTwo, " +
           "    q.optionThree = :optionThree, " +
           "    q.optionFour = :optionFour, " +
           "    q.correctOption = :correctOption, " +
           "    q.category = :category " +
           "WHERE q.id = :id")
    void updateQuestion(@Param("id") Long id,
                        @Param("question") String question,
                        @Param("optionOne") String optionOne,
                        @Param("optionTwo") String optionTwo,
                        @Param("optionThree") String optionThree,
                        @Param("optionFour") String optionFour,
                        @Param("correctOption") String correctOption,
                        @Param("category") String category);
}
