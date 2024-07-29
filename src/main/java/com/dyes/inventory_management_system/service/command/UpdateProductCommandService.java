package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.model.Supplier;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import com.dyes.inventory_management_system.repositories.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommandService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public UpdateProductCommandService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public Product execute(UpdateProductCommand command) {
        Product existingProduct = productRepository.findById(command.getProductId())
                .orElseThrow(()-> new ProductNotFoundException("Product not found " + command.getProductId()));

        if (command.getProductName() != null) {
            existingProduct.setProductName(command.getProductName());
        }
        if (command.getProductDescription() != null) {
            existingProduct.setProductDescription(command.getProductDescription());
        }
        if (command.getProductQuantity() != null){
            existingProduct.setQuantity(command.getProductQuantity());
        }
        if (command.getProductPrice()!=null){
            existingProduct.setPrice(command.getProductPrice());
        }
        if (command.getSupplierName() != null){
            Supplier supplier = supplierRepository.findBySupplierName(command.getSupplierName())
                    .orElseThrow(()-> new ProductNotFoundException("Supplier not found " + command.getSupplierName()));
            existingProduct.setSupplier(supplier);
        }

        return productRepository.save(existingProduct);
    }
}
