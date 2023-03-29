package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.SubCategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.SubCategory;
import com.example.onlinestorebackend.repositories.SubCategoryRepository;
import com.example.onlinestorebackend.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Marko
 * @Date 25/03/2023
 */
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public void createSubCategory(SubCategory subCategory) {
        subCategory.setActive(true);
        subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory findSubCategoryById(Long id) throws SubCategoryNotFoundException {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);

        if (optionalSubCategory.isEmpty()) {
            throw new SubCategoryNotFoundException(id);
        }
        return optionalSubCategory.get();
    }

    @Override
    public List<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void deleteSubCategoryById(Long id) throws SubCategoryNotFoundException {
        SubCategory subCategory = findSubCategoryById(id);
        subCategory.setActive(false);
        subCategoryRepository.saveAndFlush(subCategory);
    }

    @Override
    public void restoreSubCategoryById(Long id) throws SubCategoryNotFoundException {
        SubCategory subCategory = findSubCategoryById(id);
        subCategory.setActive(true);
        subCategoryRepository.saveAndFlush(subCategory);
    }

    @Override
    public List<SubCategory> findAllSubCategoriesByCategory(Category category) {
        return subCategoryRepository.findAllByCategory(category);
    }

    @Override
    public void updateSubCategory(SubCategory subCategory) throws SubCategoryNotFoundException {
        if (findSubCategoryById(subCategory.getId()) != null) {
            subCategoryRepository.saveAndFlush(subCategory);
        }
    }

    @Override
    public SubCategory findSubCategoryByName(String name) throws SubCategoryNotFoundException {
        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findByName(name);

        if(subCategoryOptional.isEmpty()) {
            throw new SubCategoryNotFoundException(name);
        }

        return subCategoryOptional.get();
    }
}