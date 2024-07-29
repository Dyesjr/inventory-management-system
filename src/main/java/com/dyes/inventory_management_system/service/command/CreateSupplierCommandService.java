package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.dto.SupplierDTO;
import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.model.Supplier;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import com.dyes.inventory_management_system.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSupplierCommandService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandService.class);

    @Autowired
    public CreateSupplierCommandService(SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    public SupplierDTO execute(CreateSupplierCommand command){

        Product product = null;
        if (command.getProductId() != null) {
            product = productRepository.findById(command.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + command.getProductId()));
            logger.info("Found product for supplier: {}", product.getProductName());
        }

        // Create and populate the supplier
        Supplier supplier = new Supplier();
        supplier.setSupplierName(command.getSupplierName());
        supplier.setContactNumber(command.getContactNumber());
        supplier.setSupplierAddress(command.getSupplierAddress());
        if (product != null) {
            supplier.setProduct(product);
        }

        // Save the supplier
        Supplier savedSupplier = supplierRepository.save(supplier);
        logger.info("Supplier saved successfully: ID={}, Name={}", savedSupplier.getSupplierId(), savedSupplier.getSupplierName());

        // Update the associated product if necessary
        if (savedSupplier.getProduct() != null) {
            Product associatedProduct = savedSupplier.getProduct();
            associatedProduct.setSupplier(savedSupplier);
            productRepository.save(associatedProduct);
            logger.info("Product updated with new supplier: ID={}, ProductName={}", associatedProduct.getProductId(), associatedProduct.getProductName());
        }

        // Create and return the DTO
        return new SupplierDTO(
                savedSupplier.getSupplierId(),
                savedSupplier.getSupplierName(),
                savedSupplier.getContactNumber(),
                savedSupplier.getSupplierAddress(),
                savedSupplier.getProduct() != null ? savedSupplier.getProduct().getProductName() : null
        );
    }
}
