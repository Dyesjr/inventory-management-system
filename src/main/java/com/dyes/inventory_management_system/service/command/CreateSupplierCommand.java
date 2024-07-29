package com.dyes.inventory_management_system.service.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierCommand {
    private String supplierName;
    private String contactNumber;
    private Long productId;
    private String supplierAddress;
}
