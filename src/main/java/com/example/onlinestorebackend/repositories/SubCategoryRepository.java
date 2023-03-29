package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/27/2023
 */
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findById(Long id);
    Optional<SubCategory> findByName(String name);
    List<SubCategory> findAllByCategory(Category category);
}
