package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mcq_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestionTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long question_id;
    String category;
    String question;
	String optionOne;
	String optionTwo;
	String optionThree;
	String optionFour;
	String correctOption;
	String positiveMark;
	String negativeMark;
}
