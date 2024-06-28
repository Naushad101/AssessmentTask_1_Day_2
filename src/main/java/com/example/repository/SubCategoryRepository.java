package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.SubCategory;

public interface SubCategoryRepository  extends JpaRepository<SubCategory,Long>{
    
}
