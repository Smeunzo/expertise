package com.commerce.services.inventory.dao;

import com.commerce.services.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);
    boolean existsProductByName(String name);
}
