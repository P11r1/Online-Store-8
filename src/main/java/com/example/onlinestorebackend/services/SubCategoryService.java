
package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.exceptions.SubCategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.SubCategory;

import java.util.List;

/**
 * @author Marko
 * @Date 25/03/2023
 */
public interface SubCategoryService {
    /**
     * To create a new SubCategory
     *
     * @param subCategory subCategory
     */
    void createSubCategory(SubCategory subCategory);

    /**
     * To find SubCategory by id
     *
     * @param id Category id
     * @return SubCategory
     */
    SubCategory findSubCategoryById(Long id) throws SubCategoryNotFoundException;
    /**
     * To find all SubCategories
     *
     * @return a list of SubCategories
     */
    List<SubCategory> findAllSubCategories();

    /**
     * To delete a SubCategory by id
     *
     * @param id Category id
     */
    void deleteSubCategoryById(Long id) throws SubCategoryNotFoundException;

    /**
     * To restore a SubCategory by id
     *
     * @param id SubCategory id
     */
    void restoreSubCategoryById(Long id) throws SubCategoryNotFoundException;

    /**
     * To find all SubCategories by Category
     *
     * @param category Category
     * @return a list of SubCategories
     */
    List<SubCategory> findAllSubCategoriesByCategory(Category category);

    /**
     * To update SubCategory
     *
     * @param subCategory SubCategory
     */
    void updateSubCategory(SubCategory subCategory) throws SubCategoryNotFoundException;

    /**
     * To find a Category by name
     * @param name name
     * @return Category
     */
    SubCategory findSubCategoryByName(String name) throws SubCategoryNotFoundException;


}