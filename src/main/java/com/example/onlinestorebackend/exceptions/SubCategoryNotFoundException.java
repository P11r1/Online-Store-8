package com.example.onlinestorebackend.exceptions;

/**
 * @author Marko
 * @Date 25/03/2023
 */
public class SubCategoryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public SubCategoryNotFoundException(Long id) {
        super(String.format("Sub category not found for id: %d", id));
    }

    public SubCategoryNotFoundException(String name) {
        super(String.format("Sub category not found for name: %s", name));
    }
}