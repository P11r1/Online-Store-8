package com.example.onlinestorebackend.exceptions;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public class OrderNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String userName) {
        super(String.format("Order not found for firstName : %s" , userName));
    }
}
