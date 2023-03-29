package com.example.onlinestorebackend.controllers;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.exceptions.ProductNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.Product;
import com.example.onlinestorebackend.services.CategoryService;
import com.example.onlinestorebackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Marko
 * @Date 24/03/2023
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProductListPage(Model model, @ModelAttribute("message") String message,
                                      @ModelAttribute("messageType") String messageType) {
        model.addAttribute("products", productService.findAllProducts());
        return "product/list-product";
    }

    @GetMapping("/{title}")
    public String showProductViewPage(@PathVariable String title, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("product", productService.findProductByTitle(title));
            return "product/view-product";
        } catch (ProductNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/delete/{title}")
    public String deleteProduct(@PathVariable String title, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProductByTitle(title);
            redirectAttributes.addFlashAttribute("message", String.format("Product(title=%s) deleted successfully!", title));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/product";
        } catch (ProductNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/restore/{title}")
    public String restoreProduct(@PathVariable String title, RedirectAttributes redirectAttributes) {
        try {
            productService.restoreProductByTitle(title);
            redirectAttributes.addFlashAttribute("message", String.format("Product(title=%s) deleted successfully!", title));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/product";
        } catch (ProductNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    // To show create product form page
    @GetMapping("/create")
    public String createProduct(Model model,@ModelAttribute("product") Product product,@ModelAttribute("category") Category category,
                                @ModelAttribute("message") String message,
                                @ModelAttribute("messageType") String messageType) {
        model.addAttribute("categories",categoryService.findAllCategories());
        return "product/create-product";
    }

    // Called shen we press submit button in the create product form
    @PostMapping
    public String createProduct(Product product, RedirectAttributes redirectAttributes) throws CategoryNotFoundException {
        try {
            Product searchProduct = productService.findProductByTitle(product.getTitle());
            redirectAttributes.addFlashAttribute("message", String.format("Product(%s) already exists!", product.getTitle()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/product/create-product";
        } catch (ProductNotFoundException e) {
            productService.createProduct(product);
            redirectAttributes.addFlashAttribute("message", String.format("Product(%s) has been created successfully!", product.getTitle()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/product";
        }
    }

    @GetMapping("/update/{title}")
    public String showUpdateProductPage(@PathVariable String title,
                                        RedirectAttributes redirectAttributes,
                                        @RequestParam(value = "product", required = false) Product product,
                                        Model model) {
        if (product == null) {
            try {
                model.addAttribute("product", productService.findProductByTitle(title));
            } catch (ProductNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "product/update-product";
    }

    @PostMapping("/update")
    public String updateProduct(Product product, RedirectAttributes redirectAttributes) {
        try {
            productService.updateProduct(product);
            redirectAttributes.addFlashAttribute("message", String.format("Product(%s) has been created successfully!", product.getTitle()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/product";
        } catch (ProductNotFoundException e) {
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