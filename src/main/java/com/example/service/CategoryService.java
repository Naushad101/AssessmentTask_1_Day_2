package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Category;

@Service
public interface CategoryService {

    public Category saveCategory(Category category);

    public List<Category> getCategory();

    public ResponseEntity<Category> updateCategory(Category category);

    public void deleteCategory(Long id);

}
