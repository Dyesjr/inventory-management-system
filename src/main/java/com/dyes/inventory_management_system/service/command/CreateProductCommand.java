package com.dyes.inventory_management_system.service.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {

    private String productName;
    private String description;
    private int quantity;
    private double price;
}
