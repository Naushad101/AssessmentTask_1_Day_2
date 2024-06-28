package com.example.model;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mcq_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long question_Id;
    String question;
	String optionOne;
	String optionTwo;
	String optionThree;
	String optionFour;
	String correctOption;
	String positiveMark;
	String negativeMark;
	@ManyToOne
	@JoinColumn(name="SubCategory_Id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	SubCategory subCategory;
}
