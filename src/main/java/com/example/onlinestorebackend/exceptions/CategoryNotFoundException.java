package com.example.onlinestorebackend.exceptions;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public class CategoryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(String name) {
        super(String.format("Category not found for name : %s", name));
    }

    public CategoryNotFoundException(Long id) {
        super(String.format("Category not found for id : %d", id));
    }
}