package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.exceptions.SubCategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/23/2023
 */
public interface CategoryService {
    /**
     * To create a new Category
     *
     * @param category category
     */
    void createCategory(Category category);

    /**
     * To find a Category by name
     *
     * @param name Category name
     * @return Category
     */
    Category findCategoryByName(String name) throws CategoryNotFoundException;

    /**
     * To find Category by id
     *
     * @param id Category id
     * @return Category
     */
    Category findCategoryById(Long id) throws CategoryNotFoundException;

    /**
     * To find all Categories
     *
     * @return a list of Categories
     */
    List<Category> findAllCategories();

    /**
     * To delete a Category by name
     *
     * @param name Category name
     */
    void deleteCategoryByName(String name) throws CategoryNotFoundException, SubCategoryNotFoundException;

    /**
     * To delete a Category by id
     *
     * @param id Category id
     */
    void deleteCategoryById(Long id) throws  CategoryNotFoundException, SubCategoryNotFoundException;

    /**
     * To restore a Category by name
     *
     * @param name Category name
     */
    void restoreCategoryByName(String name) throws CategoryNotFoundException, SubCategoryNotFoundException;

    /**
     * To restroe a Category by id
     *
     * @param id Category id
     */
    void restoreCategoryById(Long id) throws CategoryNotFoundException, SubCategoryNotFoundException;

    /**
     * To update Category
     *
     * @param category Category
     */
    void updateCategory(Category category) throws CategoryNotFoundException;

//    Page<Category> pageCategory(int pageNo);
//    Page<Category> searchCategory(int pageNo, String keyword);

}