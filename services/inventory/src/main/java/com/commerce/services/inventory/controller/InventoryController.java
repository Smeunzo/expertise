package com.commerce.services.inventory.controller;

import com.commerce.services.inventory.service.CategoryService;
import com.commerce.services.inventory.service.ProductService;
import com.commerce.services.inventory.entity.Category;
import com.commerce.services.inventory.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class InventoryController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping(path = "/inventory/products")
    public Collection<Product> getProducts() {
        return productService.loadProducts();
    }

    @GetMapping(path ="/inventory/categories")
    public Collection<Category> getCategories(){
        return categoryService.loadCategories();
    }

    @GetMapping(path = "/inventory/category/{name}")
    public Collection<Product> loadCategory(@PathVariable(value = "name") String categoryName){
        return categoryService.loadCategoryByName(categoryName).getProducts();
    }

    @PostMapping(path = "/inventory/addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PostMapping(path = "/inventory/addCategory")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping(path = "/inventory/addProductToCategory")
    public Category addProductToCategory(@RequestBody  CategoryProductData categoryProductData){

        Product product = productService.loadProductByName(categoryProductData.getProductName());
        Category category = categoryService.loadCategoryByName(categoryProductData.getCategoryName());
        return categoryService.linkProductToCategory(category,product);
    }

    @AllArgsConstructor
    @Data
    static class CategoryProductData{
        private String categoryName;
        private String productName ;
    }
}
