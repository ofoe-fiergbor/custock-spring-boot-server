package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;

@Data
public class CreateTransactionDto {
    private String description;
    private double quantity;
    private Integer itemId;
}
