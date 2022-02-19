package com.commerce.services.inventory.dao;

import com.commerce.services.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
    boolean existsCategoryByName(String name);
}
