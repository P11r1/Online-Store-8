package com.example.onlinestorebackend.controllers;

import com.example.onlinestorebackend.exceptions.SubCategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.SubCategory;
import com.example.onlinestorebackend.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Marko
 * @Date 25/03/2023
 */

@Controller
@RequestMapping("/subcategory")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping()
    public String showSubCategoryListPage(Model model, @ModelAttribute("message") String message,
                                          @ModelAttribute("messageType") String messageType) {
        model.addAttribute("subcategories", subCategoryService.findAllSubCategories());
        return "category/subcategory/list-subcategory";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            subCategoryService.deleteSubCategoryById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Sub category(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/subcategory";
        } catch (SubCategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreSubCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            subCategoryService.restoreSubCategoryById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Sub category(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/subcategory";
        } catch (SubCategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/create")
    public String showCreateSubCategoryPage(@ModelAttribute("subcategory") SubCategory subCategory,
                                            @ModelAttribute("message") String message,
                                            @ModelAttribute("messageType") String messageType) {
        return "category/subcategory/create-subcategory";
    }

    @PostMapping
    public String createSubCategory(Category category,SubCategory subCategory, RedirectAttributes redirectAttributes) {

        try {
            SubCategory searchSubCategory = subCategoryService.findSubCategoryByName(subCategory.getName());
            redirectAttributes.addFlashAttribute("message", String.format("Sub category(%d) already exists!", subCategory.getName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/category/subcategory/create-subcategory";
        } catch (SubCategoryNotFoundException e) {
            subCategoryService.createSubCategory(subCategory);
            redirectAttributes.addFlashAttribute("message", String.format("Sub category(%d) has been created successfully!", subCategory.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/subcategory";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateSubCategoryPage(@PathVariable Long id, RedirectAttributes redirectAttributes,
                                            @RequestParam(value = "subcategory", required = false) SubCategory subCategory,
                                            Model model) {
        if (subCategory == null) {
            try {
                model.addAttribute("subcategory", subCategoryService.findSubCategoryById(id));
            } catch (SubCategoryNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "category/subcategory/update-subcategory";
    }

    @PostMapping("/update")
    public String updateSubCategory(SubCategory subCategory, RedirectAttributes redirectAttributes) {
        try {
            subCategoryService.updateSubCategory(subCategory);
            redirectAttributes.addFlashAttribute("message", String.format("Sub category(id=%d) has been updated successfully!", subCategory.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/subcategory";
        } catch (SubCategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    // PRIVATE METHODS //
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/school";
    }
}