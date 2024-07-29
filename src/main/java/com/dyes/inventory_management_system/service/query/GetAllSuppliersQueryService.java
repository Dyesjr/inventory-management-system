package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.dto.SupplierDTO;
import com.dyes.inventory_management_system.model.Supplier;
import com.dyes.inventory_management_system.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class GetAllSuppliersQueryService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public GetAllSuppliersQueryService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    public List<SupplierDTO> execute() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplier -> new SupplierDTO(
                        supplier.getSupplierId(),
                        supplier.getSupplierName(),
                        supplier.getContactNumber(),
                        supplier.getSupplierAddress(),
                        supplier.getProduct() != null ? supplier.getProduct().getProductName() : null))
                .collect(Collectors.toList());
    }
}
