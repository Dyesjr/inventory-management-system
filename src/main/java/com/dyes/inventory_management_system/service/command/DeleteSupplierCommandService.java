package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Supplier;
import com.dyes.inventory_management_system.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteSupplierCommandService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public DeleteSupplierCommandService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void execute(Long supplierId){
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(()-> new ProductNotFoundException("Supplier not found with Id: " + supplierId));

        supplierRepository.deleteById(supplierId);
    }
}
