package com.example.onlinestorebackend.exceptions;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public class ProductNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String title) {
        super(String.format("Product not found for title : %s" , title));
    }

}
