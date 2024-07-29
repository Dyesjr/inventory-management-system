package com.dyes.inventory_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Long supplierId;
    private String supplierName;
    private String contactNumber;
    private String supplierAddress;
    private String productName;

}
