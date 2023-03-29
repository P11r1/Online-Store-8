package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.Category;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/23/2023
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findById(Long id);

    List<Category> findAllByNameContainingIgnoreCase(String name);



//    Page<Category> pageCategory (Pageable pageable);
//
//    Page<Category> searchCategories(String keyword, Pageable pageable);



}