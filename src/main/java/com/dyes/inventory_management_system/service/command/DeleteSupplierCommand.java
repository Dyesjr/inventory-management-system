package com.dyes.inventory_management_system.service.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteSupplierCommand {
    private Long supplierId;
}
