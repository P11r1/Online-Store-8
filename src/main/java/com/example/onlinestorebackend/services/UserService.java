package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.UserNotFoundException;
import com.example.onlinestorebackend.models.User;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */
public interface UserService {
    User findUserById(Long id) throws UserNotFoundException;

    /**
     * To find all users
     *
     * @return a list of User
     */
    List<User> findAllUsers();

    /**
     * To find user by fullName
     *
     * @param fullName  fullName
     * @return User
     */
    User findUserByFullName(String fullName) throws UserNotFoundException;

    /**
     * To create a new User
     *
     * @param user User
     */
    void createUser(User user);
}
