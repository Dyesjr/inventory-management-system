package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommandService {
    private final ProductRepository productRepository;

    @Autowired
    public UpdateProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(UpdateProductCommand command) {
        Product existingProduct = productRepository.findById(command.getProductId())
                .orElseThrow(()-> new EntityNotFoundException("Product not found"));

        existingProduct.setName(command.getProductName());
        existingProduct.setProductDescription(command.getProductDescription());
        existingProduct.setQuantity(command.getProductQuantity());
        existingProduct.setPrice(command.getProductPrice());

        return productRepository.save(existingProduct);
    }
}
