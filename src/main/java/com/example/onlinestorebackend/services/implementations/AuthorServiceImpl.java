package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.AuthorNotFoundException;
import com.example.onlinestorebackend.models.Author;
import com.example.onlinestorebackend.repositories.AuthorRepository;
import com.example.onlinestorebackend.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthorities() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorByFirstName(String firstName) throws AuthorNotFoundException {
        Optional<Author> optionalAuthority = authorRepository.findByFirstName(firstName);

        if (optionalAuthority.isEmpty()) {
            throw new AuthorNotFoundException(firstName);
        }

        return optionalAuthority.get();
    }

    @Override
    public void createAuthor(Author author) {

        author.setActive(true);
        authorRepository.save(author);

    }
}
