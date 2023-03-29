package com.example.onlinestorebackend.controllers;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.exceptions.SubCategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.repositories.CategoryRepository;
import com.example.onlinestorebackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * Category Controller
 *
 * @author Marko
 * @Date 25/03/2023
 */

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping()
    public String showCategoryListPage(Model model, @Param("keyword") String keyword,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.findAllCategories());

        return "category/list-category";
    }

//    @GetMapping("/category/{pageNo}")
//    public String categoryPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/category";
//        }
//        Page<Category> categories = categoryService.pageCategory(pageNo);
//        model.addAttribute("title", "Manage Category");
//        model.addAttribute("size", categories.getSize());
//        model.addAttribute("totalPages", categories.getTotalPages());
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("categories", categories);
//        return "category/list-category";
//    }
//
//    @GetMapping("/search-result/{pageNo}")
//    public String searchCategories(@PathVariable("pageNo")int pageNo,
//                                 @RequestParam("keyword") String keyword,
//                                 Model model,
//                                 Principal principal){
//        if(principal == null){
//            return "redirect:/category";
//        }
//        Page<Category> categories = categoryService.searchCategory(pageNo, keyword);
//        model.addAttribute("title", "Search Result");
//        model.addAttribute("categories", categories);
//        model.addAttribute("size", categories.getSize());
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", categories.getTotalPages());
//        return "category/list-category";
//    }

    @GetMapping("/{id}")
    public String showCategoryViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("category", categoryService.findCategoryById(id));
            return "category/view-category";
        } catch (CategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategoryById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Category(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/category";
        } catch (CategoryNotFoundException | SubCategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.restoreCategoryById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Category(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/category";
        } catch (CategoryNotFoundException | SubCategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/create")
    public String showCreateCategoryPage(@ModelAttribute("category") Category category,
                                         @ModelAttribute("message") String message,
                                         @ModelAttribute("messageType") String messageType) {
        return "category/create-category";
    }

    @PostMapping
    public String createCategory(Category category, RedirectAttributes redirectAttributes) {
        try {
            Category searchCategory = categoryService.findCategoryByName(category.getName());
            redirectAttributes.addFlashAttribute("message", String.format("Category(%s) already exists!", category.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/category/create-category";
        } catch (CategoryNotFoundException e) {
            categoryService.createCategory(category);
            redirectAttributes.addFlashAttribute("message", String.format("Category(%s) has been created successfully!", category.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/category";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateCategoryPage(@PathVariable Long id, RedirectAttributes redirectAttributes,
                                         @RequestParam(value = "category", required = false) Category category,
                                         Model model) {
        if (category == null) {
            try {
                model.addAttribute("category", categoryService.findCategoryById(id));
            } catch (CategoryNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "category/update-category";
    }

    @PostMapping("/update")
    public String updateCategory(Category category, RedirectAttributes redirectAttributes) {
        try {
            categoryService.updateCategory(category);
            redirectAttributes.addFlashAttribute("message", String.format("Category(id=%d) has been updated successfully!", category.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/category";
        } catch (CategoryNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    // PRIVATE METHODS //
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/category";
    }


    @PostMapping("/search")
    public String categorySearch(Category category, Model model, String name) {

        List<Category> foundCategories = categoryRepository.findAllByNameContainingIgnoreCase(name);
        model.addAttribute("categories", foundCategories);

        return "category/list-category";
    }
}