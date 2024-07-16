package com.dyes.inventory_management_system.service.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand {
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
}
