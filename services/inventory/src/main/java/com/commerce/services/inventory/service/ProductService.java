package com.commerce.services.inventory.service;

import com.commerce.services.inventory.dao.ProductRepository;
import com.commerce.services.inventory.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Collection<Product> loadProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        if(productRepository.existsProductByName(product.getName())){
            throw new RuntimeException("Product "+product.getName()+" already exists");
        }
        return productRepository.save(product);
    }

    public Product loadProductByName(String productName){
        return productRepository.findProductByName(productName);
    }
}
