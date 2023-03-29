package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.exceptions.ProductNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.models.Product;
import com.example.onlinestorebackend.repositories.ProductRepository;
import com.example.onlinestorebackend.services.CategoryService;
import com.example.onlinestorebackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/27/2023
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(Product product) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryById(product.getCategory().getId());
        product.setCategory(category);
        product.setActive(true);
        productRepository.save(product);
    }

    @Override
    public Product findProductByTitle(String title) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByTitle(title);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(title);
        }
        return productOptional.get();
    }


    @Override
    public void updateProduct(Product product) throws ProductNotFoundException {
        if (findProductByTitle(product.getTitle()) !=null) {
            productRepository.saveAndFlush(product);
        }
}

    @Override
    public void deleteProductByTitle(String title) throws ProductNotFoundException {
        Product product = findProductByTitle(title);
        product.setActive(false);
        productRepository.saveAndFlush(product);


    }

    @Override
    public void restoreProductByTitle(String title) throws ProductNotFoundException {
        Product product = findProductByTitle(title);
        product.setActive(true);
        productRepository.saveAndFlush(product);
    }
}
