package com.dyes.inventory_management_system.service.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupplierCommand {
    private Long supplierId;
    private String supplierName;
    private String contactNumber;
    private String supplierAddress;

    private Long productId;
}
