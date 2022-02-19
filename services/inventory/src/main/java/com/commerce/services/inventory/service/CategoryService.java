package com.commerce.services.inventory.service;

import com.commerce.services.inventory.dao.CategoryRepository;
import com.commerce.services.inventory.entity.Category;
import com.commerce.services.inventory.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category addCategory(Category category){
        if(categoryRepository.existsCategoryByName(category.getName())){
            throw new RuntimeException("Category "+category.getName()+" already exists");
        }

        return  categoryRepository.save(category);
    }

    public Category loadCategoryByName(String categoryName){
        return categoryRepository.findCategoryByName(categoryName);
    }

    @Transactional
    public Category linkProductToCategory(Category c, Product p){
        Category category = categoryRepository.findCategoryByName(c.getName());

        category.getProducts().add(p);
        return categoryRepository.save(category);
    }

    @Transactional
    public void linkProductsToCategory(Category c, Product... p){
        Category category = categoryRepository.findCategoryByName(c.getName());
        category.getProducts().addAll(Arrays.stream(p).collect(Collectors.toList()));
        categoryRepository.save(category);
    }

    public Collection<Category> loadCategories(){
        return categoryRepository.findAll();
    }
}
