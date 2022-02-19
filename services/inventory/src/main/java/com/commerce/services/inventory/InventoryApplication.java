package com.commerce.services.inventory;

import com.commerce.services.inventory.entity.Category;
import com.commerce.services.inventory.entity.Product;
import com.commerce.services.inventory.service.CategoryService;
import com.commerce.services.inventory.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	 CommandLineRunner start(ProductService productService, CategoryService categoryService){
		return args -> {

			Category phones = new Category();
			phones.setName("phones");
			categoryService.addCategory(phones);

			Product product1 = new Product(null,100,100,"iphone","description");
			Product product2 = new Product(null,100,100,"samsung","description");
			Product product3 = new Product(null,100,100,"huawei","description");
			Product product4 = new Product(null,100,100,"xiaomi","description");
			Product product5 = new Product(null,100,100,"wiko","description");
			Product product6 = new Product(null,100,100,"sony","description");
			Product product7 = new Product(null,100,100,"oneplus","description");

			productService.addProduct(product1);
			productService.addProduct(product2);
			productService.addProduct(product3);
			productService.addProduct(product4);
			productService.addProduct(product5);
			productService.addProduct(product6);
			productService.addProduct(product7);

			categoryService.linkProductsToCategory(phones,product1,product2,product3,product4,product5,product6,product7);

		};
	}
}
