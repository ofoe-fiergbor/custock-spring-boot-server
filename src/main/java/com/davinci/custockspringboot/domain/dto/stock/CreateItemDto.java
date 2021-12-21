package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateItemDto {
    private String name;
    private String description;
    private String unitMeasurement;
    private Integer supplierId;
}
