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
public class UpdateSupplierCommandService {
//    private static final Logger log = LoggerFactory.getLogger(UpdateSupplierCommandService.class);
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;


    @Autowired
    public UpdateSupplierCommandService(SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    public SupplierDTO execute(UpdateSupplierCommand command){

         Logger log = LoggerFactory.getLogger(UpdateSupplierCommandService.class);


        Supplier supplier = supplierRepository.findById(command.getSupplierId())
                .orElseThrow(()-> new ProductNotFoundException("Supplier not found with Id: " + command.getSupplierId()));

        supplier.setSupplierName(command.getSupplierName());
        supplier.setContactNumber(command.getContactNumber());
        supplier.setSupplierAddress(command.getSupplierAddress());
        supplier.setProduct(supplier.getProduct());

        if (command.getProductId() != null) {
            Product product = productRepository.findById(command.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + command.getProductId()));
            supplier.setProduct(product);
            log.info("Found product for supplier: {}", product);
        } else {
            supplier.setProduct(null);
        }


        Supplier updatedSupplier = supplierRepository.save(supplier);
        log.info("Supplier updated successfully: {}", updatedSupplier);


        if (updatedSupplier.getProduct() != null) {
            Product associatedProduct = updatedSupplier.getProduct();
            associatedProduct.setSupplier(updatedSupplier);
            productRepository.save(associatedProduct);
            log.info("Product updated with new supplier: {}", associatedProduct);
        }

        return new SupplierDTO(
                updatedSupplier.getSupplierId(),
                updatedSupplier.getSupplierName(),
                updatedSupplier.getContactNumber(),
                updatedSupplier.getSupplierAddress(),
                updatedSupplier.getProduct() != null ? updatedSupplier.getProduct().getProductName() : null
        );

    }
}
