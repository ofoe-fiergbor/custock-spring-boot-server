package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateItemDto {

    private int batch;
    private String name;
    private int supplier;
    private double quantity;
    private String description;
    private String unitOfMeasure;
}
