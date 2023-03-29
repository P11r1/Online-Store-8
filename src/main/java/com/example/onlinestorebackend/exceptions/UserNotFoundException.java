package com.example.onlinestorebackend.exceptions;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String email) {
        super(String.format("User not found for email : %s!" , email));
    }
    public UserNotFoundException(Long id) {
        super(String.format("User not found for email : %d!" , id));
    }
}
