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
@Slf4j
public class MultipleChoiceQuestionTestService {

    private MultipleChoiceQuestionTestRepository multipleChoiceQuestionTestRepository;
    private SubCategoryRepository subCategoryRepository;

    public MultipleChoiceQuestionTestService(MultipleChoiceQuestionTestRepository multipleChoiceQuestionTestRepository, SubCategoryRepository subCategoryRepository) {
        this.multipleChoiceQuestionTestRepository = multipleChoiceQuestionTestRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public ResponseEntity<List<MultipleChoiceQuestion>> saveQuestionFromExcelFile(MultipartFile file) {
        log.debug("Saving questions from Excel file.");

        List<MultipleChoiceQuestion> questionBank = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

                    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        switch (j) {
                            case 2:
                                String categoryName = cell.toString();
                                Optional<SubCategory> subCategory = subCategoryRepository.findBySubCategoryName(categoryName);
                                subCategory.ifPresent(multipleChoiceQuestion::setSubCategory);
                                break;
                            case 3:
                                multipleChoiceQuestion.setQuestion(cell.toString());
                                break;
                            case 4:
                                multipleChoiceQuestion.setOptionOne(cell.toString());
                                break;
                            case 5:
                                multipleChoiceQuestion.setOptionTwo(cell.toString());
                                break;
                            case 6:
                                multipleChoiceQuestion.setOptionThree(cell.toString());
                                break;
                            case 7:
                                multipleChoiceQuestion.setOptionFour(cell.toString());
                                break;
                            case 8:
                                multipleChoiceQuestion.setCorrectOption(cell.toString());
                                break;
                            case 9:
                                multipleChoiceQuestion.setPositiveMark(cell.toString());
                                break;
                            case 10:
                                multipleChoiceQuestion.setNegativeMark(cell.toString());
                                break;
                            default:
                                break;
                        }
                    }

                    MultipleChoiceQuestion savedQuestion = multipleChoiceQuestionTestRepository.save(multipleChoiceQuestion);
                    questionBank.add(savedQuestion);
                } else {
                    log.info("Value of row is Null at index: " + i);
                }
            }

            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            log.error("Error reading file or closing resources", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Saved {} questions from Excel file.", questionBank.size());
        return new ResponseEntity<>(questionBank, HttpStatus.OK);
    }

    public MultipleChoiceQuestion saveQuestions(MultipleChoiceQuestion multipleChoiceQuestionTest) throws QuestionIsAlreadyPresent, SubCategoryNotFoundException {
        log.debug("Saving question: {}", multipleChoiceQuestionTest.getQuestion());

        if (multipleChoiceQuestionTestRepository.findByQuestion(multipleChoiceQuestionTest.getQuestion()).isPresent()) {
            String errorMessage = "Question: " + multipleChoiceQuestionTest.getQuestion() + " is already present in database";
            log.error(errorMessage);
            throw new QuestionIsAlreadyPresent(errorMessage);
        }

        if (subCategoryRepository.findById(multipleChoiceQuestionTest.getSubCategory().getSubCategoryId()).isEmpty()) {
            String errorMessage = "Subcategory " + multipleChoiceQuestionTest.getSubCategory().getSubCategoryName() + " is not present in database";
            log.error(errorMessage);
            throw new SubCategoryNotFoundException(errorMessage);
        }

        return multipleChoiceQuestionTestRepository.save(multipleChoiceQuestionTest);
    }

    public List<MultipleChoiceQuestion> getAllQuestion() throws QuestionNotFoundException {
        log.debug("Fetching all questions.");

        try {
            return multipleChoiceQuestionTestRepository.findAll();
        } catch (DataAccessException e) {
            log.error("Failed to retrieve questions from the database", e);
            throw new QuestionNotFoundException("Failed to retrieve questions from the database");
        }
    }

    public Optional<MultipleChoiceQuestion> getQuestionById(Long id) throws QuestionNotFoundException {
        log.debug("Fetching question with id: {}", id);

        if (multipleChoiceQuestionTestRepository.findById(id).isEmpty()) {
            String errorMessage = "Question with id " + id + " is not present in database";
            log.error(errorMessage);
            throw new QuestionNotFoundException(errorMessage);
        }

        return multipleChoiceQuestionTestRepository.findById(id);
    }

    public void deleteQuestion(Long id) throws QuestionNotFoundException {
        log.debug("Deleting question with id: {}", id);

        if (multipleChoiceQuestionTestRepository.findById(id).isEmpty()) {
            String errorMessage = "Question with id " + id + " is not present in database";
            log.error(errorMessage);
            throw new QuestionNotFoundException(errorMessage);
        }

        multipleChoiceQuestionTestRepository.deleteById(id);
        log.info("Deleted question with id {}", id);
    }

    public MultipleChoiceQuestion updateQuestion(Long id, MultipleChoiceQuestion multipleChoiceQuestion) throws QuestionNotFoundException {
        log.debug("Updating question with id: {}", id);

        Optional<MultipleChoiceQuestion> existingQuestion = multipleChoiceQuestionTestRepository.findById(id);
        if (existingQuestion.isEmpty()) {
            String errorMessage = "Question with id " + id + " is not present in database";
            log.error(errorMessage);
            throw new QuestionNotFoundException(errorMessage);
        }

        MultipleChoiceQuestion updatedQuestion = existingQuestion.get();
        updatedQuestion.setQuestion(multipleChoiceQuestion.getQuestion());
        updatedQuestion.setOptionOne(multipleChoiceQuestion.getOptionOne());
        updatedQuestion.setOptionTwo(multipleChoiceQuestion.getOptionTwo());
        updatedQuestion.setOptionThree(multipleChoiceQuestion.getOptionThree());
        updatedQuestion.setOptionFour(multipleChoiceQuestion.getOptionFour());
        updatedQuestion.setCorrectOption(multipleChoiceQuestion.getCorrectOption());
        updatedQuestion.setPositiveMark(multipleChoiceQuestion.getPositiveMark());
        updatedQuestion.setNegativeMark(multipleChoiceQuestion.getNegativeMark());
        updatedQuestion.setSubCategory(multipleChoiceQuestion.getSubCategory());

        MultipleChoiceQuestion savedQuestion = multipleChoiceQuestionTestRepository.save(updatedQuestion);
        log.info("Updated question with id {}", id);
        return savedQuestion;
    }

}
