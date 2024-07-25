package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductCommandService {

    private final ProductRepository productRepository;

    @Autowired
    public DeleteProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(DeleteProductCommand command) {
        if(!productRepository.existsById(command.getProductId())){
            throw new ProductNotFoundException("Product not found with id " + command.getProductId());
        }

        productRepository.deleteById(command.getProductId());
    }
}
