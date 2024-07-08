package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.exception.QuestionIsAlreadyPresent;
import com.example.exception.QuestionNotFoundException;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.MultipleChoiceQuestion;
import com.example.model.SubCategory;
import com.example.repository.MultipleChoiceQuestionTestRepository;
import com.example.repository.SubCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public interface MultipleChoiceQuestionTestService {

    public ResponseEntity<List<MultipleChoiceQuestion>> saveQuestionFromExcelFile(MultipartFile file);

    public MultipleChoiceQuestion saveQuestions(MultipleChoiceQuestion multipleChoiceQuestionTest);

    public List<MultipleChoiceQuestion> getAllQuestion();

    public Optional<MultipleChoiceQuestion> getQuestionById(Long id);

    public void deleteQuestion(Long id);

    public MultipleChoiceQuestion updateQuestion(Long id, MultipleChoiceQuestion multipleChoiceQuestion);
        

}
