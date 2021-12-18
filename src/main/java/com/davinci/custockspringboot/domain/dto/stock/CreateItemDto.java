package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateItemDto {

    private long batch;
    private String name;
    private long supplier;
    private double quantity;
    private String description;
    private String unitOfMeasure;
}
