package com.example.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Category;
import com.example.model.MultipleChoiceQuestion;
import com.example.model.SubCategory;
import com.example.repository.CategoryRepository;
import com.example.repository.MultipleChoiceQuestionTestRepository;
import com.example.repository.SubCategoryRepository;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;

@Service
@Slf4j
public class MultipleChoiceQuestionTestService {
    @Autowired 
    MultipleChoiceQuestionTestRepository multipleChoiceQuestionTestRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;


      public ResponseEntity<List<MultipleChoiceQuestion>> saveQuestionFromExcelFile(MultipartFile file) {
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

        return new ResponseEntity<>(questionBank, HttpStatus.OK);
    }
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
