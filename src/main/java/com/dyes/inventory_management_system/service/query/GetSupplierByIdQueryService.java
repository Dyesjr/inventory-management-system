package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.dto.SupplierDTO;
import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Supplier;
import com.dyes.inventory_management_system.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetSupplierByIdQueryService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public GetSupplierByIdQueryService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierDTO execute(GetSupplierByIdQuery query) {
        Supplier supplier = supplierRepository.findById(query.getSupplierId())
                .orElseThrow(() -> new ProductNotFoundException("Supplier with id " + query.getSupplierId() + " not found"));

        // Convert Supplier to SupplierDTO
        return new SupplierDTO(
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getContactNumber(),
                supplier.getSupplierAddress(),
                supplier.getProduct() != null ? supplier.getProduct().getProductName() : null
        );
    }
}
