package com.dyes.inventory_management_system.service.command;

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
public class CreateProductCommandService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandService.class);

    @Autowired
    public CreateProductCommandService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public Product execute(CreateProductCommand command) {

        logger.info("Processing create product command: {}", command);
        try {
//            Supplier supplier = supplierRepository.findBySupplierName(command.getSupplierName())
//                    .orElseThrow(() -> new ProductNotFoundException("Supplier not found with name " + command.getSupplierName()));
//            logger.info("Found supplier: {}", supplier);

            Product product = new Product();
            product.setProductName(command.getProductName());
            product.setProductDescription(command.getProductDescription());
            product.setQuantity(command.getQuantity());
            product.setPrice(command.getPrice());
//            product.setSupplier(supplier);

            if (command.getSupplierName() != null && !command.getSupplierName().isEmpty()) {
                Supplier supplier = supplierRepository.findBySupplierName(command.getSupplierName())
                        .orElseThrow(() -> new ProductNotFoundException("Supplier not found with name " + command.getSupplierName()));
                logger.info("Found supplier: {}", supplier);
                product.setSupplier(supplier);
            } else {
                logger.info("No supplier provided for the product.");
            }

            Product savedProduct = productRepository.save(product);
            logger.info("Product saved successfully: {}", savedProduct);

            return savedProduct;
        } catch (Exception e) {
            logger.error("Error occurred while creating product", e);
            throw e;  // rethrow the exception for the controller to handle
        }
    }
}
