package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandService {

    private final ProductRepository productRepository;

    @Autowired
    public CreateProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(CreateProductCommand command) {
        Product product = new Product();
        product.setProductName(command.getProductName());
        product.setProductDescription(command.getProductDescription());
        product.setQuantity(command.getQuantity());
        product.setPrice(command.getPrice());

        return productRepository.save(product);
    }
}
