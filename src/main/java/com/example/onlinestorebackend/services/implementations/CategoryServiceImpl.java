package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.exceptions.SubCategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.SubCategory;
import com.example.onlinestorebackend.repositories.CategoryRepository;
import com.example.onlinestorebackend.repositories.SubCategoryRepository;
import com.example.onlinestorebackend.services.CategoryService;
import com.example.onlinestorebackend.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/23/2023
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryService subCategoryService;


    @Override
    public void createCategory(Category category) {
        category.setActive(true);
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String name) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);

        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException(name);
        }
        return optionalCategory.get();
    }

    @Override
    public Category findCategoryById(Long id) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        return optionalCategory.get();
    }


    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryByName(String name) throws CategoryNotFoundException, SubCategoryNotFoundException {
        Category category = findCategoryByName(name);
        category.setActive(false);
        categoryRepository.saveAndFlush(category);

        for (SubCategory subCategory : subCategoryService.findAllSubCategoriesByCategory(category)) {
            subCategoryService.deleteSubCategoryById(subCategory.getId());
        }

    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryNotFoundException, SubCategoryNotFoundException {
        Category category = findCategoryById(id);
        category.setActive(false);
        categoryRepository.saveAndFlush(category);

        for (SubCategory subCategory : subCategoryService.findAllSubCategoriesByCategory(category)) {
            subCategoryService.deleteSubCategoryById(subCategory.getId());
        }
    }

    @Override
    public void restoreCategoryByName(String name) throws CategoryNotFoundException, SubCategoryNotFoundException {
        Category category = findCategoryByName(name);
        category.setActive(true);
        categoryRepository.saveAndFlush(category);

        for (SubCategory subCategory : subCategoryService.findAllSubCategoriesByCategory(category)) {
            subCategoryService.restoreSubCategoryById(subCategory.getId());
        }
    }

    @Override
    public void restoreCategoryById(Long id) throws CategoryNotFoundException, SubCategoryNotFoundException {
        Category category = findCategoryById(id);
        category.setActive(true);
        categoryRepository.saveAndFlush(category);

        for (SubCategory subCategory : subCategoryService.findAllSubCategoriesByCategory(category)) {
            subCategoryService.restoreSubCategoryById(subCategory.getId());
        }
    }

    @Override
    public void updateCategory(Category category) throws CategoryNotFoundException {
        if (findCategoryById(category.getId()) != null) {
            categoryRepository.saveAndFlush(category);
        }
    }

//    @Override
//    public Page<Category> searchCategory(int pageNo, String keyword) {
//        Pageable pageable = PageRequest.of(pageNo, 5);
//        Page<Category> categories = categoryRepository.searchCategories(keyword, pageable);
//        return categories;
//    }
//
//
//    @Override
//    public Page<Category> pageCategory(int pageNo) {
//        Pageable pageable = PageRequest.of(pageNo, 5);
//        return categoryRepository.pageCategory(pageable);
//    }


}