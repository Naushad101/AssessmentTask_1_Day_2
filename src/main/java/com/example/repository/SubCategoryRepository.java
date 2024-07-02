package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Category;
import com.example.model.SubCategory;
@Repository
public interface SubCategoryRepository  extends JpaRepository<SubCategory,Long>{
     Optional<SubCategory> findBySubCategoryName(String subcategoryName);
}
