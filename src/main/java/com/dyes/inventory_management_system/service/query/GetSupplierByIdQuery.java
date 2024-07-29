package com.dyes.inventory_management_system.service.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSupplierByIdQuery {
    private Long supplierId;
}
