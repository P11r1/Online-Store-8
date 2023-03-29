package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByFirstName(String firstName);
}
