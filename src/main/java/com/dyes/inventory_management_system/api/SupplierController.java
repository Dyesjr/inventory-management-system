package com.dyes.inventory_management_system.api;

import com.dyes.inventory_management_system.dto.SupplierDTO;
import com.dyes.inventory_management_system.model.Supplier;
import com.dyes.inventory_management_system.service.command.*;
import com.dyes.inventory_management_system.service.query.GetAllSuppliersQuery;
import com.dyes.inventory_management_system.service.query.GetAllSuppliersQueryService;
import com.dyes.inventory_management_system.service.query.GetSupplierByIdQuery;
import com.dyes.inventory_management_system.service.query.GetSupplierByIdQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final CreateSupplierCommandService createSupplierCommandService;
    private final UpdateSupplierCommandService updateSupplierCommandService;
    private final DeleteSupplierCommandService deleteSupplierCommandService;
    private final GetAllSuppliersQueryService getAllSuppliersQueryService;
    private final GetSupplierByIdQueryService getSupplierByIdQueryService;

    @Autowired
    public SupplierController(CreateSupplierCommandService createSupplierCommandService,
                              UpdateSupplierCommandService updateSupplierCommandService,
                              DeleteSupplierCommandService deleteSupplierCommandService, GetAllSuppliersQueryService getAllSuppliersQueryService, GetSupplierByIdQueryService getSupplierByIdQueryService) {
        this.createSupplierCommandService = createSupplierCommandService;
        this.updateSupplierCommandService = updateSupplierCommandService;
        this.deleteSupplierCommandService = deleteSupplierCommandService;
        this.getAllSuppliersQueryService = getAllSuppliersQueryService;
        this.getSupplierByIdQueryService = getSupplierByIdQueryService;
    }

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody CreateSupplierCommand createSupplierCommand){
        logger.info("Received request to create supplier: {}", createSupplierCommand);
        try {
            SupplierDTO createdSupplier = createSupplierCommandService.execute(createSupplierCommand);
            logger.info("Supplier created successfully: {}", createdSupplier);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
        } catch (Exception e) {
            logger.error("Error occurred while creating supplier", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id,
                                                      @RequestBody UpdateSupplierCommand updateSupplierCommand){
        updateSupplierCommand.setSupplierId(id);
        SupplierDTO updatedSupplier = updateSupplierCommandService.execute(updateSupplierCommand);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> deleteSupplier(@PathVariable Long id){
        deleteSupplierCommandService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = getAllSuppliersQueryService.execute();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        GetSupplierByIdQuery query = new GetSupplierByIdQuery(id);
        SupplierDTO supplierDTO = getSupplierByIdQueryService.execute(query);
        return ResponseEntity.ok(supplierDTO);
    }
}
