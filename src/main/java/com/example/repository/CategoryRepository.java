package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Category;

import jakarta.transaction.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.categoryName = :categoryName, c.categoryDescription = :categoryDescription WHERE c.id = :id")
    void updateCategory(@Param("id") Long id, @Param("categoryName") String categoryName, @Param("categoryDescription") String categoryDescription);
}

